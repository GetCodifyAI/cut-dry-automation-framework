package com.cutanddry.qa.tests.price;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheSpotPricingNotFromPricingEngineTest extends TestBase{
    static User user;
    static String distributorVitco = PriceData.DISTRIBUTOR_VITCO;
    static String customerId5 = PriceData.CUSTOMER_ID_5;
    static String searchItemCode;
    static String itemName = PriceData.ITEM_NAME_SPOT_PRICE2;
    static String itemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1082")
    public void ValidateTheSpotPricingNotFromPricingEngine() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorVitco);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");


        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId5);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId5), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId5);
        Customer.searchItemOnOrderGuide(itemName);
        searchItemCode = Customer.getItemCodeFirstRow();

        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginValue("8.05");
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemAdded("$8.05"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("22%"),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$36.49"),"update error");

        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginPercentage("30");
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemAdded("$12.19"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("30%"),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$40.63"),"update error");

        Customer.enterSpotPrice("50");
        Customer.increaseFirstRowQtyCustom(1);
        softAssert.assertTrue(Customer.isItemAdded("$21.56"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("43%"),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$50.00"),"update error");

        itemPrice = Customer.getItemFinalSpotPrice();
        softAssert.assertEquals(Customer.getItemPriceOnEditOrderCheckout(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId5);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId5), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId5);
        Customer.clickOnOrdersTab();
        softAssert.assertEquals(Customer.getPriceInCustomerOrder(),itemPrice,"The item has not been selected.");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
