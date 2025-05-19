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

public class AddNewUserWithUserReferenceTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static int randomNumber = new Random().nextInt(1000);
    static String dynamicName = "Testref " + randomNumber;
    static String email = "testref"+randomNumber+"@email.com";
    static String userRef = "CC"+ randomNumber;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-148")
    public void AddNewUserWithUserReference() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.clickOnAddUser();
        Settings.enterName(dynamicName);
        Settings.enterEmail(email.trim());
        Settings.enterUserRef(userRef);
        Settings.clickOnInviteUser();
        softAssert.assertTrue(Settings.isUserDisplayed(dynamicName),"user adding error");

        Settings.clickOnEditUser(dynamicName);
        Settings.clickOnRemoveUserLabel();
        softAssert.assertTrue(Settings.isRemoveUserPopupDisplayed(),"remove pop up error");
        Settings.clickOnRemoveUser();
        Settings.clickOK();
        softAssert.assertFalse(Settings.isUserDisplayed(dynamicName),"user remove error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
