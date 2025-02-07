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

public class VerifyTheOrderViewDateDropdownTest extends TestBase {
    static User user;
    String date = "Yesterday";
    static String expectedDate;
    String status = "All";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-543")
    public void VerifyTheOrderViewDateDropdown() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

//        ZonedDateTime yesterdayUTC = ZonedDateTime.now(ZoneOffset.UTC).minusDays(1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        String yesterdayDate = yesterdayUTC.format(formatter);
        expectedDate = Orders.getLastWorkingDateUST();
        System.out.println(expectedDate);

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigation error");
        Orders.selectOrderDate(date);
        Orders.selectOrderStatus(status);
        softAssert.assertTrue(Orders.isOrderDateChanged(date),"dropdown error");
        softAssert.assertTrue(Orders.validateFilteredOrders(expectedDate),"Error in filtering order dates");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
