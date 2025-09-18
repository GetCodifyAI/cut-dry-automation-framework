# Cut+Dry Test Automation Playbook - AIO TCMS Integration

## Overview
This playbook documents the complete process for generating automated test cases for the Cut+Dry automation framework based on AIO Test Case Management System (TCMS) specifications. It covers the end-to-end workflow from retrieving test cases via AIO API to implementing TestNG tests and creating pull requests.

## Prerequisites

### Environment Setup
- **Repository**: `GetCodifyAI/cut-dry-automation-framework`
- **Java Version**: Java 22 (managed via Maven)
- **Build Tool**: Maven 3.x
- **Testing Framework**: TestNG
- **Browser**: Chrome (headless mode supported)
- **Git Authentication**: Configured for GitHub access

### Required Credentials
- **AIO TCMS API**: API token for test case retrieval
  - Generate from: AIO Tests → My Settings → API Token
  - Format: `AioAuth <token>`
- **SUT Access**: Environment-specific credentials for testing
  - Provided via environment URL parameter
  - Default UAT: `https://supplier-uat.staging.cutanddry.com`
  - Username: `415-505-5531`
  - Password: `NovaN@123`

### Framework Knowledge
- **Architecture**: Page Object Model with 3-tier structure
- **Base Classes**: `TestBase`, `KeywordBase`
- **Page Objects**: Located in `src/main/java/com/cutanddry/qa/pages/`
- **Functions**: Business logic layer in `src/main/java/com/cutanddry/qa/functions/`
- **Test Data**: Centralized in `src/main/java/com/cutanddry/qa/data/testdata/`

## Step-by-Step Process

### Phase 1: AIO API Integration and Test Case Retrieval

#### 1.1 Setup AIO API Client
```java
// Create AIO API utility class
public class AIOTestCaseClient {
    private static final String BASE_URL = "https://tcms.aiojiraapps.com/aio-tcms";
    private final String apiToken;
    private final String projectId;
    
    public AIOTestCaseClient(String apiToken, String projectId) {
        this.apiToken = apiToken;
        this.projectId = projectId;
    }
    
    public TestCaseDetails getTestCaseDetails(String testCaseId) {
        // Implementation for API call
    }
}
```

#### 1.2 Retrieve Test Case from AIO API
```bash
# API Endpoint Structure
GET /api/v1/project/{jiraProjectId}/testcase/{testCaseId}/detail

# Headers Required
Authorization: AioAuth <token>
Content-Type: application/json

# Example API Call
curl -X GET "https://tcms.aiojiraapps.com/aio-tcms/api/v1/project/AT/testcase/AT-TC-34/detail" \
  -H "Authorization: AioAuth YOUR_API_TOKEN"
```

**Key Response Fields to Extract:**
- `key`: Test Case ID (e.g., "AT-TC-34")
- `title`: Test case summary
- `description`: Detailed test description
- `precondition`: Test preconditions
- `steps[]`: Array containing:
  - `step`: Step description
  - `data`: Test data
  - `expectedResult`: Expected outcome
- `folder.name`: Test category/module
- `priority.name`: Test priority level
- `status.name`: Test case status

#### 1.3 Parse and Map Test Case Data
```java
public class TestCaseMapper {
    public AutomationTestSpec mapToAutomationSpec(TestCaseDetails aioTestCase) {
        return AutomationTestSpec.builder()
            .testId(aioTestCase.getKey())
            .title(aioTestCase.getTitle())
            .description(aioTestCase.getDescription())
            .preconditions(aioTestCase.getPrecondition())
            .steps(mapSteps(aioTestCase.getSteps()))
            .category(determineTestCategory(aioTestCase.getFolder().getName()))
            .build();
    }
    
    private String determineTestCategory(String folderName) {
        // Map AIO folder names to framework test packages
        Map<String, String> categoryMapping = Map.of(
            "Order Management", "order_guide",
            "Customer Management", "customers",
            "Payment Processing", "pay",
            "Catalog", "catalog"
        );
        return categoryMapping.getOrDefault(folderName, "general");
    }
}
```

