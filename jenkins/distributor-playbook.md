# Jenkins Pipeline Management Playbook for Distributors

## Overview
This playbook provides comprehensive instructions for managing the Cut+Dry Jenkins parallel test execution pipeline. The Jenkins instance is configured to run 13 parallel regression test suites for comprehensive quality assurance.

## Quick Reference

### Access Information
- **Jenkins URL**: Contact your administrator for the Jenkins server URL
- **Access**: Contact your administrator for Jenkins access information
- **Job Name**: `Cut-Dry-Parallel-Tests`

### Pipeline Overview
- **Parallel Stages**: 13 regression test suites
- **Execution Time**: ~30-45 minutes for full run
- **Test Environment**: UAT (User Acceptance Testing)
- **Browser**: Chrome headless mode
- **Java Version**: Java 21
- **Build Tool**: Maven

---

## 1. Build Configuration Management

### 1.1 Modifying Test Suites

#### Adding New Regression Test Suites
To add a new regression XML file to the parallel execution:

1. **Create New Regression XML**:
   - Add new `regressionX.xml` file to repository root
   - Follow existing TestNG suite structure
   - Define test classes and methods to include

2. **Update Jenkinsfile**:
   - Navigate to repository: `GetCodifyAI/cut-dry-automation-framework`
   - Edit `Jenkinsfile` in the root directory
   - Add new stage in the parallel block:
   ```groovy
   stage('Regression X - Description') {
       agent { label 'built-in' }
       steps {
           script {
               runTestSuite('regressionX.xml', 'Part_X')
           }
       }
       post {
           always {
               archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
               stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-X'
           }
       }
   }
   ```

3. **Update Result Aggregation**:
   - Modify the aggregation loop in Jenkinsfile:
   ```groovy
   for (int i = 1; i <= X; i++) {  // Update X to new total
       unstash "test-results-${i}"
   }
   ```

#### Removing Test Suites
1. **Remove Stage**: Delete the corresponding stage block from Jenkinsfile
2. **Update Loop**: Adjust the aggregation loop counter
3. **Archive Old XML**: Move unused regression XML files to archive folder

#### Modifying Existing Test Suites
1. **Edit Regression XML**: Update test class inclusions/exclusions
2. **Test Locally**: Verify changes with `mvn test -Dsurefire.suiteXmlFiles=regressionX.xml`
3. **Commit Changes**: Push updates to trigger new build

### 1.2 Environment Configuration Changes

#### Updating Test Environment URLs
1. **Locate Configuration**:
   - File: `src/main/java/com/cutanddry/qa/common/Constants.java`
   - Environment URLs defined here

2. **Modify Environment Variables**:
   - Edit Jenkinsfile environment section:
   ```groovy
   environment {
       MAVEN_OPTS = '-Xmx2048m -XX:MaxMetaspaceSize=512m'
       JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
       TEST_ENV = 'uat'  // Change to 'staging', 'prod', etc.
       RUN_HEADLESS = 'true'
   }
   ```

3. **Test Environment Validation**:
   - Verify environment accessibility
   - Update access information if needed
   - Test connectivity before full deployment

#### Browser Configuration Changes
1. **Chrome Options**: Modify browser setup in test base classes
2. **Headless Mode**: Toggle via `RUN_HEADLESS` environment variable
3. **Browser Version**: Update Chrome installation in environment setup

### 1.3 Performance Tuning

#### Adjusting Parallel Execution
1. **Resource Allocation**:
   - Monitor Jenkins system resources
   - Adjust number of parallel stages if needed
   - Consider agent capacity limitations

2. **Timeout Configuration**:
   ```groovy
   options {
       timeout(time: 4, unit: 'HOURS')  // Adjust as needed
       parallelsAlwaysFailFast()        // Stop all on first failure
   }
   ```

3. **Memory Settings**:
   - Adjust `MAVEN_OPTS` for memory allocation
   - Monitor heap usage during builds
   - Scale resources if needed

