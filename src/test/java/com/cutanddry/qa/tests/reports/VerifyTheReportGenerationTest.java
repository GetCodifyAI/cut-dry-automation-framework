package com.cutanddry.qa.tests.reports;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Reports;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;

public class VerifyTheReportGenerationTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String email = "test@cutanddry.com";
    static String fromDate, toDate;
    static String type = "All";
    static String category = "All Categories";
//    static String password = ""; // Use an App Password or OAuth
//    static String expectedSubject = ""; // The subject you want to verify

    @BeforeClass
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login error");
    }

    @Test(groups = "DOT-TC-546")
    public void VerifyTheCustomerOrderGuideReportGeneration() throws InterruptedException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isCustomerOrderGuidesDisplayed(), "Unable to find the Customer Order Guides section");
        Reports.typeOnOrderGuidesEmail(email);
        Reports.clickEmailReport();
        verifyPopupAndClickOk();

        // Verify if the email is received with the expected subject
//        softAssert.assertTrue(EmailVerifier.verifyEmail(email, password, expectedSubject),"The verification email was not found!");

        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-547")
    public void VerifyThePickListDownloadReport() throws InterruptedException, ParseException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isPickListReportDisplayed(), "Unable to find the Pick List Report section");
        Reports.selectPickListReportFromDate(2);
        fromDate = Reports.getPickListReportFromDate();
        Reports.selectPickListReportToDate(3);
        toDate = Reports.getPickListReportToDate();
        Reports.typeOnPickListReportEmail(email);
        /*Reports.clickPickListDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-822")
    public void VerifyTheCustomerBaseReportGeneration() throws InterruptedException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isCustomerBaseReportDisplayed(),"Unable to find the Customer Base Report section");
        Reports.typeOnBaseReportEmail(email);
        Reports.clickEmailReport();
        verifyPopupAndClickOk();

        // Verify if the email is received with the expected subject
//        softAssert.assertTrue(EmailVerifier.verifyEmail(email, password, expectedSubject),"The verification email was not found!");

        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-823")
    public void VerifyTheCustomerOrdersReportGeneration() throws InterruptedException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isCustomerOrdersReportDisplayed(),"Unable to find the Customer Orders Report section");
        Reports.selectOrdersReportFromDate(2);
        Reports.selectOrdersReportToDate(3);
        Reports.typeOnOrdersReportEmail(email);
        Reports.clickEmailReport();
        verifyPopupAndClickOk();

        // Verify if the email is received with the expected subject
//        softAssert.assertTrue(EmailVerifier.verifyEmail(email, password, expectedSubject),"The verification email was not found!");

        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-824")
    public void VerifyTheItemMovementReportGeneration() throws InterruptedException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isItemMovementReportDisplayed(),"Unable to find the Customer Orders Report section");
        Reports.selectItemMovementReportFromDate(2);
        Reports.selectItemMovementReportToDate(3);
        Reports.typeOnItemMovementReportEmail(email);
        Reports.clickEmailReport();
        verifyPopupAndClickOk();

        // Verify if the email is received with the expected subject
//        softAssert.assertTrue(EmailVerifier.verifyEmail(email, password, expectedSubject),"The verification email was not found!");

        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-825")
    public void VerifyTheCustomerWiseItemMovementDownloadReport() throws InterruptedException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isCustomerWiseItemMovementReportDisplayed(), "Unable to find the Customer-wise Item Movement Report section");
        Reports.selectCustomerWiseItemMovementReportFromDate(2);
        Reports.selectCustomerWiseItemMovementReportToDate(3);
        /*Reports.clickCustomerWiseItemMovementDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-826")
    public void VerifyTheCatalogExportDownloadReport() throws InterruptedException, ParseException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isCatalogExportReportDisplayed(), "Unable to find the Catalog Export Report section");
        Reports.selectCatalogExportReportType(type);
        Reports.selectCatalogExportReportCategory(category);
        Reports.setCatalogExportReportCheckboxStatus(true);
        /*Reports.clickCatalogExportDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");

        Reports.setCatalogExportReportCheckboxStatus(true);
        Reports.clickCatalogExportDownloadReport();

        boolean isDownloaded1 = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded1, "The report file was not downloaded successfully.");*/

        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-827")
    public void VerifyThePayCustomersDownloadReport() throws InterruptedException, ParseException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isPayCustomersReportDisplayed(), "Unable to find the Pay Customers Report section");
       /* Reports.clickPayCustomersDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-828")
    public void VerifyTheAtRiskAccountsDownloadReport() throws InterruptedException, ParseException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isAtRiskAccountsReportDisplayed(), "Unable to find the At-risk Accounts Report section");
        /*Reports.clickAtRiskAccountsDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-829")
    public void VerifyThePaymentTransactionDownloadReport() throws InterruptedException, ParseException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isPaymentTransactionReportDisplayed(), "Unable to find the Payment Transaction Report section");
        Reports.selectPaymentTransactionReportFromDate(2);
        Reports.selectPaymentTransactionReportToDate(3);
       /* Reports.clickPaymentTransactionDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-830")
    public void VerifyThePaymentAuthorizationStatusDownloadReport() throws InterruptedException, ParseException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isPaymentAuthorizationStatusReportDisplayed(), "Unable to find the Payment Authorization Status Report section");
        /*Reports.clickPaymentAuthorizationStatusDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }

    @Test(groups = "DOT-TC-831")
    public void VerifyTheTrackReadyOrdersDownloadReport() throws InterruptedException, ParseException {
        loginAndNavigateToReports();
        softAssert.assertTrue(Reports.isTrackReadyOrdersReportDisplayed(), "Unable to find the Track-Ready Orders Report section");
        Reports.selectTrackReadyOrdersReportDeliveryDate(2);
        toDate = Reports.getTrackReadyOrdersReportDeliveryDate();
        /*Reports.clickTrackReadyOrdersDownloadReport();

        boolean isDownloaded = Reports.isFileDownloaded(downloadPath, expectedFileName,fromDate, toDate);
        softAssert.assertTrue(isDownloaded, "The report file was not downloaded successfully.");*/
        softAssert.assertAll();
    }

    private void loginAndNavigateToReports() {
        Dashboard.navigateToReports();
        softAssert.assertTrue(Reports.isUserNavigatedToReports(), "Navigation to reports error");
    }

    private void verifyPopupAndClickOk() {
        softAssert.assertTrue(Reports.isGeneratingReportPopupDisplayed(), "Generating report popup not displayed");
        Reports.clickOkReport();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
//        closeAllBrowsers();
    }

    @AfterClass
    public void cleanUp() {
        closeAllBrowsers();
    }
}
