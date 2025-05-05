package com.cutanddry.qa.tests.order_reminder;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.OrderGuideSettings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class VerifyTheOrderReminderAlertOptionIsRemovedFromOrderGuideSettingsTest extends TestBase {

     static User user;
     static String CustomerCode = "16579";


     @BeforeMethod
     public void setUp(){
         initialization();
         user = JsonUtil.readUserLogin();
     }

     @Test(groups = "DOT-TC-1086")
     public void VerifyTheOrderReminderAlertOptionIsRemovedFromOrderGuideSettings() throws InterruptedException{
         SoftAssert softAssert = new SoftAssert();
         Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
         Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
         Dashboard.navigateToCustomers();
         softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section isn't displayed");
         Customer.searchCustomerByCode(CustomerCode);
         Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"Customer is not found");
         Customer.clickOnOrderGuide(CustomerCode);
         softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(),"order guide not displayed");

         Customer.goToEdit();
         softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"Edit Order Guide screen isn't displayed");
         Customer.expandMoreOptionsDropdown();
         Customer.clickOnOrderGuideSettings();
         softAssert.assertFalse(OrderGuideSettings.isOrderReminderAlertSettingDisplayed(),"order reminder alert setting displayed");
         softAssert.assertAll();
     }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}