#### Test Execution Optimization
1. **Test Grouping**: Redistribute tests across regression suites for balance
2. **Retry Logic**: Configure retry attempts for flaky tests
3. **Cleanup Procedures**: Ensure proper resource cleanup between tests

### 1.4 Advanced Build Parameters

#### Parameterized Builds
To enable build parameters for flexible execution:

1. **Configure Job Parameters**:
   - Go to Jenkins job configuration
   - Check "This project is parameterized"
   - Add parameters:
     - **Choice Parameter**: `TEST_ENVIRONMENT` (uat, staging, prod)
     - **Boolean Parameter**: `RUN_SMOKE_ONLY` (true/false)
     - **String Parameter**: `REGRESSION_SUITES` (comma-separated list)

2. **Update Jenkinsfile for Parameters**:
   ```groovy
   parameters {
       choice(
           name: 'TEST_ENVIRONMENT',
           choices: ['uat', 'staging', 'prod'],
           description: 'Target environment for testing'
       )
       booleanParam(
           name: 'RUN_SMOKE_ONLY',
           defaultValue: false,
           description: 'Run only smoke tests'
       )
       string(
           name: 'REGRESSION_SUITES',
           defaultValue: '1,2,3,4,5,6,7,8,9,10,11,12,13',
           description: 'Comma-separated list of regression suite numbers'
       )
   }
   ```

3. **Conditional Execution**:
   ```groovy
   script {
       def suitesToRun = params.REGRESSION_SUITES.split(',')
       if (suitesToRun.contains('1')) {
           // Execute regression 1
       }
   }
   ```

## 2. Jenkins Job Configuration Changes

### 2.1 Modifying Build Triggers

#### Scheduled Execution Changes
1. **Access Job Configuration**:
   - Navigate to Jenkins job
   - Click "Configure" in left sidebar
   - Scroll to "Build Triggers" section

2. **Cron Schedule Modification**:
   ```
   Current Schedule:
   - Nightly: H 22 * * 1-5 (10 PM UTC, Mon-Fri)
   - Daily: H 4 * * 1-5 (4 AM UTC, Mon-Fri)
   
   Example Changes:
   - Weekend runs: H 22 * * 6-7
   - Hourly during business: H 9-17 * * 1-5
   - Custom schedule: 0 2,14 * * 1-5
   ```

3. **Webhook Triggers**:
   - Enable "GitHub hook trigger for GITScm polling"
   - Configure webhook in GitHub repository settings
   - Set up branch-specific triggers

### 2.2 Environment Variable Management

#### Global Environment Variables
1. **System Configuration**:
   - Go to "Manage Jenkins" → "Configure System"
   - Scroll to "Global properties"
   - Add/modify environment variables:
     ```
     MAVEN_OPTS = -Xmx2048m -XX:MaxMetaspaceSize=512m
     TEST_ENV = uat
     RUN_HEADLESS = true
     BROWSER_VERSION = latest
     ```

2. **Job-Specific Variables**:
   - In job configuration, scroll to "Build Environment"
   - Check "Set environment variables"
   - Add custom variables for this job only

#### Credential Management
1. **Adding New Credentials**:
   - Go to "Manage Jenkins" → "Manage Credentials"
   - Select appropriate domain
   - Click "Add Credentials"
   - Choose credential type (Username/Password, Secret Text, etc.)

2. **Using Credentials in Pipeline**:
   ```groovy
   environment {
       DB_CREDENTIALS = credentials('database-credentials-id')
       API_KEY = credentials('api-key-secret')
   }
   ```

### 2.3 Agent Configuration

#### Built-in Node Settings
1. **Executor Configuration**:
   - Go to "Manage Jenkins" → "Manage Nodes and Clouds"
   - Click on "Built-In Node"
   - Adjust "# of executors" based on system capacity

2. **Node Properties**:
   - Set environment variables specific to this node
   - Configure tool locations (Java, Maven, Git)
   - Set usage restrictions if needed

