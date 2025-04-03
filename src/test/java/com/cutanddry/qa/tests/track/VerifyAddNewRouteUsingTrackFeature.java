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

public class VerifyAddNewRouteUsingTrackFeature extends TestBase {
    static User user;
    static String distributorName = "Brandon IFC Cut+Dry Agent - In";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-866")
    public void VerifyAddNewRouteUsingTrackFeature() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        Dashboard.navigateToTrackRoutes();
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");
//        Track.clickOkIfErrorTextDisplayed();
        Track.deleteExistingRoute();
        Track.clickBtnManageRoutes();
        Track.clickBtnAddNewRoutes();
        String randomCode = Track.generateRandomCode();
        Track.typeRouteName("Test Route - "+randomCode);
        Track.selectOptionDriverDropDown("Dangerous Dave");
        Track.selectOptionTruckDropDown("Roadrunner");
        Track.typeStartTime("1000AM");
        Track.clickOnSaveChanges();
        Track.clickOK();
//        Track.clickOkIfErrorTextDisplayed();
        softAssert.assertTrue(Track.isMapDisplayed(),"The map is not displayed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
