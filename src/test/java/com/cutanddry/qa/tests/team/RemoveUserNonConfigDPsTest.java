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

public class RemoveUserNonConfigDPsTest extends TestBase {
    static User user;
    //static String name = "Test 99";
    static int randomNumber = new Random().nextInt(1000);
    static String name = "Testnoconfig" + randomNumber;
    static String email = "test"+randomNumber+"@email.com";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-145")
    public void RemoveUserNonConfigDPs() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");

        // Create a new user as pre-request
        Settings.clickOnAddUser();
        Settings.enterName(name);
        Settings.enterEmail(email);
        Settings.clickOnInviteUser();
        softAssert.assertTrue(Settings.isUserDisplayed(name),"user adding error");

        // Testing flow as Delete user
        Settings.clickOnEditUser(name);
        Settings.clickOnRemoveUserLabel();
        softAssert.assertTrue(Settings.isRemoveUserPopupDisplayed(),"remove pop up error");
        Settings.clickOnRemoveUser();
        Settings.clickOK();
        softAssert.assertFalse(Settings.isUserDisplayed(name),"user remove error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
