package com.cutanddry.qa.tests.price;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheSpotPricingByPricingEngineTest extends TestBase{
    static User user;
    static String distributorCheeseImp = PriceData.DISTRIBUTOR_CHEESE_IMP;
    static String customerId3 = PriceData.CUSTOMER_ID_3;
    static String searchItemCode;
    static String itemName = PriceData.ITEM_NAME_SPOT_PRICE;
    static double itemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1081")
    public void ValidateTheSpotPricingByPricingEngine() throws InterruptedException {
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
        softAssert.assertTrue(Customer.isItemValueAdded("$8.05"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("8.01"),"update error percentage1");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$100.45"),"update error price");

        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginPercentage("30");
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemValueAdded("$39.60"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("30"),"update error percentage2");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$132.00"),"update error price");

        Customer.editSpotPrice();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterSpotPrice("200");
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemValueAdded("$107.60"),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded("53.8"),"update error percentage3");
        softAssert.assertTrue(Customer.isSpotPriceAdded("$200.00"),"update error price");

        Customer.increaseFirstRowQtyCustom(1);

        itemPrice = Customer.getActiveItemPriceFirstRow();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected . .");
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
        Customer.OrderDateSort();
        Customer.OrderDateSort();
        String priceText = Customer.getPriceInCustomerOrder().replace("$", "").replace(",", "");
        Double actualPrice = Double.valueOf(priceText);
        softAssert.assertEquals(actualPrice, itemPrice, "The item has not been selected.");
//        softAssert.assertEquals(Customer.getPriceInCustomerOrder(),itemPrice,"The item has not been selected.");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
