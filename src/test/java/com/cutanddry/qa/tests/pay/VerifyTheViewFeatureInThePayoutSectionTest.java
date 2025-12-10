package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheViewFeatureInThePayoutSectionTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    String startDay = "Sunday";
    String startMonth = "November";
    String startDate = "9";
    String startYear = "2025";

    String endDay = "Saturday";
    String endMonth = "December";
    String endDate = "6";
    String endYear = "2025";

    String payoutStatus = "All";
    static String payoutCode;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-903")
    public void VerifyTheViewFeaturesInThePayoutSection() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnPayouts();
        softAssert.assertTrue(Pay.isPayoutBtnSelected(), "The user is unable to land on the Payout tab.");
        Pay.selectPayoutStatusDropdown(payoutStatus);
        Pay.selectDateRange(startDay,  startMonth,  startDate,  startYear, endDay,  endMonth,  endDate,  endYear);
        softAssert.assertTrue(Pay.isPayOutDateRangeCorrect(startMonth, startDate, startYear, endMonth, endDate, endYear), "The date range is not selected correctly");
        payoutCode = Pay.getPayOutCode();
        Pay.clickThreeDotButton();
        Pay.clickViewPayout();
        softAssert.assertTrue(Pay.isPayoutRecordDisplayed(payoutCode),"payout record not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