### Phase 2: Comprehensive Method Discovery and Framework Analysis

#### 2.1 Search for Existing Methods Before Implementation
**CRITICAL FOR QA ENGINEERS**: Always search for existing methods before creating new ones to prevent duplication and maintain framework consistency.

```bash
cd ~/repos/cut-dry-automation-framework

# Step 1: Search for existing page object methods by functionality
# Purpose: Find existing UI interaction methods to avoid recreating them
# Expected Output: List of method signatures with file paths and line numbers
grep -r "public.*click.*" src/main/java/com/cutanddry/qa/pages/ | grep -i "download\|report\|button"
# Example output: DashboardPage.java:245:public void clickDownloadReportButton()

grep -r "public.*is.*Display" src/main/java/com/cutanddry/qa/pages/ | grep -i "download\|report"
# Purpose: Find existing validation methods for UI elements
# Example output: DashboardPage.java:250:public boolean isDownloadReportButtonDisplayed()

grep -r "public.*wait.*" src/main/java/com/cutanddry/qa/pages/ | grep -i "download\|report"
# Purpose: Find existing wait methods for dynamic elements
# Example output: DashboardPage.java:255:public void waitForDownloadReportModal()

# Step 2: Search for existing function layer methods
# Purpose: Find business logic methods that wrap page object interactions
grep -r "public static.*" src/main/java/com/cutanddry/qa/functions/ | grep -i "download\|report\|order"
# Example output: Dashboard.java:120:public static void downloadFirstTimeOrdersReport()

grep -r "public static.*navigate" src/main/java/com/cutanddry/qa/functions/
# Purpose: Find navigation methods across all function classes
# Example output: Dashboard.java:45:public static void navigateToReports()

grep -r "public static.*click" src/main/java/com/cutanddry/qa/functions/
# Purpose: Find click action methods in function layer
# Example output: Customer.java:89:public static void clickOrderGuide()

# Step 3: Search for existing test data constants
# Purpose: Find reusable test data to maintain consistency across tests
grep -r "public static.*String" src/main/java/com/cutanddry/qa/data/testdata/ | grep -i "customer\|order\|report"
# Example output: CustomerData.java:15:public static final String CUSTOMER_CODE3 = "16579";

find src/main/java/com/cutanddry/qa/data/testdata/ -name "*.java" -exec grep -l "CUSTOMER\|ORDER\|REPORT" {} \;
# Purpose: List all data files containing relevant constants
# Example output: CustomerData.java, OrderData.java, ReportData.java

# Step 4: Search for similar test patterns
# Purpose: Find existing tests with similar workflows to reuse patterns
find src/test/java -name "*Test.java" -exec grep -l "download\|report\|dashboard" {} \;
# Example output: VerifyDownloadReportTest.java, DashboardFunctionalityTest.java

grep -r "@Test.*groups.*DOT-TC" src/test/java/ | head -10
# Purpose: Find existing test case group patterns for consistency
# Example output: @Test(groups = "DOT-TC-1234")

# Step 5: Search for existing WebElement definitions
# Purpose: Find existing UI element locators to avoid duplicate selectors
grep -r "By\." src/main/java/com/cutanddry/qa/pages/ | grep -i "download\|report\|button"
# Example output: By btn_downloadReport = By.xpath("//button[contains(text(),'Download Report')]");

grep -r "@FindBy" src/main/java/com/cutanddry/qa/pages/ | grep -i "download\|report"
# Purpose: Find existing @FindBy annotations for UI elements
# Example output: @FindBy(xpath = "//button[@id='download-btn']")
```

**QA Engineer Tips:**
- **No Results Found**: If searches return no results, you may need to create new methods
- **Multiple Results**: Review each result to find the most appropriate existing method
- **Partial Matches**: Look for methods with similar functionality that can be extended
- **Naming Patterns**: Follow existing naming conventions when creating new methods

