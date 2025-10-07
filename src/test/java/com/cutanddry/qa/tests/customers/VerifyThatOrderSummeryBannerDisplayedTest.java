package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatOrderSummeryBannerDisplayedTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_ID_SW;
    static String distributor = CustomerData.DISTRIBUTOR_SW;
    static String orderSummery = CustomerData.ORDER_SUMMERY;
    static String orderSummerySalesCommission = CustomerData.ORDER_SUMMERY_SALES_COMMISSION;
    static String orderSummeryTotalLines = CustomerData.ORDER_SUMMERY_TOTAL_LINES;
    static String orderSummeryTotalPieces = CustomerData.ORDER_SUMMERY_TOTAL_PIECES;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-416")
    public void VerifyThatOrderSummeryBannerDisplayed() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.expandShrinkedOrderSummery();
        softAssert.assertTrue(Customer.orderSummeryDisplay(orderSummery),"order summery not display");
        softAssert.assertTrue(Customer.orderSummeryDisplay(orderSummerySalesCommission),"Order Summery Sales Commission not display");
        softAssert.assertTrue(Customer.orderSummeryDisplay(orderSummeryTotalLines),"Order Summery TotalLines not display");
        softAssert.assertTrue(Customer.orderSummeryDisplay(orderSummeryTotalPieces),"Order Summery TotalPieces not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
