package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VerifyTheOrderSummeryIsDisplayedCorrectlyOnTheEditOrderFlowTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemName, orderId;
    static double itemPrice;
    static String orderSummery = CustomerData.ORDER_SUMMERY;
    static String orderSummerySalesCommission = CustomerData.ORDER_SUMMERY_SALES_COMMISSION;
    static String orderSummeryTotalLines = CustomerData.ORDER_SUMMERY_TOTAL_LINES;
    static String orderSummeryTotalPieces = CustomerData.ORDER_SUMMERY_TOTAL_PIECES;
    static String itemCode = CustomerData.PRODUCT_ITEM_CODE;
    static double marginPrice;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1506")
    public void VerifyTheOrderSummeryIsDisplayedCorrectlyOnTheEditOrderFlow() throws InterruptedException {

        softAssert = new SoftAssert();

        // Distributor Flows
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.searchItemOnOrderGuide(itemCode);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        marginPrice = Customer.getItemMarginPriceFirstRow();
        BigDecimal marginValue = new BigDecimal(Double.toString(marginPrice));
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

        // Verify in the review screen
        Customer.expandShrinkedOrderSummery();
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.setScale(1, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummerySalesCommission,"0"),"Order Summery Sales Commission value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"1"),"Order Summery TotalPieces value not equal");

        Draft.clickEditOrder();

        Customer.searchItemOnOrderGuide(itemCode);
        Customer.increaseFirstRowQtyCustom(1);

        // Verify in the order Guide
        Customer.expandShrinkedOrderSummery();
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.multiply(BigDecimal.valueOf(2)).setScale(2, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummerySalesCommission,"0"),"Order Summery Sales Commission value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"2"),"Order Summery TotalPieces value not equal");

        // Add the product via Catalog
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        Customer.expandShrinkedOrderSummery();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*3,"The item has not been selected.");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.multiply(BigDecimal.valueOf(3)).setScale(2, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"3"),"Order Summery TotalPieces value not equal");

        // Add the product via PDP
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnCheckoutButtonViaPDP() * 100.0) / 100.0,
                Math.round(itemPrice * 3 * 100.0) / 100.0, "The item has not been selected.");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.multiply(BigDecimal.valueOf(4)).setScale(2, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"4"),"Order Summery TotalPieces value not equal");
        Customer.clickCheckOutPDP();
        Customer.clickConfirm();
        softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(),"update popup error");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
