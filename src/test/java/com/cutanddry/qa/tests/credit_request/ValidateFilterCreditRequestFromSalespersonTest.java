package com.cutanddry.qa.tests.credit_request;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.CreditRequests;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateFilterCreditRequestFromSalespersonTest extends TestBase {
    static User user;
    String date = "All";
    String salesperson = "Kevin";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-782")
    public void ValidateFilterCreditRequestFromSalesperson() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCreditRequests();
        CreditRequests.changeRequestDate(date);
        softAssert.assertTrue(CreditRequests.isRequestDateChanged(date),"dropdown error");
        CreditRequests.changeSalesperson(salesperson);
        softAssert.assertTrue(CreditRequests.isSalespersonChanged(salesperson),"dropdown error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
