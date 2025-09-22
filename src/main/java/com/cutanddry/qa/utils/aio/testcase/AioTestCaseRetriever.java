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
        try {
            Response response = AioAPIHelper.doGet(GET_TEST_CASE_DETAIL, PROJECT_KEY, testCaseId);
            
            if (response.getStatusCode() == 200) {
                return parseTestCaseResponse(response.getBody().asString());
            } else {
                System.err.println("Failed to retrieve test case " + testCaseId + ". Status: " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving test case " + testCaseId + ": " + e.getMessage());
            return null;
        }
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
