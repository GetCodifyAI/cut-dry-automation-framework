package com.cutanddry.qa.utils.api.core.graphql;

import com.cutanddry.qa.common.Constants;
import com.cutanddry.qa.common.Headers;
import com.cutanddry.qa.common.URIs;
import com.cutanddry.qa.data.models.api.requests.create_task.CreateTaskRequest;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestUtil extends RestUtil {

    private static void setExecutionEnvironment() {
        RestUtil.baseURL = Constants.BASE_PATH;
        RestUtil.port = Integer.parseInt(Constants.PORT);
    }

    public static Response createTask(CreateTaskRequest createTaskRequest) {
        setExecutionEnvironment();
        Response response = given().log().all().spec(requestSpecificationWithHeader(baseURL, Headers.getCreateTaskHeaders(Constants.IH_API_SECRET_KEY)))
                .body(createTaskRequest).when().post(URIs.CREATE_TASK_API).then().log().all().extract().response();
        return response;
    }

    public static Response getTask(String id) {
        setExecutionEnvironment();

        Map<String, String> params = new HashMap<String, String>();
        params.put("task_id", id);

        Response response = given().log().all().spec(requestSpecificationMultipleQueryParam(baseURL, params, Headers.getCreateTaskHeaders(Constants.IH_API_SECRET_KEY)))
                .when().get(URIs.TASK_STATUS_API).then().log().all().extract().response();
        return response;
    }

}
