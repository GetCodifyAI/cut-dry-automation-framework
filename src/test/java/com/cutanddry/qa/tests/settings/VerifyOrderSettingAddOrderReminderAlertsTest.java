package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.SettingData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class VerifyOrderSettingAddOrderReminderAlertsTest extends TestBase {
    static User user;
    static String distributorName = SettingData.DISTRIBUTOR_NAME;
    static String option = SettingData.SEND_ALERT;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-861")
    public void VerifyOrderSettingAddOrderReminderAlerts() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Settings.clickAddAlert();
        softAssert.assertTrue(Settings.isOrderRemindAlertTextDisplayed(),"Order remind alert pop up not display");
        Settings.clickSendAlertDropDown();
        Settings.clickSendAlertOption(option);
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
