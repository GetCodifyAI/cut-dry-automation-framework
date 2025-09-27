package com.cutanddry.qa.utils.aio.testcase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestCodeGenerator {
    
    public static void generateTestClass(AioTestCase testCase, String outputDirectory) {
        if (testCase == null) {
            System.err.println("Cannot generate test class: testCase is null");
            return;
        }
        
        String packageName = TestPatternMatcher.determineTestPackage(testCase.getFolder(), testCase.getTitle());
        String className = TestPatternMatcher.generateTestClassName(testCase.getKey(), testCase.getTitle());
        String methodName = TestPatternMatcher.generateTestMethodName(testCase.getTitle());
        
        StringBuilder testClass = new StringBuilder();
        
        testClass.append("package com.cutanddry.qa.tests.").append(packageName).append(";\n\n");
        
        testClass.append("import com.cutanddry.qa.base.TestBase;\n");
        testClass.append("import com.cutanddry.qa.data.models.User;\n");
        testClass.append("import com.cutanddry.qa.data.testdata.CustomerData;\n");
        testClass.append("import com.cutanddry.qa.functions.*;\n");
        testClass.append("import com.cutanddry.qa.utils.JsonUtil;\n");
        testClass.append("import org.testng.ITestResult;\n");
        testClass.append("import org.testng.annotations.*;\n");
        testClass.append("import org.testng.asserts.SoftAssert;\n\n");
        
        testClass.append("public class ").append(className).append(" extends TestBase {\n");
        testClass.append("    static User user;\n");
        testClass.append("    String customerId = CustomerData.CUSTOMER_CODE3;\n\n");
        
        testClass.append("    @BeforeMethod\n");
        testClass.append("    public void setUp(){\n");
        testClass.append("        initialization();\n");
        testClass.append("        user = JsonUtil.readUserLogin();\n");
        testClass.append("    }\n\n");
        
        testClass.append("    @Test(groups = \"").append(testCase.getKey()).append("\")\n");
        testClass.append("    public void ").append(methodName).append("() throws InterruptedException {\n");
        testClass.append("        SoftAssert softAssert = new SoftAssert();\n\n");
        
        generateTestSteps(testClass, testCase.getSteps());
        
        testClass.append("        softAssert.assertAll();\n");
        testClass.append("    }\n\n");
        
        testClass.append("    @AfterMethod\n");
        testClass.append("    public void tearDown(ITestResult result) {\n");
        testClass.append("        takeScreenshotOnFailure(result);\n");
        testClass.append("        closeAllBrowsers();\n");
        testClass.append("    }\n");
        testClass.append("}\n");
        
        writeTestClassToFile(testClass.toString(), outputDirectory, packageName, className);
    }
    
    private static void generateTestSteps(StringBuilder testClass, List<AioTestCase.TestStep> steps) {
        boolean hasLogin = false;
        
        for (AioTestCase.TestStep step : steps) {
            String stepText = step.getStep().toLowerCase();
            String expectedResult = step.getExpectedResult();
            
            testClass.append("        // Step ").append(step.getStepNumber()).append(": ").append(step.getStep()).append("\n");
            
            if (stepText.contains("login") && stepText.contains("distributor") && !hasLogin) {
                testClass.append("        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());\n");
                testClass.append("        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), \"Login error - user not navigated to dashboard\");\n\n");
                hasLogin = true;
            } else if (stepText.contains("navigate") && stepText.contains("customer")) {
                testClass.append("        Dashboard.navigateToCustomers();\n");
                testClass.append("        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), \"Customers section not displayed\");\n\n");
            } else if (stepText.contains("search") && stepText.contains("customer")) {
                testClass.append("        Customer.searchCustomerByCode(customerId);\n");
                testClass.append("        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), \"Customer search result not displayed for code: \" + customerId);\n\n");
            } else if (stepText.contains("order") && stepText.contains("guide")) {
                testClass.append("        Customer.clickOnOrderGuide(customerId);\n\n");
            } else if (stepText.contains("add") && stepText.contains("item")) {
                testClass.append("        Customer.increaseFirstRowQtyByOne();\n");
                testClass.append("        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, \"Product quantity not increased\");\n\n");
            } else if (stepText.contains("checkout")) {
                testClass.append("        Customer.checkoutItems();\n");
                testClass.append("        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), \"Review order page not displayed\");\n\n");
            } else if (stepText.contains("submit") && stepText.contains("order")) {
                testClass.append("        Customer.submitOrder();\n\n");
            } else if (stepText.contains("navigate") && stepText.contains("catalog")) {
                testClass.append("        Dashboard.navigateToCatalog();\n");
                testClass.append("        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(), \"Navigation to catalog failed\");\n\n");
            }
            
            if (expectedResult != null && !expectedResult.trim().isEmpty()) {
                if (expectedResult.toLowerCase().contains("success") || expectedResult.toLowerCase().contains("thank")) {
                    testClass.append("        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), \"").append(expectedResult).append("\");\n\n");
                } else {
                    testClass.append("        // Expected: ").append(expectedResult).append("\n\n");
                }
            }
        }
    }
    
    private static void writeTestClassToFile(String testClassContent, String outputDirectory, String packageName, String className) {
        try {
            String packagePath = packageName.replace(".", "/");
            File packageDir = new File(outputDirectory + "/src/test/java/com/cutanddry/qa/tests/" + packagePath);
            packageDir.mkdirs();
            
            File testFile = new File(packageDir, className + ".java");
            
            try (FileWriter writer = new FileWriter(testFile)) {
                writer.write(testClassContent);
            }
            
            System.out.println("Generated test class: " + testFile.getAbsolutePath());
            
        } catch (IOException e) {
            System.err.println("Error writing test class file: " + e.getMessage());
        }
    }
}
