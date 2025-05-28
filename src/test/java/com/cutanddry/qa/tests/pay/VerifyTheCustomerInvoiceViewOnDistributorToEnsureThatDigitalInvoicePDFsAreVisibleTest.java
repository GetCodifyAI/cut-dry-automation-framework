package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class VerifyTheCustomerInvoiceViewOnDistributorToEnsureThatDigitalInvoicePDFsAreVisibleTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String invoiceCode;
    static String invoiceDate;
    String DistributorName ="96939129 - Cut+Dry Agent - Hillcrest Foodservice";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-191")
    public void VerifyTheCustomerInvoiceViewOnDistributorToEnsureThatDigitalInvoicePDFsAreVisible() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        String expectedId = Pay.getInvoiceRecordID(1);
        Pay.clickOnInvoiceCustomerClearViaFilter();
//        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");

        Pay.typeInvoiceIDViaFilter(expectedId);
        softAssert.assertEquals(Pay.getInvoiceRecordID(1), expectedId, "The invoice id in the invoice record does not match the expected value.");
        invoiceCode=Pay.getInvoiceCode();
        invoiceDate=Pay.getInvoiceDate();
        Pay.clickOneInvoice();
        softAssert.assertTrue(Pay.navigateInvoice(expectedId),"navigate invoice error");
        Pay.clickDownloadInvoice();
        Pay.clickPrintInvoice();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
