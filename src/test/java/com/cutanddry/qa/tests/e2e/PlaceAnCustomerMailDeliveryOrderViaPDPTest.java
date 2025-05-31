package com.cutanddry.qa.tests.e2e;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.SplitWeightUOMData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PlaceAnCustomerMailDeliveryOrderViaPDPTest extends TestBase {
    private SoftAssert softAssert;
    private User user;
    private static final String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    private static final String sortOption = DistributorOrderData.SORT_ITEM_BY;
    private String itemName, orderId, searchItemCode;
    private double itemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-718")
    public void placeCustomerMailDeliveryOrderViaPDP() throws InterruptedException {
        softAssert = new SoftAssert();

        // Distributor Flow
        loginAsDistributor();
        navigateToCustomerOrderGuide();
        selectItemFromCatalog();
        submitOrder();

        // Restaurant Flow
        verifyOrderInRestaurantHistory();
        checkInOrder();

        softAssert.assertAll();
    }

    private void loginAsDistributor() {
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Failed to navigate to Dashboard.");
    }

    private void navigateToCustomerOrderGuide() throws InterruptedException {
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer not found.");
        Customer.clickOnOrderGuide(customerId);
        Customer.selectSortItemByOption(sortOption);
    }

    private void selectItemFromCatalog() throws InterruptedException {
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "Item not found.");
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(), "Failed to navigate to Product Details page.");
        Customer.clickAddToCartPDP();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButtonViaPDP(), itemPrice, "Item not selected.");
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Failed to navigate to Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "Item mismatch on Review Order page.");
        Customer.selectMailDelivery();
        softAssert.assertTrue(Customer.isMailDeliveryOptionSelected(), "Mail delivery option not selected.");
    }

    private void submitOrder() {
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Order submission failed.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();
    }

    private void verifyOrderInRestaurantHistory() throws InterruptedException {
        Login.switchIntoNewTab();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Failed to navigate to Restaurant Dashboard.");
        RestaurantDashboard.navigateToHistory();
        Assert.assertTrue(RestaurantDashboard.isUserNavigatedToHistory(), "Failed to navigate to History page.");
        RestaurantOrderHistory.searchOrderByOrderId(orderId);
        softAssert.assertTrue(RestaurantOrderHistory.isOrderSearchResultByOrderIdDisplayed(orderId), "Order not found.");
        RestaurantOrderHistory.clickOnSpecificRecord(orderId);
        softAssert.assertTrue(RestaurantOrderDetails.isOrderIdDisplayed(orderId), "Order ID mismatch.");
    }

    private void checkInOrder() throws InterruptedException {
        RestaurantOrderDetails.clickOnCheckIn();
        softAssert.assertTrue(RestaurantCheckIn.isCheckInOrderIdDisplayed(orderId), "Failed to navigate to Check In page.");
        RestaurantCheckIn.clickOnCheckInAll();
        softAssert.assertTrue(RestaurantCheckIn.isCheckInAllPopupDisplayed(), "Check In confirmation popup not displayed.");
        RestaurantCheckIn.clickConfirm();
        softAssert.assertTrue(RestaurantOrderDetails.isOrderIdDisplayed(orderId), "Failed to navigate back to Order Details page.");
        softAssert.assertTrue(RestaurantOrderDetails.isOrderStatusDisplayed(DistributorOrderData.CHECKIN_STATUS), "Order status mismatch.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
