package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyProfileNewUserInviteTest extends TestBase {
    static User user;
    String CustomerCode = "16579";
    String UserName = "Test user";
    static String UserEmail = "Testuser" + generateDynamicValue() + "@gmail.com";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-270")
    public void VerifyProfileNewUserInvite() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.SelectCustomer(CustomerCode);
        Customer.InviteNewUsers();
        softAssert.assertTrue(Customer.AddUserOverlayDisplayed(),"ERROR in Displaying add user overlay");
        Customer.FillNameInAddUserOverlay(UserName);
        Customer.FillEmailInAddUserOverlay(UserEmail);
        Customer.SaveChangesWithoutSendingInvite();
        softAssert.assertTrue(Customer.UserDetailsSuccessfullyUpdatedMsgDisplayed(),"ERROR in Saving User Details");
        Customer.CloseSuccessOverlayByOkBtn();
        softAssert.assertTrue(Customer.IsAddedUserSuccessfullyDisplayed(UserName),"Username not Found");

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }



}
