package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheBatchOperationFunctionalityTest extends TestBase
{
    static User user;
    String CustomerCode = CustomerInvoiceData.RESTAURANT_TEST_HAYES_ID;
    static String status_payment = PayInvoiceData.OPTION_FUNDS;
    String CustomerFilterOption = CustomerInvoiceData.CUSTOMER_FILTER_OPTION_PAST_DUE;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-697")
    public void VerifyTheBatchOperationFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode), "Unable to find the customer Id");
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();
        Customer.clickOnDropDownFilter();
        Customer.selectFilterDropDown(CustomerFilterOption);
        softAssert.assertTrue(Customer.isFilterSelectedCorrectly(CustomerFilterOption.replace("- ","").trim()),"The filter hasn't selected correctly");

        Pay.clickOnInvoiceRecord(2);
        Pay.clickOnInvoiceBatchOperationButton();
        Pay.selectTheBatchOperationOption(status_payment);

        softAssert.assertTrue(Pay.isInvoiceCaptureFundPopupDisplayed(), "Unable to see the Capture Funds overlay");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
