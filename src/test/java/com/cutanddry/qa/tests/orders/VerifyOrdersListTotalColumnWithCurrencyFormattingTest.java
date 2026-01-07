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

public class VerifyOrdersListTotalColumnWithCurrencyFormattingTest extends TestBase {
    static User user;
    String orderId = "794438940";
    SoftAssert softAssert;
    String totalValue1;
    String totalValue2;
    String totalValue3;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3642")
    public void verifyOrdersListTotalColumnWithCurrencyFormatting() throws InterruptedException {
        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(), "navigation error");

        softAssert.assertTrue(Orders.isTotalColumnHeaderDisplayed(), "Total column header is not displayed");

        int totalColumnIndex = Orders.getTotalColumnIndex();
        softAssert.assertTrue(totalColumnIndex > 0, "Total column not found in the orders table");

        totalValue1 = Orders.getTotalValueFromRow(2, totalColumnIndex);
        System.out.println("Total value from row 1: " + totalValue1);

        softAssert.assertTrue(Orders.isTotalAmountFormattedWithDollarSign(totalValue1),
                "Total amount does not start with $ sign: " + totalValue1);

        softAssert.assertTrue(Orders.isTotalAmountFormattedWithTwoDecimalPlaces(totalValue1),
                "Total amount does not have two decimal places: " + totalValue1);

        softAssert.assertTrue(Orders.isTotalAmountFormattedWithCommaForLargeAmounts(totalValue1),
                "Large total amount does not have comma separator: " + totalValue1);

        totalValue2 = Orders.getTotalValueFromRow(3, totalColumnIndex);
        System.out.println("Total value from row 2: " + totalValue2);

        if (!totalValue2.isEmpty()) {
            softAssert.assertTrue(Orders.isTotalAmountFormattedWithDollarSign(totalValue2),
                    "Total amount does not start with $ sign: " + totalValue2);

            softAssert.assertTrue(Orders.isTotalAmountFormattedWithTwoDecimalPlaces(totalValue2),
                    "Total amount does not have two decimal places: " + totalValue2);

            softAssert.assertTrue(Orders.isTotalAmountFormattedWithCommaForLargeAmounts(totalValue2),
                    "Large total amount does not have comma separator: " + totalValue2);
        }

       RestaurantOrderHistory.searchOrderByOrderId(orderId);

        totalValue3 = Orders.getTotalValueFromRow(2, totalColumnIndex);

        System.out.println("Total value from row 3: " + totalValue3);

        if (!totalValue3.isEmpty()) {
            softAssert.assertTrue(Orders.isTotalAmountFormattedWithDollarSign(totalValue2),
                    "Total amount does not start with $ sign: " + totalValue3);

            softAssert.assertTrue(Orders.isTotalAmountFormattedWithTwoDecimalPlaces(totalValue2),
                    "Total amount does not have two decimal places: " + totalValue3);

            softAssert.assertTrue(Orders.isTotalAmountFormattedWithCommaForLargeAmounts(totalValue2),
                    "Large total amount does not have comma separator: " + totalValue3);
        }

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}