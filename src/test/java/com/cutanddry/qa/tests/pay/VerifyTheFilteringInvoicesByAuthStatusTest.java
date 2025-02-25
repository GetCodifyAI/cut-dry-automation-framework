package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFilteringInvoicesByAuthStatusTest extends TestBase {
    static User user;
    SoftAssert softAssert;

    static String status_all = PayInvoiceData.STATUS_ALL;
    static String status_authorized = PayInvoiceData.STATUS_AUTHORIZED;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-882")
    public void VerifyTheFilteringInvoicesByAuthStatus() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        Pay.clickOnInvoiceCustomerClearViaFilter();
        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");

        Pay.selectInvoiceAuthStatusViaFilter(status_authorized);
        softAssert.assertTrue(Pay.isInvoiceRecordCustomerStatusExist(status_authorized), "The auth status in the invoice record does not match the expected value.");

        Pay.selectInvoiceAuthStatusViaFilter(status_all);
        softAssert.assertFalse(Pay.isInvoiceRecordCustomerStatusExist(status_authorized), "The status in the invoice record does match the expected.");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
