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

public class ValidateTheRouteRecordTest extends TestBase {
    static User user;
    static String distributorName = TrackData.DISTRIBUTOR_NAME;
    static String addCustomerCode = TrackData.ADD_CUSTOMER_CODE;
    static String addAddressStreet = TrackData.ADD_ADDRESS_STREET;
    static String editRouteName = TrackData.EDIT_ROUTE_NAME;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-905")
    public void ValidateTheRouteRecord() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToTrackRoutes();
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");
        Track.clickRouteName(editRouteName);
        softAssert.assertTrue(Track.isRouteStopAdded(addCustomerCode),"Route customer code record not display");
        softAssert.assertTrue(Track.isRouteStopAdded(addAddressStreet),"Route address street record not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
