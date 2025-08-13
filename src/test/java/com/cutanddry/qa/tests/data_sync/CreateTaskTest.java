package com.cutanddry.qa.tests.data_sync;

import com.cutanddry.qa.api.JacksonUtil;
import com.cutanddry.qa.common.Codes;
import com.cutanddry.qa.data.models.api.requests.create_task.CreateTaskRequest;
import com.cutanddry.qa.data.models.api.responses.create_task.CreateTaskResponse;
import com.cutanddry.qa.data.models.api.responses.get_task.GetTaskResponse;
import com.cutanddry.qa.data.testdata.CreateTaskData;
import com.cutanddry.qa.utils.api.core.graphql.RequestUtil;
import com.cutanddry.qa.utils.api.core.graphql.ResponseUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;

public class CreateTaskTest {
    private String token;
    public static int taskId;


    @Test(description = "Create a Task")
    public void taskCreationTest() {
        SoftAssert softAssert = new SoftAssert();

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.controller = CreateTaskData.CONTROLLER;
        createTaskRequest.method = CreateTaskData.METHOD;
        createTaskRequest.args = Collections.singletonList("");
        createTaskRequest.runImmediately = CreateTaskData.RUN_IMMEDIATELY;

        Response taskCreation = RequestUtil.createTask(createTaskRequest);
        softAssert.assertEquals(ResponseUtil.getResponseCode(taskCreation), Codes.HTTP_200, "Expected the response code to be HTTP 200 (OK), but it is not.");

        CreateTaskResponse getCreateTaskResponse = (CreateTaskResponse) JacksonUtil.getResponseAsObject(taskCreation.asString(), CreateTaskResponse.class);
        softAssert.assertEquals(getCreateTaskResponse.message, CreateTaskData.SUCCESS_MSG, "Incorrect Message.");
        taskId = getCreateTaskResponse.taskId;
        System.out.println("Test " + taskId);
        softAssert.assertAll();
    }

    @Test(description = "Verify the task is successfully created", dependsOnMethods = "taskCreationTest")
    public void getTaskTest() {
        SoftAssert softAssert = new SoftAssert();
        boolean assertionPassed = false;

        for (int i = 0; i < 10; i++) {
            Response getTaskDetails = RequestUtil.getTask(String.valueOf(taskId));
            if (ResponseUtil.getResponseCode(getTaskDetails) == Codes.HTTP_200) {
                GetTaskResponse getTaskDetailsResponse = (GetTaskResponse) JacksonUtil.getResponseAsObject(getTaskDetails.asString(), GetTaskResponse.class);
                if (Integer.parseInt(getTaskDetailsResponse.taskId) == taskId && "Task not found".equals(getTaskDetailsResponse.message)) {
                    assertionPassed = true;
                    break;
                }
            }
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
        softAssert.assertTrue(assertionPassed, "Task was not found after multiple attempts.");

        /*Response getTaskDetails = RequestUtil.getTask(String.valueOf(taskId));
        softAssert.assertEquals(ResponseUtil.getResponseCode(getTaskDetails), Codes.HTTP_200, "Expected the response code to be HTTP 200 (OK), but it is not.");

        GetTaskResponse getTaskDetailsResponse = (GetTaskResponse) JacksonUtil.getResponseAsObject(getTaskDetails.asString(), GetTaskResponse.class);
        softAssert.assertEquals(Integer.parseInt(getTaskDetailsResponse.taskId), taskId, "Incorrect task ID.");
        softAssert.assertEquals(getTaskDetailsResponse.message, "Task not found", "Task is not completed.");*/

        softAssert.assertAll();
    }

}
