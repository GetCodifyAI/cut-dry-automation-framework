package com.cutanddry.qa.tests.track;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.TrackData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Track;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.URISyntaxException;

public class VerifySearchingFunctionUnderMonitoring extends TestBase {
    static User user;
    static String distributorName = "Brandon IFC Cut+Dry Agent";
    static String driverName = "Test Driver";
    static String customerName = "Test Customer";
    static String routeName = "Test Route";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-872")
    public void VerifySearchingFunctionUnderMonitoring() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(distributorName);
        Dashboard.navigateToTrackMonitoring();
        softAssert.assertTrue(Track.isMonitoringTextDisplayed(),"navigation to track monitoring error");
        Track.searchTrackMonitoring(driverName);
        Track.searchTrackMonitoring(customerName);
        Track.searchTrackMonitoring(routeName);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
