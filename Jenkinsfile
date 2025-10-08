pipeline {
    agent { label 'ec2-fleet' }
    
    parameters {
        booleanParam(
            name: 'CLEANUP_AFTER_EXECUTION',
            defaultValue: true,
            description: 'Clean up temporary files after execution to save disk space'
        )
    }
    
    environment {
        MAVEN_OPTS = '-Xmx2048m'
        JAVA_HOME='/usr/lib/jvm/java-21-openjdk-amd64'
        TEST_ENV = 'uat'
        RUN_HEADLESS = 'true'
        WORKSPACE_CLEANUP = "${params.CLEANUP_AFTER_EXECUTION}"
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
        // TEMPORARY: Running only bvt.xml for Slack notification testing (important-comment)
        // TODO: Revert to parallel regression execution after testing completes (important-comment)
        
        stage('BVT Test Execution') {
            agent any
            steps {
                script {
                    echo "Running BVT tests for Slack notification testing"
                    runTestSuiteWithCleanup('bvt.xml', 'BVT', 1)
                }
            }
                    post {
                        always {
                            script {
                                archiveAndCleanup('1', 'BVT Test Report')
                            }
                        }
                    }
                }
            }
        }
        
        stage('Report Results') {
            agent any
            steps {
                script {
                    // Unstash test results
                    try {
                        unstash "test-results-1"
                    } catch (Exception e) {
                        echo "Warning: Could not unstash test-results-1: ${e.getMessage()}"
                    }
                    
                    // Calculate and report results
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
        
        stage('Final Cleanup') {
            when {
                expression { params.CLEANUP_AFTER_EXECUTION }
            }
            steps {
                script {
                    performFinalCleanup()
                }
            }
        }
    }
    
    post {
        always {
            script {
                if (params.CLEANUP_AFTER_EXECUTION) {
                    performWorkspaceCleanup()
                }
            }
        }
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

def runTestSuiteWithCleanup(xmlFile, partName, jobNumber) {
    echo "Running test suite: ${xmlFile} (${partName}) with cleanup optimization"
    
    // Pre-execution cleanup
    sh '''
        # Clean up any existing test artifacts
        rm -rf target/surefire-reports* || true
        rm -rf target/screenshots* || true
        rm -rf target/test-output* || true
        
        # Clean browser cache and temp files
        rm -rf ~/.cache/google-chrome* || true
        rm -rf /tmp/chrome_* /tmp/.org.chromium.* || true
        
        # Display disk usage before test
        echo "Disk usage before test execution:"
        df -h
    '''
    
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
                -Duser.timezone=UTC \\
                -Dmaven.repo.local=\${WORKSPACE}/.m2/repository
        """
        
    } catch (Exception e) {
        echo "Test execution completed with some failures: ${e.getMessage()}"
    }
    
    // Verify test results exist and create compressed archives
    sh """
        # Verify test results exist
        ls -R target/surefire-reports || echo "Test results missing!"
        
        # Create part-specific directory for screenshots and reports
        if [ -d "target/surefire-reports" ]; then
            mkdir -p "target/surefire-reports-${partName}"
            cp -r target/surefire-reports/* "target/surefire-reports-${partName}/" || true
            
            # Compress screenshots to save space
            if [ -d "target/surefire-reports-${partName}/screenshots" ]; then
                cd "target/surefire-reports-${partName}"
                tar -czf "screenshots-${partName}-${jobNumber}.tar.gz" screenshots/ || true
                rm -rf screenshots/ || true
                cd -
            fi
            
            # Create summary report
            echo "Test Suite: ${xmlFile}" > "target/surefire-reports-${partName}/suite-info.txt"
            echo "Part Name: ${partName}" >> "target/surefire-reports-${partName}/suite-info.txt"
            echo "Job Number: ${jobNumber}" >> "target/surefire-reports-${partName}/suite-info.txt"
            echo "Execution Time: \$(date)" >> "target/surefire-reports-${partName}/suite-info.txt"
            
            # Copy test results with part name prefix for aggregation
            for file in target/surefire-reports/TEST-*.xml; do
                if [ -f "\$file" ]; then
                    cp "\$file" "target/surefire-reports/${partName}_job-${jobNumber}_\$(basename "\$file")"
                fi
            done
        fi
        
        # Display disk usage after test
        echo "Disk usage after test execution:"
        df -h
    """
}

def setupTestEnvironment() {
    sh '''
        echo "Setting up test environment..."
        
        # Clean up any existing temporary files
        rm -rf /tmp/chrome_* /tmp/.org.chromium.* || true
        
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
        
        # Display available disk space
        df -h
        
        echo "Environment setup complete"
    '''
}

def calculateAndReportResults() {
    sh '''
        echo "Calculating combined test results..."
        
        # Check if bc is available for calculations
        if ! command -v bc &> /dev/null; then
            echo "WARNING: bc (basic calculator) not found - using shell arithmetic instead"
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

def archiveAndCleanup(suiteNumber, reportName) {
    echo "Archiving artifacts and performing cleanup for suite ${suiteNumber}"
    
    // Archive artifacts with compression
    archiveArtifacts artifacts: 'target/surefire-reports*/**/*', allowEmptyArchive: true, fingerprint: true
    
    // Publish HTML reports
    publishHTML([
        allowMissing: false,
        alwaysLinkToLastBuild: true,
        keepAll: true,
        reportDir: 'target/surefire-reports',
        reportFiles: 'index.html',
        reportName: reportName
    ])
    
    // Stash test results for potential aggregation
    stash includes: 'target/surefire-reports/TEST-*.xml', name: "test-results-${suiteNumber}", allowEmpty: true
    
    // Cleanup if enabled
    if (env.WORKSPACE_CLEANUP == 'true') {
        sh """
            # Clean up large temporary files but keep archived artifacts
            rm -rf target/classes || true
            rm -rf target/test-classes || true
            rm -rf target/maven-* || true
            
            # Clean browser temp files
            rm -rf ~/.cache/google-chrome* || true
            rm -rf /tmp/chrome_* /tmp/.org.chromium.* || true
            
            # Clean Maven temp files
            rm -rf ~/.m2/repository/com/cutanddry/*/target || true
            
            echo "Cleanup completed for suite ${suiteNumber}"
            df -h
        """
    }
}

def performFinalCleanup() {
    echo "Performing final workspace cleanup..."
    sh '''
        # Clean up all temporary Maven files
        rm -rf target/maven-archiver || true
        rm -rf target/maven-status || true
        rm -rf .m2/repository/com/cutanddry || true
        
        # Clean up browser and system temp files
        rm -rf ~/.cache/* || true
        rm -rf /tmp/chrome_* /tmp/.org.chromium.* || true
        rm -rf /var/tmp/chrome_* || true
        
        # Clean up old log files
        find . -name "*.log" -type f -mtime +1 -delete || true
        
        # Display final disk usage
        echo "Final disk usage after cleanup:"
        df -h
        
        # Display workspace size
        echo "Workspace size:"
        du -sh . || true
    '''
}

def performWorkspaceCleanup() {
    echo "Performing post-build workspace cleanup..."
    sh '''
        # Keep only essential files and archived artifacts
        find . -type f -name "*.class" -delete || true
        find . -type f -name "*.jar" -not -path "./target/surefire-reports*" -delete || true
        
        # Clean up Maven local repository
        rm -rf .m2 || true
        
        echo "Post-build cleanup completed"
        df -h
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
    
    // Slack notifications are handled by the Java TestNGListener class
    // which automatically sends detailed test results to Slack using the
    // SLACK_WEBHOOK environment variable configured in Jenkins.
    // See: src/main/java/com/cutanddry/qa/utils/TestNGListener.java
    echo "Slack notifications will be sent by TestNG listener after test completion"
}
