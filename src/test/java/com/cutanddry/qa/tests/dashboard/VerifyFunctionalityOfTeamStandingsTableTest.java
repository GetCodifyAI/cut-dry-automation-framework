package com.cutanddry.qa.tests.dashboard;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyFunctionalityOfTeamStandingsTableTest extends TestBase {
    static User user;
    static String salesperson = "Bob";
    static String days = "Last 7 Days";
    static String defaultSalesperson = "All";
    static String defaultDays = "Last 30 Days";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-241")
    public void verifyFunctionalityOfTeamStandingsTableTest() {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        softAssert.assertTrue(Dashboard.isTeamStandingsDisplayed(),"team standing display error");
        Dashboard.selectSalesperson(salesperson);
        softAssert.assertTrue(Dashboard.isDashboardSalespersonChanged(salesperson),"salesrep display error");
        //
        Dashboard.selectDuration(days);
        softAssert.assertTrue(Dashboard.isDashboardDurationChanged(days),"duration display error");
        //
        Dashboard.selectSalesperson(defaultSalesperson);
        Dashboard.selectDuration(defaultDays);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
