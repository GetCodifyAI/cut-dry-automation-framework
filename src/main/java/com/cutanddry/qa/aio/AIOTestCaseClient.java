package com.cutanddry.qa.aio;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AIOTestCaseClient {
    private static final String BASE_URL = "https://tcms.aiojiraapps.com/aio-tcms";
    private final String apiToken;
    private final String projectId;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    public AIOTestCaseClient(String apiToken, String projectId) {
        this.apiToken = apiToken;
        this.projectId = projectId;
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .build();
        this.objectMapper = new ObjectMapper();
    }
    
    public TestCaseDetails getTestCaseDetails(String testCaseId) throws IOException, InterruptedException {
        String url = String.format("%s/api/v1/project/%s/testcase/%s/detail", 
            BASE_URL, projectId, testCaseId);
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "AioAuth " + apiToken)
            .header("Content-Type", "application/json")
            .timeout(Duration.ofSeconds(60))
            .GET()
            .build();
        
        HttpResponse<String> response = httpClient.send(request, 
            HttpResponse.BodyHandlers.ofString());
        
        AIOApiException.handleApiResponse(response);
        
        return objectMapper.readValue(response.body(), TestCaseDetails.class);
    }
}
