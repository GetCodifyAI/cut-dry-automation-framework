package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.functions.RestaurantOrderHistory;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderViewOrderInfoSectionFieldDisplayTest extends TestBase {
    static User user;
    String date = "Last 90 Days";
    String OrderID = "867523366";
    String customerName = "Kafe Layers #3 Test (16579)";
    String customerLocation = "San Francisco (28070)";
    String deliveryOn = "Tue, 03/24/2026";
    String shipTo = "Kafe Layers #3 Test, Test street, Test city, Test state 28070";
    String fulfilmentMethod = "Delivery";
    String orderStatus = "Confirmed";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3627")
    public void VerifyOrderViewOrderInfoSectionFieldDisplay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(), "navigation error");

        Orders.selectOrderDate(date);
        RestaurantOrderHistory.searchOrderByOrderId(OrderID);
        Orders.clickOnFirstOrder();
        softAssert.assertTrue(Orders.isOrderSectionDisplayed(), "Order View page did not load");


        softAssert.assertTrue(Orders.isOrderInfoCustomerDisplayed(customerName), "Customer field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoLocationCodeDisplayed(customerLocation), "Location Code field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoDeliveryOnDisplayed(deliveryOn), "Delivery on field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoShipToDisplayed(shipTo), "Ship To field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoFulfilmentMethodDisplayed(fulfilmentMethod), "Fulfilment Method field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoStatusDisplayed(orderStatus), "Status field is not displayed in Order Info section");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
