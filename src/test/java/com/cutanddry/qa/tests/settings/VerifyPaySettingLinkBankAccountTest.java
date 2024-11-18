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

public class VerifyPaySettingLinkBankAccountTest extends TestBase {
    static User user;
    static String acc_num = "2222220";
    static String routing_num = "321081669";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-607")
    public void VerifyPaySettingLinkBankAccount() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToPaySettings();
        softAssert.assertTrue(Settings.isPaySettingsTextDisplayed(),"navigation to pay settings error");
        Settings.clickOnLinkBank();
        softAssert.assertTrue(Settings.isLinkAccPopupDisplayed(),"link popup error");
        Settings.clickOnLinkBankManually();
        Settings.enterAccountNumber(acc_num);
        Settings.enterRoutingNumber(routing_num);
        Settings.clickOnSave();
        softAssert.assertTrue(Settings.isBankDetailsAddedPopup(),"bank adding popup error");
        Settings.clickOK();
        softAssert.assertTrue(Settings.isPayoutMethodAdded(),"payout method adding error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
