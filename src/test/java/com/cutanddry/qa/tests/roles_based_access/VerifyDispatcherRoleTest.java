package com.cutanddry.qa.tests.roles_based_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDispatcherRoleTest extends TestBase {
    static User user;
    static String userAR = "Hadley Dispatcher";
    static String customerId = "16579";
    static String itemName = "Artichoke";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test
    public void VerifyARRole() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(userAR);
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");
        //settings
        Dashboard.navigateToProfileSettings();
        softAssert.assertTrue(Settings.isProfileSettingsTextDisplayed(),"navigation to profile settings error");
        Dashboard.navigateToTrackSettings();
        softAssert.assertTrue(Settings.isTrackSettingsTextDisplayed(),"navigation to track settings error");
        //track
        Dashboard.navigateToTrackResources();
        softAssert.assertTrue(Track.isResourcesTextDisplayed(),"navigation to track resources error");
        Dashboard.navigateToTrackNotifications();
        softAssert.assertTrue(Track.isNotificationsTextDisplayed(),"navigation to track notif error");
        Dashboard.navigateToTrackMonitoring();
        softAssert.assertTrue(Track.isMonitoringTextDisplayed(),"navigation to track monitoring error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
