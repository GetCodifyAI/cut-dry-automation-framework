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

public class ValidateGettingEditRouteFlowTest extends TestBase {
    static User user;
    static String distributorName = TrackData.DISTRIBUTOR_NAME;
    static String editRoute = TrackData.EDIT_ROUTE;
    static String editRouteName = TrackData.EDIT_ROUTE_NAME;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-884")
    public void ValidateGettingEditRouteFlow() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToTrackRoutes();
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");
        Track.uploadRoute(Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("csvFiles/Sample_Route_Template.csv")).toURI()).toString());
        Track.clickEditRouteFunction(editRoute);
        softAssert.assertTrue(Track.isEditRoutePopupDisplayed(),"Edit route pop up not displayed");
        Track.editRouteName(editRouteName);
        Track.clickOnSaveChanges();
        softAssert.assertTrue(Track.isRouteEditedPopupDisplayed(),"Route edit error");
        Track.clickOK();
        softAssert.assertTrue(Track.isEditRouteNameDisplayed(editRouteName),"Route name not edit successfully");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
