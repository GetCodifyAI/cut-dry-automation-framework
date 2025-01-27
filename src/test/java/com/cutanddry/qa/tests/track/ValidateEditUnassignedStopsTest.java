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

public class ValidateEditUnassignedStopsTest extends TestBase {
    static User user;
    static String distributorName = TrackData.DISTRIBUTOR_NAME;
    static String addCustomerName = TrackData.ADD_CUSTOMER_NAME;
    static String editCustomerCode = TrackData.EDIT_CUSTOMER_CODE;
    static String editAddressStreet = TrackData.EDIT_ADDRESS_STREET;
    static String addAddressCity = TrackData.ADD_ADDRESS_CITY;
    static String addAddressState = TrackData.ADD_ADDRESS_STATE;
    static String addAddressZipCode = TrackData.ADD_ADDRESS_ZIPCODE;
    static String addCustomerCode = TrackData.ADD_CUSTOMER_CODE;
    static String addAddressStreet = TrackData.ADD_ADDRESS_STREET;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-908")
    public void ValidateEditUnassignedStops() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToTrackRoutes();
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");

        //pre-req
        Track.uploadRoute(Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("csvFiles/Sample_Route_Template.csv")).toURI()).toString());
        Track.clickAddStop();
        softAssert.assertTrue(Track.isAddStopPopupDisplayed(),"Add stop pop up window not display");
        Track.addCustomerName(addCustomerName);
        Track.addCustomerCode(addCustomerCode);
        Track.addAddressStreet(addAddressStreet);
        Track.addAddressCity(addAddressCity);
        Track.addAddressState(addAddressState);
        Track.addAddressZipCode(addAddressZipCode);
        Track.clickOnSaveChanges();
        softAssert.assertTrue(Track.isUnassignedStopAdded(addAddressStreet),"Unassigned stop not added");

        Track.clickEditUnassignStop();
        Track.addCustomerName(addCustomerName);
        Track.addCustomerCode(editCustomerCode);
        Track.addAddressStreet(editAddressStreet);
        Track.addAddressCity(addAddressCity);
        Track.addAddressState(addAddressState);
        Track.addAddressZipCode(addAddressZipCode);
        Track.clickOnSaveChanges();
        softAssert.assertTrue(Track.isUnassignedStopAdded(editAddressStreet),"Unassigned stop not added");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
