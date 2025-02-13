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

public class ValidateThePoundSpotPricingTest extends TestBase{
    static User user;
    static String distributorCheeseImp = PriceData.DISTRIBUTOR_CHEESE_IMP;
    static String customerId3 = PriceData.CUSTOMER_ID_3;
    static String searchItemCode;
    static String itemName = PriceData.ITEM_NAME_POUND_SPOT_PRICE;
    static String itemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1083")
    public void ValidateThePoundSpotPricing() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorCheeseImp);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");


        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId3);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId3), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId3);
        Customer.searchItemOnOrderGuide(itemName);
        searchItemCode = Customer.getItemCodeFirstRow();

        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginValue("8.05");
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemAdded("$8.05/lb"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("28%"),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$28.98/lb"),"update error sp1");

        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginPercentage("30");
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemAdded("$8.97/lb"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("30%"),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$29.90/lb"),"update error sp2");

        Customer.enterSpotPrice("200");
        Customer.increaseFirstRowQtyCustom(1);
        softAssert.assertTrue(Customer.isItemAdded("$179.07/lb"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("90%"),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$200.00/lb"),"update error sp3");


        itemPrice = Customer.getItemFinalPoundSpotPrice();
        softAssert.assertEquals(Customer.getItemPriceOnEditOrderCheckout(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId3);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId3), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId3);
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
