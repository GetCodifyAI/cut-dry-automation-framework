package com.cutanddry.qa.tests.customers;

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

public class VerifyUserCanPlaceOrderWithItemAmountDotZeroInEditOrderFlowTest extends TestBase {
    static User user;
    static String customerId = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1348")
    public void verifyUserCanPlaceOrderWithItemAmountDotZeroInEditOrderFlow() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "search error");

        Customer.clickOnOrderGuide(customerId);
        String itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Order not completed");
        String orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "search error after order");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);

        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(), "edit popup error");
        Orders.clickOnConfirm();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed after edit");

        Draft.clickEditOrder();
        double itemPrice = Customer.getActiveItemPriceFirstRow();

        Customer.typeQuantityInFirstRow("1");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice, "Price mismatch for quantity 1 on edit order");

        Customer.typeQuantityInFirstRow(".0");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice * 0, "Price mismatch for quantity .0 on edit order");

        Customer.typeQuantityInFirstRow("1.0");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice, "Price mismatch for quantity 1.0 on edit order");

        Customer.typeQuantityInFirstRow("1.00");
        Customer.clickOutsideQuantityField();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice, "Price mismatch for quantity 1.00 on edit order");

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found in catalog");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice * 2, "Price mismatch after adding item from catalog in edit order");

        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(), "PDP not displayed");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnCheckoutButtonViaPDP() * 100.0) / 100.0,
                Math.round(itemPrice * 3 * 100.0) / 100.0, "Price mismatch after adding item from PDP in edit order");

        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed after checkout");

        Customer.clickConfirm();
        softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(), "update popup error");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
