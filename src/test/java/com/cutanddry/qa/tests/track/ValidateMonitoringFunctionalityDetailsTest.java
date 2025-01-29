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
import java.nio.file.Paths;
import java.util.Objects;

public class ValidateMonitoringFunctionalityDetailsTest extends TestBase {
    static User user;
    static String distributorName = TrackData.DISTRIBUTOR_NAME;
    static String routeName = TrackData.ROUTE_NAME;
    static String customerName = TrackData.CUSTOMER_NAME;
    static String orderID = TrackData.ORDER_ID;
    static String customerStop = TrackData.CUSTOMER_STOP;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-915")
    public void ValidateMonitoringFunctionalityDetails() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToTrackRoutes();
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");
        Track.uploadRoute(Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("csvFiles/Sample_Route_Template.csv")).toURI()).toString());

        Dashboard.navigateToTrackMonitoring();
        softAssert.assertTrue(Track.isMonitoringTextDisplayed(),"navigation to track monitoring error");
        Track.clickMonitorRouteName(routeName);
        softAssert.assertTrue(Track.isCustomerColumnTextDisplayed(customerName),"customer not display");
        softAssert.assertTrue(Track.isOrderIdColumnTextDisplayed(orderID),"order id not display");
        softAssert.assertTrue(Track.isCustomerStopColumnTextDisplayed(customerStop),"stop not display");
        Track.clickMonitorCustomer(customerName);
        softAssert.assertTrue(Track.isMonitorCustomerNameDisplayed(customerName),"customer name display error");
        softAssert.assertTrue(Track.isMonitorCustomerNameDisplayed(orderID),"customer order id display error");
        softAssert.assertTrue(Track.isMonitorCustomerStopDisplayed(customerStop),"customer stop display error");
        Track.clickCloseCustomerDetails();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
