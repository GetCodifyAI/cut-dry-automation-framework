package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorSpecificData;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCashAndCarryAllowedOptionIsNotDisplayedInsidePricingAndPromotionsForCashAndCarryNotEnabledDPTest extends TestBase {
    static User user;
    static String DP = DistributorSpecificData.DISTRIBUTOR_WCW_ROCKIES;
    static String cashAndCarryAllowedOption = "Cash and Carry Allowed";



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1308")
    public void VerifyCashAndCarryAllowedOptionIsNotDisplayedInsidePricingAndPromotionsForCashAndCarryNotEnabledDP() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.clickOnPreviewCatalog();
        Assert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        Catalog.selectFirstItem();
        softAssert.assertTrue(Catalog.isProductDescriptionDisplayed(),"select product error");
        Catalog.clickOnEditProduct();
        Catalog.navigateToPricingAndPromotions();
        softAssert.assertFalse(Catalog.isCashAndCarryAllowedDisplay(cashAndCarryAllowedOption),"Cash and carry Allowed option is not displayed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
