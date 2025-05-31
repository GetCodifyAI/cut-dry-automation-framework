package com.cutanddry.qa.tests.e2e;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PlaceAnCustomerPickUpOrderViaCatalogTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String itemName, orderId, searchItemCode;
    static double itemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-765")
    public void placeCustomerPickUpOrderViaCatalog() throws InterruptedException {
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

    private void loginAsDistributor() throws InterruptedException {
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Failed to navigate to Dashboard.");
        Customer.ensureCarouselDisplayStatus(false);
    }

    private void navigateToCustomerOrderGuide() throws InterruptedException {
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer not found.");
        Customer.clickOnOrderGuide(customerId);
    }

    private void selectItemFromCatalog() throws InterruptedException {
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "Item not found.");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(), itemPrice, "Item not selected.");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Failed to navigate to Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "Item mismatch on Review Order page.");
        Customer.selectPickUpWillCall();
        softAssert.assertTrue(Customer.isPickUpOptionSelected(), "Pick-up option not selected.");
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
