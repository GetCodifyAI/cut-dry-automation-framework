# CircleCI to Jenkins Migration Guide

## Overview
This guide provides step-by-step instructions for migrating your Cut+Dry automation framework from CircleCI to Jenkins hosted on AWS.

## Current CircleCI Analysis

### Existing Parallel Structure
Your current CircleCI setup runs **13 parallel jobs**:
- `nightly-build-and-test-1` through `nightly-build-and-test-13`
- Each job runs a specific regression XML file
- Results are aggregated in `calculate-pass-fail-rate` job
- Slack notifications are sent with combined results

### Key Features to Migrate
1. **Parallel Execution**: 13 simultaneous test jobs
2. **Scheduled Triggers**: Nightly and daily test runs
3. **Result Aggregation**: Combined pass/fail rate calculation
4. **Artifact Storage**: Test reports and screenshots
5. **Slack Notifications**: Build status and test results
6. **Environment Setup**: Java 22, Maven, Chrome headless

## Migration Steps

### Phase 1: Infrastructure Setup (Week 1)

#### 1.1 AWS Infrastructure Deployment
```bash
# Clone the repository
git clone https://github.com/GetCodifyAI/cut-dry-automation-framework.git
cd cut-dry-automation-framework

# Deploy AWS infrastructure
chmod +x jenkins/scripts/deploy-infrastructure.sh
./jenkins/scripts/deploy-infrastructure.sh
```

#### 1.2 Jenkins Master Setup
1. **Launch Jenkins Master EC2 Instance**:
   - Instance Type: `t3.large`
   - AMI: Amazon Linux 2
   - Security Group: Allow ports 8080, 22, 50000
   - IAM Role: Jenkins master role with EC2 permissions

2. **Install Jenkins**:
   ```bash
   # SSH to Jenkins master
   ssh -i your-key.pem ec2-user@jenkins-master-ip
   
   # Run Jenkins installation script
   sudo ./jenkins/scripts/install-jenkins-master.sh
   ```

3. **Initial Jenkins Configuration**:
   - Access Jenkins at `http://jenkins-master-ip:8080`
   - Complete initial setup wizard
   - Install required plugins (see plugin list below)

#### 1.3 Test Agent Auto Scaling Group
```bash
# Create launch template for test agents
aws ec2 create-launch-template \
  --launch-template-name jenkins-test-agents \
  --launch-template-data file://jenkins/aws/launch-template.json

# Create auto scaling group
aws autoscaling create-auto-scaling-group \
  --auto-scaling-group-name jenkins-test-agents \
  --launch-template LaunchTemplateName=jenkins-test-agents \
  --min-size 0 \
  --max-size 15 \
  --desired-capacity 0 \
  --vpc-zone-identifier "subnet-xxx,subnet-yyy"
```

### Phase 2: Jenkins Configuration (Week 2)

#### 2.1 Required Jenkins Plugins
Install these plugins via Jenkins Plugin Manager:
```
- Pipeline
- Pipeline: Stage View
- EC2
- TestNG Results
- Slack Notification
- Build Timeout
- Timestamper
- Workspace Cleanup
- Parallel Test Executor
- Matrix Authorization Strategy
```

#### 2.2 Global Tool Configuration
1. **Java Configuration**:
   - Name: `Java-22`
   - JAVA_HOME: `/usr/lib/jvm/java-22-openjdk`

2. **Maven Configuration**:
   - Name: `Maven-3.9`
   - MAVEN_HOME: `/opt/maven`

3. **Git Configuration**:
   - Name: `Default`
   - Path to Git executable: `/usr/bin/git`

#### 2.3 EC2 Cloud Configuration
1. Navigate to **Manage Jenkins → Manage Nodes and Clouds → Configure Clouds**
2. Add **Amazon EC2** cloud:
   ```
   Name: AWS-Test-Agents
   Amazon EC2 Credentials: [Your AWS credentials]
   Region: us-west-2
   EC2 Key Pair's Private Key: [Your private key]
   
   AMI Configuration:
   - AMI ID: ami-xxxxxxxxx (custom AMI with test tools)
   - Instance Type: t3.xlarge
   - Security group names: jenkins-agents-sg
   - Remote user: jenkins
   - Labels: aws-test-agent
   - Usage: Only build jobs with label expressions matching this node
   ```

### Phase 3: Pipeline Migration (Week 3)

#### 3.1 Create Main Pipeline Job
1. **New Item → Pipeline**
2. **Pipeline Configuration**:
   ```
   Definition: Pipeline script from SCM
   SCM: Git
   Repository URL: https://github.com/GetCodifyAI/cut-dry-automation-framework.git
   Branch: main
   Script Path: Jenkinsfile
   ```

#### 3.2 Configure Build Triggers
```groovy
triggers {
    // Nightly tests (matches CircleCI 22:00 UTC)
    cron('H 22 * * 1-5')
    // Daily tests (matches CircleCI 04:00 UTC)
    cron('H 4 * * 1-5')
}
```

#### 3.3 Environment Variables
Set these in Jenkins global properties:
```
MAVEN_OPTS = -Xmx2048m
JAVA_HOME = /usr/lib/jvm/java-22-openjdk
TEST_ENV = uat
RUN_HEADLESS = true
```

