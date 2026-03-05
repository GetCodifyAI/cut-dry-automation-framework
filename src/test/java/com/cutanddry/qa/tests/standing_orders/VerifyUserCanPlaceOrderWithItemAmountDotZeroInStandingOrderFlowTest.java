package com.cutanddry.qa.tests.standing_orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyUserCanPlaceOrderWithItemAmountDotZeroInStandingOrderFlowTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String deliveryDay = "Monday";
    static String title = "Test Standing Order";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1349")
    public void verifyUserCanPlaceOrderWithItemAmountDotZeroInStandingOrderFlow() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "search error");

        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isStandingOrdersDisplayed(), "navigation error");

        Customer.removeStandingOrdersIfAvailable();
        Customer.clickOnCreateStandingOrder();

        String itemName = Customer.getItemNameFirstRow();

        double itemPrice = Customer.getActiveItemPriceFirstRow();

        Customer.typeQuantityInFirstRow("1");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice, "Price mismatch for quantity 1 on standing order");

        Customer.typeQuantityInFirstRow(".0");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice * 0, "Price mismatch for quantity .0 on standing order");

        Customer.typeQuantityInFirstRow("1.0");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice, "Price mismatch for quantity 1.0 on standing order");

        Customer.typeQuantityInFirstRow("1.00");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice, "Price mismatch for quantity 1.00 on standing order");

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found in catalog");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice * 2, "Price mismatch after adding item from catalog in standing order");

        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(), "PDP not displayed");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnCheckoutButtonViaPDP() * 100.0) / 100.0,
                Math.round(itemPrice * 3 * 100.0) / 100.0, "Price mismatch after adding item from PDP in standing order");

        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed");

        Customer.typeOnStandingOrderTitle(title);
        Customer.selectDeliveryDate(deliveryDay);

        String reviewItemName = Customer.getItemNameFirstRow();
        softAssert.assertNotNull(reviewItemName, "Item not displayed in review cart");
        softAssert.assertTrue(!reviewItemName.isEmpty(), "Item name is empty in review cart");

        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(), "email popup not displayed");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(), "standing order creation error");
        Customer.clickOK();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
