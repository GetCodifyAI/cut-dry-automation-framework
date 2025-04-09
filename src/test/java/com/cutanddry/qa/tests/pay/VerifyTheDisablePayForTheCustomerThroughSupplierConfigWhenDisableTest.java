package com.cutanddry.qa.tests.pay;

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

public class VerifyTheDisablePayForTheCustomerThroughSupplierConfigWhenDisableTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "25212";
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    static String restaurantName = "Kafe Layers #2 Test";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1140")
    public void VerifyTheDisablePayForTheCustomerThroughSupplierConfigWhenDisable() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToPayDetailsTab();
        InternalTools.clickPayEnabledToggle(false);
        InternalTools.clickSave();
      //  softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickonInvoice();
        softAssert.assertTrue(Pay.isCutAndDryPayToggleEnabled(),"cut and dry pay enable");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToPayDetailsTab();
        InternalTools.clickPayEnabledToggle(false);
        InternalTools.deleteRestaurantInPayEnable(restaurantName);
        InternalTools.clickSave();
        //  softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();
        softAssert.assertFalse(InternalTools.isPayEnableRestaurantDisplayed(restaurantName),"restaurant not display");

        //revert change
        InternalTools.addCustomerToPayEnable(restaurantName);
        InternalTools.clickSave();
        //  softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();
        softAssert.assertTrue(InternalTools.isPayEnableRestaurantDisplayed(restaurantName),"restaurant not display");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
