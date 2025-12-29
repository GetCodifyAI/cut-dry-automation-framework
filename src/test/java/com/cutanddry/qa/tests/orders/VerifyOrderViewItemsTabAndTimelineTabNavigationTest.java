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
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(), "navigation error");

        Orders.selectOrderDate(date);
        Orders.clickOnFirstOrder();
        softAssert.assertTrue(Orders.isOrderSectionDisplayed(), "order view error");

        softAssert.assertTrue(Orders.isItemsTabActive(), "Items tab not active by default");
        softAssert.assertTrue(Orders.isOrderedItemsSectionDisplayed(), "Ordered Items section not displayed");

        Orders.clickTimeline();
        softAssert.assertTrue(Orders.isTimelineContentDisplayed(), "Timeline content not displayed");

        String[] timelineData = Orders.getTimelineData();
        softAssert.assertNotNull(timelineData[0], "Timeline timestamp not displayed");
        softAssert.assertNotNull(timelineData[1], "Timeline status not displayed");

        Orders.clickItems();
        softAssert.assertTrue(Orders.isItemsTabActive(), "Items tab not active after clicking");
        softAssert.assertTrue(Orders.isOrderedItemsSectionDisplayed(), "Ordered Items section not displayed after clicking Items tab");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