#### Adding Additional Agents
1. **New Node Setup**:
   - Click "New Node" in node management
   - Choose "Permanent Agent"
   - Configure connection method (SSH, JNLP, etc.)
   - Set labels for job targeting

## 3. Pipeline Modification Procedures

### 3.1 Jenkinsfile Structure Changes

#### Adding New Pipeline Stages
1. **Sequential Stages**:
   ```groovy
   stage('New Stage Name') {
       agent { label 'built-in' }
       steps {
           script {
               // Your stage logic here
           }
       }
       post {
           always {
               // Cleanup or archiving
           }
       }
   }
   ```

2. **Parallel Stage Groups**:
   ```groovy
   stage('New Parallel Group') {
       parallel {
           stage('Sub-stage 1') {
               // Stage definition
           }
           stage('Sub-stage 2') {
               // Stage definition
           }
       }
   }
   ```

#### Modifying Existing Stages
1. **Stage Conditions**:
   ```groovy
   stage('Conditional Stage') {
       when {
           branch 'main'
           // or
           environment name: 'DEPLOY_ENV', value: 'production'
       }
       steps {
           // Stage steps
       }
   }
   ```

2. **Error Handling**:
   ```groovy
   stage('Stage with Error Handling') {
       steps {
           script {
               try {
                   // Main logic
               } catch (Exception e) {
                   currentBuild.result = 'UNSTABLE'
                   echo "Stage failed but continuing: ${e.getMessage()}"
               }
           }
       }
   }
   ```

### 3.2 Build Options Configuration

#### Timeout and Retry Settings
```groovy
options {
    timeout(time: 4, unit: 'HOURS')
    retry(3)
    parallelsAlwaysFailFast()
    buildDiscarder(logRotator(numToKeepStr: '10'))
    skipDefaultCheckout()
}
```

#### Build Parameters
```groovy
parameters {
    choice(
        name: 'ENVIRONMENT',
        choices: ['uat', 'staging', 'production'],
        description: 'Target environment'
    )
    booleanParam(
        name: 'SKIP_TESTS',
        defaultValue: false,
        description: 'Skip test execution'
    )
    string(
        name: 'BRANCH_NAME',
        defaultValue: 'main',
        description: 'Git branch to build'
    )
}
```

## 4. Integration Configuration

### 4.1 Slack Notification Setup

#### Slack App Configuration
1. **Create Slack App**:
   - Go to Slack API website
   - Create new app for your workspace
   - Add bot token scopes: `chat:write`, `files:write`
   - Install app to workspace

2. **Jenkins Slack Plugin**:
   - Install "Slack Notification" plugin
   - Go to "Manage Jenkins" → "Configure System"
   - Find "Slack" section
   - Add workspace and bot token

#### Custom Notification Messages
```groovy
def sendSlackNotification(String status, Map testResults) {
    def color = status == 'SUCCESS' ? 'good' : 'danger'
    def message = """
*Cut+Dry Automation Test Results*
*Build:* ${env.BUILD_NUMBER}
*Status:* ${status}
*Total Tests:* ${testResults.TOTAL_TESTS}
*Passed:* ${testResults.PASSED_TESTS}
*Failed:* ${testResults.FAILED_TESTS}
*Pass Rate:* ${testResults.PASS_RATE}%
"""
    
    slackSend(
        channel: '#qa-automation',
        color: color,
        message: message
    )
}
```

### 4.2 JIRA Integration

#### JIRA Plugin Configuration
1. **Install JIRA Plugin**:
   - Go to "Manage Jenkins" → "Manage Plugins"
   - Install "JIRA" plugin
   - Restart Jenkins

2. **JIRA Site Configuration**:
   - Go to "Manage Jenkins" → "Configure System"
   - Add JIRA site with URL and credentials
   - Test connection

