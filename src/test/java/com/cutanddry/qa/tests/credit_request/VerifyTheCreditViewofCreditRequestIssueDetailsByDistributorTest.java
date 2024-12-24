package com.cutanddry.qa.tests.credit_request;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.CreditRequests;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheCreditViewofCreditRequestIssueDetailsByDistributorTest extends TestBase{

    static User user;
    String timeRange = "All";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-496")
    public void VerifyTheCreditViewofCreditRequestIssueDetailsByDistributor() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCreditRequests();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        CreditRequests.changeRequestDate(timeRange); //Select the "All" option
        CreditRequests.clickOnFirstItemOfCreditView();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        CreditRequests.clickOnCreditView();
        softAssert.assertTrue(CreditRequests.checkIfCreditViewSectionVisible(), "Timeline Section is not visible");
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        CreditRequests.clickOnFirstItemOfCreditRequests();
        softAssert.assertTrue(CreditRequests.checkIfIssueDetailsModalDisplayed(), "Issue Detail Modal Card is not visible");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
