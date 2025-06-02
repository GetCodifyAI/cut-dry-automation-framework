package com.cutanddry.qa.tests.track;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class VerifyTheDispatchButtonFunctionalityForMultipleRoutes extends TestBase{
    static User user;
//    static String distributorName = "Brandon IFC Cut+Dry Agent";
static String distributorName = "47837013";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-871")
    public void VerifyTheDispatchButtonFunctionalityForMultipleRoutes() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(distributorName);
        Dashboard.navigateToTrackRoutes();
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");
        Track.clickBtnDispatch();
        Track.typeStartTimeDispatched("0400PM");
        Track.selectTruckNameDispatch("Black Longhauler");
        Track.ifAreYouSureDisplayed();
        Track.selectDriverDispatch("Hadley");
        Track.clickBtnSaveChanges();
        Track.clickBtnDispatch1();
        Track.clickYesButton();
        Track.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
