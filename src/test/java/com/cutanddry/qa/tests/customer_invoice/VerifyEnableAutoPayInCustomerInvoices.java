package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyEnableAutoPayInCustomerInvoices extends TestBase
{
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;

    @BeforeMethod
    public void setUp() {
        // Test Initialization
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-683")
    public void VerifyEnableAutoPayInCustomerInvoices() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
//        Customer.searchCustomerByCode(CustomerCode);
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode), "Unable to find the customer Id");
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();
        Customer.clickOnEnable();
        Customer.clickOnIAgree();
        Customer.clickOnEnableAutoPay();
        softAssert.assertTrue(Customer.isAutoPayEnabled(), "Auto pay has not been enabled properly");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture a screenshot if the test fails
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshotOnFailure(result);
            System.out.println("Screenshot captured for failed test case.");
        }

        // Close the browser
        closeAllBrowsers();
        System.out.println("Browser closed successfully.");
    }
}
