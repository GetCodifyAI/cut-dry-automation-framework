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

public class VerifyTheTrackerIssuesTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-541")
    public void VerifyTheTrackerIssues() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTracker();
        softAssert.assertTrue(Tracker.isOnboardingDocsDisplayed(),"navigation to tracker error");
        Tracker.clickIssueTracker();
        softAssert.assertTrue(Tracker.isIssueTrackerDisplayed(),"navigation issue tracker error");
        Tracker.clickNewTicket();
        softAssert.assertTrue(Tracker.isCreateNewIssueDisplayed(),"create new issue window not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
