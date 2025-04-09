package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheAutoApplyCreditMemosOnAllCustomerDPPortalWhenFeatureEnabledTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    static String status_past_due = PayInvoiceData.STATUS_PAST_DUE;
    static String customerName = PayInvoiceData.CUSTOMER_NAME2;
    static String status_payment = PayInvoiceData.OPTION_PAYMENT;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-972")
    public void VerifyTheAutoApplyCreditMemosOnAllCustomerDPPortalWhenFeatureEnabled() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToPayDetailsTab();
        InternalTools.clickCreditMemoCheckbox(true);
        InternalTools.clickSave();
      //  softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
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
        Pay.selectTheBulkInvoiceOption(status_payment);

        softAssert.assertTrue(Pay.isInvoiceCaptureFundPopupDisplayed(), "Unable to see the Capture Funds overlay");
        softAssert.assertFalse(Pay.isCreditMemoDisplayed(),"credit memo not display");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
