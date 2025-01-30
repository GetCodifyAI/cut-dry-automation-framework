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

public class VerifyThePayDetailsSummeryTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String total_receivables = PayData.TOTAL_RECEIVABLES;
    static String totalCollected = PayData.TOTAL_COLLECTED;
    static String pastDue = PayData.PAST_DUE;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-876")
    public void VerifyThePayDetailsSummery() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        softAssert.assertTrue(Pay.isUserNavigatedToPay(),"navigation error");
        softAssert.assertTrue(Pay.isPayDetailsDisplayed(total_receivables),"total receivables details display error");
        softAssert.assertTrue(Pay.isPayDetailsAmountDisplayed(total_receivables),"total receivables details display error");
        softAssert.assertTrue(Pay.isPayDetailsDisplayed(totalCollected),"total collected via Cut+Dry details display error");
        softAssert.assertTrue(Pay.isPayDetailsAmountDisplayed(totalCollected),"total collected via Cut+Dry details display error");
        softAssert.assertTrue(Pay.isPayDetailsDisplayed(pastDue),"past due details display error");
        softAssert.assertTrue(Pay.isPastDueAmountDisplayed(),"Past due amount details display error");
        softAssert.assertTrue(Pay.isPaymentProcessingDisplayed(),"Payment Processing details display error");
        softAssert.assertTrue(Pay.isPaymentProcessingAmountDisplayed(),"Payment Processing amount details display error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
