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

public class VerifyTheBatchOperationFunctionality extends TestBase {
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
//    String CustomerFilterOption = CustomerInvoiceData.CUSTOMER_FILTER_OPTION;

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
        softAssert.assertTrue(Customer.isSearchedRowDisplayed(CustomerCode),"The searched customer is not displayed");
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();


        softAssert.assertAll();
    }

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        takeScreenshotOnFailure(result);
//        closeAllBrowsers();
//    }
}
