package com.cutanddry.qa.tests.banner;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheBannerFeatureTest extends TestBase {
    static User user;
    static String customer = "Brandon IFC White";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-43")
    public void verifyTheBannerFeature() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickCatalogHome();
        softAssert.assertTrue(Boost.isCatalogHomeTabDisplayed(),"navigate to catalog home error");
        Boost.toggleOnPrimaryBanner();
        Boost.clickOnSaveChanges();
        softAssert.assertTrue(Boost.isActiveDisplayed(),"toggle on banner error");
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(customer);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboardWhiteLabel(),"login error");
        softAssert.assertTrue(Boost.isPrimaryBannerDisplayed(),"banner display error");
        Login.navigateToDistributor();
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickCatalogHome();
        softAssert.assertTrue(Boost.isCatalogHomeTabDisplayed(),"navigate to catalog home error");
        Boost.toggleOffPrimaryBanner();
        Boost.clickOnSaveChanges();
        softAssert.assertTrue(Boost.isHiddenDisplayed(),"toggle off banner error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
