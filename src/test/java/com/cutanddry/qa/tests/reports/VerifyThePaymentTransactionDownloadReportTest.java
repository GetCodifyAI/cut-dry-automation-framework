package com.cutanddry.qa.tests.reports;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Reports;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;

public class VerifyThePaymentTransactionDownloadReportTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String downloadPath = System.getProperty("user.dir") + "/downloads";
    static String expectedFileName = "payment_summary";
    static String fromDate, toDate;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-829")
    public void VerifyThePaymentTransactionDownloadReport() throws InterruptedException, ParseException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToReports();
        softAssert.assertTrue(Reports.isUserNavigatedToReports(),"navigation to reports error");
        softAssert.assertTrue(Reports.isPaymentTransactionReportDisplayed(), "Unable to find the Payment Transaction Report section");
        Reports.selectPaymentTransactionReportFromDate(2);
        Reports.selectPaymentTransactionReportToDate(3);
       /* Reports.clickPaymentTransactionDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
//        Reports.cleanUpDownloads(downloadPath);
    }
}
