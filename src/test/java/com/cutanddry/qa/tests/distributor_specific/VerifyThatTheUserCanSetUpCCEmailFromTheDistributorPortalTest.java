package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorSpecificData;
import com.cutanddry.qa.functions.Catalog;
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

public class VerifyThatTheUserCanSetUpCCEmailFromTheDistributorPortalTest extends TestBase {
    static User user;
    static String DP = DistributorSpecificData.DISTRIBUTOR_LIPARI;
    static String CustomerCode = "12922001";
    static String orderCCEmailAlert = "Configure Order CC Alerts";
    static String email = "test@email.com";



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1322")
    public void VerifyThatTheUserCanSetUpCCEmailFromTheDistributorPortal() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        Customer.clickOrderCCEmailAlert();
        softAssert.assertTrue(Customer.isOrderCCEmailAlertDisplay(orderCCEmailAlert),"Order CC Email Alert not Display");
        Customer.addEmailToSendAlertTo(email);
        Customer.clickSave();
        softAssert.assertTrue(Customer.isSendAlertForNewOrderDisplay(),"Send Alert For New Order not Display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
