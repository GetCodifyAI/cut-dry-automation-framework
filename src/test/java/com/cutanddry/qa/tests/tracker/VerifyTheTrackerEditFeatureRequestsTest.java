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

public class VerifyTheTrackerEditFeatureRequestsTest extends TestBase {
    static User user;
//    static String ticketTitle = "Test Ticket 10";
    static String ticketTitle = "Test Ticket " + generateDynamicValue();
    static String editTicket ="To-do";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-739")
    public void VerifyTheTrackerEditFeatureRequests() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTracker();
        softAssert.assertTrue(Tracker.isOnboardingDocsDisplayed(),"navigation to tracker error");
        Tracker.clickFeatureRequests();
        softAssert.assertTrue(Tracker.isFeatureRequestsDisplayed(),"navigation feature requests error");
        Tracker.clickRequestFeature();
        softAssert.assertTrue(Tracker.isRequestNewFeatureDisplayed(),"create request new feature not display");
        Tracker.typeTicketTitle(ticketTitle);
        Tracker.selectStatus();
        Tracker.selectPriority();
        Tracker.selectCategory();
        Tracker.clickCreateTicket();
        softAssert.assertTrue(Tracker.isTaskTitleDisplayed(ticketTitle),"feature request ticket not create");
        Tracker.clickFirstRow(ticketTitle);
        softAssert.assertTrue(Tracker.isEditTicketPopUpDisplayed(ticketTitle),"edit ticket pop up not display");
        Tracker.clickEditStatus();
        Tracker.clickEditStatusOption();
        Tracker.clickCloseEditTicket();
        softAssert.assertTrue(Tracker.isEditTicketDisplayed(editTicket),"ticket not edit");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
