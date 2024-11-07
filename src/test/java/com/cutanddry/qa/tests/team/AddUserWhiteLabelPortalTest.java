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

public class AddUserWhiteLabelPortalTest extends TestBase {
    static User user;
    static String name = "Test";
    static String email = "test@email.com";
    static String customer = "1I2S E- COMMERCE LLC";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-146")
    public void AddUserWhiteLabelPortal() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(customer);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboardWhiteLabel(),"login error");
        Dashboard.navigateToUsersWhiteLabel();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        Settings.clickOnAddUser();
        Settings.enterNameWL(name);
        Settings.enterEmailWL(email);
        Settings.clickOnAddUserWL();
        Settings.clickOK();
        softAssert.assertTrue(Settings.isWLUserDisplayed(name),"user adding error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
