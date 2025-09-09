#!/bin/bash


set -e

REGION="us-west-2"
VPC_ID=""  # Will be detected or created
KEY_NAME="jenkins-cutanddry"
PROJECT_NAME="cutanddry-jenkins"
ENVIRONMENT="production"

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

log() {
    echo -e "${GREEN}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

warn() {
    echo -e "${YELLOW}[$(date +'%Y-%m-%d %H:%M:%S')] WARNING: $1${NC}"
}

error() {
    echo -e "${RED}[$(date +'%Y-%m-%d %H:%M:%S')] ERROR: $1${NC}"
    exit 1
}

check_prerequisites() {
    log "Checking prerequisites..."
    
    if ! command -v aws &> /dev/null; then
        error "AWS CLI is not installed. Please install it first."
    fi
    
    if ! aws sts get-caller-identity &> /dev/null; then
        error "AWS credentials not configured. Please run 'aws configure'."
    fi
    
    if ! command -v jq &> /dev/null; then
        warn "jq is not installed. Installing..."
        sudo apt-get update && sudo apt-get install -y jq
    fi
    
    log "Prerequisites check completed."
}

setup_vpc() {
    log "Setting up VPC..."
    
    if [ -z "$VPC_ID" ]; then
        VPC_ID=$(aws ec2 describe-vpcs \
            --filters "Name=tag:Name,Values=${PROJECT_NAME}-vpc" \
            --query 'Vpcs[0].VpcId' \
            --output text 2>/dev/null)
        
        if [ "$VPC_ID" = "None" ] || [ -z "$VPC_ID" ]; then
            log "Creating new VPC..."
            VPC_ID=$(aws ec2 create-vpc \
                --cidr-block 10.0.0.0/16 \
                --query 'Vpc.VpcId' \
                --output text)
            
            aws ec2 create-tags \
                --resources $VPC_ID \
                --tags Key=Name,Value=${PROJECT_NAME}-vpc Key=Environment,Value=$ENVIRONMENT
            
            aws ec2 modify-vpc-attribute --vpc-id $VPC_ID --enable-dns-hostnames
            
            log "Created VPC: $VPC_ID"
        else
            log "Using existing VPC: $VPC_ID"
        fi
    fi
    
    IGW_ID=$(aws ec2 describe-internet-gateways \
        --filters "Name=tag:Name,Values=${PROJECT_NAME}-igw" \
        --query 'InternetGateways[0].InternetGatewayId' \
        --output text 2>/dev/null)
    
    if [ "$IGW_ID" = "None" ] || [ -z "$IGW_ID" ]; then
        log "Creating Internet Gateway..."
        IGW_ID=$(aws ec2 create-internet-gateway \
            --query 'InternetGateway.InternetGatewayId' \
            --output text)
        
        aws ec2 create-tags \
            --resources $IGW_ID \
            --tags Key=Name,Value=${PROJECT_NAME}-igw Key=Environment,Value=$ENVIRONMENT
        
        aws ec2 attach-internet-gateway --vpc-id $VPC_ID --internet-gateway-id $IGW_ID
        log "Created and attached Internet Gateway: $IGW_ID"
    fi
}

create_subnets() {
    log "Creating subnets..."
    
    AZS=($(aws ec2 describe-availability-zones --query 'AvailabilityZones[].ZoneName' --output text))
    
    SUBNET_IDS=()
    
    for i in {0..2}; do
        if [ $i -lt ${#AZS[@]} ]; then
            AZ=${AZS[$i]}
            CIDR="10.0.$((i+1)).0/24"
            
            SUBNET_ID=$(aws ec2 describe-subnets \
                --filters "Name=tag:Name,Values=${PROJECT_NAME}-subnet-$((i+1))" \
                --query 'Subnets[0].SubnetId' \
                --output text 2>/dev/null)
            
            if [ "$SUBNET_ID" = "None" ] || [ -z "$SUBNET_ID" ]; then
                log "Creating subnet in $AZ..."
                SUBNET_ID=$(aws ec2 create-subnet \
                    --vpc-id $VPC_ID \
                    --cidr-block $CIDR \
                    --availability-zone $AZ \
                    --query 'Subnet.SubnetId' \
                    --output text)
                
                aws ec2 create-tags \
                    --resources $SUBNET_ID \
                    --tags Key=Name,Value=${PROJECT_NAME}-subnet-$((i+1)) Key=Environment,Value=$ENVIRONMENT
                
                aws ec2 modify-subnet-attribute --subnet-id $SUBNET_ID --map-public-ip-on-launch
                
                log "Created subnet: $SUBNET_ID in $AZ"
            fi
            
            SUBNET_IDS+=($SUBNET_ID)
        fi
    done
    
    RT_ID=$(aws ec2 describe-route-tables \
        --filters "Name=tag:Name,Values=${PROJECT_NAME}-rt" \
        --query 'RouteTables[0].RouteTableId' \
        --output text 2>/dev/null)
    
    if [ "$RT_ID" = "None" ] || [ -z "$RT_ID" ]; then
        log "Creating route table..."
        RT_ID=$(aws ec2 create-route-table \
            --vpc-id $VPC_ID \
            --query 'RouteTable.RouteTableId' \
            --output text)
        
        aws ec2 create-tags \
            --resources $RT_ID \
            --tags Key=Name,Value=${PROJECT_NAME}-rt Key=Environment,Value=$ENVIRONMENT
        
        aws ec2 create-route \
            --route-table-id $RT_ID \
            --destination-cidr-block 0.0.0.0/0 \
            --gateway-id $IGW_ID
        
        for subnet_id in "${SUBNET_IDS[@]}"; do
            aws ec2 associate-route-table --subnet-id $subnet_id --route-table-id $RT_ID
        done
        
        log "Created and configured route table: $RT_ID"
    fi
}

create_security_groups() {
    log "Creating security groups..."
    
    MASTER_SG_ID=$(aws ec2 describe-security-groups \
        --filters "Name=group-name,Values=${PROJECT_NAME}-master-sg" \
        --query 'SecurityGroups[0].GroupId' \
        --output text 2>/dev/null)
    
    if [ "$MASTER_SG_ID" = "None" ] || [ -z "$MASTER_SG_ID" ]; then
        log "Creating Jenkins master security group..."
        MASTER_SG_ID=$(aws ec2 create-security-group \
            --group-name ${PROJECT_NAME}-master-sg \
            --description "Security group for Jenkins master" \
            --vpc-id $VPC_ID \
            --query 'GroupId' \
            --output text)
        
        aws ec2 create-tags \
            --resources $MASTER_SG_ID \
            --tags Key=Name,Value=${PROJECT_NAME}-master-sg Key=Environment,Value=$ENVIRONMENT
        
        aws ec2 authorize-security-group-ingress \
            --group-id $MASTER_SG_ID \
            --protocol tcp \
            --port 8080 \
            --cidr 0.0.0.0/0
        
        aws ec2 authorize-security-group-ingress \
            --group-id $MASTER_SG_ID \
            --protocol tcp \
            --port 22 \
            --cidr 0.0.0.0/0
        
        aws ec2 authorize-security-group-ingress \
            --group-id $MASTER_SG_ID \
            --protocol tcp \
            --port 50000 \
            --cidr 10.0.0.0/16
        
        log "Created Jenkins master security group: $MASTER_SG_ID"
    fi
    
    AGENTS_SG_ID=$(aws ec2 describe-security-groups \
        --filters "Name=group-name,Values=${PROJECT_NAME}-agents-sg" \
        --query 'SecurityGroups[0].GroupId' \
        --output text 2>/dev/null)
    
    if [ "$AGENTS_SG_ID" = "None" ] || [ -z "$AGENTS_SG_ID" ]; then
        log "Creating Jenkins agents security group..."
        AGENTS_SG_ID=$(aws ec2 create-security-group \
            --group-name ${PROJECT_NAME}-agents-sg \
            --description "Security group for Jenkins agents" \
            --vpc-id $VPC_ID \
            --query 'GroupId' \
            --output text)
        
        aws ec2 create-tags \
            --resources $AGENTS_SG_ID \
            --tags Key=Name,Value=${PROJECT_NAME}-agents-sg Key=Environment,Value=$ENVIRONMENT
        
        aws ec2 authorize-security-group-ingress \
            --group-id $AGENTS_SG_ID \
            --protocol tcp \
            --port 22 \
            --cidr 10.0.0.0/16
        
        aws ec2 authorize-security-group-ingress \
            --group-id $AGENTS_SG_ID \
            --protocol tcp \
            --port 8080 \
            --source-group $MASTER_SG_ID
        
        log "Created Jenkins agents security group: $AGENTS_SG_ID"
    fi
}

create_iam_roles() {
    log "Creating IAM roles..."
    
    if ! aws iam get-role --role-name ${PROJECT_NAME}-master-role &>/dev/null; then
        log "Creating Jenkins master IAM role..."
        
        cat > /tmp/jenkins-master-trust-policy.json << EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
        
        aws iam create-role \
            --role-name ${PROJECT_NAME}-master-role \
            --assume-role-policy-document file:///tmp/jenkins-master-trust-policy.json
        
        cat > /tmp/jenkins-master-policy.json << EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "ec2:DescribeInstances",
        "ec2:DescribeImages",
        "ec2:DescribeSnapshots",
        "ec2:DescribeSecurityGroups",
        "ec2:DescribeSubnets",
        "ec2:DescribeVpcs",
        "ec2:RunInstances",
        "ec2:TerminateInstances",
        "ec2:CreateTags",
        "autoscaling:DescribeAutoScalingGroups",
        "autoscaling:UpdateAutoScalingGroup",
        "autoscaling:SetDesiredCapacity"
      ],
      "Resource": "*"
    },
    {
      "Effect": "Allow",
      "Action": [
        "iam:PassRole"
      ],
      "Resource": "arn:aws:iam::*:role/${PROJECT_NAME}-agent-role"
    }
  ]
}
EOF
        
        aws iam put-role-policy \
            --role-name ${PROJECT_NAME}-master-role \
            --policy-name JenkinsMasterPolicy \
            --policy-document file:///tmp/jenkins-master-policy.json
        
        aws iam create-instance-profile --instance-profile-name ${PROJECT_NAME}-master-profile
        aws iam add-role-to-instance-profile \
            --instance-profile-name ${PROJECT_NAME}-master-profile \
            --role-name ${PROJECT_NAME}-master-role
        
        log "Created Jenkins master IAM role and instance profile"
    fi
    
    if ! aws iam get-role --role-name ${PROJECT_NAME}-agent-role &>/dev/null; then
        log "Creating Jenkins agent IAM role..."
        
        aws iam create-role \
            --role-name ${PROJECT_NAME}-agent-role \
            --assume-role-policy-document file:///tmp/jenkins-master-trust-policy.json
        
        cat > /tmp/jenkins-agent-policy.json << EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:GetObject",
        "s3:PutObject",
        "s3:DeleteObject"
      ],
      "Resource": [
        "arn:aws:s3:::${PROJECT_NAME}-artifacts/*"
      ]
    },
    {
      "Effect": "Allow",
      "Action": [
        "ssm:GetParameter",
        "ssm:GetParameters"
      ],
      "Resource": [
        "arn:aws:ssm:*:*:parameter/jenkins/*"
      ]
    }
  ]
}
EOF
        
        aws iam put-role-policy \
            --role-name ${PROJECT_NAME}-agent-role \
            --policy-name JenkinsAgentPolicy \
            --policy-document file:///tmp/jenkins-agent-policy.json
        
        aws iam create-instance-profile --instance-profile-name ${PROJECT_NAME}-agent-profile
        aws iam add-role-to-instance-profile \
            --instance-profile-name ${PROJECT_NAME}-agent-profile \
            --role-name ${PROJECT_NAME}-agent-role
        
        log "Created Jenkins agent IAM role and instance profile"
    fi
    
    rm -f /tmp/jenkins-master-trust-policy.json /tmp/jenkins-master-policy.json /tmp/jenkins-agent-policy.json
}

