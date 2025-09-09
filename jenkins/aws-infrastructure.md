# AWS Infrastructure Setup for Jenkins Test Automation

## Overview
This document outlines the AWS infrastructure setup required to run the Cut+Dry automation framework tests in parallel using Jenkins.

## Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────────┐
│   Jenkins       │    │   Auto Scaling   │    │   Test Agents       │
│   Master        │───▶│   Group          │───▶│   (EC2 Instances)   │
│   (EC2)         │    │                  │    │   - Java 22         │
└─────────────────┘    └──────────────────┘    │   - Maven           │
                                               │   - Chrome          │
                                               │   - Docker          │
                                               └─────────────────────┘
```

## 1. Jenkins Master Setup

### EC2 Instance Configuration
```bash
# Launch Jenkins master instance
aws ec2 run-instances \
  --image-id ami-0c02fb55956c7d316 \
  --instance-type t3.large \
  --key-name your-jenkins-key \
  --security-group-ids sg-jenkins-master \
  --subnet-id subnet-xxxxxxxxx \
  --user-data file://jenkins-master-userdata.sh \
  --tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=jenkins-master},{Key=Environment,Value=production}]'
```

### Jenkins Master User Data Script
```bash
#!/bin/bash
# jenkins-master-userdata.sh

# Update system
yum update -y

# Install Java 17 for Jenkins
amazon-linux-extras install java-openjdk17 -y

# Install Jenkins
wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
yum install jenkins -y

# Install Docker
yum install docker -y
systemctl start docker
systemctl enable docker
usermod -a -G docker jenkins

# Install AWS CLI
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
./aws/install

# Start Jenkins
systemctl start jenkins
systemctl enable jenkins

# Install required Jenkins plugins via CLI (after initial setup)
# This would be done manually or via configuration as code
```

## 2. Test Agent Auto Scaling Group

### Launch Template
```bash
# Create launch template for test agents
aws ec2 create-launch-template \
  --launch-template-name jenkins-test-agents \
  --launch-template-data '{
    "ImageId": "ami-0c02fb55956c7d316",
    "InstanceType": "t3.xlarge",
    "KeyName": "your-jenkins-key",
    "SecurityGroupIds": ["sg-jenkins-agents"],
    "IamInstanceProfile": {
      "Name": "jenkins-agent-profile"
    },
    "UserData": "'$(base64 -w 0 jenkins-agent-userdata.sh)'",
    "TagSpecifications": [
      {
        "ResourceType": "instance",
        "Tags": [
          {"Key": "Name", "Value": "jenkins-test-agent"},
          {"Key": "Environment", "Value": "testing"},
          {"Key": "AutoScaling", "Value": "true"}
        ]
      }
    ]
  }'
```

### Test Agent User Data Script
```bash
#!/bin/bash
# jenkins-agent-userdata.sh

# Update system
yum update -y

# Install Java 22
wget https://download.java.net/java/GA/jdk22/830ec9fcccef480bb3e73fb7ecafe059/36/GPL/openjdk-22_linux-x64_bin.tar.gz
tar zxf openjdk-22_linux-x64_bin.tar.gz -C /opt/
ln -s /opt/jdk-22 /opt/java
echo 'export JAVA_HOME=/opt/java' >> /etc/environment
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> /etc/environment
source /etc/environment

# Install Maven
cd /opt
wget https://archive.apache.org/dist/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz
tar zxf apache-maven-3.9.4-bin.tar.gz
ln -s apache-maven-3.9.4 maven
echo 'export MAVEN_HOME=/opt/maven' >> /etc/environment
echo 'export PATH=$MAVEN_HOME/bin:$PATH' >> /etc/environment

# Install Chrome
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | rpm --import -
echo "[google-chrome]
name=google-chrome
baseurl=http://dl.google.com/linux/chrome/rpm/stable/x86_64
enabled=1
gpgcheck=1
gpgkey=https://dl.google.com/linux/linux_signing_key.pub" > /etc/yum.repos.d/google-chrome.repo
yum install google-chrome-stable -y

# Install Docker
yum install docker -y
systemctl start docker
systemctl enable docker

# Install Git
yum install git -y

# Install additional tools
yum install -y bc wget curl unzip

