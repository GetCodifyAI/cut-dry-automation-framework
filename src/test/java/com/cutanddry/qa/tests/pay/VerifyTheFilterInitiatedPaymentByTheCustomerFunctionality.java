package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFilterInitiatedPaymentByTheCustomerFunctionality extends TestBase{
    static User user;
    SoftAssert softAssert;
    static String customerName = "Cafe Pay Demo";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-889")
    public void VerifyTheFilterInitiatedPaymentByTheCustomerFunctionality() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToPay();
        Pay.clickOnPaymentsInit();
        softAssert.assertTrue(Pay.isPaymentsInitBtnSelected(), "The user is unable to land on the Invoice tab.");
        Pay.selectOptionCustomerDropdown(customerName);
        softAssert.assertTrue(Pay.isCustomerNameCorrect(customerName), "The correct customer name is not displayed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}