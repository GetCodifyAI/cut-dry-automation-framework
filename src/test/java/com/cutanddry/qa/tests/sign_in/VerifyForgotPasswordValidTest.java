package com.cutanddry.qa.tests.sign_in;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.ForgotPasswordUser;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyForgotPasswordValidTest extends TestBase{
    static User userLogin;
    static ForgotPasswordUser user;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        initialization();
        userLogin = JsonUtil.readUserLogin();
        user = JsonUtil.readForgotPasswordUserLogin();
        softAssert = new SoftAssert();
    }

    @Test(groups = "DOT-TC-28")
    public void loginAsDistributor() {
        Login.loginAsDistributor(userLogin.getEmailOrMobile(), userLogin.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-98")
    public void verifyForgotPasswordValidEmail() {
        verifyForgotPassword(user.getEmail_valid(), true);
        verifyForgotPassword(user.getMobile_valid(), true);
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-101")
    public void verifyForgotPasswordInvalidEmail() {
        verifyForgotPassword(user.getEmail_invalid(), false);
        verifyForgotPassword(user.getMobile_invalid(), false);
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-104")
    public void verifyForgotPasswordUnauthorizedEmail() {
        verifyForgotPassword(user.getEmail_unauthorized(), false);
        verifyForgotPassword(user.getMobile_unauthorized(), false);
        softAssert.assertAll();
    }


    private void verifyForgotPassword(String input, boolean isValid) {
        Login.forgotPassword();
        softAssert.assertTrue(Login.forgotPassword(), "forgot password navigation error");
        Login.passwordResetRequest(input);
        if (isValid) {
            softAssert.assertTrue(Login.validEmailOrMobileForgotPassword(), "valid email pop up error");
            Login.clickOk();
        } else {
            softAssert.assertTrue(Login.invalidEmailOrMobileForgotPassword(), "invalid email pop up error");
            Login.clickTryAgain();
        }
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