#### 2.2 Analyze Search Results and Map Required Methods
```bash
# Create a method inventory before implementation
echo "=== EXISTING METHOD INVENTORY ===" > method_inventory.txt

# Document existing page object methods
echo "Page Object Methods:" >> method_inventory.txt
grep -r "public.*click" src/main/java/com/cutanddry/qa/pages/DashboardPage.java >> method_inventory.txt
grep -r "public.*is.*Display" src/main/java/com/cutanddry/qa/pages/DashboardPage.java >> method_inventory.txt

# Document existing function methods
echo "Function Layer Methods:" >> method_inventory.txt
grep -r "public static" src/main/java/com/cutanddry/qa/functions/Dashboard.java >> method_inventory.txt
grep -r "public static" src/main/java/com/cutanddry/qa/functions/Customer.java >> method_inventory.txt

# Document existing test data
echo "Test Data Constants:" >> method_inventory.txt
grep -r "public static.*String" src/main/java/com/cutanddry/qa/data/testdata/ >> method_inventory.txt
```

#### 2.3 Verify Method Availability Using LSP Commands
**For QA Engineers**: Use Language Server Protocol (LSP) commands to understand method relationships and usage patterns.

```bash
# Purpose: Navigate to method definition to understand implementation
# When to use: When you find a method name but need to see its full implementation
go_to_definition path="/absolute/path/to/DashboardPage.java" line="100" symbol="downloadReport"
# Expected outcome: Opens the method definition showing parameters, return type, and implementation

# Purpose: Find all places where a method is used across the codebase
# When to use: To understand how existing methods are called and what parameters they expect
go_to_references path="/absolute/path/to/DashboardPage.java" line="100" symbol="downloadReport"
# Expected outcome: List of all files and line numbers where this method is referenced

# Purpose: Get method signature, parameters, and documentation
# When to use: To quickly understand method usage without opening the file
hover_symbol path="/absolute/path/to/DashboardPage.java" line="100" symbol="downloadReport"
# Expected outcome: Tooltip showing method signature: public void downloadReport(String reportType)
```

**QA Engineer Workflow:**
1. **Search First**: Use grep commands to find potential methods
2. **Verify with LSP**: Use go_to_definition to confirm method exists and understand its purpose
3. **Check Usage**: Use go_to_references to see how the method is used in existing tests
4. **Understand Parameters**: Use hover_symbol to see required parameters and return types

#### 2.4 Analyze Existing Framework Patterns
```bash
# Review test structure patterns
find src/test/java -name "*Test.java" | head -5
grep -r "@Test(groups" src/test/java/ | head -3

# Identify available page objects and functions
find src/main/java/com/cutanddry/qa/pages -name "*.java"
find src/main/java/com/cutanddry/qa/functions -name "*.java"

# Check for existing similar workflows
grep -r "loginAsDistributor\|navigateToCustomers\|clickOnOrderGuide" src/test/java/
```

#### 2.5 Generate Test Class Structure (Only After Method Discovery)
```java
// Template generator based on AIO test case
public class TestClassGenerator {
    public String generateTestClass(AutomationTestSpec spec) {
        StringBuilder classContent = new StringBuilder();
        
        // Package declaration based on category
        classContent.append("package com.cutanddry.qa.tests.").append(spec.getCategory()).append(";\n\n");
        
        // Standard imports
        classContent.append(generateImports());
        
        // Class declaration
        String className = generateClassName(spec.getTitle());
        classContent.append("public class ").append(className).append(" extends TestBase {\n");
        
        // Test method
        classContent.append(generateTestMethod(spec));
        
        classContent.append("}\n");
        return classContent.toString();
    }
    
    private String generateTestMethod(AutomationTestSpec spec) {
        StringBuilder method = new StringBuilder();
        method.append("    @Test(groups = \"").append(spec.getTestId()).append("\")\n");
        method.append("    public void ").append(generateMethodName(spec.getTitle())).append("() throws InterruptedException {\n");
        method.append("        SoftAssert softAssert = new SoftAssert();\n\n");
        
        // Generate step implementations
        for (TestStep step : spec.getSteps()) {
            method.append(generateStepImplementation(step));
        }
        
        method.append("        softAssert.assertAll();\n");
        method.append("    }\n");
        return method.toString();
    }
}
```

