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

import java.util.Random;

public class UpdateUserTest extends TestBase {
    static User user;
//    static String testUser = "Test Test";
//    static String testEmail = "other@email.com";


    SoftAssert softAssert;
    static int randomNumber = new Random().nextInt(1000);
    static String testUser = "Testupdate " + randomNumber;
    static String testEmail = "testupdate"+randomNumber+"@email.com";
    static String testUserRef = "AZ"+randomNumber;
    static String userRef = "ZZ"+randomNumber;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-140")
    public void UpdateUser() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.clickOnAddUser();
        Settings.enterName(testUser);
        Settings.enterEmail(testEmail);
        Settings.enterUserRef(testUserRef);
        Settings.clickOnInviteUser();
        softAssert.assertTrue(Settings.isUserDisplayed(testUser),"add test user error");

        Settings.clickOnEditUser(testUser);
        Settings.enterUserRef(userRef);
        Settings.clickOnSaveChanges();
        Settings.clickOnEditUser(testUser);
        softAssert.assertTrue(Settings.isUserRefAdded(userRef),"ref updating error");
        Settings.clickOnRemoveUserLabel();
        softAssert.assertTrue(Settings.isRemoveUserPopupDisplayed(),"remove pop up error");
        Settings.clickOnRemoveUser();
        Settings.clickOK();
        softAssert.assertFalse(Settings.isUserDisplayed(testUser),"test user remove error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
