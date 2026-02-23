package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyHardOrderMinimumWarningTest extends TestBase {
    static User user;
    static String customerId = "34419";
    static String orderMin = "2500000";
    static String defaultOrderMin = "0";
    static String orderMinimumType = "Hard Order Minimum";
    static String DistributorName ="Independent Foods Co";
    static String orderMinimumSetting = "Use Global Settings";
    static String orderGuideName = "Test_Automation";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-85")
    public void VerifyHardOrderMinimumWarning() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        //Turning on hard order minimum form internal tools
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnOrderMinimumGloballyToggle(true);
        InternalTools.clickOnOrderMinimumDropdown(orderMinimumType);
        InternalTools.clickSave();

        Login.navigateToDistributorPortal(DistributorName);
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.enterOrderMinimum(orderMin);
        Settings.setOrderMinimums(false);
        Settings.clickOnSaveChanges();
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.SelectCustomer(customerId);
        Customer.SelectOrderMinimumFromProfile(orderMinimumSetting);

        Customer.clickOnOrderGuideInProfile();
        Customer.selectOrderGuideIfOverlayDisplayed(orderGuideName);
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItemsDist();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        softAssert.assertTrue(Customer.isMinOrderBannerDisplayed(),"banner not appearing error");
        Customer.submitOrderMinimum();

        softAssert.assertTrue(Customer.isOrderMinPopupDisplayed(),"popup display error");
        Login.clickOk();
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.enterOrderMinimum(defaultOrderMin);
        Settings.clickOnSaveChanges();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
