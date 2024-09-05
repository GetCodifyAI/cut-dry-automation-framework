package com.cutanddry.qa.utils;

import com.cutanddry.qa.data.models.ForgotPasswordUser;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.models.api.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonMutationsUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_LOGIN_MUTATION_JSON_PATH = "src/main/resources/api/loginMutation.json";

    public static <T> T readJson(String filePath, Class<T> clazz) {
        try {
            File file = new File(filePath);
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LoginUser readUserLogin() {
        return readJson(USER_LOGIN_MUTATION_JSON_PATH, LoginUser.class);
    }

}
