package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
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

public class VerifyTheOrderSummeryDisplayedOnDPPortalTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemName = "J. Hungerford Smith Chocolate Cone Coating";
    static String searchItemCode = "21981";
    static String brandName = "J. Hungerford Smith";
    static double itemPrice;
    static double marginPrice;
    static String orderSummeryTotalLines = CustomerData.ORDER_SUMMERY_TOTAL_LINES;
    static String orderSummeryTotalPieces = CustomerData.ORDER_SUMMERY_TOTAL_PIECES;
    static String orderSummery = CustomerData.ORDER_SUMMERY;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1394")
    public void VerifyTheOrderSummeryDisplayedOnDPPortal() throws InterruptedException {

        softAssert = new SoftAssert();

        // Distributor Flows
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        // Add the product via Order Guide
        Customer.searchItemOnOrderGuide(searchItemCode);
        itemPrice = Customer.getActiveItemPriceFirstRow();
        marginPrice = Customer.getItemMarginPriceFirstRow();
        BigDecimal marginValue = new BigDecimal(Double.toString(marginPrice));
        marginValue = marginValue.setScale(2, RoundingMode.DOWN);
        Customer.expandShrinkedOrderSummery();
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.setScale(1, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"1"),"Order Summery TotalPieces value not equal");

        // Add the product via Catalog
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*2,"The item has not been selected.");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.multiply(BigDecimal.valueOf(2)).setScale(2, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"2"),"Order Summery TotalPieces value not equal");

        // Add the product via PDP
        Customer.clickOnProduct(itemName);
        Customer.navigateToBrandPage(brandName);
        softAssert.assertTrue(Customer.isNavigatedToBrandPage(brandName),"The user is unable to land on the brand Details page.");
        Customer.clickOnBackButton();
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnCheckoutButtonViaPDP() * 100.0) / 100.0,
                Math.round(itemPrice * 3 * 100.0) / 100.0, "The item has not been selected.");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.multiply(BigDecimal.valueOf(3)).setScale(2, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"3"),"Order Summery TotalPieces value not equal");
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.expandShrinkedOrderSummery();
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.multiply(BigDecimal.valueOf(3)).setScale(2, RoundingMode.DOWN).toPlainString()), "gross profit value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"3"),"Order Summery TotalPieces value not equal");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
