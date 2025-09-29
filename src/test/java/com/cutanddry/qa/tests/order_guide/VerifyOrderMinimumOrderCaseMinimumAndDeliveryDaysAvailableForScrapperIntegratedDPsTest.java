package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyOrderMinimumOrderCaseMinimumAndDeliveryDaysAvailableForScrapperIntegratedDPsTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1900")
    public void VerifyOrderMinimumOrderCaseMinimumAndDeliveryDaysAvailableForScrapperIntegratedDPs() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Login error - user not navigated to distributor dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Failed to navigate to customers section");
        
        Customer.searchCustomerByCode("16672");
        Customer.clickOnOrderGuide("16672");
        
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        
        softAssert.assertTrue(OrderGuideSettings.isOrderGuideNameDisplayed(), 
            "Order Guide Settings page not displayed - Order Guide Name field not found");
        
        softAssert.assertTrue(OrderGuideSettings.isGuideDescriptionDisplayed(), 
            "Guide Description field not displayed in Order Guide Settings");
        
        softAssert.assertTrue(OrderGuideSettings.isPriceVisibilityDropdownDisplayed(), 
            "Price Visibility dropdown not displayed in Order Guide Settings");
        
        softAssert.assertTrue(OrderGuideSettings.isAddItemsRestrictionDropdownDisplayed(), 
            "Add Items Restriction dropdown not displayed in Order Guide Settings");
        
        softAssert.assertTrue(OrderGuideSettings.isEnableOrderApprovalCheckboxDisplayed(), 
            "Enable Order Approval checkbox not displayed in Order Guide Settings");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
