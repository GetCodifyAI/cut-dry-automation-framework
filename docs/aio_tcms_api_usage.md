# AIO TCMS API Usage Guide

## Overview
This document describes how to retrieve test case details from the AIO Tests TCMS API for automation implementation.

## API Endpoint

**Base URL**: `https://tcms.aiojiraapps.com/aio-tcms/api/v1`

**Get Test Case Detail**:
```
GET /project/{projectKey}/testcase/{testCaseKey}/detail
```

### Parameters
- `projectKey` (required): Jira project key (e.g., "DOT")
- `testCaseKey` (required): Test case key (e.g., "DOT-TC-838")
- `needDataInRTF` (optional): Boolean - If true, returns Rich Text Format metadata
- `needAttachments` (optional): Boolean - If true, returns attachments
- `version` (optional): Integer - Case version number (defaults to latest)

### Authentication
Uses `AioAuth` token in Authorization header. Token is already configured in `AioAPIHelper.java`.

## Implementation

### 1. AioAPIHelper Method
The `getTestCaseDetail` method has been added to `AioAPIHelper.java`:

```java
public static Response getTestCaseDetail(String projectKey, String caseKey) {
    Response response = doGet(GET_TEST_CASE_DETAIL, projectKey, caseKey);
    return response;
}
```

### 2. Test Case Retrieval Utility
Use the `TestCaseRetrieval` utility class to retrieve test case details:

```bash
cd ~/repos/cut-dry-automation-framework
mvn compile exec:java -Dexec.mainClass="com.cutanddry.qa.utils.TestCaseRetrieval"
```

### 3. Response Structure
The API returns a JSON response with the following key fields:

```json
{
  "key": "DOT-TC-838",
  "title": "Test case title",
  "description": "Test case description",
  "precondition": "Prerequisites for the test",
  "folder": {
    "name": "Billing"
  },
  "steps": [
    {
      "step": "Step description",
      "data": "Test data",
      "expectedResult": "Expected outcome",
      "stepType": "TEXT"
    }
  ]
}
```

## Usage Example

### Retrieve Test Case Details
```java
String projectKey = "DOT";
String testCaseKey = "DOT-TC-838";

Response response = AioAPIHelper.getTestCaseDetail(projectKey, testCaseKey);
System.out.println("Status Code: " + response.getStatusCode());
response.prettyPrint();
```

### Extract Test Steps
```java
Response response = AioAPIHelper.getTestCaseDetail("DOT", "DOT-TC-838");
List<Map<String, Object>> steps = response.jsonPath().getList("steps");

for (Map<String, Object> step : steps) {
    String stepDescription = (String) step.get("step");
    String expectedResult = (String) step.get("expectedResult");
    System.out.println("Step: " + stepDescription);
    System.out.println("Expected: " + expectedResult);
}
```

## Best Practices

1. **Always retrieve test case details first** before implementing automation
2. **Follow exact steps from API response** to ensure accurate test implementation
3. **Verify portal type** (Operator vs Distributor) from test case data
4. **Use existing page objects and functions** whenever possible
5. **Document any new XPaths** discovered during manual verification

## Common Test Case Patterns

### Distributor Portal Login
```java
Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
```

### Navigate to Settings/Billing
```java
Dashboard.navigateToSettings();
softAssert.assertTrue(Settings.isBillingSettingsTextDisplayed(), 
    "Failed to navigate to billing settings");
```

## Troubleshooting

### 404 Error
If you receive a 404 error, ensure:
- The endpoint includes `/detail` at the end
- The project key and test case key are correct
- The test case exists in AIO TCMS

### Authentication Error
If you receive a 401 error:
- Verify the AioAuth token in `AioAPIHelper.java` is valid
- Check that the token has not expired

## References
- AIO TCMS API Documentation: https://tcms.aiojiraapps.com/aio-tcms/aiotcms-static/api-docs/#/
- Framework Playbook: See `tc_automation_playbook` for complete workflow
