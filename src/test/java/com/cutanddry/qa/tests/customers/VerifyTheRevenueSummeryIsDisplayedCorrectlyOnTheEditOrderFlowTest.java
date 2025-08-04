package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheRevenueSummeryIsDisplayedCorrectlyOnTheEditOrderFlowTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemName, orderId, searchItemCode;
    static double itemPrice,grossProfitValue;
    static String cartSummary = "Cart Summary";
    static String totalLine = "Total Line items";
    static String totalQuantity = "Total Quantity";
    static String revenueSummary = "Revenue Summary";
    static String grossProfit = "Gross Profit";
    static String margin = "Margin";
    static String totalLineValue,totalQuantityValue,marginValue;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1506")
    public void VerifyTheRevenueSummeryIsDisplayedCorrectlyOnTheEditOrderFlow() throws InterruptedException {

        softAssert = new SoftAssert();

        // Distributor Flows
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.searchItemOnOrderGuide(searchItemCode);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        softAssert.assertTrue(Customer.isDeliveryOptionSelected(), "The expected fulfillment type is not selected.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);

        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.clickCartSummery();
        totalLineValue = Customer.getCartSummeryValue(totalLine);
        totalQuantityValue = Customer.getCartSummeryValue(totalQuantity);

        marginValue = Customer.getRevenueSummeryValue(margin);
        grossProfitValue = Customer.getGrossProfitValueStable(grossProfit);


        softAssert.assertTrue(Customer.isCartSummaryDisplay(cartSummary),"cart summary display error");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalLine),totalLineValue,"cart summary value display error");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalQuantity),totalQuantityValue,"cart summary value display error");

        softAssert.assertTrue(Customer.isRevenueSummaryDisplay(revenueSummary),"Revenue summary display error");
        softAssert.assertEquals(Customer.getGrossProfitValueStable(grossProfit),grossProfitValue,"Revenue summary value display error");
        softAssert.assertEquals(Customer.getRevenueSummeryValue(margin),marginValue,"Revenue summary value display error");
        Customer.refreshCustomersPage();

        Draft.clickEditOrder();

        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyCustom(1);

        Customer.clickCartSummery();
        softAssert.assertTrue(Customer.isCartSummaryDisplay(cartSummary),"cart summary display error");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalLine),totalLineValue,"cart summary value display error OG");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalQuantity),"2","cart summary value display error OG");

        softAssert.assertTrue(Customer.isRevenueSummaryDisplay(revenueSummary),"Revenue summary display error");
        softAssert.assertEquals(Customer.getGrossProfitValueStable(grossProfit),grossProfitValue*2,"Revenue summary value display error OG");
        softAssert.assertEquals(Customer.getRevenueSummeryValue(margin),marginValue,"Revenue summary value display error OG");
        Customer.checkoutItems();

        Customer.createOrderFromCatalog();

        // Add the product via Catalog
        Customer.searchItemOnCatalog(searchItemCode);
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        Customer.clickCartSummery();
        softAssert.assertTrue(Customer.isCartSummaryDisplay(cartSummary),"cart summary display error");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalLine),totalLineValue,"cart summary value display error OG");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalQuantity),"3","cart summary value display error OG");

        softAssert.assertTrue(Customer.isRevenueSummaryDisplay(revenueSummary),"Revenue summary display error");
        softAssert.assertEquals(Customer.getGrossProfitValueStable(grossProfit),grossProfitValue*3,"Revenue summary value display error OG");
        softAssert.assertEquals(Customer.getRevenueSummeryValue(margin),marginValue,"Revenue summary value display error OG");

        // Add the product via PDP
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        Customer.clickOnBack();

        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.clickCartSummery();
        softAssert.assertTrue(Customer.isCartSummaryDisplay(cartSummary),"cart summary display error");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalLine),totalLineValue,"cart summary value display error OG");
        softAssert.assertEquals(Customer.getCartSummeryValue(totalQuantity),"4","cart summary value display error OG");

        softAssert.assertTrue(Customer.isRevenueSummaryDisplay(revenueSummary),"Revenue summary display error");
        softAssert.assertEquals(Customer.getGrossProfitValueStable(grossProfit),grossProfitValue*4,"Revenue summary value display error OG");
        softAssert.assertEquals(Customer.getRevenueSummeryValue(margin),marginValue,"Revenue summary value display error OG");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
