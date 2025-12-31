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

public class VerifyOrderViewItemsTabAndTimelineTabNavigationTest extends TestBase {
    static User user;
    String date = "Last 90 Days";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3626")
    public void verifyOrderViewItemsTabAndTimelineTabNavigation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(), "navigation to orders error");

        Orders.selectOrderDate(date);
        Orders.clickOnFirstOrder();
        softAssert.assertTrue(Orders.isOrderSectionDisplayed(), "order view page not loaded");

        softAssert.assertTrue(Orders.isItemsTabActive(), "Items tab is not active by default");
        softAssert.assertTrue(Orders.isOrderedItemsSectionDisplayed(), "Ordered Items section is not displayed");

        Orders.clickTimeline();
        softAssert.assertTrue(Orders.isTimelineTabActive(), "Timeline tab is not active after clicking");
        softAssert.assertTrue(Orders.isTimelineContentDisplayed(), "Timeline content is not displayed");

        Orders.clickItems();
        softAssert.assertTrue(Orders.isItemsTabActive(), "Items tab is not active after clicking back");
        softAssert.assertTrue(Orders.isOrderedItemsSectionDisplayed(), "Ordered Items section is not displayed after clicking back");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
