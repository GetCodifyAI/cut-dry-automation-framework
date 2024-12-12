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
    static String ticketTitle = "Test Ticket 10";

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
        Tracker.typeTicketTitle(ticketTitle);
        Tracker.selectStatus();
        Tracker.selectPriority();
        Tracker.selectCategory();
        Tracker.clickCreateTicket();
        softAssert.assertTrue(Tracker.isTaskTitleDisplayed(ticketTitle),"Issue ticket not create");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