create_s3_bucket() {
    log "Creating S3 bucket for artifacts..."
    
    BUCKET_NAME="${PROJECT_NAME}-artifacts-$(date +%s)"
    
    if aws s3 ls "s3://$BUCKET_NAME" 2>&1 | grep -q 'NoSuchBucket'; then
        aws s3 mb s3://$BUCKET_NAME --region $REGION
        
        aws s3api put-bucket-versioning \
            --bucket $BUCKET_NAME \
            --versioning-configuration Status=Enabled
        
        cat > /tmp/lifecycle-policy.json << EOF
{
  "Rules": [
    {
      "ID": "DeleteOldArtifacts",
      "Status": "Enabled",
      "Filter": {},
      "Expiration": {
        "Days": 30
      },
      "NoncurrentVersionExpiration": {
        "NoncurrentDays": 7
      }
    }
  ]
}
EOF
        
        aws s3api put-bucket-lifecycle-configuration \
            --bucket $BUCKET_NAME \
            --lifecycle-configuration file:///tmp/lifecycle-policy.json
        
        rm -f /tmp/lifecycle-policy.json
        
        log "Created S3 bucket: $BUCKET_NAME"
    fi
}

create_launch_template() {
    log "Creating launch template for Jenkins agents..."
    
    AMI_ID=$(aws ec2 describe-images \
        --owners amazon \
        --filters "Name=name,Values=amzn2-ami-hvm-*-x86_64-gp2" \
        --query 'Images | sort_by(@, &CreationDate) | [-1].ImageId' \
        --output text)
    
    cat > /tmp/agent-userdata.sh << 'EOF'
#!/bin/bash
yum update -y

cd /opt
wget -q https://download.java.net/java/GA/jdk22/830ec9fcccef480bb3e73fb7ecafe059/36/GPL/openjdk-22_linux-x64_bin.tar.gz
tar zxf openjdk-22_linux-x64_bin.tar.gz
ln -s jdk-22 java
echo 'export JAVA_HOME=/opt/java' >> /etc/environment
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> /etc/environment

wget -q https://archive.apache.org/dist/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz
tar zxf apache-maven-3.9.4-bin.tar.gz
ln -s apache-maven-3.9.4 maven
echo 'export MAVEN_HOME=/opt/maven' >> /etc/environment
echo 'export PATH=$MAVEN_HOME/bin:$PATH' >> /etc/environment

wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | rpm --import -
cat > /etc/yum.repos.d/google-chrome.repo << 'CHROME_EOF'
[google-chrome]
name=google-chrome
baseurl=http://dl.google.com/linux/chrome/rpm/stable/x86_64
enabled=1
gpgcheck=1
gpgkey=https://dl.google.com/linux/linux_signing_key.pub
CHROME_EOF
yum install -y google-chrome-stable

yum install -y docker
systemctl start docker
systemctl enable docker

yum install -y git bc wget curl unzip

useradd jenkins
usermod -a -G docker jenkins

source /etc/environment
EOF
    
    USER_DATA=$(base64 -w 0 /tmp/agent-userdata.sh)
    
    cat > /tmp/launch-template.json << EOF
{
  "LaunchTemplateName": "${PROJECT_NAME}-agents",
  "LaunchTemplateData": {
    "ImageId": "$AMI_ID",
    "InstanceType": "t3.xlarge",
    "KeyName": "$KEY_NAME",
    "SecurityGroupIds": ["$AGENTS_SG_ID"],
    "IamInstanceProfile": {
      "Name": "${PROJECT_NAME}-agent-profile"
    },
    "UserData": "$USER_DATA",
    "TagSpecifications": [
      {
        "ResourceType": "instance",
        "Tags": [
          {"Key": "Name", "Value": "${PROJECT_NAME}-agent"},
          {"Key": "Environment", "Value": "$ENVIRONMENT"},
          {"Key": "Project", "Value": "$PROJECT_NAME"}
        ]
      }
    ]
  }
}
EOF
    
    if ! aws ec2 describe-launch-templates --launch-template-names ${PROJECT_NAME}-agents &>/dev/null; then
        aws ec2 create-launch-template --cli-input-json file:///tmp/launch-template.json
        log "Created launch template: ${PROJECT_NAME}-agents"
    else
        log "Launch template ${PROJECT_NAME}-agents already exists"
    fi
    
    rm -f /tmp/agent-userdata.sh /tmp/launch-template.json
}

