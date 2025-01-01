package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.pages.PayPage;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFilteringInvoicesByCustomerTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String customerName = PayInvoiceData.CUSTOMER_NAME;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-878")
    public void VerifyTheFilteringInvoicesByCustomer() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        Pay.clickOnInvoiceCustomerClearViaFilter();
        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");
        Pay.selectInvoiceCustomerViaFilter(customerName);
        softAssert.assertEquals(Pay.getInvoiceRecordCustomerName(1).trim(), customerName.trim(), "The customer name in the first invoice record does not match the expected value.");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
