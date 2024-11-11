package com.cutanddry.qa.tests.customer_specific;

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

public class VerifyCustomerSpecificProductRecommendationsTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemCode = "01407";
    static String itemName = "Artichoke -24ct";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-269")
    public void verifyCustomerSpecificProductRecommendations() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnBoostTab();
        softAssert.assertTrue(Customer.isBroadcastTextDisplayed(),"navigation error");
        Customer.clickOnAddItems();
        Customer.selectItem(itemCode);
        Customer.clickOnAdd();
        softAssert.assertTrue(Customer.isItemAdded(itemCode),"item adding error");
        Customer.clickOnOrderGuideInProfile();
        Customer.searchItemOnOrderGuide(itemName);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        softAssert.assertTrue(Customer.isRecommendedBySalesRepDisplayed(itemCode),"recommended by sales rep item missing error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnBoostTab();
        softAssert.assertTrue(Customer.isBroadcastTextDisplayed(),"navigation error");
        Customer.clickOnRemoveItem(itemCode);
        softAssert.assertFalse(Customer.isItemAdded(itemCode),"item removing error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
