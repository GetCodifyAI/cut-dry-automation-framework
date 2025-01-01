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

public class VerifyTheFunctionalityOfFilteringUsersByUserRoles extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-834")
    public void VerifyTheFunctionalityOfFilteringUsersByUserRoles() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.selectRoleDropDown("Admin");
        softAssert.assertTrue(Settings.isDisplayedRoleCorrect("Admin"),"The displayed role is incorrect");
        Settings.selectRoleDropDown("Salesperson");
        softAssert.assertTrue(Settings.isDisplayedRoleCorrect("Salesperson"),"The displayed role is incorrect");
        Settings.selectRoleDropDown("Accounts Receivable");
        softAssert.assertTrue(Settings.isDisplayedRoleCorrect("Accounts Receivable"),"The displayed role is incorrect");
        Settings.selectRoleDropDown("Dispatcher");
        softAssert.assertTrue(Settings.isDisplayedRoleCorrect("Dispatcher"),"The displayed role is incorrect");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
