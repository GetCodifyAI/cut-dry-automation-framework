package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.*;
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
    String DistributorName ="46505655 - Kevin - Independent Foods Co";
    static String status_past_due = PayInvoiceData.STATUS_PAST_DUE;
    static String status_payment = PayInvoiceData.OPTION_PAYMENT;
    String CustomerCode = CustomerInvoiceData.RESTAURANT_TEST_HAYES_ID;
    String CreditMemoNumber = CustomerInvoiceData.CREDIT_MEMO_NUMBER;
    String CreditMemoAmount = CustomerInvoiceData.CREDIT_MEMO_AMOUNT_2;
    String CreditMemoDescription = CustomerInvoiceData.CREDIT_MEMO_DESCRIPTION;


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
        InternalTools.ensureCreditMemoCheckboxStatus(true);
        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isSearchedRowDisplayed(CustomerCode),"The searched customer is not displayed");
        Customer.SelectCustomer(CustomerCode);
        Customer.clickonInvoice();
        Customer.clickDropdownMoreActions();
        Customer.clickCreateCreditMemo();
        String generateCreditMemoNumber = CreditMemoNumber + generateThreeDigitValue();
        Customer.typeCreditMemoNumber(generateCreditMemoNumber);
        Customer.typeCreditMemoAmount(CreditMemoAmount);
        Customer.typeCreditMemoDescription(CreditMemoDescription);
        Customer.clickBtnCreateCreditMemo();
        softAssert.assertTrue(Customer.isCreditMemoFinalized(CreditMemoNumber),"There has been an error creating the credit memo");
        Customer.clickOK();

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        Pay.clickOnInvoiceCustomerClearViaFilter();
        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");

        Pay.selectInvoiceCustomerCodeViaFilter(CustomerCode);
//        softAssert.assertEquals(Pay.getInvoiceRecordCustomerName(1).trim(), customerName.trim(), "The customer name in the first invoice record does not match the expected value.");

        Pay.selectInvoiceStatusViaFilter(status_past_due);
        softAssert.assertTrue(Pay.getInvoiceRecordStatus(1).trim().contains(status_past_due.replace("- ","").trim()), "The past due status in the first invoice record does not match the expected value.");

        Pay.clickOnInvoiceRecord(5);
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
