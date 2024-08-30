package com.cutanddry.qa.tests.sign_in;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.ForgotPasswordUser;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyForgotPasswordInvalidTest extends TestBase {
    static ForgotPasswordUser user;
    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readInvalidUserLogin();
    }

    @Test(groups = "DOT-TC-101")
    public void verifyForgotPasswordInvalidEmail() {
        SoftAssert softAssert = new SoftAssert();
        Login.forgotPassword();
        softAssert.assertTrue(Login.forgotPassword(),"forgot password navigation error");
        Login.passwordResetRequest(user.getEmail());
        softAssert.assertTrue(Login.invalidEmailOrMobileForgotPassword(),"invalid email pop up error");
        Login.clickTryAgain();
        Login.forgotPassword();
        softAssert.assertTrue(Login.forgotPassword(),"forgot password navigation error");
        Login.passwordResetRequest(user.getMobile());
        softAssert.assertTrue(Login.invalidEmailOrMobileForgotPassword(),"invalid email pop up error");
        Login.clickTryAgain();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}