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
    String OrderID = "794428453";

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
        Orders.isOrderInfoCustomerDisplayed();


        softAssert.assertTrue(Orders.isOrderInfoCustomerDisplayed(), "Customer field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoLocationCodeDisplayed(), "Location Code field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoDeliveryOnDisplayed(), "Delivery on field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoShipToDisplayed(), "Ship To field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoFulfilmentMethodDisplayed(), "Fulfilment Method field is not displayed in Order Info section");
        softAssert.assertTrue(Orders.isOrderInfoStatusDisplayed(), "Status field is not displayed in Order Info section");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
