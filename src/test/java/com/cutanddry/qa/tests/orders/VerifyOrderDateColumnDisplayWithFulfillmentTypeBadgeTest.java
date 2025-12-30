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

import java.util.regex.Pattern;

public class VerifyOrderDateColumnDisplayWithFulfillmentTypeBadgeTest extends TestBase {
    static User user;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3633")
    public void verifyOrderDateColumnDisplayWithFulfillmentTypeBadge() throws InterruptedException {
        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(), "navigation error to Orders page");

        softAssert.assertTrue(Orders.isOrderDateColumnHeaderDisplayed(), "Order Date column header is not displayed");

        String orderDateText = Orders.getFirstOrderDateText();
        System.out.println("Order Date Text: " + orderDateText);
        Pattern datePattern = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4}$");
        softAssert.assertTrue(datePattern.matcher(orderDateText).matches(),
                "Order date format is not MM/DD/YYYY. Actual: " + orderDateText);

        softAssert.assertTrue(Orders.isFirstOrderFulfillmentBadgeDisplayed(),
                "Fulfillment type badge is not displayed below the order date");

        String fulfillmentBadgeText = Orders.getFirstOrderFulfillmentBadgeText();
        System.out.println("Fulfillment Badge Text: " + fulfillmentBadgeText);
        softAssert.assertTrue(fulfillmentBadgeText != null && !fulfillmentBadgeText.isEmpty(),
                "Fulfillment type badge text is empty");

        String badgeColor = Orders.getFirstOrderFulfillmentBadgeColor();
        System.out.println("Fulfillment Badge Color: " + badgeColor);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
