package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerProfileData;
import com.cutanddry.qa.data.testdata.ParentChildOGData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDeleteCustomerUserAndReceiveUserDeletionNotificationTest extends TestBase {
    static User user;
    String CustomerCode = CustomerProfileData.CUSTOMER_CODE2;
    String UserName = CustomerProfileData.USER_NAME;
    String UserEmail = CustomerProfileData.USER_EMAIL;
    String DistributorName = ParentChildOGData.DISTRIBUTOR_INDIANHEAD;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-155")
    public void VerifyDeleteCustomerUserAndReceiveUserDeletionNotification() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.SelectCustomer(CustomerCode);

        //Add new User
        Customer.InviteNewUsers();
        softAssert.assertTrue(Customer.AddUserOverlayDisplayed(),"ERROR in Displaying add user overlay");
        Customer.FillNameInAddUserOverlay(UserName);
        Customer.FillEmailInAddUserOverlay(UserEmail);
        Customer.SaveChangesWithoutSendingInvite();
        softAssert.assertTrue(Customer.UserDetailsSuccessfullyUpdatedMsgDisplayed(),"ERROR in Saving User Details");
        Customer.CloseSuccessOverlayByOkBtn();
        softAssert.assertTrue(Customer.IsAddedUserSuccessfullyDisplayed(UserName),"Username not Found");

        //Delete User
        Customer.EditUserDetails(UserName);
        Customer.RemoveUser();
        softAssert.assertTrue(Customer.RemovalConfirmationOverlayDisplayed(),"ERROR in Displaying Removal confirmation overlay");
        Customer.ClickRemovalConfirmationOverlayYesBtn();
        softAssert.assertTrue(Customer.UserSuccessfullyRemovedMsgDisplayed(),"ERROR in Saving User Details");
        Customer.CloseSuccessOverlayByOkBtn();
        softAssert.assertFalse(Customer.RemovedUserNotDisplayed(UserName),"customer not deleted properly");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }



}
