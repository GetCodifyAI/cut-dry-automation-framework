package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFilteringInvoicesByDateTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String expectedDate;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-881")
    public void VerifyTheFilteringInvoicesByDate() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to land on the Invoice tab.");

        Pay.clickOnInvoiceCustomerClearViaFilter();
        softAssert.assertTrue(Pay.isEmptyInvoiceMsgDisplayed(), "Invoices are not cleared.");

        expectedDate = generateUTCCurrentDateFormatted();
        String expectedYesterdayDate = generateUTCYesterdayDateFormatted();

        System.out.println(expectedDate+" - "+expectedYesterdayDate);
        Pay.selectInvoiceDateViaFilter(3);
        softAssert.assertTrue(Pay.getInvoiceRecordDate(1).trim().contains(expectedDate.trim()) || Pay.getInvoiceRecordDate(1).trim().contains(expectedYesterdayDate.trim()), "The selected invoice date should be either today or yesterday, but found: " + Pay.getInvoiceRecordDate(1).trim());

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
