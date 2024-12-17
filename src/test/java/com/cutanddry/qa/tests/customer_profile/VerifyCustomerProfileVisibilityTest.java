package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerProfileData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCustomerProfileVisibilityTest extends TestBase{
    static User user;
    String CustomerCode = "16579";
    static String email = "Test_Automation_QA";
    static String nameCus = "Test_Automation_QA - 209223241 - Owner - Kafe Layers #3 Test - San Francisco - quinn-bins-sd9lph1ucd@e.rainforestqa.com - null";
    //static String cusCode = CustomerProfileData.CUSTOMER_PROFILE_CODE;




    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-674")
    public void VerifyCustomerProfileVisibility() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"Error in displaying the customer");
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerProfileDisplayed(),"Error in navigation to customer page");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountHiddenOption();
        softAssert.assertTrue(Customer.isAccountHiddenOptionDisplayed(), "text error");
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Dashboard.navigateToCustomers();
        Customer.logIntoCustomer();
        Customer.loginAsCustomer(email,nameCus);
        Customer.clickCustomerPortalOrderIcon();
        softAssert.assertTrue(Customer.isVisibleAddSupplierButton(), "error in visibility");
        Customer.logIntoDP();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomerListTextDisplayed(), "text error");
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountVisibleOption();
        softAssert.assertTrue(Customer.isAccountVisibleOptionDisplayed(), "error");
        Customer.logIntoCustomer();
        Customer.loginAsCustomer(email, nameCus);
        Customer.clickCustomerPortalOrderIcon();
        softAssert.assertTrue(Customer.isOrderGuideVisibleCustomerPortal(), "error in visibility");
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
