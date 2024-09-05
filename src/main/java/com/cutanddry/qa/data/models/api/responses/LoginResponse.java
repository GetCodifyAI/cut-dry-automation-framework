package com.cutanddry.qa.data.models.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private DataContainer data;
    @Data
    public static class DataContainer {
        @JsonProperty("LoginMutation")
        private LoginMutation loginMutation; // Using @JsonProperty to match the JSON field "LoginMutation"
    }
    @Data
    public static class LoginMutation {
        private String id;
        private String name;
        private String email;
        private String phone;
    }
}
