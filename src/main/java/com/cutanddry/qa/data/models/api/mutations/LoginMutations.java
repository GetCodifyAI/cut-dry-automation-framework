package com.cutanddry.qa.data.models.api.mutations;

public class LoginMutations {
    public static String loginMutation_01 = "mutation LoginMutation($emailOrPhoneNumber: String!, $password: String!, $token: String!) { " +
            "LoginMutation(emailOrPhoneNumber: $emailOrPhoneNumber, password: $password, token: $token) { " +
            "id name email phone } }";

}
