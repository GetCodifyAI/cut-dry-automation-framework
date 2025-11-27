package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.openqa.selenium.devtools.v127.network.model.TrustTokenOperationDone;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFilteringInvoicesByStatusTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String status_past_due = PayInvoiceData.STATUS_PAST_DUE;
    static String status_unpaid = PayInvoiceData.STATUS_UNPAID;
    static String status_upcoming = PayInvoiceData.STATUS_UPCOMING_DUE;
    static String status_scheduled = PayInvoiceData.STATUS_SCHEDULED;
    static String status_processing = PayInvoiceData.STATUS_PROCESSING;
    static String status_paid = PayInvoiceData.STATUS_PAID;
    static String DistributerName = "Hillcrest";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-879")
    public void VerifyTheFilteringInvoicesByStatus() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        Pay.clickOnInvoiceCustomerClearViaFilter();
        Thread.sleep(4000);
        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");

        Pay.selectInvoiceStatusViaFilter(status_past_due);
        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_past_due.replace("- ","").trim()), "The past due status in the first invoice record does not match the expected value.");

        Pay.selectInvoiceStatusViaFilter(status_upcoming);
        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_unpaid.trim()), "The unpaid status in the first invoice record does not match the expected value.");

        // TODO: Need to uncomment the following status_scheduled test steps after fixing the prerequisite for status_scheduled creation.
//        Pay.selectInvoiceStatusViaFilter(status_scheduled);
//        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_scheduled.trim()), "The scheduled status in the first invoice record does not match the expected value.");

        Pay.selectInvoiceStatusViaFilter(status_processing);
        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_processing.trim()), "The processing status in the first invoice record does not match the expected value.");

        Pay.selectInvoiceStatusViaFilter(status_paid);
        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_paid.trim()), "The paid status in the first invoice record does not match the expected value.");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
