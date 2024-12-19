package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.base.TestBase;
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

public class VerifyTheFilterCustomerInvoices extends TestBase {
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
    String CustomerNote = CustomerInvoiceData.CUSTOMER_NOTE;
    String CustomerFilterOption = CustomerInvoiceData.CUSTOMER_FILTER_OPTION;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-691")
    public void VerifyTheFilterCustomerInvoices() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();
        Customer.clickOnDropDownFilter();
        Customer.selectFilterDropDown(CustomerFilterOption);
        softAssert.assertTrue(Customer.isFilterSelectedCorrectly(CustomerFilterOption),"The filter hasn't selected correctly");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
