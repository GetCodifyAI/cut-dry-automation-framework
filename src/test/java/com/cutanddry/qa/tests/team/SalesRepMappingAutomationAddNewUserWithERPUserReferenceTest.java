package com.cutanddry.qa.tests.team;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

import java.util.Random;

public class SalesRepMappingAutomationAddNewUserWithERPUserReferenceTest extends TestBase {
    static User user;
    static int randomNumber = new Random().nextInt(1000);
    static String name = "Testuser01"+ randomNumber;
    static String email = "test"+randomNumber+"@email.com";
    static String ERPUserRef = "AATest"+randomNumber;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1258")
    public void SalesRepMappingAutomationAddNewUserWithERPUserReference() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.clickOnAddUser();
        Settings.enterName(name);
        Settings.enterEmail(email);
        Settings.enterERPUserRef(ERPUserRef);

        Settings.clickOnInviteUser();
        softAssert.assertTrue(Settings.isUserDisplayed(name),"user adding error");
        Settings.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