### Phase 4: Testing and Validation (Week 4)

#### 4.1 Test Single Regression Suite
```bash
# Test one regression file first
mvn clean test -Dsurefire.suiteXmlFiles=regression1.xml -Drun.headless=true -Dtest.env=uat
```

#### 4.2 Test Parallel Execution
1. **Manual Pipeline Trigger**:
   - Go to your pipeline job
   - Click "Build Now"
   - Monitor all 13 parallel stages

2. **Verify Results**:
   - Check TestNG results
   - Verify artifacts are stored
   - Confirm Slack notifications

#### 4.3 Performance Comparison
Create a comparison report:
```
Metric                | CircleCI | Jenkins
---------------------|----------|--------
Total Execution Time | X min    | Y min
Resource Usage       | xlarge   | t3.xlarge
Cost per Run         | $X       | $Y
Parallel Jobs        | 13       | 13
Success Rate         | X%       | Y%
```

### Phase 5: Slack Integration

#### 5.1 Slack App Configuration
1. **Create Slack App**:
   - Go to https://api.slack.com/apps
   - Create new app for your workspace
   - Add bot token scopes: `chat:write`, `files:write`

2. **Jenkins Slack Configuration**:
   ```
   Manage Jenkins → Configure System → Slack
   Workspace: your-workspace
   Credential: [Slack bot token]
   Default channel: #qa-automation
   ```

#### 5.2 Notification Template
The Jenkinsfile includes a `sendSlackNotification()` function that matches your CircleCI format:
```groovy
def message = """
✅ *Cut+Dry Automation Test Results*
*Build:* ${env.BUILD_NUMBER}
*Status:* ${status}
*Total Tests:* ${testResults.TOTAL_TESTS}
*Passed:* ${testResults.PASSED_TESTS}
*Failed:* ${testResults.FAILED_TESTS}
*Pass Rate:* ${testResults.PASS_RATE}%
"""
```

### Phase 6: Monitoring and Optimization

#### 6.1 CloudWatch Monitoring
Set up monitoring for:
- Jenkins master CPU/memory usage
- Test agent scaling metrics
- Build duration trends
- Success/failure rates

#### 6.2 Cost Optimization
1. **Spot Instances**: Use spot instances for test agents
2. **Auto Scaling**: Scale down agents when not in use
3. **Scheduled Scaling**: Pre-scale before scheduled builds

#### 6.3 Performance Tuning
```groovy
// Optimize parallel execution
options {
    parallelsAlwaysFailFast()
    timeout(time: 4, unit: 'HOURS')
    buildDiscarder(logRotator(numToKeepStr: '10'))
}
```

## Migration Checklist

### Pre-Migration
- [ ] AWS infrastructure deployed
- [ ] Jenkins master configured
- [ ] Test agents auto scaling group created
- [ ] Security groups configured
- [ ] IAM roles and policies created

### During Migration
- [ ] Jenkins plugins installed
- [ ] Pipeline job created
- [ ] Environment variables configured
- [ ] Slack integration configured
- [ ] Test execution validated

### Post-Migration
- [ ] Scheduled builds working
- [ ] Parallel execution verified
- [ ] Result aggregation working
- [ ] Slack notifications sending
- [ ] Performance monitoring enabled
- [ ] Cost optimization implemented

### Rollback Plan
- [ ] CircleCI configuration preserved
- [ ] Ability to switch back quickly
- [ ] Data backup procedures
- [ ] Communication plan for team

## Troubleshooting Guide

### Common Issues

#### 1. Agent Connection Problems
```bash
# Check agent logs
sudo journalctl -u jenkins-agent -f

# Verify security groups
aws ec2 describe-security-groups --group-ids sg-jenkins-agents
```

#### 2. Test Execution Failures
```bash
# Check Java version on agents
java -version

# Verify Maven installation
mvn -version

# Check Chrome installation
google-chrome --version
```

#### 3. Result Aggregation Issues
```bash
# Verify test result files exist
ls -la target/surefire-reports/

# Check file permissions
chmod 644 target/surefire-reports/*.xml
```

### Performance Issues

#### 1. Slow Build Times
- Increase agent instance types
- Optimize Maven dependencies caching
- Parallel test distribution optimization

#### 2. Agent Scaling Issues
- Adjust auto scaling policies
- Monitor CloudWatch metrics
- Check EC2 service limits

### Support Contacts
- **AWS Support**: For infrastructure issues
- **Jenkins Community**: For Jenkins-specific problems
- **Internal DevOps Team**: For company-specific configurations

## Success Metrics

### Key Performance Indicators
1. **Build Time**: Target ≤ CircleCI execution time
2. **Success Rate**: Target ≥ 95%
3. **Cost**: Target ≤ CircleCI monthly cost
4. **Reliability**: Target 99.9% uptime
5. **Scalability**: Handle peak load without issues

### Monitoring Dashboard
Create a dashboard tracking:
- Daily build success rates
- Average build duration
- Cost per build
- Agent utilization
- Test result trends

This migration guide ensures a smooth transition from CircleCI to Jenkins while maintaining all existing functionality and improving scalability on AWS infrastructure.
