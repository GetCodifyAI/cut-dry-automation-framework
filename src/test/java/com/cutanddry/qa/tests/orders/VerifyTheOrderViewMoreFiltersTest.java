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

public class VerifyTheOrderViewMoreFiltersTest extends TestBase {
    static User user;
    String date = "Last 90 Days";
    String creditStatus = "Credit Requested";
    static String type = "Requested";
    static String salesPersonType = "All";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-545")
    public void VerifyTheOrderViewMoreFilters() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigation error");
        Orders.selectOrderDate(date);
        softAssert.assertTrue(Orders.isOrderDateChanged(date),"dropdown error");
        Orders.clickOnMoreFilters();
        softAssert.assertTrue(Orders.isFilterOrdersPopupDisplayed(),"popup error");
//        Orders.selectCreditReqStatus();
        Orders.selectSalespersonStatusStable(salesPersonType);
        Orders.selectCreditReqStatusStable(type);
        softAssert.assertTrue(Orders.checkFiltersCorrectlyDisplayed(creditStatus),"Error in adding more filters");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
