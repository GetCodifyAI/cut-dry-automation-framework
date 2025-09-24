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
    static String nameCus = "209223241";
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";

    
    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1861")
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
        softAssert.assertTrue(Customer.isCustomerProfileDisplayed(BusinessName),"Error in navigation to customer page");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountHiddenOption();

        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(nameCus);
        Customer.clickCustomerPortalOrderIcon();
        softAssert.assertFalse(Customer.isVisibleAddSupplierButton(), "Add Supplier button should not be visible when customer visibility is Hidden");

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
        softAssert.assertTrue(Customer.isOrderGuideVisibleCustomerPortal(), "error in visibility");
        softAssert.assertTrue(Customer.isVisibleAddSupplierButton(), "Add Supplier button should be visible when customer visibility is Visible");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
