package com.cutanddry.qa.aio;

import java.io.IOException;

public class TestCaseRetriever {
    public static void main(String[] args) {
        try {
            String apiToken = "ZGYzMWU0NDItNzllZS0zMzhiLWFiZjYtMTBkYzJlYTE1M2U1LjBjNTRhYTY0LTk0YjctNGZiNy1iZDYwLWYxMWZkOWMzOWViOQ==";
            String projectId = "DOT"; // Assuming DOT project for DOT-TC-1825
            
            AIOTestCaseClient client = new AIOTestCaseClient(apiToken, projectId);
            TestCaseDetails testCase = client.getTestCaseDetails("DOT-TC-1825");
            
            System.out.println("=== Test Case Details ===");
            System.out.println("Key: " + testCase.getKey());
            System.out.println("Title: " + testCase.getTitle());
            System.out.println("Description: " + testCase.getDescription());
            System.out.println("Precondition: " + testCase.getPrecondition());
            
            if (testCase.getFolder() != null) {
                System.out.println("Folder: " + testCase.getFolder().getName());
            }
            
            if (testCase.getPriority() != null) {
                System.out.println("Priority: " + testCase.getPriority().getName());
            }
            
            if (testCase.getStatus() != null) {
                System.out.println("Status: " + testCase.getStatus().getName());
            }
            
            System.out.println("\n=== Test Steps ===");
            if (testCase.getSteps() != null) {
                for (int i = 0; i < testCase.getSteps().size(); i++) {
                    TestStep step = testCase.getSteps().get(i);
                    System.out.println("Step " + (i + 1) + ":");
                    System.out.println("  Action: " + step.getStep());
                    System.out.println("  Data: " + step.getData());
                    System.out.println("  Expected Result: " + step.getExpectedResult());
                    System.out.println();
                }
            }
            
            System.out.println("\n=== Workflow Analysis ===");
            WorkflowAnalyzer analyzer = new WorkflowAnalyzer();
            WorkflowAnalyzer.WorkflowAnalysis analysis = analyzer.analyzeWorkflow(testCase);
            
            System.out.println("Login Pattern: " + analysis.getLoginPattern());
            System.out.println("Test Type: " + analysis.getTestType());
            System.out.println("Page Transitions:");
            for (WorkflowAnalyzer.PageTransition transition : analysis.getPageTransitions()) {
                System.out.println("  " + transition.getPage() + " -> " + transition.getMethod());
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Error retrieving test case: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
