package com.cutanddry.qa.tests.disclaimer_msg;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class VerifyCaseDiscountsDisclaimerMsgInOrderDetailsCDAppTest extends TestBase {
    static User user;
    static String DP = "Jordan Paige";
    static String customerId = "ZTEST";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-112")
    public void verifyCaseDiscountsDisclaimerMsgInOrderDetailsCDApp() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToWhiteLabelPortal(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboardWhiteLabel(),"login error");
        Dashboard.navigateToUsersWhiteLabel();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation error");
        //
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
