package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.SettingData;
import com.cutanddry.qa.data.testdata.TrackData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyPaySettingContactDetailsTest extends TestBase {
    static User user;
    static String ARContacts = SettingData.AR_CONTACTS;
    static String technicalContacts = SettingData.TECHNICAL_CONTACTS;
    static String ARContactsMessage = SettingData.AR_CONTACTS_MESSAGE;
    static String technicalContactsMessage = SettingData.TECHNICAL_CONTACTS_MESSAGE;
    static String validEmail = SettingData.VALID_EMAIL;
    static String invalidEmail = SettingData.INVALID_EMAIL;
    static String invalidArContact = SettingData.INVALID_AR_CONTACT;
    static String invalidTechnicalContact = SettingData.INVALID_TECHNICAL_CONTACT;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-860")
    public void VerifyPaySettingContactDetails() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToPaySettings();
        softAssert.assertTrue(Settings.isPaySettingsTextDisplayed(),"navigation to pay settings error");
        Settings.hoverContacts(ARContacts);
        softAssert.assertTrue(Settings.isContactMessageDisplayed(ARContactsMessage),"AR contacts message should be not displayed when hovering");
        Settings.hoverContacts(technicalContacts);
        softAssert.assertTrue(Settings.isContactMessageDisplayed(technicalContactsMessage),"Technical contacts message should be not displayed when hovering");
        Settings.enterEmailToARContact(validEmail);
        Settings.enterEmailToTechnicalContact(validEmail);
        Settings.clickOnSaveBtn();
        Settings.enterEmailToARContact(invalidEmail);
        Settings.clickOnSaveBtn();
        softAssert.assertTrue(Settings.isInvalidPopUpDisplayed(invalidArContact),"");
        Settings.clickOK();
        Settings.enterEmailToARContact(validEmail);
        Settings.enterEmailToTechnicalContact(invalidEmail);
        Settings.clickOnSaveBtn();
        softAssert.assertTrue(Settings.isInvalidPopUpDisplayed(invalidTechnicalContact),"");
        Settings.clickOK();
        Settings.enterEmailToARContact(null);
        Settings.enterEmailToTechnicalContact(null);
        Settings.clickOnSaveBtn();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
