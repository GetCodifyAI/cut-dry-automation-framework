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

public class VerifyForgotPasswordValidTest extends TestBase{
    static ForgotPasswordUser user;
    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readForgotPasswordUserLogin();
    }

    @Test(groups = "DOT-TC-98")
    public void verifyForgotPasswordValidEmail() {
        SoftAssert softAssert = new SoftAssert();
        Login.forgotPassword();
        softAssert.assertTrue(Login.forgotPassword(),"forgot password navigation error");
        Login.passwordResetRequest(user.getEmail_valid());
        softAssert.assertTrue(Login.validEmailOrMobileForgotPassword(),"valid email pop up error");
        Login.clickOk();
        Login.forgotPassword();
        softAssert.assertTrue(Login.forgotPassword(),"forgot password navigation error");
        Login.passwordResetRequest(user.getMobile_valid());
        softAssert.assertTrue(Login.validEmailOrMobileForgotPassword(),"valid email pop up error");
        Login.clickOk();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
