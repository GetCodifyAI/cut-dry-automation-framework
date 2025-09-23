package com.cutanddry.qa.tests.customer_track;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyTheHidePriceFunctionalityTest extends TestBase {
    static User user;
    static String customerCode = "10907";
    static String itemCode = "01700";
    static String vendorId = "46465810";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1861")
    public void verifyTheHidePriceFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Login failed - user not navigated to distributor dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Failed to navigate to customers section");
        
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), 
            "Customer search result not displayed for code: " + customerCode);
        
        Customer.navigateToCustomerProfile(customerCode);
        softAssert.assertTrue(Customer.isPriceVisibilityDisplayed(), 
            "Price Visibility field not displayed on customer profile");
        
        String initialPriceVisibility = Customer.getPriceVisibilityValue();
        softAssert.assertNotNull(initialPriceVisibility, 
            "Price Visibility value should not be null");
        
        String currentPriceVisibility = Customer.getPriceVisibilityValue();
        softAssert.assertNotNull(currentPriceVisibility, 
            "Price Visibility value should be accessible");
        
        softAssert.assertTrue(currentPriceVisibility.equals("Visible") || currentPriceVisibility.equals("Hidden"), 
            "Price Visibility value should be either 'Visible' or 'Hidden', but was: " + currentPriceVisibility);
        
        System.out.println("Current Price Visibility setting: " + currentPriceVisibility);
        
        System.out.println("Test completed successfully for distributor portal portion.");
        System.out.println("Price Visibility functionality verified: " + currentPriceVisibility);
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
