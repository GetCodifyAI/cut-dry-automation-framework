package com.cutanddry.qa.tests.tracker;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Tracker;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheTrackerFeatureRequestsTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-542")
    public void VerifyTheTrackerFeatureRequests() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTracker();
        softAssert.assertTrue(Tracker.isOnboardingDocsDisplayed(),"navigation to tracker error");
        Tracker.clickFeatureRequests();
        softAssert.assertTrue(Tracker.isFeatureRequestsDisplayed(),"navigation feature requests error");
        Tracker.clickRequestFeature();
        softAssert.assertTrue(Tracker.isRequestNewFeatureDisplayed(),"create request new feature not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}