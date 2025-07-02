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

public class VerifyTheCartSummeryAndRevenueSummeryIsDisplayedOnTheReviewScreenTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemName, searchItemCode;
    static double itemPrice;
    static String cartSummary = "Cart Summary";
    static String totalLine = "Total Line items";
    static String totalQuantity = "Total Quantity";
    static String revenueSummary = "Revenue Summary";
    static String grossProfit = "Gross Profit";
    static String margin = "Margin";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1394")
    public void VerifyTheCartSummeryAndRevenueSummeryIsDisplayedOnTheReviewScreen() throws InterruptedException {

        softAssert = new SoftAssert();

        // Distributor Flows
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        // Add the product via Order Guide
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");

        // Add the product via Catalog
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*2,"The item has not been selected.");

        // Add the product via PDP
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnCheckoutButtonViaPDP() * 100.0) / 100.0,
                Math.round(itemPrice * 3 * 100.0) / 100.0, "The item has not been selected.");
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.clickCartSummery();
        softAssert.assertTrue(Customer.isCartSummaryDisplay(cartSummary),"cart summary display error");
        softAssert.assertTrue(Customer.isCartSummaryValueDisplay(totalLine),"cart summary value display error");
        softAssert.assertTrue(Customer.isCartSummaryValueDisplay(totalQuantity),"cart summary value display error");

        softAssert.assertTrue(Customer.isRevenueSummaryDisplay(revenueSummary),"Revenue summary display error");
        softAssert.assertTrue(Customer.isRevenueSummaryValueDisplay(grossProfit),"Revenue summary value display error");
        softAssert.assertTrue(Customer.isRevenueSummaryValueDisplay(margin),"Revenue summary value display error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
