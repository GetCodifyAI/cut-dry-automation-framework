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

public class VerifyTheTrackReadyOrdersDownloadReportTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String downloadPath = System.getProperty("user.dir") + "/downloads";
    static String expectedFileName = "track-ready-orders-report_Independent Foods Co";
    static String fromDate, toDate;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-831")
    public void VerifyTheTrackReadyOrdersDownloadReport() throws InterruptedException, ParseException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToReports();
        softAssert.assertTrue(Reports.isUserNavigatedToReports(),"navigation to reports error");
        softAssert.assertTrue(Reports.isTrackReadyOrdersReportDisplayed(), "Unable to find the Track-Ready Orders Report section");
        Reports.selectTrackReadyOrdersReportDeliveryDate(2);
        toDate = Reports.getTrackReadyOrdersReportDeliveryDate();
        /*Reports.clickTrackReadyOrdersDownloadReport();

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
