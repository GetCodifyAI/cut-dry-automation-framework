package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheAllowBuyersEdgePlatformRebateTagsFlowTest extends TestBase {
    static User user;
    String distributorName ="Independent Foods Co";
    String dp = "Independent Foods Co";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-865")
    public void VerifyTheAllowBuyersEdgePlatformRebateTagsFlow() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertTrue(Settings.isAdsSettingsTextDisplayed(),"navigation to ads settings error");

        Login.navigateToInternalToolsPage();
        InternalTools.ensureBuyerEdgePlatformRebateStatus(true,dp);
        Login.navigateToDistributorPortal(distributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertTrue(Settings.isCustomerRestrictionTextDisplayed(),"enabling the 'BuyerEdgePlatformRebate' toggle button from the Internal tools is error");
        softAssert.assertTrue(Settings.isRebatesAllowedColumnDisplayed(),"Rebates allowed column Not displayed");
        Settings.clickBuyerEdgePlatformRebateToggle();
        Settings.clickGeneralSettingSaveChanges();

        Login.navigateToInternalToolsPage();
        InternalTools.ensureBuyerEdgePlatformRebateStatus(false,dp);
        Login.navigateToDistributorPortal(distributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertFalse(Settings.isRebatesAllowedColumnDisplayed(),"Rebates allowed column displayed");

        Login.navigateToInternalToolsPage();
        InternalTools.ensureBuyerEdgePlatformRebateStatus(true,dp);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
