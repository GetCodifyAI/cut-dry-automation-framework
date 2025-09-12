pipeline {
    agent { label 'built-in' }
    
    parameters {
        choice(
            name: 'TEST_ENVIRONMENT',
            choices: ['uat', 'stag', 'prod'],
            description: 'Target environment for testing'
        )
        string(
            name: 'TEST_STAG_VALUE',
            defaultValue: 'unit-price',
            description: 'TEST_STAG value for STAG environment URL construction'
        )
    }
    
    options {
        timeout(time: 2, unit: 'HOURS')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    
    environment {
        MAVEN_OPTS = '-Xmx2048m -XX:MaxMetaspaceSize=512m'
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        RUN_HEADLESS = 'true'
    }
    
    stages {
        stage('Checkout Sanity Suite Branch') {
            steps {
                script {
                    echo "Checking out sanity_suite branch for sanity_test_1.xml"
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: 'sanity_suite']],
                        userRemoteConfigs: [[url: env.GIT_URL]]
                    ])
                }
            }
        }
        
        stage('Setup Environment') {
            steps {
                script {
                    echo "Setting up test environment: ${params.TEST_ENVIRONMENT}"
                    echo "TEST_STAG value: ${params.TEST_STAG_VALUE}"
                }
            }
        }
        
        stage('Run Sanity Tests') {
            steps {
                script {
                    try {
                        echo "Executing sanity_test_1.xml with environment: ${params.TEST_ENVIRONMENT}"
                        sh """
                            mvn clean test \
                            -Dsurefire.suiteXmlFiles=sanity_test_1.xml \
                            -Dtest.env=${params.TEST_ENVIRONMENT} \
                            -Drun.headless=true \
                            -Dtest.stag=${params.TEST_STAG_VALUE}
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
                    publishTestResults testResultsPattern: 'target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
    
    post {
        always {
            script {
                def testResults = [:]
                try {
                    def testResultsXml = readFile('target/surefire-reports/testng-results.xml')
                    testResults.TOTAL_TESTS = (testResultsXml =~ /total="(\d+)"/)[0][1]
                    testResults.PASSED_TESTS = (testResultsXml =~ /passed="(\d+)"/)[0][1]
                    testResults.FAILED_TESTS = (testResultsXml =~ /failed="(\d+)"/)[0][1]
                    testResults.PASS_RATE = Math.round((testResults.PASSED_TESTS.toInteger() / testResults.TOTAL_TESTS.toInteger()) * 100)
                } catch (Exception e) {
                    echo "Could not parse test results: ${e.getMessage()}"
                    testResults = [TOTAL_TESTS: 'N/A', PASSED_TESTS: 'N/A', FAILED_TESTS: 'N/A', PASS_RATE: 'N/A']
                }
                
                sendSlackNotification(currentBuild.result ?: 'SUCCESS', testResults)
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

def sendSlackNotification(String status, Map testResults) {
    def color = status == 'SUCCESS' ? 'good' : 'danger'
    def message = """
*Cut+Dry Sanity Test Results*
*Build:* ${env.BUILD_NUMBER}
*Environment:* ${params.TEST_ENVIRONMENT}
*TEST_STAG:* ${params.TEST_STAG_VALUE}
*Status:* ${status}
*Total Tests:* ${testResults.TOTAL_TESTS}
*Passed:* ${testResults.PASSED_TESTS}
*Failed:* ${testResults.FAILED_TESTS}
*Pass Rate:* ${testResults.PASS_RATE}%
"""
    
    try {
        slackSend(
            channel: '#qa-automation',
            color: color,
            message: message
        )
    } catch (Exception e) {
        echo "Failed to send Slack notification: ${e.getMessage()}"
    }
}
