package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.SettingData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateAddPaymentMethodForAddBankAccountTest extends TestBase {
    static User user;
    static String AccountNumber = SettingData.ACCOUNT_NUMBER;
    static String RoutingNumber = SettingData.ROUTING_NUMBER;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-838")
    public void validateAddPaymentMethodForAddBankAccount() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - user not navigated to dashboard");
        Dashboard.navigateToBillingSettings();
        softAssert.assertTrue(Settings.isBillingSettingsTextDisplayed(), "Failed to navigate to Billing Settings");
        Settings.removePaymentMethod();
        softAssert.assertTrue(Settings.isAddPaymentMethodBtnDisplayed(), "Add Payment Method button not displayed - payment method may already exist");
        Settings.clickOnAddPaymentMethod();
        softAssert.assertTrue(Settings.isAddPaymentPopupDisplayed(), "Add Payment Method popup not displayed");
        Settings.clickOnAddBank();
        Settings.enterAccountNumber(AccountNumber);
        Settings.enterRoutingNumber(RoutingNumber);
        Settings.clickOnNext();
        softAssert.assertTrue(Settings.isAddPaymentSuccessPopupDisplayed(), "Payment method success popup not displayed");
        Settings.clickOK();
        softAssert.assertTrue(Settings.isPaymentMethodAdded(), "Payment method not added - Remove Account button not displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
