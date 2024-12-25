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

public class VerifyTheCustomerBaseReportGenerationTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    // Define email credentials and the subject to search for
    static String email = "test@cutanddry.com";
    static String password = ""; // Use an App Password or OAuth
    static String expectedSubject = ""; // The subject you want to verify

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-822")
    public void VerifyTheCustomerBaseReportGeneration() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToReports();
        softAssert.assertTrue(Reports.isUserNavigatedToReports(),"navigation to reports error");
        softAssert.assertTrue(Reports.isCustomerBaseReportDisplayed(),"Unable to find the Customer Base Report section");
        Reports.typeOnBaseReportEmail(email);
        Reports.clickEmailReport();
        softAssert.assertTrue(Reports.isGeneratingReportPopupDisplayed(),"generating report pop up not display");
        Reports.clickOkReport();

        // Verify if the email is received with the expected subject
//        softAssert.assertTrue(EmailVerifier.verifyEmail(email, password, expectedSubject),"The verification email was not found!");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
