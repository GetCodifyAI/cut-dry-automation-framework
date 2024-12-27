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

public class ValidateTheTimelineInCreditRequestUsingTimestampStatusOrganizationAndUser extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-785")
    public void ValidateTheTimelineInCreditRequestUsingTimestampStatusOrganizationAndUser() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCreditRequests();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        CreditRequests.clickOnFirstItemOfCreditRequests();
        softAssert.assertTrue(CreditRequests.isNavigatedToOrderSection(),"Error while navigating to Order section.");
        CreditRequests.clickOnTimeline();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");


//        CreditRequests.clickOnItems();
//        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
//        CreditRequests.isPriceAndTotalEqual();
        softAssert.assertAll();
    }

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        takeScreenshotOnFailure(result);
//        closeAllBrowsers();
//    }
}
