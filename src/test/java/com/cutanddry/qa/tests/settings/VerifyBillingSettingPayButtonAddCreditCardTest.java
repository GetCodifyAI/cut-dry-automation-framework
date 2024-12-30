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

public class VerifyBillingSettingPayButtonAddCreditCardTest extends TestBase {
    static User user;
    static String card_num = "4111111111111111";
    static String exp_date = "10/25";
    static String cvv = "999";
    static String address = "N0 07 Beakers' street";
    static String city = "California";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-848")
    public void VerifyBillingSettingPayButtonAddCreditCard() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBillingSettings();
        softAssert.assertTrue(Settings.isBillingSettingsTextDisplayed(),"navigation to billing settings error");
        Settings.clickPayButton();
        softAssert.assertTrue(Settings.isAddPaymentPopupDisplayed(),"add payment popup error");
        Settings.clickCreditCard();
        Settings.enterCardNumber(card_num);
        Settings.enterExpDate(exp_date);
        Settings.enterCVV(cvv);
        Settings.enterStreetAddress(address);
        Settings.enterCity(city);
        Settings.clickOnNext();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
