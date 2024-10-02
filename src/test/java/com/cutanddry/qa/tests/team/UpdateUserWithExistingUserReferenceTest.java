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

public class UpdateUserWithExistingUserReferenceTest extends TestBase {
    static User user;
    static String name_1 = "Test";
    static String name_2 = "Test3";
    static String email_2 = "test3@email.com";
    static String userRef = "KF";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-139")
    public void UpdateUserWithExistingUserReference() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.clickOnAddUser();
        Settings.enterName(name_2);
        Settings.enterEmail(email_2);
        Settings.enterUserRef(userRef);
        Settings.clickOnInviteUser();
        softAssert.assertTrue(Settings.isUserDisplayed(name_2),"user adding error");
        Settings.clickOnEditUser(name_1);
        Settings.enterUserRef(userRef);
        Settings.clickOnSaveChanges();
        softAssert.assertTrue(Settings.isUserRefErrorDisplayed(),"user ref error");
        Settings.clickOK();
        Settings.clickRemoveAddedUserRef(userRef);
        Settings.clickOnSaveChanges();
        Settings.clickOnEditUser(name_2);
        Settings.clickOnRemoveUserLabel();
        softAssert.assertTrue(Settings.isRemoveUserPopupDisplayed(),"remove pop up error");
        Settings.clickOnRemoveUser();
        Settings.clickOK();
        softAssert.assertFalse(Settings.isUserDisplayed(name_2),"user remove error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
