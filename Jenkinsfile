pipeline {
    agent none
    
    environment {
        MAVEN_OPTS = '-Xmx2048m -XX:MaxPermSize=512m'
        JAVA_HOME = '/usr/lib/jvm/java-22-openjdk'
        TEST_ENV = 'uat'
        RUN_HEADLESS = 'true'
    }
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 4, unit: 'HOURS')
        parallelsAlwaysFailFast()
    }
    
    triggers {
        // Nightly and daily tests - matches CircleCI schedule (22:00 and 04:00 UTC weekdays)
        cron('H 4,22 * * 1-5')
    }
    
    stages {
        stage('Parallel Test Execution') {
            parallel {
                stage('Regression 1 - Order Guide & Reports') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression1.xml', 'Part_One', 1)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-1', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 2 - Track & Order Desk') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression2.xml', 'Part_Two', 2)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-2', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 3 - Catalog & Pay') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression3.xml', 'Part_Three', 3)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-3', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 4 - Customers') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression4.xml', 'Part_Four', 4)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-4', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 5 - Orders') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression5.xml', 'Part_Five', 5)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-5', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 6 - Multi UOM') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression6.xml', 'Part_Six', 6)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-6', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 7 - Restaurant Portal') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression7.xml', 'Part_Seven', 7)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-7', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 8 - White Label') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression8.xml', 'Part_Eight', 8)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-8', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 9 - Operator Portal') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression9.xml', 'Part_Nine', 9)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-9', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 10 - Internal Tools') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression10.xml', 'Part_Ten', 10)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-10', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 11 - Credit Requests') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression11.xml', 'Part_Eleven', 11)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-11', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 12 - Scan to Order') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression12.xml', 'Part_Twelve', 12)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-12', allowEmpty: true
                        }
                    }
                }
                
                stage('Regression 13 - Purchase History') {
                    agent any
                    steps {
                        script {
                            runTestSuite('regression13.xml', 'Part_Thirteen', 13)
                        }
                    }
                    post {
                        always {
                            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                            stash includes: 'target/surefire-reports/TEST-*.xml', name: 'test-results-13', allowEmpty: true
                        }
                    }
                }
            }
        }
        
        stage('Aggregate Results & Report') {
            agent any
            steps {
                script {
                    // Unstash all test results
                    for (int i = 1; i <= 13; i++) {
                        try {
                            unstash "test-results-${i}"
                        } catch (Exception e) {
                            echo "Warning: Could not unstash test-results-${i}: ${e.getMessage()}"
                        }
                    }
                    
                    // Calculate combined pass/fail rate
                    calculateAndReportResults()
                }
            }
            post {
                always {
                    // Publish TestNG results
                    publishTestResults testResultsPattern: '**/target/surefire-reports/TEST-*.xml'
                    
                    // Archive all test artifacts
                    archiveArtifacts artifacts: '**/target/surefire-reports/**/*', allowEmptyArchive: true
                    
                    // Send Slack notification
                    script {
                        sendSlackNotification()
                    }
                }
            }
        }
    }
    
    post {
        failure {
            script {
                sendSlackNotification('FAILURE')
            }
        }
        unstable {
            script {
                sendSlackNotification('UNSTABLE')
            }
        }
    }
}

def runTestSuite(xmlFile, partName, jobNumber) {
    try {
        // Checkout code
        checkout scm
        
        // Setup environment
        setupTestEnvironment()
        
        // Run the specific test suite
        sh """
            echo "Starting ${partName} test execution with ${xmlFile}"
            mvn clean test \\
                -Dsurefire.suiteXmlFiles=${xmlFile} \\
                -Drun.headless=${env.RUN_HEADLESS} \\
                -Dtest.env=${env.TEST_ENV} \\
                -Dmaven.test.failure.ignore=true \\
                -Duser.timezone=UTC
        """
        
        // Verify test results exist
        sh """
            if [ -d "target/surefire-reports" ]; then
                echo "Test results found for ${partName}:"
                ls -la target/surefire-reports/
            else
                echo "Warning: No test results directory found for ${partName}"
                mkdir -p target/surefire-reports
            fi
        """
        
        // Copy test results with part name prefix
        sh """
            if [ -d "target/surefire-reports" ]; then
                for file in target/surefire-reports/TEST-*.xml; do
                    if [ -f "\$file" ]; then
                        cp "\$file" "target/surefire-reports/${partName}_job-${jobNumber}_\$(basename "\$file")"
                    fi
                done
            fi
        """
        
    } catch (Exception e) {
        echo "Error in ${partName}: ${e.getMessage()}"
        currentBuild.result = 'UNSTABLE'
        throw e
    }
}

def setupTestEnvironment() {
    sh '''
        echo "Setting up test environment..."
        
        # Install Java 22 if not present
        if ! java -version 2>&1 | grep -q "22"; then
            echo "Installing Java 22..."
            sudo apt-get update && sudo apt-get install -y wget
            wget -q https://download.java.net/java/GA/jdk22/830ec9fcccef480bb3e73fb7ecafe059/36/GPL/openjdk-22_linux-x64_bin.tar.gz
            sudo mkdir -p /usr/lib/jvm
            sudo tar zxf openjdk-22_linux-x64_bin.tar.gz -C /usr/lib/jvm
            sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-22/bin/java 2200
            sudo update-alternatives --set java /usr/lib/jvm/jdk-22/bin/java
            export PATH="/usr/lib/jvm/jdk-22/bin:$PATH"
            export JAVA_HOME="/usr/lib/jvm/jdk-22"
        fi
        
        # Install Maven if not present
        if ! command -v mvn &> /dev/null; then
            echo "Installing Maven..."
            sudo apt-get install -y maven
        fi
        
        # Install Chrome for headless testing
        if ! command -v google-chrome &> /dev/null; then
            echo "Installing Chrome..."
            wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
            sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
            sudo apt-get update
            sudo apt-get install -y google-chrome-stable
        fi
        
        # Verify installations
        java -version
        mvn -version
        google-chrome --version
    '''
}

