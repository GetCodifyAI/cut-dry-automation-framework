package com.cutanddry.qa.utils.aio.testcase;

import com.cutanddry.qa.utils.aio.core.rest.AioAPIHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static com.cutanddry.qa.common.Constants.PROJECT_KEY;

public class AioTestCaseRetriever {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String GET_TEST_CASE_DETAIL = "/project/{projectKey}/testcase/{testCaseId}/detail";

    public static AioTestCase getTestCaseDetails(String testCaseId) {
        String ciEnvironment = System.getProperty("CI");
        String testExecution = System.getProperty("maven.test.skip");
        
        if ("true".equals(ciEnvironment) || "true".equals(testExecution)) {
            System.out.println("Skipping AIO API call in CI/test environment for: " + testCaseId);
            return createMockTestCase(testCaseId);
        }
        
        try {
            System.out.println("Attempting to retrieve test case from AIO: " + testCaseId);
            Response response = AioAPIHelper.doGet(GET_TEST_CASE_DETAIL, PROJECT_KEY, testCaseId);
            
            if (response.getStatusCode() == 200) {
                return parseTestCaseResponse(response.getBody().asString());
            } else {
                System.err.println("Failed to retrieve test case " + testCaseId + ". Status: " + response.getStatusCode());
                System.out.println("Falling back to mock test case");
                return createMockTestCase(testCaseId);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving test case " + testCaseId + ": " + e.getMessage());
            System.out.println("Falling back to mock test case");
            return createMockTestCase(testCaseId);
        }
    }
    
    private static AioTestCase createMockTestCase(String testCaseId) {
        AioTestCase mockTestCase = new AioTestCase();
        mockTestCase.setKey(testCaseId);
        mockTestCase.setTitle("Mock Test Case for " + testCaseId);
        mockTestCase.setDescription("This is a mock test case created when AIO API is unavailable");
        mockTestCase.setPrecondition("Mock precondition");
        mockTestCase.setFolder("mock");
        mockTestCase.setPriority("Medium");
        mockTestCase.setStatus("Active");
        mockTestCase.setType("Functional");
        
        List<AioTestCase.TestStep> mockSteps = new ArrayList<>();
        AioTestCase.TestStep step1 = new AioTestCase.TestStep();
        step1.setStep("Login as distributor");
        step1.setData("Valid credentials");
        step1.setExpectedResult("User successfully logged in");
        step1.setStepNumber(1);
        mockSteps.add(step1);
        
        AioTestCase.TestStep step2 = new AioTestCase.TestStep();
        step2.setStep("Navigate to dashboard");
        step2.setData("N/A");
        step2.setExpectedResult("Dashboard displayed");
        step2.setStepNumber(2);
        mockSteps.add(step2);
        
        mockTestCase.setSteps(mockSteps);
        return mockTestCase;
    }

    private static AioTestCase parseTestCaseResponse(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            
            AioTestCase testCase = new AioTestCase();
            testCase.setKey(rootNode.path("key").asText());
            testCase.setTitle(rootNode.path("title").asText());
            testCase.setDescription(rootNode.path("description").asText());
            testCase.setPrecondition(rootNode.path("precondition").asText());
            testCase.setFolder(rootNode.path("folder").asText());
            testCase.setPriority(rootNode.path("priority").asText());
            testCase.setStatus(rootNode.path("status").asText());
            testCase.setType(rootNode.path("type").asText());

            List<AioTestCase.TestStep> steps = new ArrayList<>();
            JsonNode stepsNode = rootNode.path("steps");
            
            if (stepsNode.isArray()) {
                int stepNumber = 1;
                for (JsonNode stepNode : stepsNode) {
                    AioTestCase.TestStep step = new AioTestCase.TestStep();
                    step.setStep(stepNode.path("step").asText());
                    step.setData(stepNode.path("data").asText());
                    step.setExpectedResult(stepNode.path("expectedResult").asText());
                    step.setStepNumber(stepNumber++);
                    steps.add(step);
                }
            }
            
            testCase.setSteps(steps);
            return testCase;
            
        } catch (Exception e) {
            System.err.println("Error parsing test case response: " + e.getMessage());
            return null;
        }
    }
}
