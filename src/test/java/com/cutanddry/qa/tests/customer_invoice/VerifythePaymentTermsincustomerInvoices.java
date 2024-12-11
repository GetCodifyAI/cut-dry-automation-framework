package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.CreditRequests;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifythePaymentTermsincustomerInvoices extends TestBase
{
    static User user;
    String timeRange = "All";
    String Customer_Code = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-678")
    public void VerifythePaymentTermsincustomerInvoices() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isErrorTextNotDisplayed(),"Error Message Displayed");
        Customer.searchCustomerByCode(Customer_Code);
        Customer.clickOnFirstItemOfCreditRequests();
        Customer.clickonInvoice();
        Customer.verifyPaymentTermStatus();

    }
}
