package com.cutanddry.qa.utils;

import com.cutanddry.qa.data.models.Broadcast;
import com.cutanddry.qa.data.models.CashAndCarryAppUser;
import com.cutanddry.qa.data.models.ForgotPasswordUser;
import com.cutanddry.qa.data.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_LOGIN_JSON_PATH = "src/main/resources/userlogin.json";
    private static final String FORGOT_PASSWORD_USER_LOGIN_JSON_PATH = "src/main/resources/forgotpassworduserlogin.json";
    private static final String BROADCAST_URL_JSON_PATH = "src/main/resources/broadcast_url.json";
    private static final String CASH_AND_CARRY_APP_USER_LOGIN_JSON_PATH = "src/main/resources/cashandcarryapp.json";

    public static <T> T readJson(String filePath, Class<T> clazz) {
        try {
            File file = new File(filePath);
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User readUserLogin() {
        return readJson(USER_LOGIN_JSON_PATH, User.class);
    }
    public static ForgotPasswordUser readForgotPasswordUserLogin() {
        return readJson(FORGOT_PASSWORD_USER_LOGIN_JSON_PATH, ForgotPasswordUser.class);
    }
    public static Broadcast readUrl() {
        return readJson(BROADCAST_URL_JSON_PATH, Broadcast.class);
    }
    public static CashAndCarryAppUser readCashAndCarryAppUserDetails() {
        return readJson(CASH_AND_CARRY_APP_USER_LOGIN_JSON_PATH, CashAndCarryAppUser.class);
    }
}
