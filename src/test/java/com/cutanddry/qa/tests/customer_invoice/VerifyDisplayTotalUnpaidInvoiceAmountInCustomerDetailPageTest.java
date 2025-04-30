package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
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

public class VerifyDisplayTotalUnpaidInvoiceAmountInCustomerDetailPageTest extends TestBase
{
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
    static String upcomingDue = CustomerInvoiceData.UPCOMING_DUE;
    static String pastDue = CustomerInvoiceData.PAST_DUE;
    static String totalDue = CustomerInvoiceData.TOTAL_DUE;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-976")
    public void VerifyDisplayTotalUnpaidInvoiceAmountInCustomerDetailPage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode), "Unable to find the customer Id");
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();
        softAssert.assertTrue(Customer.isUnpaidInvoiceNamDisplayed(upcomingDue),"Upcoming Due not display");
        softAssert.assertTrue(Customer.isUnpaidInvoiceNamDisplayed(pastDue),"Past Due not display");
        softAssert.assertTrue(Customer.isUnpaidInvoiceNamDisplayed(totalDue),"Total Due not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
