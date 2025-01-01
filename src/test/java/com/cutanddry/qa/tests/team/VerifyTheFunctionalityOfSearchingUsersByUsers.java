package com.cutanddry.qa.tests.team;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFunctionalityOfSearchingUsersByUsers extends TestBase {
    static User user;
    static String name = "Ishan Ifc";
    static String email = "ishan+ifc@cutanddry.com";
    static String role = "Admin";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-835")
    public void VerifyTheFunctionalityOfSearchingUsersByUsers() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.typeUserInfo(name);
        softAssert.assertTrue(Settings.isDisplayedNameCorrect(name),"The expected result is not displayed");
        Settings.typeUserInfo(email);
        softAssert.assertTrue(Settings.isDisplayedEmailCorrect(email),"The expected result is not displayed");
        Settings.typeUserInfo(role);
        softAssert.assertTrue(Settings.isDisplayedRoleCorrect(role),"The expected result is not displayed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
