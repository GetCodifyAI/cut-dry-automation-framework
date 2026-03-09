package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyNewUserCanNotHaveExistingMobileNumberTest extends TestBase {
    static User user;
    static String distributor = CustomerData.DISTRIBUTOR_NAME_MAPLEVALE;
    static String CustomerCode = "37215";
    static String UserName = "TestUserOne";
    static String UserMobileNum = "17167253873";
    static String ExistingUserName = "Mike Carr";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2199")
    public void VerifyNewUserCanNotHaveExistingMobileNumber() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.SelectCustomer(CustomerCode);
        Customer.InviteNewUsers();
        softAssert.assertTrue(Customer.AddUserOverlayDisplayed(),"ERROR in Displaying add user overlay");
        Customer.FillNameInAddUserOverlay(UserName);
        Customer.fillBookKeeperMobile(UserMobileNum);
        Orders.closeRatingOverlay();
        Customer.clickInviteViaMobileNumber();
        softAssert.assertTrue(Customer.UserMobileNumberExistingMsgDisplayed(),"User mobile number exists error msg not displayed");
        Customer.CloseSuccessOverlayByOkBtn();
        Customer.clickOrderSuccessMessageClose();

        Customer.EditUserDetails(ExistingUserName);
        softAssert.assertTrue(Customer.EditUserOverlayDisplayed(),"ERROR in Displaying add user overlay");
        Customer.FillNameInAddUserOverlay(UserName);
        Customer.FillEmailInAddUserOverlay("");
        Customer.fillBookKeeperMobile(UserMobileNum);
        Customer.clickReInviteViaText();
        softAssert.assertTrue(Customer.MobileNumberAccountExistingMsgDisplayed(),"Mobile number Account exists error msg not displayed");
        Customer.CloseSuccessOverlayByOkBtn();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }
}
