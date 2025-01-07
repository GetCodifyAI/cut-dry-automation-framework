package com.cutanddry.qa.tests.customer_orders;

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

public class VerifyThePauseStandingOrderTest extends TestBase {
    static User user;
    String CustomerCode = "16579";
    static String deliveryDay = "Monday";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-665")
    public void VerifyThePauseStandingOrder() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        String BusinessName = Customer.getBusinessNameFromCustomers(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerProfileDisplayed(BusinessName),"customer profile not display ");
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isStandingOrdersDisplayed(),"navigation error");
        Customer.removeStandingOrdersIfAvailable();
        Customer.clickOnCreateStandingOrder();
        Customer.selectDeliveryDate(deliveryDay);
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyBysix();
        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(),"order creating error");
        Customer.clickOK();
        Customer.clickOnOrdersTab();
        Customer.clickPause();
        softAssert.assertTrue(Customer.isStandingOrdersPaused(),"standing orders not paused");
        Customer.clickResume();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
