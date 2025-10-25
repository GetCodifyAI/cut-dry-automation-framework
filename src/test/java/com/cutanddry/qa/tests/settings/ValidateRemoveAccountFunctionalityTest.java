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

public class ValidateRemoveAccountFunctionalityTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-839")
    public void validateRemoveAccountFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - user not navigated to dashboard");
        Dashboard.navigateToBillingSettings();
        softAssert.assertTrue(Settings.isBillingSettingsTextDisplayed(), "Failed to navigate to Billing Settings");
        softAssert.assertTrue(Settings.isPaymentMethodAdded(), "Remove Account button not displayed - no payment method exists");
        Settings.clickOnRemoveAcc();
        softAssert.assertTrue(Settings.isAreYouSurePopupDisplayed(), "Are you sure popup not displayed");
        Settings.clickYes();
        softAssert.assertTrue(Settings.isRemovePaymentSuccessPopupDisplayed(), "Payment method removal success popup not displayed");
        Settings.clickOK();
        softAssert.assertTrue(Settings.isAddPaymentMethodBtnDisplayed(), "Add Payment Method button not displayed after removal");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
