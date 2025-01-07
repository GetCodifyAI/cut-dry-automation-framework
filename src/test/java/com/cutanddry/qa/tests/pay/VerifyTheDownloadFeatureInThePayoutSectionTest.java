package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.functions.Reports;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.tests.pay.ValidateTheDownloadStatementTest.downloadPath;

public class VerifyTheDownloadFeatureInThePayoutSectionTest extends TestBase {
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
    static String payoutCode;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-966")
    public void VerifyTheDownloadFeatureInThePayoutSection() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnPayouts();
        softAssert.assertTrue(Pay.isPayoutBtnSelected(), "The user is unable to land on the Payout tab.");
        Pay.selectPayoutStatusDropdown(payoutStatus);
        Pay.selectDateRange(startDay,  startMonth,  startDate,  startYear, endDay,  endMonth,  endDate,  endYear);
        softAssert.assertTrue(Pay.isPayOutDateRangeCorrect(startMonth, startDate, startYear, endMonth, endDate, endYear), "The date range is not selected correctly");
        Pay.clickThreeDotButton();
        Pay.clickDownloadPayout();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        Reports.cleanUpDownloads(downloadPath);
        closeAllBrowsers();
    }
}