create_auto_scaling_group() {
    log "Creating auto scaling group..."
    
    SUBNET_LIST=$(IFS=,; echo "${SUBNET_IDS[*]}")
    
    if ! aws autoscaling describe-auto-scaling-groups --auto-scaling-group-names ${PROJECT_NAME}-agents &>/dev/null; then
        aws autoscaling create-auto-scaling-group \
            --auto-scaling-group-name ${PROJECT_NAME}-agents \
            --launch-template LaunchTemplateName=${PROJECT_NAME}-agents,Version='$Latest' \
            --min-size 0 \
            --max-size 15 \
            --desired-capacity 0 \
            --vpc-zone-identifier "$SUBNET_LIST" \
            --health-check-type EC2 \
            --health-check-grace-period 300 \
            --tags Key=Name,Value=${PROJECT_NAME}-agents,PropagateAtLaunch=true Key=Environment,Value=$ENVIRONMENT,PropagateAtLaunch=true
        
        log "Created auto scaling group: ${PROJECT_NAME}-agents"
    else
        log "Auto scaling group ${PROJECT_NAME}-agents already exists"
    fi
}

launch_jenkins_master() {
    log "Launching Jenkins master..."
    
    MASTER_INSTANCE_ID=$(aws ec2 describe-instances \
        --filters "Name=tag:Name,Values=${PROJECT_NAME}-master" "Name=instance-state-name,Values=running" \
        --query 'Reservations[0].Instances[0].InstanceId' \
        --output text 2>/dev/null)
    
    if [ "$MASTER_INSTANCE_ID" = "None" ] || [ -z "$MASTER_INSTANCE_ID" ]; then
        AMI_ID=$(aws ec2 describe-images \
            --owners amazon \
            --filters "Name=name,Values=amzn2-ami-hvm-*-x86_64-gp2" \
            --query 'Images | sort_by(@, &CreationDate) | [-1].ImageId' \
            --output text)
        
        cat > /tmp/master-userdata.sh << 'EOF'
#!/bin/bash
yum update -y

amazon-linux-extras install java-openjdk17 -y

wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
yum install jenkins -y

yum install docker -y
systemctl start docker
systemctl enable docker
usermod -a -G docker jenkins

curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
./aws/install

systemctl start jenkins
systemctl enable jenkins

yum install -y git
EOF
        
        USER_DATA=$(base64 -w 0 /tmp/master-userdata.sh)
        
        MASTER_INSTANCE_ID=$(aws ec2 run-instances \
            --image-id $AMI_ID \
            --instance-type t3.large \
            --key-name $KEY_NAME \
            --security-group-ids $MASTER_SG_ID \
            --subnet-id ${SUBNET_IDS[0]} \
            --iam-instance-profile Name=${PROJECT_NAME}-master-profile \
            --user-data "$USER_DATA" \
            --tag-specifications "ResourceType=instance,Tags=[{Key=Name,Value=${PROJECT_NAME}-master},{Key=Environment,Value=$ENVIRONMENT},{Key=Project,Value=$PROJECT_NAME}]" \
            --query 'Instances[0].InstanceId' \
            --output text)
        
        log "Launched Jenkins master instance: $MASTER_INSTANCE_ID"
        
        log "Waiting for Jenkins master to be running..."
        aws ec2 wait instance-running --instance-ids $MASTER_INSTANCE_ID
        
        rm -f /tmp/master-userdata.sh
    else
        log "Jenkins master already running: $MASTER_INSTANCE_ID"
    fi
    
    MASTER_PUBLIC_IP=$(aws ec2 describe-instances \
        --instance-ids $MASTER_INSTANCE_ID \
        --query 'Reservations[0].Instances[0].PublicIpAddress' \
        --output text)
    
    log "Jenkins master public IP: $MASTER_PUBLIC_IP"
}

