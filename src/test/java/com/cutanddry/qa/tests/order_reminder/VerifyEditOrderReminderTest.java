package com.cutanddry.qa.tests.order_reminder;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.OrderGuideSettings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class VerifyEditOrderReminderTest extends TestBase {

     static User user;
     String CustomerCode = "16579";
     String OrderCutoffTime = "3:30 AM";
     String AlertTime = "2 hours before cutoff time";
     String AlertDays = "Tuesday";

     @BeforeMethod
     public void setUp(){
         initialization();
         user = JsonUtil.readUserLogin();
     }

     @Test(groups = "DOT-TC-1078")
     public void VerifyEditOrderReminder() throws InterruptedException{
         SoftAssert softAssert = new SoftAssert();
         Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
         softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
         Dashboard.navigateToCustomers();
         softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section isn't displayed");
         Customer.searchCustomerByCode(CustomerCode);
         softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"Customer is not found");
         String BusinessName = Customer.getBusinessNameFromCustomers(CustomerCode);
         Customer.SelectCustomer(CustomerCode);
         softAssert.assertTrue(Customer.isCustomerProfileDisplayed(BusinessName),"customer profile isn't displayed");
         Customer.clickOnOrderGuideInProfile();
         softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed());

         Customer.goToEdit();
         softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"Edit Order Guide screen isn't displayed");
         Customer.expandMoreOptionsDropdown();
         Customer.clickOnOrderGuideSettings();
         OrderGuideSettings.clickOnOrderReminderAlert();
         softAssert.assertTrue(OrderGuideSettings.isOrderReminderAlertPopDisplayed());
         OrderGuideSettings.clickOrderCutoffTime(OrderCutoffTime);
         OrderGuideSettings.clickSendAlert(AlertTime);
         OrderGuideSettings.clickAlertDays(AlertDays);
         OrderGuideSettings.clickOrderRemiderAlertSettingSave();
         OrderGuideSettings.clickOnSave();
         softAssert.assertAll();
     }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}