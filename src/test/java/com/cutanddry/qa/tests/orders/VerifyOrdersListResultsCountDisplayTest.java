package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrdersListResultsCountDisplayTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3645")
    public void verifyOrdersListResultsCountDisplay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(), "navigation error");
        softAssert.assertTrue(Orders.isResultsCountDisplayed(), "results count not displayed");
        String initialCount = Orders.getResultsCount();
        softAssert.assertNotNull(initialCount, "initial results count is null");
        softAssert.assertFalse(initialCount.isEmpty(), "initial results count is empty");
        Orders.selectOrderStatus("Submitted");
        softAssert.assertTrue(Orders.isOrderStatusChanged("Submitted"), "order status filter not applied");
        softAssert.assertTrue(Orders.isResultsCountDisplayed(), "results count not displayed after filter");
        String filteredCount = Orders.getResultsCount();
        softAssert.assertNotNull(filteredCount, "filtered results count is null");
        softAssert.assertFalse(filteredCount.isEmpty(), "filtered results count is empty");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}