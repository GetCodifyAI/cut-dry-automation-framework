package com.cutanddry.qa.tests.e2e;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PlaceAnCustomerDeliveryOrderViaPDPTest extends TestBase {
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

    @Test(groups = "DOT-TC-716")
    public void PlaceAnCustomerDeliveryOrderViaPDP() throws InterruptedException {

        softAssert = new SoftAssert();

        // Distributor Flows
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickAddToCartPDP();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButtonViaPDP(),itemPrice,"The item has not been selected.");
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        softAssert.assertTrue(Customer.isDeliveryOptionSelected(), "The expected fulfillment type is not selected.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        // Restaurant Flows
        Login.switchIntoNewTab();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");

        RestaurantDashboard.navigateToHistory();
        softAssert.assertTrue(RestaurantDashboard.isUserNavigatedToHistory(), "The user is unable to land on the History page.");
        RestaurantOrderHistory.searchOrderByOrderId(orderId);
        softAssert.assertTrue(RestaurantOrderHistory.isOrderSearchResultByOrderIdDisplayed(orderId), "Unable to find the specific order Id");
        RestaurantOrderHistory.clickOnSpecificRecord(orderId);
        softAssert.assertTrue(RestaurantOrderDetails.isOrderIdDisplayed(orderId), "The newly added order ID does not match the order reference number on the Order History page.");
        RestaurantOrderDetails.clickOnCheckIn();

        softAssert.assertTrue(RestaurantCheckIn.isCheckInOrderIdDisplayed(orderId), "The user is unable to land on the Check In page.");
        RestaurantCheckIn.clickOnCheckInAll();
        softAssert.assertTrue(RestaurantCheckIn.isCheckInAllPopupDisplayed(), "The Check In all confirmation popup message is not displayed as expected.");
        RestaurantCheckIn.clickConfirm();

        softAssert.assertTrue(RestaurantOrderDetails.isOrderIdDisplayed(orderId), "The user is unable to navigate back to the Order Details page.");
        softAssert.assertTrue(RestaurantOrderDetails.isOrderStatusDisplayed(DistributorOrderData.CHECKIN_STATUS), "The user is unable to navigate back to the Order Details page.");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
