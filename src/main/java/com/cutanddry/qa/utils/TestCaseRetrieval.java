package com.cutanddry.qa.utils;

import com.cutanddry.qa.utils.aio.core.rest.AioAPIHelper;
import io.restassured.response.Response;

public class TestCaseRetrieval {
    public static void main(String[] args) {
        String projectKey = "DOT";
        String testCaseKey = "DOT-TC-1900";
        
        System.out.println("Retrieving test case details for: " + testCaseKey);
        
        try {
            String endpoint = "/project/" + projectKey + "/testcase/" + testCaseKey + "/detail";
            Response response = AioAPIHelper.doGet(endpoint);
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body:");
            response.prettyPrint();
        } catch (Exception e) {
            System.err.println("Error retrieving test case details: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
