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

public class VerifyTheCaptureFundForBulkInvoicesTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String status_past_due = PayInvoiceData.STATUS_PAST_DUE;
    static String status_unpaid = PayInvoiceData.STATUS_UNPAID;
    static String status_payment = PayInvoiceData.OPTION_PAYMENT;
    static String status_authorized = PayInvoiceData.STATUS_AUTHORIZED;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-888")
    public void VerifyTheCaptureFundForBulkInvoicesTest() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        Pay.clickOnInvoiceCustomerClearViaFilter();
        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");

        Pay.selectInvoiceStatusViaFilter(status_past_due);
        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_past_due.replace("- ","").trim()), "The past due status in the first invoice record does not match the expected value.");
        Pay.selectInvoiceAuthStatusViaFilter(status_authorized);
        softAssert.assertTrue(Pay.isInvoiceRecordCustomerStatusExist(status_authorized), "The auth status in the invoice record does not match the expected value.");

        Pay.clickOnInvoiceRecord(1);
        Pay.clickOnInvoiceBulkActionButton();
        Pay.selectTheBulkInvoiceOption(status_payment);

        softAssert.assertTrue(Pay.isInvoiceCaptureFundPopupDisplayed(), "Unable to see the Capture Funds overlay");
        Pay.clickOnInvoiceCaptureFundPay();
        softAssert.assertTrue(Pay.isInvoiceCaptureFundDisplayed(), "Unable to see the Capture Fund invoice overlay");
        Pay.clickOnYes();
        softAssert.assertTrue(Pay.isErrorPopUpDisplayed(), "Unable to see the error overlay");
        Pay.clickOkPopUp();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
