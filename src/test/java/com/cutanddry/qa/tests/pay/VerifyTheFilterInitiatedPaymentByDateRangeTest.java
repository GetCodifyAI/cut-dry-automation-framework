package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class VerifyTheFilterInitiatedPaymentByDateRangeTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    String startDay = "Sunday";
    String startMonth = "December";
    String startDate = "1";
    String startYear = "2024";

    String endDay = "Saturday";
    String endMonth = "January";
    String endDate = "4";
    String endYear = "2025";

    String payoutStatus = "All";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-896")
    public void VerifyTheFilterInitiatedPaymentByDateRange() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToPay();
        Pay.clickOnPaymentsInit();
        softAssert.assertTrue(Pay.isPaymentsInitBtnSelected(), "The user is unable to land on the Invoice tab.");
        Pay.selectOptionPayoutStatusDropdown(payoutStatus);
        Pay.selectTimeRange(startDay,  startMonth,  startDate,  startYear, endDay,  endMonth,  endDate,  endYear);
        softAssert.assertTrue(Pay.isDateRangeCorrect(startMonth, startDate, startYear, endMonth, endDate, endYear), "The date range is not selected correctly");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
