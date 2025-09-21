package com.cutanddry.qa.tests.dashboard;

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

public class VerifyOrderGuideChangesSectionFunctionalityTest extends TestBase {
    static User user;
    String customerId = "37631";
    String itemCode = "Test item";
    String distributorName = "Test distributor";
    String restaurantName = "Test restaurant";
    String salespersonName = "Test salesperson";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1644")
    public void verifyOrderGuideChangesSectionFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Operator login failed");
        
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemCode);
        Customer.clickOrderGuide();
        softAssert.assertTrue(Customer.addedItemDisplayOnOrderGuide(itemCode), "Item not added to OG");
        Customer.clickOrderGuideProduct(itemCode);
        Customer.clickRemoveOrderGuide();
        
        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "DP login failed");
        
        softAssert.assertTrue(Dashboard.isOrderGuideChangesSectionDisplayed(), 
            "Order Guide Changes section not displayed");
        softAssert.assertTrue(Dashboard.isOrderGuideChangesDataDisplayed(), 
            "Order Guide Changes data not displayed");
        
        Dashboard.selectDateRange("30 days");
        Dashboard.selectRestaurantFilter(restaurantName);
        Dashboard.selectSalespersonFilter(salespersonName);
        
        Dashboard.clickViewAllOrderGuideChanges();
        softAssert.assertTrue(Dashboard.isOrderGuideChangesDataDisplayed(), 
            "View All functionality failed");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
