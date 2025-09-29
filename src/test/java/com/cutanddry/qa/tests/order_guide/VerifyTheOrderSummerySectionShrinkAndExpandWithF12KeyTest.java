package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheOrderSummerySectionShrinkAndExpandWithF12KeyTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_ID;
    static String distributor = CustomerData.DISTRIBUTOR_AFFILIATED;
    static String orderSummery = CustomerData.ORDER_SUMMERY;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-402")
    public void VerifyTheOrderSummerySectionShrinkAndExpandWithF12Key() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.clickF12HotKey();
        softAssert.assertFalse(Customer.shrinkedOrderSummeryDisplayed(),"order summery display");
        Customer.clickF12HotKey();
        Customer.expandShrinkedOrderSummery();
        softAssert.assertTrue(Customer.orderSummeryDisplay(orderSummery),"order summery not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
