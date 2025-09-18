package com.cutanddry.qa.aio;

import java.net.http.HttpResponse;

public class AIOApiException extends RuntimeException {
    
    public AIOApiException(String message) {
        super(message);
    }
    
    public AIOApiException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static void handleApiResponse(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        
        switch (statusCode) {
            case 200:
            case 201:
                return;
            case 401:
                throw new AIOApiException("Invalid or missing API Token. Please check your AIO API credentials.");
            case 404:
                throw new AIOApiException("Test case not found. Please verify the test case ID and project ID.");
            case 400:
                throw new AIOApiException("Malformed request. Please check the API parameters.");
            case 403:
                throw new AIOApiException("Access forbidden. Please check your API permissions.");
            case 500:
                throw new AIOApiException("Internal server error from AIO API.");
            default:
                throw new AIOApiException("Unexpected API response: " + statusCode + " - " + response.body());
        }
    }
}
