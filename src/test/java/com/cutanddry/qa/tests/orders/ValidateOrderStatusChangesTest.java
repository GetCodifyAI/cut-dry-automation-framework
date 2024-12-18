package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class ValidateOrderStatusChangesTest extends TestBase {
    static User user;
    List<String> statuses = Arrays.asList("Submitted", "Confirmed", "Invoiced", "Fulfilled", "Shipped", "Delivered","Checked In","Merged-out");

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-798")
    public void ValidateOrderStatusChanges() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigation error");
        Orders.clickOnFirstOrder();
        softAssert.assertTrue(Orders.isOrderSectionDisplayed(),"order section not displayed");
        for (String status : statuses) {
            Orders.clickOrderStatus();
            Orders.selectOrderStatusOption(status);
            softAssert.assertTrue(Orders.isOrderStatusUpdatedPopUpDisplayed(), "Order status updated pop-up not displayed for status: " + status);
            Orders.clickOkUpdate();
            softAssert.assertTrue(Orders.isOrderStatusUpdatedDisplayed(status), "Order status not updated for status: " + status);
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
