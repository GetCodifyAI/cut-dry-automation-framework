package com.cutanddry.qa.tests.customer_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFunctionalityOfShowLastOrderedPriceOnForCustomerPortalTest extends TestBase {
    static User user;
    String RestaurantUserCode = "103430764";
    static String itemName = "bacon, uncured maple, e&p, food service";
    String SupplierName = "Independent Foods Co";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-820")
    public void VerifyTheFunctionalityOfShowLastOrderedPriceOnForCustomerPortal() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnLastOrderedPoundPrice();
        InternalTools.SaveLastOrderedPoundPriceTurnOn();
        Login.switchIntoNewTab();
        Login.navigateToLoginAsPortal(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Customer.searchItemOnOrderGuide(itemName);
        softAssert.assertTrue(Customer.isLastOrderedPoundPriceDisplayed(),"display error");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }

}
