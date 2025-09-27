package com.cutanddry.qa.utils.aio.testcase;

public class AioTestCaseGenerator {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java AioTestCaseGenerator <TEST_CASE_ID>");
            System.err.println("Example: java AioTestCaseGenerator DOT-TC-1836");
            System.exit(1);
        }
        
        String testCaseId = args[0];
        System.out.println("Generating test for AIO test case: " + testCaseId);
        
        System.setProperty("aio.generation.mode", "true");
        
        try {
            AioTestCase testCase = AioTestCaseRetriever.getTestCaseDetails(testCaseId);
            
            if (testCase == null) {
                System.err.println("Failed to retrieve test case details for: " + testCaseId);
                System.err.println("This may be due to network connectivity or API authentication issues");
                System.exit(1);
            }
            
            System.out.println("Retrieved test case: " + testCase.getTitle());
            System.out.println("Test steps: " + testCase.getSteps().size());
            
            String projectRoot = System.getProperty("user.dir");
            TestCodeGenerator.generateTestClass(testCase, projectRoot);
            
            String packageName = TestPatternMatcher.determineTestPackage(testCase.getFolder(), testCase.getTitle());
            String className = TestPatternMatcher.generateTestClassName(testCase.getKey(), testCase.getTitle());
            
            RegressionSuiteUpdater.addTestToSuite(className, packageName, projectRoot);
            
            System.out.println("Test generation completed successfully!");
            System.out.println("Generated class: " + className);
            System.out.println("Package: com.cutanddry.qa.tests." + packageName);
            
        } catch (Exception e) {
            System.err.println("Error generating test: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