# Create jenkins user
useradd jenkins
usermod -a -G docker jenkins

# Install Jenkins agent (swarm plugin)
cd /opt
wget https://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/3.17/swarm-client-3.17.jar
chown jenkins:jenkins swarm-client-3.17.jar

# Create systemd service for Jenkins agent
cat > /etc/systemd/system/jenkins-agent.service << 'EOF'
[Unit]
Description=Jenkins Agent
After=network.target

[Service]
Type=simple
User=jenkins
WorkingDirectory=/home/jenkins
ExecStart=/opt/java/bin/java -jar /opt/swarm-client-3.17.jar -master http://JENKINS_MASTER_IP:8080 -username jenkins-agent -password AGENT_PASSWORD -name %H -labels "aws-test-agent"
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
EOF

# Note: Replace JENKINS_MASTER_IP and AGENT_PASSWORD with actual values
# This would typically be done via AWS Systems Manager Parameter Store

systemctl daemon-reload
systemctl enable jenkins-agent
```

### Auto Scaling Group
```bash
# Create Auto Scaling Group
aws autoscaling create-auto-scaling-group \
  --auto-scaling-group-name jenkins-test-agents \
  --launch-template LaunchTemplateName=jenkins-test-agents,Version='$Latest' \
  --min-size 0 \
  --max-size 15 \
  --desired-capacity 0 \
  --vpc-zone-identifier "subnet-xxxxxxxxx,subnet-yyyyyyyyy,subnet-zzzzzzzzz" \
  --health-check-type EC2 \
  --health-check-grace-period 300 \
  --tags Key=Name,Value=jenkins-test-agents,PropagateAtLaunch=true Key=Environment,Value=testing,PropagateAtLaunch=true
```

## 3. Security Groups

### Jenkins Master Security Group
```bash
# Create security group for Jenkins master
aws ec2 create-security-group \
  --group-name jenkins-master-sg \
  --description "Security group for Jenkins master"

# Allow HTTP access to Jenkins
aws ec2 authorize-security-group-ingress \
  --group-id sg-jenkins-master \
  --protocol tcp \
  --port 8080 \
  --cidr 10.0.0.0/8

# Allow SSH access
aws ec2 authorize-security-group-ingress \
  --group-id sg-jenkins-master \
  --protocol tcp \
  --port 22 \
  --cidr 10.0.0.0/8

# Allow JNLP agent connections
aws ec2 authorize-security-group-ingress \
  --group-id sg-jenkins-master \
  --protocol tcp \
  --port 50000 \
  --source-group sg-jenkins-agents
```

### Jenkins Agents Security Group
```bash
# Create security group for Jenkins agents
aws ec2 create-security-group \
  --group-name jenkins-agents-sg \
  --description "Security group for Jenkins test agents"

# Allow SSH access
aws ec2 authorize-security-group-ingress \
  --group-id sg-jenkins-agents \
  --protocol tcp \
  --port 22 \
  --cidr 10.0.0.0/8

# Allow outbound internet access (default)
# Allow communication with Jenkins master
aws ec2 authorize-security-group-ingress \
  --group-id sg-jenkins-agents \
  --protocol tcp \
  --port 8080 \
  --source-group sg-jenkins-master
```

## 4. IAM Roles and Policies

### Jenkins Master IAM Role
```json
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
      "Resource": "arn:aws:iam::*:role/jenkins-agent-role"
    }
  ]
}
```

### Jenkins Agent IAM Role
```json
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
        "arn:aws:s3:::your-test-artifacts-bucket/*"
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
```

## 5. Jenkins Configuration

### Required Plugins
Install these plugins in Jenkins:
- EC2 Plugin
- Pipeline Plugin
- TestNG Results Plugin
- Slack Notification Plugin
- Build Timeout Plugin
- Timestamper Plugin
- Workspace Cleanup Plugin

### EC2 Cloud Configuration
1. Go to Manage Jenkins → Manage Nodes and Clouds → Configure Clouds
2. Add EC2 Cloud with these settings:
   - **Name**: AWS-Test-Agents
   - **Amazon EC2 Credentials**: Your AWS credentials
   - **Region**: Your AWS region
   - **EC2 Key Pair's Private Key**: Your private key
   - **AMI ID**: Your custom AMI with test tools
   - **Instance Type**: t3.xlarge
   - **Security group names**: jenkins-agents-sg
   - **Remote user**: jenkins
   - **Labels**: aws-test-agent
   - **Usage**: Only build jobs with label expressions matching this node

### Pipeline Configuration
1. Create a new Pipeline job
2. Configure it to use the Jenkinsfile from your repository
3. Set up build triggers for scheduled execution
4. Configure post-build actions for notifications

## 6. Monitoring and Logging

### CloudWatch Monitoring
```bash
# Create CloudWatch alarms for Jenkins master
aws cloudwatch put-metric-alarm \
  --alarm-name "Jenkins-Master-CPU-High" \
  --alarm-description "Jenkins master CPU utilization is high" \
  --metric-name CPUUtilization \
  --namespace AWS/EC2 \
  --statistic Average \
  --period 300 \
  --threshold 80 \
  --comparison-operator GreaterThanThreshold \
  --dimensions Name=InstanceId,Value=i-jenkins-master \
  --evaluation-periods 2