#### 2.6 Map AIO Steps to Framework Actions (Using Discovered Methods)
```java
public class StepMapper {
    private final Map<String, String> actionMappings = Map.of(
        "login", "Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());",
        "navigate to customers", "Dashboard.navigateToCustomers();",
        "search customer", "Customer.searchCustomerByCode(customerId);",
        "access order guide", "Customer.clickOnOrderGuide(customerId);",
        "add items", "Customer.increaseFirstRowQtyByOne();",
        "checkout", "Customer.checkoutItems();",
        "submit order", "Customer.submitOrder();"
    );
    
    public String mapStepToAction(String stepDescription) {
        String normalizedStep = stepDescription.toLowerCase();
        return actionMappings.entrySet().stream()
            .filter(entry -> normalizedStep.contains(entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse("// TODO: Implement step - " + stepDescription);
    }
}
```

### Phase 3: Automated Test Implementation

#### 3.1 Create Test Class File
```java
// Generated test class example based on AIO-TC-34
package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyDistributorOrderSubmissionFromAIOTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE3;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "AIO-TC-34")
    public void VerifyDistributorOrderSubmissionFromAIO() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        // Step 1: Login as distributor (from AIO step 1)
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Login error - user not navigated to dashboard");
        
        // Step 2: Navigate to customers (from AIO step 2)
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Customers section not displayed");
        
        // Additional steps generated from AIO test case...
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
```

#### 3.2 Update Test Suite Configuration
```xml
<!-- Add to appropriate regression suite -->
<class name="com.cutanddry.qa.tests.order_guide.VerifyDistributorOrderSubmissionFromAIOTest" />
```

### Phase 4: Compilation and Testing

#### 4.1 Compile and Validate
```bash
cd ~/repos/cut-dry-automation-framework
mvn clean compile -DskipTests

# Validate generated test
mvn test -Dtest=VerifyDistributorOrderSubmissionFromAIOTest -Drun.headless=true -Dtest.env=uat
```

#### 4.2 Environment-Specific Testing
```bash
# Test against provided environment URL
mvn test -Dtest=VerifyDistributorOrderSubmissionFromAIOTest \
  -Drun.headless=true \
  -Dtest.env=custom \
  -Dbase.url=${ENVIRONMENT_URL}
```

### Phase 5: Git Operations and PR Creation

#### 5.1 Create Feature Branch
```bash
# Generate timestamp for unique branch name
TIMESTAMP=$(date +%s)
TEST_CASE_ID="AIO-TC-34"  # From API response
git checkout -b devin/${TIMESTAMP}-${TEST_CASE_ID,,}-automation
```

#### 5.2 Commit Generated Test
```bash
# Stage generated files
git add src/test/java/com/cutanddry/qa/tests/order_guide/VerifyDistributorOrderSubmissionFromAIOTest.java
git add regression1.xml

# Commit with AIO reference
git commit -m "[Devin] Add ${TEST_CASE_ID}: Automated test generation from AIO TCMS

- Generated from AIO Test Case Management System
- Implements workflow: ${TEST_TITLE}
- Mapped AIO steps to framework actions
- Added to regression suite for CI execution
- Environment: ${ENVIRONMENT_URL}"
```

#### 5.3 Create Pull Request
```bash
git push --set-upstream origin devin/${TIMESTAMP}-${TEST_CASE_ID,,}-automation

# Create PR with AIO context
git_create_pr repo="GetCodifyAI/cut-dry-automation-framework" \
  title="[Devin] Add ${TEST_CASE_ID}: Automated test from AIO TCMS" \
  head_branch="devin/${TIMESTAMP}-${TEST_CASE_ID,,}-automation" \
  base_branch="release" \
  exec_dir="/home/ubuntu/repos/cut-dry-automation-framework"
```

