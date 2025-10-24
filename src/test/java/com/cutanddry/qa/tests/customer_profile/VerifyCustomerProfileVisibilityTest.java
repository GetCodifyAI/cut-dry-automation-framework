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

public class VerifyCustomerProfileVisibilityTest extends TestBase{
    static User user;
    String CustomerCode = "41922";
    static String nameCus = "52557616";
    static String businessName = "Nicks Pizza - San Francisco";
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    static String dpName = "Independent Foods Co";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-674")
    public void VerifyCustomerProfileVisibility() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"Error in displaying the customer");
        String BusinessName = Customer.getBusinessNameFromCustomers(CustomerCode);

        Customer.SelectCustomer(CustomerCode);
        softAssert.assertEquals(BusinessName,businessName,"Error in navigation to customer page");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountHiddenOption();

        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(nameCus);
        Customer.clickCustomerPortalOrderIcon();
        softAssert.assertFalse(Customer.isDistributorVisibleOnOPSide(dpName), "error in visibility");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomerListTextDisplayed(), "text error");
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountVisibleOption();
        softAssert.assertTrue(Customer.isAccountVisibleOptionDisplayed(), "error");

        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(nameCus);
        Customer.clickCustomerPortalOrderIcon();
        softAssert.assertTrue(Customer.isDistributorVisibleOnOPSide(dpName), "error in visibility");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
