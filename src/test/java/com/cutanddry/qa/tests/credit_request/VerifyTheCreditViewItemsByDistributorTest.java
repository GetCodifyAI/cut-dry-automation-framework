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

public class VerifyTheCreditViewItemsByDistributorTest extends TestBase {
    static User user;
    String timeRange = "All";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-494")
    public void VerifyTheCreditViewItemsByDistributor() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCreditRequests();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        CreditRequests.changeRequestDate(timeRange); //Select the "All" option
        CreditRequests.clickOnFirstItemOfCreditRequests();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        CreditRequests.clickOnItems();
        softAssert.assertTrue(CreditRequests.checkIfItemSectionVisible(), "Item Section is not visible");
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
