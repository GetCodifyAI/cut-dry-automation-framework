package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class VerifyOrderMinimumOrderCaseMinimumAndDeliveryDaysNotAvailableForIntegratedDPsTest extends TestBase {

     static User user;
     static String CustomerCode = "16579";


     @BeforeMethod
     public void setUp(){
         initialization();
         user = JsonUtil.readUserLogin();
     }

     @Test(groups = "DOT-TC-1898")
     public void VerifyOrderMinimumOrderCaseMinimumAndDeliveryDaysNotAvailableForIntegratedDPs() throws InterruptedException{
         SoftAssert softAssert = new SoftAssert();
         Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
         Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
         Dashboard.navigateToCustomers();
         softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section isn't displayed");
         Customer.searchCustomerByCode(CustomerCode);
         Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"Customer is not found");
         Customer.clickOnOrderGuide(CustomerCode);
         softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(),"order guide not displayed");

         Customer.expandMoreOptionsDropdown();
         Customer.clickOnOrderGuideSettings();
         softAssert.assertFalse(OrderGuideSettings.isEditMinOrderAmountDisplayed(),"order min order amount field not displayed");
         softAssert.assertFalse(OrderGuideSettings.isEditMinOrderCaseDisplayed(),"order min order case field not displayed");
         softAssert.assertFalse(OrderGuideSettings.isDeliveryDaysDisplayed(),"order delivery days field not displayed");

         Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
         softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");
         Dashboard.navigateToIndependentFoodsCo();
         Dashboard.navigateToOrderGuide();
         Customer.expandMoreOptionsDropdown();
         Customer.clickOnOrderGuideSettings();
         softAssert.assertFalse(OrderGuideSettings.isEditMinOrderAmountDisplayed(),"order min order amount field not displayed");
         softAssert.assertFalse(OrderGuideSettings.isEditMinOrderCaseDisplayed(),"order min order case field not displayed");
         softAssert.assertFalse(OrderGuideSettings.isDeliveryDaysDisplayed(),"order delivery days field not displayed");
         softAssert.assertAll();
     }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}