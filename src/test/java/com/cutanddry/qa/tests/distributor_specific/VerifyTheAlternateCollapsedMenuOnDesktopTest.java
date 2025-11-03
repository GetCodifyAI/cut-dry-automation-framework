package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheAlternateCollapsedMenuOnDesktopTest extends TestBase{
    static User user;
    static String distributorTradewell = "445359484 - Michael Gappy - Tradewell Distributors";
    static String userName = "Michael Gappy";
    static String DPName = "Tradewell Distributors";



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1453")
    public void VerifyTheAlternateCollapsedMenuOnDesktop() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorTradewell);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.clickMenu();
        softAssert.assertTrue(Customer.isUserNameDisplay(userName),"user name display error");
        softAssert.assertTrue(Customer.isDistributorNameDisplay(DPName),"distributor name error");
        Customer.clickCloseMenu();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
