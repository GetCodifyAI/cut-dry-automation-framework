package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheAllowSponsoredProductAdvertisementsFlowTest extends TestBase {
    static User user;
    String distributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-858")
    public void VerifyTheAllowSponsoredProductAdvertisementsFlow() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertTrue(Settings.isAdsSettingsTextDisplayed(),"navigation to ads settings error");
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToSponsoredAdsRebatesTab();
        InternalTools.clickSponsoredProductAdsToggle();
        InternalTools.clickProductAdsSave();

        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertTrue(Settings.isCustomerRestrictionTextDisplayed(),"enabling the 'Sponsored Ads' toggle button from the Internal tools is error");
        Settings.clickSponsorProdAdsToggle();
        Settings.clickGeneralSettingSaveChanges();

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToSponsoredAdsRebatesTab();
        InternalTools.clickSponsoredProductAdsToggle();
        InternalTools.clickProductAdsSave();

        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertFalse(Settings.isCustomerRestrictionTextDisplayed(),"disable the 'Sponsored Ads' toggle button from the Internal tools is error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}