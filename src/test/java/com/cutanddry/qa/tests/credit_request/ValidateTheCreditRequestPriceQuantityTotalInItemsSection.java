package com.cutanddry.qa.tests.credit_request;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.CreditRequests;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheCreditRequestPriceQuantityTotalInItemsSection extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-784")
    public void ValidateTheCreditRequestPriceQuantityTotalInItemsSection() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCreditRequests();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        String[] CrResult = CreditRequests.getCrQtyCrValue();
        String CrQty = CrResult[0];
        String CrValue = CrResult[1];
        CreditRequests.clickOnFirstItemOfCreditRequests();
        String CrItemName = CreditRequests.getItemNameInCR();
        CreditRequests.clickOnItems();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Message Displayed");
        softAssert.assertTrue(CreditRequests.getItemQtyItemPrice(CrQty, CrValue, CrItemName),"The values in the Item Section CR are different from those in the Credit Request section.");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
