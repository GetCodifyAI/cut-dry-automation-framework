# Jenkins Pipeline Plan for Sanity Test Execution

## Overview
Create a Jenkins job named "Cut-Dry-Distributor-Stag-Sanity" that executes `sanity_test_1.xml` from the `sanity_suite` branch with configurable environment and TEST_STAG parameters.

## Requirements Met
1. ✅ Execute `sanity_test_1.xml` from `sanity_suite` branch
2. ✅ Add parameter to select environment (uat, stag, prod)
3. ✅ Make TEST_STAG configurable via parameter (default: "unit-price")

## Implementation Plan

### 1. Jenkinsfile Configuration
- **Pipeline Name**: `Cut-Dry-Distributor-Stag-Sanity`
- **Location**: `/Jenkinsfile` (root directory)
- **Parameters**:
  - `TEST_ENVIRONMENT`: Choice parameter (uat, stag, prod)
  - `TEST_STAG_VALUE`: String parameter (default: "unit-price")
- **Environment Variables**:
  - `MAVEN_OPTS`: `-Xmx2048m -XX:MaxMetaspaceSize=512m`
  - `JAVA_HOME`: `/usr/lib/jvm/java-21-openjdk-amd64`
  - `RUN_HEADLESS`: `true`

### 2. Pipeline Stages
1. **Checkout Sanity Suite Branch**: Switch to `sanity_suite` branch to access `sanity_test_1.xml`
2. **Setup Environment**: Configure test environment variables
3. **Run Sanity Tests**: Execute Maven test with parameters

### 3. Maven Execution Command
```bash
mvn clean test \
-Dsurefire.suiteXmlFiles=sanity_test_1.xml \
-Dtest.env=${TEST_ENVIRONMENT} \
-Drun.headless=true \
-Dtest.stag=${TEST_STAG_VALUE}
```

### 4. Constants.java Modification
- **File**: `src/main/java/com/cutanddry/qa/common/Constants.java`
- **Change**: Modify `TEST_STAG` to accept system property
- **Before**: `public static final String TEST_STAG ="unit-price";`
- **After**: `public static final String TEST_STAG = System.getProperty("test.stag", "unit-price");`

### 5. Test Results & Notifications
- Archive surefire reports
- Publish test results
- Send Slack notifications with test summary
- Handle test failures gracefully (UNSTABLE status)

### 6. Post-Build Actions
- Archive artifacts: `target/surefire-reports/**/*`
- Publish test results: `target/surefire-reports/TEST-*.xml`
- Slack notification with build status and test metrics

## Compliance with Playbook
- ✅ Follows Jenkins Pipeline Management Playbook structure
- ✅ Uses parameterized builds as outlined in section 1.4
- ✅ Implements environment configuration per section 1.2
- ✅ Includes proper error handling and notifications
- ✅ Uses Maven execution pattern from existing CI setup
- ✅ Follows timeout and build options best practices

## Testing Strategy
1. Validate pipeline syntax
2. Test with different environment parameters
3. Verify TEST_STAG parameter is properly passed to tests
4. Confirm test results are properly archived and reported
