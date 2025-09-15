pipeline {
    agent any
    
    triggers {
        cron('0 2 * * *')  // Daily at 2:00 AM UTC
    }
    
    options {
        timeout(time: 2, unit: 'HOURS')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    
    parameters {
        choice(
            name: 'TEST_ENVIRONMENT',
            choices: ['uat', 'stag', 'prod'],
            description: 'Target environment for testing'
        )
        string(
            name: 'TEST_STAG_VALUE',
            defaultValue: 'mfg2640',
            description: 'TEST_STAG value for STAG environment URL construction'
        )
    }
    
    environment {
        MAVEN_OPTS = '-Xmx2048m'
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        TEST_ENV = 'uat'
        RUN_HEADLESS = 'true'
        STAGING_SLACK_WEBHOOK = 'https://hooks.slack.com/services/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'
    }
    
    stages {
        stage('Verify Test Suite') {
            steps {
                script {
                    echo "Using sanity_test_1.xml from current branch"
                    sh "ls -la sanity_test_1.xml"
                }
            }
        }
        
        stage('Display Parameters') {
            steps {
                script {
                    echo "Setting up test environment: ${params.TEST_ENVIRONMENT}"
                    echo "TEST_STAG value: ${params.TEST_STAG_VALUE}"
                }
            }
        }
        
        stage('Setup Environment') {
            steps {
                script {
                    setupTestEnvironment()
                }
            }
        }
        
        stage('Run Sanity Tests') {
            steps {
                script {
                    try {
                        echo "Executing sanity_test_1.xml with environment: ${params.TEST_ENVIRONMENT}"
                        echo "TEST_STAG value: ${params.TEST_STAG_VALUE}"
                        sh """
                            mvn clean test \\
                            -Dsurefire.suiteXmlFiles=sanity_test_1.xml \\
                            -Dtest.env=${params.TEST_ENVIRONMENT} \\
                            -Drun.headless=${env.RUN_HEADLESS} \\
                            -DTEST_STAG=${params.TEST_STAG_VALUE} \\
                            -Dcreate.cycle=true \\
                            -Dmaven.test.failure.ignore=true \\
                            -Duser.timezone=UTC
                        """
                    } catch (Exception e) {
                        currentBuild.result = 'UNSTABLE'
                        echo "Tests failed but continuing: ${e.getMessage()}"
                    }
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                    script {
                        // Archive test results without using publishTestResults plugin
                        sh """
                            if [ -d "target/surefire-reports" ]; then
                                echo "Test results found:"
                                ls -la target/surefire-reports/
                            else
                                echo "Warning: No test results directory found"
                                mkdir -p target/surefire-reports
                            fi
                        """
                    }
                }
            }
        }
    }
    
    post {
        always {
            script {
                calculateAndReportResults()
            }
        }
        success {
            echo 'Sanity tests completed successfully!'
        }
        failure {
            echo 'Sanity tests failed!'
        }
        unstable {
            echo 'Sanity tests completed with some failures'
        }
    }
}

def setupTestEnvironment() {
    sh '''
        echo "Setting up test environment..."
        
        # Set JAVA_HOME to the correct OpenJDK path for Ubuntu
        export JAVA_HOME='/usr/lib/jvm/java-21-openjdk-amd64'
        echo "Set JAVA_HOME to: $JAVA_HOME"
        
        # Use existing Java installation
        echo "Current Java version:"
        java -version
        
        # Check if Maven is available with JAVA_HOME set
        if command -v mvn &> /dev/null; then
            echo "Maven version:"
            mvn -version
        else
            echo "ERROR: Maven not found in Jenkins environment"
            echo "Please ensure Maven is installed on the Jenkins node"
            exit 1
        fi
        
        # Check for browser availability (Chrome, Chromium, or Firefox)
        if command -v google-chrome &> /dev/null; then
            echo "Chrome version:"
            google-chrome --version
        elif command -v chromium-browser &> /dev/null; then
            echo "Chromium version:"
            chromium-browser --version
        elif command -v firefox &> /dev/null; then
            echo "Firefox version:"
            firefox --version
        else
            echo "WARNING: No browser found for headless testing"
            echo "Tests requiring browser automation may fail"
        fi
        
        # Set up workspace
        echo "Setting up workspace..."
        mkdir -p target/surefire-reports
        
        echo "Environment setup complete"
    '''
}

def calculateAndReportResults() {
    sh '''
        echo "Calculating test results..."
        
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
            PASS_RATE=$(echo "scale=2; ($PASSED / $ACTUAL_TOTAL) * 100.0" | bc 2>/dev/null || echo "0.00")
        else
            PASS_RATE="0.00"
        fi
        
        echo "=== SANITY TEST RESULTS ==="
        echo "Environment: ''' + params.TEST_ENVIRONMENT + '''"
        echo "TEST_STAG: ''' + params.TEST_STAG_VALUE + '''"
        echo "Total tests run: $ACTUAL_TOTAL"
        echo "Passed: $PASSED"
        echo "Failed: $FAILED"
        echo "Errors: $ERRORS"
        echo "Skipped: $SKIPPED"
        echo "Pass Rate: $PASS_RATE%"
        echo "=========================="
        
        # Note: Slack notification is sent automatically by TestNGListener
        echo "Slack notification should be sent automatically by TestNGListener"
        echo "STAGING_SLACK_WEBHOOK is set to: $STAGING_SLACK_WEBHOOK"
    '''
}
