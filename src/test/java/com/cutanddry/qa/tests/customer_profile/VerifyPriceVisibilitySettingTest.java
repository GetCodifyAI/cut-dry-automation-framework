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

public class VerifyPriceVisibilitySettingTest extends TestBase {
    static User user;
    String customerCode = "16672";
    String distributorName = "47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    
    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1861")
    public void verifyPriceVisibilitySetting() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), 
            "Login failed - user not navigated to restaurant dashboard");
        
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Failed to navigate to distributor portal");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomerListTextDisplayed(), 
            "Failed to navigate to customers section");
        
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), 
            "Customer search result not displayed for code: " + customerCode);
        
        Customer.SelectCustomer(customerCode);
        
        softAssert.assertTrue(Customer.isPriceVisibilityDisplayed(), 
            "Price Visibility field not displayed in customer profile");
        
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
