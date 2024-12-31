package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateSearchCustomerPaymentTest extends TestBase {
    static User user;
    static String customerCode = PayData.CUSTOMER_CODE;
    static String customerName = PayData.CUSTOMER_NAME;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-910")
    public void ValidateSearchCustomerPayment() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToPay();
        softAssert.assertTrue(Pay.isUserNavigatedToPay(),"navigation error");
        Pay.searchCustomer(customerName);
        softAssert.assertTrue(Pay.isSearchCustomerDisplayed(customerName),"Search customer by customer Name not display");
        Pay.searchCustomer(customerCode);
        softAssert.assertTrue(Pay.isSearchCustomerDisplayed(customerCode),"Search customer by customer Name not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
