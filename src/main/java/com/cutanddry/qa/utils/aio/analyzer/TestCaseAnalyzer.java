package com.cutanddry.qa.utils.aio.analyzer;

import com.cutanddry.qa.utils.aio.client.AIOTestCaseClient;
import com.cutanddry.qa.utils.aio.models.TestCaseDetails;
import java.util.List;

public class TestCaseAnalyzer {
    
    public static void main(String[] args) {
        String testCaseId = "DOT-TC-1644";
        
        AIOTestCaseClient client = new AIOTestCaseClient();
        TestCaseDetails testCase = client.getTestCaseDetails(testCaseId);
        
        WorkflowAnalyzer analyzer = new WorkflowAnalyzer();
        
        WorkflowAnalyzer.LoginPattern loginPattern = analyzer.analyzeLoginPattern(testCase.getSteps());
        WorkflowAnalyzer.TestType testType = analyzer.detectTestType(testCase);
        
        System.out.println("=== AIO Test Case Analysis for " + testCaseId + " ===");
        System.out.println("Title: " + testCase.getTitle());
        System.out.println("Description: " + testCase.getDescription());
        System.out.println("Login Pattern: " + loginPattern);
        System.out.println("Test Type: " + testType);
        System.out.println("\n=== Test Steps Analysis ===");
        
        for (int i = 0; i < testCase.getSteps().size(); i++) {
            var step = testCase.getSteps().get(i);
            boolean isOperatorStep = analyzer.isOperatorPortalStep(step);
            boolean isDPStep = analyzer.isDPPortalStep(step);
            
            System.out.println("Step " + (i + 1) + ": " + step.getStep());
            System.out.println("  Expected: " + step.getExpectedResult());
            System.out.println("  Portal: " + (isOperatorStep ? "OPERATOR" : isDPStep ? "DP" : "GENERAL"));
            System.out.println();
        }
        
        System.out.println("\n=== Method Mapping Analysis ===");
        List<MethodMapper.ExistingMethod> mappedMethods = MethodMapper.mapTestStepsToMethods(testCase.getSteps());
        MethodMapper.printMethodMappingReport(mappedMethods);
    }
}
