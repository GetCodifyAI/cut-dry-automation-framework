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

public class VerifyProfileEditTest extends TestBase {
    static User user;
    String CustomerCode = "16579";
    String UserName = "Test user";
    String EditedUserName = "Test user Edit";
    String EditedEmail = "TestuserEdit@gmail.com";
    String UserEmail = "Testuser@gmail.com";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-271")
    public void VerifyProfileEdit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.SelectCustomer(CustomerCode);
        Customer.EditUserDetails(UserName);
        Customer.FillNameInAddUserOverlay(EditedUserName);
        Customer.FillEmailInAddUserOverlay(EditedEmail);
        Customer.SaveChangesWithoutSendingInvite();
        softAssert.assertTrue(Customer.UserDetailsSuccessfullyUpdatedMsgDisplayed(),"ERROR in Saving User Details");
        Customer.CloseSuccessOverlayByOkBtn();
        softAssert.assertTrue(Customer.IsAddedUserSuccessfullyDisplayed(EditedUserName),"Username not Found");

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){

        //Reverting back the changes
        Customer.EditUserDetails(EditedUserName);
        Customer.FillNameInAddUserOverlay(UserName);
        Customer.FillEmailInAddUserOverlay(UserEmail);
        Customer.SaveChangesWithoutSendingInvite();
        Customer.CloseSuccessOverlayByOkBtn();

        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }


}
