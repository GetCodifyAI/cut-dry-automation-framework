package com.cutanddry.qa.tests.track;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import lombok.extern.java.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.DataInput;

public class VerifyMonitoringFunctionalityForDispatchedUnderMonitoring extends TestBase {
    static User user;
    String orderID = "316727041";
    String timeRange = "All";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-915")
    public void ValidateTheEditCreditRequestFlowApproveCreditThenChangeItToDeclineCredit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.switchIntoNewTab();
        distributorUI.openURL("google.com");
//        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
//        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
//        Dashboard.navigateToTrackMonitoring();
//        softAssert.assertTrue(Track.isMonitoringTextDisplayed(),"Monitoring text not displayed");



    }

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        takeScreenshotOnFailure(result);
//        closeAllBrowsers();
//    }
}

