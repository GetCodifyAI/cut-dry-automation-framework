package com.cutanddry.qa.tests.search;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyMultipleSearchTermsAndNavigationTest extends TestBase {
    static User user;
    static String customerCode = "415-505-5531";
    static String beefSearchTerm = "beef";
    static String vegetablesSearchTerm = "vegetables";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1825")
    public void VerifyMultipleSearchTermsAndNavigation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Customers section not displayed");
        
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), "Customer search failed");
        
        Customer.clickOnOrderGuide(customerCode);
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "Order guide navigation failed");
        
        Customer.searchItemOnOrderGuide(beefSearchTerm);
        String beefSearchResult = Customer.getFirstElementFrmSearchResults(beefSearchTerm);
        softAssert.assertTrue(beefSearchResult != null && beefSearchResult.length() > 0, 
                             "Step 1: Beef search in order guide failed - no search results found");
        
        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isUserNavigatedToCatalog(), "Step 2: Catalog navigation failed");
        
        Customer.searchItemOnCatalog(vegetablesSearchTerm);
        String vegetableSearchResult = Customer.getFirstElementFrmSearchResults(vegetablesSearchTerm);
        softAssert.assertTrue(vegetableSearchResult != null && vegetableSearchResult.length() > 0, 
                             "Step 2: Vegetables search in catalog failed - no search results found");
        
        Customer.clearSearchField();
        softAssert.assertTrue(Customer.isUserNavigatedToCatalog(), "Step 3: Still on catalog after clearing search");
        
        Customer.clickOnOrderGuide(customerCode);
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "Step 4: Return to order guide failed");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeMultipleTabs();
    }
}
