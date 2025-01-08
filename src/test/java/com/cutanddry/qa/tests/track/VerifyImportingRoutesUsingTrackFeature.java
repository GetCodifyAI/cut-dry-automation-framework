package com.cutanddry.qa.tests.track;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
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

public class VerifyImportingRoutesUsingTrackFeature extends TestBase {
    static User user;
    static String distributorName = "Brandon IFC Cut+Dry Agent";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-864")
    public void VerifyImportingRoutesUsingTrackFeature() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(distributorName);
        Dashboard.navigateToTrackRoutes();
//        Track.clickBtnManageRoutes();
//        Track.clickBtnImportRoutes();


        softAssert.assertAll();
    }

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        takeScreenshotOnFailure(result);
//        closeAllBrowsers();
//    }
}
