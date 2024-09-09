package com.cutanddry.qa.data.models.api.requests;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String query;
    private Map<String, Object> variables;
}
