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

public class VerifyTheFunctionalityOfShowLastOrderedPriceOffForCustomerPortalTest extends TestBase {
    static User user;
    String RestaurantUserCode = "103430764";
    static String itemName = "bacon, uncured maple, e&p, food service";
    String SupplierName = "QA ONLY : test distributor";
    static String itemId ="24857";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-821")
    public void VerifyTheFunctionalityOfShowLastOrderedPriceOffForCustomerPortal() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOffLastOrderedPoundPrice();
        InternalTools.SaveLastOrderedPoundPriceTurnOn();
        Login.navigateToLoginAsPortal(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Customer.searchItemOnOrderGuide(itemId);
        softAssert.assertTrue(Customer.isLastOrderedPriceNotSameAfterToggleOff(),"error");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
