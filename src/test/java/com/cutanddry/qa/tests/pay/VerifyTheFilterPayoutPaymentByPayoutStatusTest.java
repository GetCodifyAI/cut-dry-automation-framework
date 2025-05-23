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

public class VerifyTheFilterPayoutPaymentByPayoutStatusTest extends TestBase{
    static User user;
    SoftAssert softAssert;
    static String payoutStatus = "Paid";

    String startDay = "Sunday";
    String startMonth = "December";
    String startDate = "1";
    String startYear = "2024";

    String endDay = "Saturday";
    String endMonth = "January";
    String endDate = "4";
    String endYear = "2025";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-898")
    public void VerifyTheFilterPayoutPaymentByPayoutStatus() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToPay();
        Pay.clickOnPayouts();
        softAssert.assertTrue(Pay.isPayoutBtnSelected(), "The user is unable to land on the Payout tab.");
        Pay.selectPayoutStatusDropdown(payoutStatus);
        Pay.selectDateRange(startDay,  startMonth,  startDate,  startYear, endDay,  endMonth,  endDate,  endYear);
        softAssert.assertTrue(Pay.isPayoutStatusDisplayed(),"Payout filter not work");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