create_cloudwatch_alarms() {
    log "Creating CloudWatch alarms..."
    
    aws cloudwatch put-metric-alarm \
        --alarm-name "${PROJECT_NAME}-master-cpu-high" \
        --alarm-description "Jenkins master CPU utilization is high" \
        --metric-name CPUUtilization \
        --namespace AWS/EC2 \
        --statistic Average \
        --period 300 \
        --threshold 80 \
        --comparison-operator GreaterThanThreshold \
        --dimensions Name=InstanceId,Value=$MASTER_INSTANCE_ID \
        --evaluation-periods 2 \
        --alarm-actions arn:aws:sns:$REGION:$(aws sts get-caller-identity --query Account --output text):${PROJECT_NAME}-alerts
    
    log "Created CloudWatch alarms"
}

output_summary() {
    log "Deployment completed successfully!"
    echo
    echo "=== INFRASTRUCTURE SUMMARY ==="
    echo "VPC ID: $VPC_ID"
    echo "Subnets: ${SUBNET_IDS[*]}"
    echo "Master Security Group: $MASTER_SG_ID"
    echo "Agents Security Group: $AGENTS_SG_ID"
    echo "Jenkins Master Instance: $MASTER_INSTANCE_ID"
    echo "Jenkins Master Public IP: $MASTER_PUBLIC_IP"
    echo "Auto Scaling Group: ${PROJECT_NAME}-agents"
    echo "Launch Template: ${PROJECT_NAME}-agents"
    echo
    echo "=== NEXT STEPS ==="
    echo "1. Access Jenkins at: http://$MASTER_PUBLIC_IP:8080"
    echo "2. SSH to Jenkins master: ssh -i $KEY_NAME.pem ec2-user@$MASTER_PUBLIC_IP"
    echo "3. Get initial admin password: sudo cat /var/lib/jenkins/secrets/initialAdminPassword"
    echo "4. Configure Jenkins with the provided Jenkinsfile"
    echo "5. Set up Slack notifications and other integrations"
    echo
    echo "=== CONFIGURATION FILES ==="
    echo "- Jenkinsfile: Use the provided Jenkinsfile in your repository"
    echo "- AWS Infrastructure: All resources tagged with Project=$PROJECT_NAME"
    echo "- Auto Scaling: Configured for 0-15 instances"
    echo
}

main() {
    log "Starting Cut+Dry Jenkins infrastructure deployment..."
    
    check_prerequisites
    setup_vpc
    create_subnets
    create_security_groups
    create_iam_roles
    create_s3_bucket
    create_launch_template
    create_auto_scaling_group
    launch_jenkins_master
    create_cloudwatch_alarms
    output_summary
    
    log "Deployment script completed!"
}

main "$@"