```

### Log Aggregation
Consider using CloudWatch Logs or ELK stack for centralized logging:
- Jenkins build logs
- Test execution logs
- System logs from EC2 instances

## 7. Cost Optimization

### Auto Scaling Policies
```bash
# Scale up policy
aws autoscaling put-scaling-policy \
  --auto-scaling-group-name jenkins-test-agents \
  --policy-name scale-up \
  --policy-type TargetTrackingScaling \
  --target-tracking-configuration '{
    "TargetValue": 70.0,
    "PredefinedMetricSpecification": {
      "PredefinedMetricType": "ASGAverageCPUUtilization"
    }
  }'

# Scale down policy (automatic with target tracking)
```

### Spot Instances
Consider using Spot Instances for test agents to reduce costs:
```bash
# Modify launch template to use Spot Instances
aws ec2 modify-launch-template \
  --launch-template-id lt-xxxxxxxxx \
  --launch-template-data '{
    "InstanceMarketOptions": {
      "MarketType": "spot",
      "SpotOptions": {
        "MaxPrice": "0.10",
        "SpotInstanceType": "one-time"
      }
    }
  }'
```

## 8. Backup and Disaster Recovery

### Jenkins Configuration Backup
- Use Configuration as Code plugin
- Store Jenkins configuration in Git
- Regular EBS snapshots of Jenkins master volume
- S3 backup of build artifacts and logs

### Multi-AZ Deployment
For high availability:
- Deploy Jenkins master in multiple AZs
- Use Application Load Balancer
- Shared EFS for Jenkins home directory
- RDS for Jenkins database (if using external DB)

## 9. Security Best Practices

1. **Network Security**:
   - Use private subnets for agents
   - NAT Gateway for internet access
   - VPC endpoints for AWS services

2. **Access Control**:
   - IAM roles instead of access keys
   - Least privilege principle
   - Regular credential rotation

3. **Encryption**:
   - EBS encryption for volumes
   - S3 encryption for artifacts
   - SSL/TLS for Jenkins web interface

4. **Monitoring**:
   - CloudTrail for API logging
   - VPC Flow Logs
   - Security group monitoring

## 10. Deployment Commands

### Complete Infrastructure Deployment
```bash
#!/bin/bash
# deploy-jenkins-infrastructure.sh

# Set variables
REGION="us-west-2"
VPC_ID="vpc-xxxxxxxxx"
SUBNET_IDS="subnet-xxxxxxxxx,subnet-yyyyyyyyy"
KEY_NAME="your-jenkins-key"

# Create security groups
./create-security-groups.sh

# Create IAM roles
./create-iam-roles.sh

# Create launch template
./create-launch-template.sh

# Create auto scaling group
./create-auto-scaling-group.sh

# Launch Jenkins master
./launch-jenkins-master.sh

echo "Jenkins infrastructure deployment complete!"
echo "Access Jenkins at: http://$(aws ec2 describe-instances --filters 'Name=tag:Name,Values=jenkins-master' --query 'Reservations[0].Instances[0].PublicIpAddress' --output text):8080"
```

This infrastructure setup provides a scalable, cost-effective solution for running your Cut+Dry automation tests in parallel on AWS using Jenkins.
