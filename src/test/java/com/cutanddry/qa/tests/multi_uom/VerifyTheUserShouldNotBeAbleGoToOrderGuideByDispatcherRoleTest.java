package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheUserShouldNotBeAbleGoToOrderGuideByDispatcherRoleTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String dispatcherRoll =CatalogData.DISPATCHER_ROLL;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1021")
    public void VerifyTheUserShouldNotBeAbleGoToOrderGuideByDispatcherRole() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(dispatcherRoll);
        softAssert.assertTrue(Track.isRoutesTextDisplayed()|| Track.isMonitoringTextDisplayed(), "Unexpected text: " );
        softAssert.assertFalse(Dashboard.isCustomerDisplayed(),"customer section display");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
