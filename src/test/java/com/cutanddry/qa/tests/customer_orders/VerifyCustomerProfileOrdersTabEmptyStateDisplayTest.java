package com.cutanddry.qa.tests.customer_orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCustomerProfileOrdersTabEmptyStateDisplayTest extends TestBase {
    static User user;
    String customerCodeWithNoOrders = "7883823";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3622")
    public void VerifyCustomerProfileOrdersTabEmptyStateDisplay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(customerCodeWithNoOrders);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCodeWithNoOrders),"customer not found");
        Customer.SelectCustomer(customerCodeWithNoOrders);;
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isOrdersEmptyStateDisplayed(),"orders empty state not displayed - customer may have orders");
        softAssert.assertTrue(Customer.isNoOrdersMessageDisplayed(),"no orders message not displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
