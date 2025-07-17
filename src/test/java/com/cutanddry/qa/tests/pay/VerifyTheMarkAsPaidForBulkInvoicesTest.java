package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheMarkAsPaidForBulkInvoicesTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String status_past_due = PayInvoiceData.STATUS_PAST_DUE;
    static String status_unpaid = PayInvoiceData.STATUS_UNPAID;
    static String status_capturePaid = PayInvoiceData.OPTION_PAID;
    static String customerName = PayInvoiceData.CUSTOMER_NAME;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-887")
    public void VerifyTheMarkAsPaidForBulkInvoices() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        Pay.clickOnInvoiceCustomerClearViaFilter();
        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");

        Pay.selectInvoiceCustomerViaFilter(customerName);
        softAssert.assertEquals(Pay.getInvoiceRecordCustomerName(1).trim(), customerName.trim(), "The customer name in the first invoice record does not match the expected value.");


        Pay.selectInvoiceStatusViaFilter(status_past_due);
        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_past_due.replace("- ","").trim()), "The past due status in the first invoice record does not match the expected value.");

        Pay.clickOnInvoiceRecord(2);
        Pay.clickOnInvoiceBulkActionButton();
        Pay.selectTheBulkInvoiceOption(status_capturePaid);
        softAssert.assertTrue(Pay.isMarkPaidInvoicePopupDisplayed(), "Unable to see the mark this invoice as Paid overlay");
        // TODO: Once the invoice flow identify, added the invoice flow and uncomment the following code
        Pay.clickOnYes();
        softAssert.assertTrue(Pay.isSuccessPopUpDisplayed(), "Unable to see the email sent overlay");
        Pay.clickOkPopUp();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
