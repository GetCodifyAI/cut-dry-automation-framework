package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyBillingSettingAddPaymentMethodTest extends TestBase {
    static User user;
    static String acc_num = "20222220";
    static String routing_num = "321081669";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-603")
    public void VerifyBillingSettingAddPaymentMethod() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBillingSettings();
        softAssert.assertTrue(Settings.isBillingSettingsTextDisplayed(),"navigation to billing settings error");
        Settings.clickOnAddPaymentMethod();
        softAssert.assertTrue(Settings.isAddPaymentPopupDisplayed(),"add payment popup error");
        Settings.clickOnAddBank();
        Settings.enterAccountNumber(acc_num);
        Settings.enterRoutingNumber(routing_num);
        Settings.clickOnNext();
        softAssert.assertTrue(Settings.isAddPaymentSuccessPopupDisplayed(),"add payment success popup error");
        Settings.clickOK();
        softAssert.assertTrue(Settings.isPaymentMethodAdded(),"payment method adding error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
