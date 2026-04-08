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

public class VerifyEndToEndUserInviteFlowTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static int randomNumber = new Random().nextInt(10000);
    static String dynamicName = "Test User " + randomNumber;
    static String email = "testuser" + randomNumber + "@test.com";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-NEW")
    public void verifyEndToEndUserInviteFlow() throws InterruptedException {
        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - Dashboard not displayed");

        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(), "Team Settings page not displayed");

        Settings.clickOnAddUser();
        Settings.enterName(dynamicName);
        Settings.enterEmail(email);
        Settings.clickOnInviteUser();

        softAssert.assertTrue(Settings.isUserDisplayed(dynamicName), "User was not added successfully");

        Settings.clickOnEditUser(dynamicName);
        softAssert.assertTrue(Settings.isEditUserPopupDisplayed(), "Edit User popup not displayed");
        Settings.clickOnRemoveUserLabel();
        softAssert.assertTrue(Settings.isRemoveUserPopupDisplayed(), "Remove User confirmation popup not displayed");
        Settings.clickOnRemoveUser();
        Settings.clickOK();

        softAssert.assertFalse(Settings.isUserDisplayed(dynamicName), "User was not removed successfully");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
