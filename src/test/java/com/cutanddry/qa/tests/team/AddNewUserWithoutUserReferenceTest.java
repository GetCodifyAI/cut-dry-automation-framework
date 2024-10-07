package com.cutanddry.qa.tests.team;

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

public class AddNewUserWithoutUserReferenceTest extends TestBase {
    static User user;
    static String name = "Test";
    static String email = "test@email.com";
    static String verifiedVendorId = "46017666";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-135")
    public void AddNewUserWithoutUserReference() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToRestaurantDashboard();
        Login.navigateToSupplierPortalVendor(verifiedVendorId);
        Login.setNode();
        Login.navigateToDistributor();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.clickOnAddUser();
        Settings.enterName(name);
        Settings.enterEmail(email);
        Settings.clickOnInviteUser();
        softAssert.assertTrue(Settings.isUserDisplayed(name),"user adding error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
