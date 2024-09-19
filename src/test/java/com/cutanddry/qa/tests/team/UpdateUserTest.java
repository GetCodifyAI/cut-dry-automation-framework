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

public class UpdateUserTest extends TestBase {
    static User user;
    static String testUser = "Test Other";
    static String userRef1 = "AZ";
    static String userRef2 = "ZZ";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-140")
    public void UpdateUser() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.clickOnEditUser(testUser);
        Settings.enterUserRef(userRef2);
        Settings.clickOnSaveChanges();
        Settings.clickOnEditUser(testUser);
        softAssert.assertTrue(Settings.isUserRefAdded(userRef2),"ref updating error");
        Settings.clickRemoveAddedUserRef();
        Settings.clickRemoveAddedUserRef();
        Settings.enterUserRef(userRef1);
        Settings.clickOnSaveChanges();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}