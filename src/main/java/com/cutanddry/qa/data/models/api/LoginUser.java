package com.cutanddry.qa.data.models.api;

import lombok.Data;

@Data
public class LoginUser {
    private String phoneNumber;
    private String password;
    private String token;
    private String name;
    private String email;
}
