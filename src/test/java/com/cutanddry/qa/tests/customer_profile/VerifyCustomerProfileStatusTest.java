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

public class VerifyCustomerProfileStatusTest extends TestBase{
    static User user;
    String customerID = "97071";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-675")
    public void VerifyCustomerProfileStatus() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID),"search error");
        Customer.SelectCustomer(customerID);
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        Customer.clickCusAccountStatusOption();
        Customer.clickCusAccountStatusDropdown();
        softAssert.assertTrue(Customer.isAccountStatusDropdownMenuDisplayed(), "text error");
        Customer.selectCusAccountStatusOption();
        softAssert.assertTrue(Customer.isAccountStatusTextDisplayed(), "text error");
        Dashboard.navigateToCustomers();
        Customer.refreshCustomersPage();
        Customer.searchCustomerByCode(customerID);
        softAssert.assertTrue(Customer.isSearchedCustomerNotDisplayedText(), "text error");
        Customer.clickCustomersMoreFilter();
        Customer.clickOnMoreFilterStatusDropdownMenu();
        Customer.clickCustomersInactiveStatusFilter();
        Customer.clickCustomerFilterApply();
        Customer.SelectCustomer(customerID);
        Customer.clickCusAccountStatusOption();
        Customer.clickCusAccountStatusDropdown();
        softAssert.assertTrue(Customer.isAccountStatusDropdownMenuDisplayed(), "text error");
        Customer.selectCusAccountActiveStatusOption();
        softAssert.assertTrue(Customer.isCusActiveStatusTextDisplayed(), "text error");
        Dashboard.navigateToCustomers();
        Customer.refreshCustomersPage();
        Customer.clickCustomersMoreFilter();
        Customer.clickOnMoreFilterStatusDropdownMenu();
        Customer.clickCustomersActiveStatusFilter();
        Customer.clickCustomerFilterApply();
        Customer.searchCustomerByCode(customerID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID),"search error");
        softAssert.assertAll();
    }

  @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
