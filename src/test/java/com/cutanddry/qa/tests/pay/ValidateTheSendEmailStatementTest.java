package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PayData;
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

public class ValidateTheSendEmailStatementTest extends TestBase {
    static User user;
    static String sendEmailStatement = PayData.SEND_EMAIL_STATEMENT;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-912")
    public void ValidateTheSendEmailStatement() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToPay();
        Assert.assertTrue(Pay.isUserNavigatedToPay(),"navigation error");
        Pay.sendEmailStatement(sendEmailStatement);
        softAssert.assertTrue(Pay.isEmailStatementDisplayed(),"Email Statement pop up not displayed");
        Pay.clickSend();
        softAssert.assertTrue(Pay.isSuccessPopUpDisplayed(),"Email statement not send");
        Pay.clickOkPopUp();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
