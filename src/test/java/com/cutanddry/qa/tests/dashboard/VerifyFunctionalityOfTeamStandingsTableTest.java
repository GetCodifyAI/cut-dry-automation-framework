package com.cutanddry.qa.tests.dashboard;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import kotlin.DslMarker;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyFunctionalityOfTeamStandingsTableTest extends TestBase {
    static User user;
    static String days = "Last 7 Days";
    static String defaultDays = "Last 30 Days";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-241")
    public void verifyFunctionalityOfTeamStandingsTableTest() {
        String[] beforeSum;
        String[] afterSum;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        softAssert.assertTrue(Dashboard.isTeamStandingsDisplayed(),"team standing display error");
        beforeSum = Dashboard.getTotalSumDisplayed();
        Dashboard.selectDuration(days);
        softAssert.assertTrue(Dashboard.isDashboardDurationChanged(days),"duration display error");
        afterSum = Dashboard.getTotalSumDisplayed();
        softAssert.assertNotEquals(beforeSum, afterSum,"totals not changed error");
        Dashboard.selectDuration(defaultDays);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