## Usage Instructions

### Command Line Interface
```bash
# Execute the automation process
./generate-test-from-aio.sh \
  --aio-token "YOUR_AIO_API_TOKEN" \
  --project-id "AT" \
  --test-case-id "AT-TC-34" \
  --environment-url "https://supplier-uat.staging.cutanddry.com" \
  --credentials "415-505-5531:NovaN@123"
```

### Programmatic Usage
```java
public class AIOTestAutomationRunner {
    public static void main(String[] args) {
        AIOTestCaseClient client = new AIOTestCaseClient(apiToken, projectId);
        TestCaseDetails testCase = client.getTestCaseDetails(testCaseId);
        
        TestClassGenerator generator = new TestClassGenerator();
        String testClass = generator.generateTestClass(
            TestCaseMapper.mapToAutomationSpec(testCase)
        );
        
        FileUtils.writeTestClass(testClass, determineFilePath(testCase));
        GitOperations.createBranchAndCommit(testCase.getKey());
    }
}
```

## AIO API Integration Details

### Authentication Setup
```java
public class AIOAuthenticator {
    private final String apiToken;
    
    public HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "AioAuth " + apiToken);
        headers.set("Content-Type", "application/json");
        return headers;
    }
}
```

### Error Handling
```java
public class AIOApiException extends Exception {
    public static void handleApiResponse(ResponseEntity<String> response) {
        switch (response.getStatusCode().value()) {
            case 401:
                throw new AIOApiException("Invalid or missing API Token");
            case 404:
                throw new AIOApiException("Test case not found");
            case 400:
                throw new AIOApiException("Malformed request");
        }
    }
}
```

### Data Models
```java
@Data
public class TestCaseDetails {
    private String key;
    private String title;
    private String description;
    private String precondition;
    private List<TestStep> steps;
    private FolderDetails folder;
    private Priority priority;
    private Status status;
}

@Data
public class TestStep {
    private String step;
    private String data;
    private String expectedResult;
    private String stepType;
}
```

## Best Practices

### AIO Integration
- **API Rate Limits**: Implement exponential backoff for API calls
- **Token Security**: Store API tokens in environment variables
- **Error Recovery**: Handle network failures and API timeouts gracefully
- **Data Validation**: Validate AIO response structure before processing

### Test Generation
- **Pre-Implementation Search**: Execute comprehensive method discovery before any code generation
- **Step Mapping**: Maintain comprehensive mapping between AIO steps and framework actions
- **Dynamic Assertions**: Generate appropriate assertions based on expected results
- **Test Data**: Extract and parameterize test data from AIO step data fields
- **Naming Conventions**: Generate consistent class and method names from AIO titles
- **Existing Method Reuse**: Prioritize using discovered methods over creating new implementations

### Framework Integration
- **Method Discovery First**: Always search for existing methods before creating new implementations
- **Package Structure**: Map AIO folder hierarchy to test package organization
- **Regression Suites**: Automatically add generated tests to appropriate suites
- **Dependencies**: Ensure generated tests use existing page objects and functions
- **Lifecycle Management**: Include proper setup and teardown methods
- **Code Reuse**: Leverage existing page object methods and function layer implementations

## Validation Checklist

### Pre-Implementation
- [ ] AIO API token configured and validated
- [ ] Project ID and test case ID verified in AIO
- [ ] Environment URL accessible and credentials valid
- [ ] Framework repository cloned and dependencies resolved

### Method Discovery Phase (MANDATORY BEFORE ANY IMPLEMENTATION)
- [ ] Searched for existing page object methods using grep and find commands
- [ ] Verified function layer method availability across all function classes
- [ ] Checked test data constants for reusable values
- [ ] Used LSP commands to understand method signatures and usage
- [ ] Documented method inventory before implementation
- [ ] Identified similar test patterns for workflow reuse
- [ ] Verified no duplicate functionality exists in the codebase
- [ ] Confirmed method naming follows existing framework conventions