#### Automated Issue Updates
```groovy
stage('Update JIRA') {
    steps {
        script {
            if (currentBuild.result == 'FAILURE') {
                jiraCreateIssue(
                    site: 'your-jira-site',
                    issue: [
                        fields: [
                            project: [key: 'QA'],
                            summary: "Build ${env.BUILD_NUMBER} failed",
                            description: "Automated test build failed",
                            issuetype: [name: 'Bug']
                        ]
                    ]
                )
            }
        }
    }
}
```

## 5. Daily Operations

### 5.1 Starting a Test Run

#### Manual Test Execution
1. **Access Jenkins**:
   - Navigate to your Jenkins server
   - Login with your provided access information

2. **Navigate to Job**:
   - Click on "Cut-Dry-Parallel-Tests" from the dashboard
   - Or go directly to the job URL

3. **Trigger Build**:
   - Click "Build Now" button on the left sidebar
   - Build will start immediately and show in build history

#### Scheduled Execution
The pipeline is configured with automatic triggers:
- **Nightly Tests**: Monday-Friday at 22:00 UTC (6:00 PM EST)
- **Daily Tests**: Monday-Friday at 04:00 UTC (12:00 AM EST)

### 5.2 Monitoring Build Progress

#### Real-time Monitoring
1. **Build Status**:
   - Green progress bar = Running successfully
   - Red progress bar = Build failed
   - Blue ball = Successful completion
   - Red ball = Failed completion

2. **Console Output**:
   - Click on build number (e.g., "#8")
   - Click "Console Output" to view real-time logs
   - Look for parallel stage execution logs

3. **Stage View**:
   - Click "Pipeline Steps" to see visual stage progress
   - Each of the 13 regression stages will show individual status

#### Key Metrics to Monitor
- **Build Duration**: Normal range 30-45 minutes
- **Parallel Execution**: All 13 stages should start simultaneously
- **Java Version**: Should show Java 21.0.x in logs
- **Test Results**: Pass/fail counts for each stage

## 6. Understanding Test Results

### 6.1 Test Result Interpretation

#### Build Status Indicators
```
✅ SUCCESS: All tests passed or within acceptable failure threshold
❌ FAILURE: Critical test failures or infrastructure issues
⚠️  UNSTABLE: Some test failures but build completed
🔄 ABORTED: Build was manually stopped or timed out
```

#### Test Result Files
After each build, the following artifacts are generated:
- **TestNG Reports**: `target/surefire-reports/`
- **Screenshots**: Captured on test failures
- **Console Logs**: Full execution details

### 6.2 Accessing Test Reports

1. **From Build Page**:
   - Click on build number
   - Click "Test Result" in left sidebar
   - View detailed pass/fail breakdown

2. **Download Artifacts**:
   - Click "Build Artifacts" 
   - Download test reports and screenshots
   - Review failed test screenshots for debugging

### 6.3 Test Result Analysis

#### Normal Test Results
```
Total Tests: ~500-800 (varies by regression suite)
Expected Pass Rate: 85-95%
Acceptable Failures: <50 tests
Critical Failures: 0 (infrastructure/login issues)
```

#### Red Flags to Escalate
- **Infrastructure Failures**: Java/Maven/Chrome setup issues
- **Login Failures**: Authentication problems across multiple stages
- **Timeout Issues**: Consistent timeouts across all stages
- **Pass Rate < 80%**: Indicates potential application issues

## 7. Troubleshooting Guide

### 7.1 Common Issues and Solutions

#### Issue: Build Fails to Start
**Symptoms**: Build shows "Pending" status indefinitely
**Causes**: 
- Jenkins agent unavailable
- Resource constraints
- Network connectivity issues

**Solutions**:
1. Check Jenkins system status: Manage Jenkins → System Information
2. Restart Jenkins service if needed
3. Verify network connectivity to test environment
4. Contact DevOps if agents are unavailable

#### Issue: Java Version Mismatch
**Symptoms**: 
```
Error: invalid target release: 22
Error: JAVA_HOME not set correctly
```

**Solutions**:
1. Verify Java 21 is installed: Check console output for `java -version`
2. Confirm JAVA_HOME setting: Should be `/usr/lib/jvm/java-21-openjdk-amd64`
3. If issue persists, contact DevOps team

