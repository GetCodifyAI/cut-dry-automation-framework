package com.cutanddry.qa.tests.poc_api_test;

import com.cutanddry.qa.common.Constants;
import com.cutanddry.qa.data.models.api.LoginUser;
import com.cutanddry.qa.data.models.api.mutations.LoginMutations;
import com.cutanddry.qa.data.models.api.requests.LoginRequest;
import com.cutanddry.qa.data.models.api.responses.LoginResponse;
import com.cutanddry.qa.utils.JsonMutationsUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class LoginAPITestFormatted {
    static LoginUser loginUser;
    @BeforeMethod
    public void setUp() {
        loginUser = JsonMutationsUtil.readUserLogin();
    }

    @Test
    public void loginAPI() {
        SoftAssert softAssert = new SoftAssert();
        // Define the base URI for the GraphQL endpoint
        RestAssured.baseURI = Constants.BASE_URI;
        // Define the GraphQL mutation query
        String mutationQuery = LoginMutations.loginMutation_01;
        // Set up variables for the mutation
        Map<String, Object> variables = new HashMap<>();
        variables.put("emailOrPhoneNumber", loginUser.getPhoneNumber());
        variables.put("password", loginUser.getPassword());
        variables.put("token", loginUser.getToken());
        // Create a GraphQL request using the POJO
        LoginRequest request = new LoginRequest(mutationQuery, variables);
        // Perform the POST request
        Response response = given().header("Content-Type", "application/json").body(request).when().post();
        // Deserialize the response to the LoginResponse POJO
        LoginResponse loginResponse = response.as(LoginResponse.class);
        // Assertions using POJO
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(loginResponse.getData().getLoginMutation().getName(), loginUser.getName());
        softAssert.assertEquals(loginResponse.getData().getLoginMutation().getEmail(), loginUser.getEmail());
        // Print the response for debugging
        System.out.println("Response: " + response.asString());
        softAssert.assertAll();
    }
}
