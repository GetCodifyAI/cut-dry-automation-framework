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

public class UpdateUserWithExistingUserReferenceTest extends TestBase {
    static User user;
    static int randomNumber = new Random().nextInt(1000);
    static String name_1 = "Test" + randomNumber;
    static String name_2 = "Test3" + randomNumber;
    static String email_2 = "test"+randomNumber+"@email.com";
    static String userRef = "KF"+randomNumber;

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
        Settings.clickOnEditUser(name_2);
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
    public void tearDown(ITestResult result) throws InterruptedException {

        //Reverting back the Ref id Since it will be used in TC:141
       /* Settings.clickOnEditUser(name_1);
        Settings.enterUserRef(userRef);
        Settings.clickOnSaveChanges();*/

        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