def calculateAndReportResults() {
    sh '''
        echo "Calculating combined test results..."
        
        # Install bc for calculations if not present
        if ! command -v bc &> /dev/null; then
            sudo apt-get update && sudo apt-get install -y bc
        fi
        
        # Initialize counters
        TOTAL=0
        PASSED=0
        FAILED=0
        ERRORS=0
        SKIPPED=0
        
        # Process all test result files
        for file in target/surefire-reports/TEST-*.xml; do
            if [ -f "$file" ]; then
                echo "Processing: $file"
                
                TESTS=$(grep -oP '(?<=tests=")[0-9]+' "$file" | head -1)
                FAILURES=$(grep -oP '(?<=failures=")[0-9]+' "$file" | head -1)
                FILE_ERRORS=$(grep -oP '(?<=errors=")[0-9]+' "$file" | head -1)
                FILE_SKIPPED=$(grep -oP '(?<=skipped=")[0-9]+' "$file" | head -1)
                
                TESTS=${TESTS:-0}
                FAILURES=${FAILURES:-0}
                FILE_ERRORS=${FILE_ERRORS:-0}
                FILE_SKIPPED=${FILE_SKIPPED:-0}
                
                TOTAL=$((TOTAL + TESTS))
                FAILED=$((FAILED + FAILURES))
                ERRORS=$((ERRORS + FILE_ERRORS))
                SKIPPED=$((SKIPPED + FILE_SKIPPED))
                
                echo "  Tests: $TESTS, Failures: $FAILURES, Errors: $FILE_ERRORS, Skipped: $FILE_SKIPPED"
            fi
        done
        
        # Calculate pass rate
        ACTUAL_TOTAL=$((TOTAL - SKIPPED))
        PASSED=$((ACTUAL_TOTAL - FAILED - ERRORS))
        
        if [ "$ACTUAL_TOTAL" -gt 0 ]; then
            PASS_RATE=$(echo "scale=2; ($PASSED / $ACTUAL_TOTAL) * 100.0" | bc)
            FAIL_RATE=$(echo "scale=2; (($FAILED + $ERRORS) / $ACTUAL_TOTAL) * 100.0" | bc)
        else
            PASS_RATE="0.00"
            FAIL_RATE="0.00"
        fi
        
        echo "=== FINAL TEST RESULTS ==="
        echo "Total tests run: $ACTUAL_TOTAL"
        echo "Passed: $PASSED"
        echo "Failed: $FAILED"
        echo "Errors: $ERRORS"
        echo "Skipped: $SKIPPED"
        echo "Pass Rate: $PASS_RATE%"
        echo "Fail Rate: $FAIL_RATE%"
        echo "=========================="
        
        # Save results to environment file for Slack notification
        echo "TOTAL_TESTS=$ACTUAL_TOTAL" > test_results.env
        echo "PASSED_TESTS=$PASSED" >> test_results.env
        echo "FAILED_TESTS=$FAILED" >> test_results.env
        echo "ERROR_TESTS=$ERRORS" >> test_results.env
        echo "SKIPPED_TESTS=$SKIPPED" >> test_results.env
        echo "PASS_RATE=$PASS_RATE" >> test_results.env
        echo "FAIL_RATE=$FAIL_RATE" >> test_results.env
    '''
}

def sendSlackNotification(buildStatus = null) {
    def status = buildStatus ?: currentBuild.currentResult
    def color = 'good'
    def emoji = '✅'
    
    if (status == 'FAILURE') {
        color = 'danger'
        emoji = '❌'
    } else if (status == 'UNSTABLE') {
        color = 'warning'
        emoji = '⚠️'
    }
    
    try {
        // Read test results if available
        def testResults = [:]
        if (fileExists('test_results.env')) {
            def props = readProperties file: 'test_results.env'
            testResults = props
        }
        
        def message = """
${emoji} *Cut+Dry Automation Test Results*
*Build:* ${env.BUILD_NUMBER}
*Status:* ${status}
*Branch:* ${env.BRANCH_NAME ?: 'main'}
*Duration:* ${currentBuild.durationString}

*Test Summary:*
• Total Tests: ${testResults.TOTAL_TESTS ?: 'N/A'}
• Passed: ${testResults.PASSED_TESTS ?: 'N/A'}
• Failed: ${testResults.FAILED_TESTS ?: 'N/A'}
• Errors: ${testResults.ERROR_TESTS ?: 'N/A'}
• Pass Rate: ${testResults.PASS_RATE ?: 'N/A'}%

*Build URL:* ${env.BUILD_URL}
        """.trim()
        
        // Send to Slack (requires Slack plugin configuration)
        slackSend(
            channel: '#qa-automation',
            color: color,
            message: message,
            teamDomain: 'your-workspace',
            token: 'your-slack-token'
        )
        
    } catch (Exception e) {
        echo "Failed to send Slack notification: ${e.getMessage()}"
    }
}
