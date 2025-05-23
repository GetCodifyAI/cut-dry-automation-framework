package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.SettingData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.URISyntaxException;

public class VerifyThatSMSFunctionalityIsWorkingAsExpectedForOrderReminderAlertsTest extends TestBase {
    static User user;
    static String sendAlertsTo = SettingData.SEND_ALERTS_TO;
    static String sendAlertsToOption = SettingData.SEND_ALERTS_TO_OPTION;
    static String whoHas = SettingData.WHO_HAS;
    static String whoHasOption = SettingData.WHO_HAS_OPTION;
    static String ANDHas = SettingData.AND_HAS;
    static String ANDHasOption = SettingData.AND_HAS_OPTION;
    static String sendAlerts = SettingData.SEND_ALERTS;
    static String sendAlertsOption = SettingData.SEND_ALERTS_OPTION;
    static String atTime = SettingData.AT_TIME;
    static String atTimeOption = SettingData.AT_TIME_OPTION;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1183")
    public void VerifyThatSMSFunctionalityIsWorkingAsExpectedForOrderReminderAlerts() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Settings.clickOrderReminderToggle(true);
        Settings.clickConfigureAlert();
        softAssert.assertTrue(Settings.icConfigureOrderReminderPopUpDisplayed(),"pop up not display");
        Settings.selectReminderDropDown(sendAlertsTo,sendAlertsToOption);
        Settings.selectReminderDropDown(whoHas,whoHasOption);
        Settings.selectReminderDropDown(ANDHas,ANDHasOption);
        Settings.selectReminderDropDown(sendAlerts,sendAlertsOption);
        Settings.selectReminderDropDown(atTime,atTimeOption);
        Settings.clickOnSave();
        softAssert.assertTrue(Settings.isSettingOrderReminderTextDisplayed(),"Settings yor order reminder pop up not displayed");
        Settings.clickOkAlert();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
