package com.cutanddry.qa.tests.orders;

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

public class VerifyTheClickOnQuantityPickerAndIncrementTheItemCountFromQuantityPickerAndIncrementItemCountFromPlusIconTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String itemName,itemQuantity;
    static String searchItemCode = "01700";
    static double itemPrice,totalItemPriceReviewOrder;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1360")
    public void VerifyTheClickOnQuantityPickerAndIncrementTheItemCountFromQuantityPickerAndIncrementItemCountFromPlusIcon() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        // Add the product via Order Guide
        Customer.searchItemOnOrderGuide(searchItemCode);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.addAmountUsingDataPickerOG(itemName,"5");
        itemQuantity = Customer.getAmountUsingDataPickerOG(itemName);
        softAssert.assertEquals(Customer.getAmountUsingDataPickerOG(itemName),"5","The item has not been updated OG data picker.");
        Customer.clickOnPlusIconInCatalogPDP(3, itemName);
        softAssert.assertEquals(Customer.getAmountUsingDataPickerOG(itemName),"8","The item has not been updated OG +");
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*8,"The item has not been selected.");

        // Add the product via Catalog
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*9,"The item has not been selected.");
        Customer.addAmountUsingDataPickerCatalog(itemName,"12");
        softAssert.assertEquals(Customer.getAmountUsingDataPickerCatalogAndPDP(itemName),"12","The item has not been updated catalog data picker");
        Customer.clickOnPlusIconInCatalogPDP(3, itemName);
        softAssert.assertEquals(Customer.getAmountUsingDataPickerCatalogAndPDP(itemName),"15","The item has not been updated catalog +.");
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*15,"The item has not been selected.");

        // Add the product via PDP
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnCheckoutButtonViaPDP() * 100.0) / 100.0, Math.round(itemPrice * 16 * 100.0) / 100.0, "The item has not been selected.");
        Customer.addAmountUsingDataPickerCatalog(itemName,"18");
        softAssert.assertEquals(Customer.getAmountUsingDataPickerCatalogAndPDP(itemName),"18","The item has not been updated PDP data picker");
        Customer.clickOnPlusIconInCatalogPDP(2, itemName);
        softAssert.assertEquals(Customer.getAmountUsingDataPickerCatalogAndPDP(itemName),"20","The item has not been updated PDP +");
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnCheckoutButtonViaPDP() * 100.0) / 100.0, Math.round(itemPrice * 20 * 100.0) / 100.0, "The item has not been selected.");
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.addAmountUsingDataPickerReviewOrder(itemName,"25");
        softAssert.assertEquals(Customer.getAmountUsingDataPickerReviewOrder(itemName),"25","The item has not been updated review order page data picker");
        Customer.clickOnPlusIconInReviewOrder(1, itemName);
        softAssert.assertEquals(Customer.getAmountUsingDataPickerReviewOrder(itemName),"26","The item has not been updated review order page +");
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        softAssert.assertEquals(Math.round(totalItemPriceReviewOrder * 100.0) / 100.0, Math.round(itemPrice * 26 * 100.0) / 100.0, "The item has not been selected.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
