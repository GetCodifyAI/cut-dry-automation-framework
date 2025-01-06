package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.cutanddry.qa.base.TestBase;

public class VerifyTheCreateCreditMemoInMoreActionsTest extends TestBase {
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
    String CreditMemoNumber = CustomerInvoiceData.CREDIT_MEMO_NUMBER;
    String AssociatedInvoive = CustomerInvoiceData.ASSOCIATED_INVOICE;
    String CreditMemoAmount = CustomerInvoiceData.CREDIT_MEMO_AMOUNT;
    String CreditMemoDescription = CustomerInvoiceData.CREDIT_MEMO_DESCRIPTION;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-702")
    public void VerifyTheCreateCreditMemoInMoreActions() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isSearchedRowDisplayed(CustomerCode),"The searched customer is not displayed");
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();
        Customer.clickDropdownMoreActions();
        Customer.clickCreateCreditMemo();
        Customer.typeCreditMemoNumber(CreditMemoNumber);
        Customer.fillDropdownAssociatedInvoice(AssociatedInvoive);
        Customer.typeCreditMemoAmount(CreditMemoAmount);
        Customer.typeCreditMemoDescription(CreditMemoDescription);
        Customer.clickBtnCreateCreditMemo();
        softAssert.assertTrue(Customer.isCreditMemoFinalized(CreditMemoNumber),"There has been an error creating the credit memo");
        Customer.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
