package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyEditAutoPayDetailsEnabledStateinCustomerInvoice extends TestBase {
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
    String ScheduleOption = CustomerInvoiceData.SCHEDULE_OPTION;

    @BeforeMethod
    public void setUp() {
        // Test Initialization
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-684")
    public void VerifyEditAutoPayDetailsEnabledStateinCustomerInvoice() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();
        Customer.editAutoPay();
        Customer.clickOnAutoPayScheduleDropdown();
        Customer.selectDropdownOption(ScheduleOption);
        Customer.clickOnUpdate();
        softAssert.assertTrue(Customer.isAutoPayUpdated(ScheduleOption),"There has been an error updated auto Pay" );
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture a screenshot if the test fails
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshotOnFailure(result);
            System.out.println("Screenshot captured for failed test case.");
        }

        // Close the browser
        closeAllBrowsers();
        System.out.println("Browser closed successfully.");
    }
}