#### Issue: Maven Compilation Failures
**Symptoms**:
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin
[ERROR] Source option 21 is no longer supported
```

**Solutions**:
1. Check Maven version compatibility
2. Verify pom.xml compiler settings (should be Java 21)
3. Clear Maven cache: `mvn clean install -U`

#### Issue: Chrome Browser Issues
**Symptoms**:
```
WebDriverException: chrome not reachable
SessionNotCreatedException: Could not start a new session
```

**Solutions**:
1. Verify Chrome installation in console logs
2. Check headless mode configuration
3. Restart build - temporary Chrome issues often resolve

#### Issue: Test Environment Connectivity
**Symptoms**:
```
TimeoutException: Timed out waiting for page to load
ConnectException: Connection refused
```

**Solutions**:
1. Verify UAT environment status
2. Check network connectivity from Jenkins
3. Confirm test environment URLs are accessible
4. Contact application team if environment is down

## 8. Change Management Procedures

### 8.1 Configuration Change Process

#### Standard Change Workflow
1. **Planning Phase**:
   - Document proposed changes
   - Assess impact on existing builds
   - Plan rollback procedures
   - Schedule maintenance window if needed

2. **Implementation Phase**:
   - Create feature branch for changes
   - Test changes in development environment
   - Peer review configuration updates
   - Deploy during low-usage periods

3. **Validation Phase**:
   - Run test builds to verify changes
   - Monitor system performance
   - Validate all functionality works
   - Document any issues encountered

#### Emergency Change Procedures
1. **Critical Issue Response**:
   - Immediate assessment of impact
   - Quick fix implementation
   - Bypass normal approval for urgent fixes
   - Post-incident documentation

2. **Communication Protocol**:
   - Notify stakeholders immediately
   - Provide regular status updates
   - Document lessons learned
   - Update procedures based on findings

### 8.2 Version Control Integration

#### Configuration as Code
1. **Pipeline Configuration**:
   - All pipeline logic in version-controlled Jenkinsfile
   - Branch-based development workflow
   - Code review for all changes
   - Automated testing of pipeline changes

2. **Job Configuration Backup**:
   - Regular export of job configurations
   - Version control for configuration files
   - Disaster recovery procedures
   - Configuration drift detection

## 9. Best Practices and Guidelines

### 9.1 Development Best Practices

#### Pipeline Design Principles
1. **Modularity**:
   - Break complex pipelines into reusable functions
   - Use shared libraries for common operations
   - Maintain clear separation of concerns

2. **Error Handling**:
   - Implement comprehensive error handling
   - Provide meaningful error messages
   - Ensure graceful failure recovery

#### Code Quality Standards
1. **Documentation Requirements**:
   - Comment complex pipeline logic
   - Maintain up-to-date README files
   - Document configuration changes

2. **Testing Procedures**:
   - Test pipeline changes in development
   - Validate configuration before deployment
   - Monitor build performance after changes

### 9.2 Operational Excellence

#### Monitoring and Alerting
1. **Proactive Monitoring**:
   - Set up alerts for build failures
   - Monitor system resource usage
   - Track build performance trends

2. **Incident Response**:
   - Define escalation procedures
   - Maintain incident response playbooks
   - Conduct post-incident reviews

#### Continuous Improvement
1. **Regular Reviews**:
   - Monthly pipeline performance reviews
   - Quarterly configuration audits
   - Annual security assessments

2. **Feedback Integration**:
   - Collect user feedback on pipeline performance
   - Implement suggested improvements
   - Share lessons learned across teams

---

## Contact Information

**Primary Support**: QA Team Lead
**Secondary Support**: DevOps Team
**Emergency Contact**: On-call Engineer

**Documentation Version**: 1.0
**Last Updated**: September 2025
**Next Review**: October 2025

---

*This playbook is a living document and should be updated as the Jenkins pipeline evolves and new procedures are established.*
