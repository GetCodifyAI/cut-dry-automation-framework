package com.cutanddry.qa.tests.influence;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.InfluenceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;

public class VerifyInfluenceNavigationForRebateEnabledDPTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    String DP = InfluenceData.DISTRIBUTOR_BIRITE;
    String Active_Campaigns = InfluenceData.ACTIVE_CAMPAIGNS;
    String Total_Income = InfluenceData.TOTAL_INCOME;
    String Attributed_Units = InfluenceData.ATTRIBUTED_UNITS;
    String Attributed_Sales = InfluenceData.ATTRIBUTED_SALES;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2389")
    public void VerifyInfluenceNavigationForRebateEnabledDP() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToInternalToolsPage();
        InternalTools.ensureBuyerEdgePlatformRebateStatus(true,DP);

        Login.navigateToDistributorPortal(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertTrue(Settings.isCustomerRestrictionTextDisplayed(),"enabling the 'Sponsored Ads' toggle button from the Internal tools is error");
        Settings.AllowRewardAndRebateTags(true);
        Settings.clickGeneralSettingSaveChanges();      softAssert.assertTrue(Settings.isAdsSettingsTextDisplayed(),"navigation to ads settings error");
        softAssert.assertTrue(Dashboard.isInfluenceTabDisplayedInMenuOptions(),"Influence Tab not displayed in menu options");
        Dashboard.navigateToInfluenceTab();
        softAssert.assertTrue(Influence.isInfluenceTxtDisplayed(),"Influence text not displayed");
        softAssert.assertTrue(Influence.isCampaignMatricesDisplayed(Active_Campaigns),"Active Campaigns text not displayed");
        softAssert.assertTrue(Influence.isCampaignMatricesDisplayed(Total_Income),"Total Income text not displayed");
        softAssert.assertTrue(Influence.isCampaignMatricesDisplayed(Attributed_Units),"Attributed Units text not displayed");
        softAssert.assertTrue(Influence.isCampaignMatricesDisplayed(Attributed_Sales),"Attributed Sales text not displayed");


        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}