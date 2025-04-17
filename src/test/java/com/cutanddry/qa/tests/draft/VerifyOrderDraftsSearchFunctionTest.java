package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Draft;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderDraftsSearchFunctionTest extends TestBase {
    static User user;
    static String code, customer,referenceNum;
    static String customerCode = "4";
    static int draftCount;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-968")
    public void VerifyOrderDraftsSearchFunction() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToDrafts();
        Assert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        customer = Draft.getCustomerNameDP();
        referenceNum = Draft.getReferenceNumDP();
        code = Draft.getDraftsColumn(customerCode);
        Draft.typeOnSearchDrafts(customer);
        softAssert.assertEquals(Draft.getCustomerNameDP(),customer,"search draft by customer not working");
        Draft.typeOnSearchDrafts(referenceNum);
        softAssert.assertEquals(Draft.getReferenceNumDP(),referenceNum,"search draft by reference number not working");
        draftCount = Draft.getDraftCount();
        softAssert.assertTrue(Draft.isResultCountDisplayed(String.valueOf(draftCount)),"count is not equal");
        Draft.typeOnSearchDrafts(code);
        softAssert.assertEquals(Draft.getDraftsColumn(customerCode),code,"search draft by customer code not working");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
