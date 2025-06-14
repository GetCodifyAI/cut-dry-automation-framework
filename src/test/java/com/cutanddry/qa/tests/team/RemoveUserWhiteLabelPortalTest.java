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

public class RemoveUserWhiteLabelPortalTest extends TestBase {
    static User user;
    static String name = "Test";
    static String email = "test@email.com";
    static String customer = "13038005947";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-151")
    public void RemoveUserWhiteLabelPortal() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(customer);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboardWhiteLabel(),"login error");
        Dashboard.navigateToUsersWhiteLabel();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");

        //Pre-request
        Settings.deleteUser(name);
        Settings.addUser(name,email);

        Settings.clickOnUser(name);
        softAssert.assertTrue(Settings.isEditUserPopupDisplayed(),"navigation error");
        Settings.clickOnRemoveUser();
        softAssert.assertTrue(Settings.isRemovePopupWLUserDisplayed(),"remove pop up error");
        Settings.clickYes();
        Settings.clickOK();
        softAssert.assertFalse(Settings.isWLUserDisplayed(name),"user remove error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