### API Integration
- [ ] Test case retrieved successfully from AIO API
- [ ] All required fields present in API response
- [ ] Step data parsed and mapped correctly
- [ ] Error handling implemented for API failures

### Test Generation
- [ ] Test class generated with proper structure
- [ ] TestNG annotations and lifecycle methods included
- [ ] Framework patterns followed (SoftAssert, page objects)
- [ ] Test steps mapped to appropriate framework actions
- [ ] Assertions generated from expected results

### Validation and Deployment
- [ ] Generated test compiles without errors
- [ ] Test executes successfully in target environment
- [ ] All assertions pass with valid test data
- [ ] Git branch created with descriptive naming
- [ ] Pull request created with AIO context

## Common Pitfalls

### AIO API Issues
- **Token Expiration**: API tokens may expire, implement refresh mechanism
- **Project Access**: Ensure API token has access to specified project
- **Version Handling**: Handle test case versions appropriately
- **Rich Text Content**: Process HTML content in step descriptions

### Test Generation Issues
- **Insufficient Method Discovery**: Not searching comprehensively before implementation leads to duplication
- **Step Mapping Failures**: Unmapped steps require manual implementation
- **Data Extraction**: Complex test data may need custom parsing
- **Framework Compatibility**: Generated code must match framework patterns
- **Import Resolution**: Ensure all required imports are included
- **Method Duplication**: Creating new methods when existing ones are available

### Environment Issues
- **URL Validation**: Verify environment URLs are accessible
- **Credential Management**: Secure handling of environment credentials
- **Network Connectivity**: Handle network issues during API calls
- **Browser Compatibility**: Ensure headless mode works in CI environment

## Success Metrics

### Automation Coverage
- Percentage of AIO test cases successfully automated
- Reduction in manual test case implementation time
- Consistency of generated test code quality

### Integration Reliability
- API call success rate and error handling effectiveness
- Generated test execution success rate
- CI/CD pipeline integration stability

### Development Efficiency
- Time from AIO test case to deployed automation
- Reduction in manual code review requirements
- Maintainability of generated test code

## QA Engineer Guidelines

### Method Discovery for QA Engineers
QA engineers implementing test automation should follow this systematic approach:

1. **Pre-Implementation Research**:
   ```bash
   # Search for existing test patterns
   grep -r "loginAsDistributor\|navigateToCustomers" src/test/java/
   
   # Find similar test workflows
   find src/test/java -name "*Test.java" -exec grep -l "order\|customer\|dashboard" {} \;
   
   # Check existing page object methods
   grep -r "public.*click\|public.*is.*Display" src/main/java/com/cutanddry/qa/pages/
   ```

2. **Validation Scenarios**: Understand existing functionality for testing edge cases and validation workflows
3. **Test Data Reuse**: Leverage existing test data constants to maintain consistency
4. **Framework Patterns**: Follow established patterns for TestNG lifecycle, SoftAssert usage, and error handling

### Target Audiences
This playbook serves multiple stakeholders:
- **QA Engineers**: Primary implementers of automated test cases
- **Product Managers**: Understanding test coverage and validation workflows
- **Customer Success Teams**: Validating customer-facing functionality
- **Sales Teams**: Demonstrating platform reliability through automated testing
- **Training Teams**: Onboarding new team members to testing practices
- **New Team Members**: Learning platform functionality and testing approaches

## Conclusion

This updated playbook provides a comprehensive framework for automated test generation from AIO Test Case Management System with mandatory method discovery to prevent code duplication. By integrating AIO API capabilities with the existing Cut+Dry automation framework and emphasizing systematic method search, teams can significantly reduce the time and effort required to implement automated tests while maintaining high quality and consistency standards.

The process transforms manual test case analysis into an automated workflow that retrieves test specifications, discovers existing methods, generates appropriate test code, and deploys the results through standard CI/CD processes. This approach ensures that test automation keeps pace with test case creation, maintains alignment between manual and automated testing efforts, and prevents unnecessary code duplication through comprehensive method discovery.
