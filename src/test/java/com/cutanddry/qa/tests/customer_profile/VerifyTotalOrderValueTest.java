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

public class VerifyTotalOrderValueTest extends TestBase{
    static User user;
    String customerID = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-673")
    public void VerifyTotalOrderValue() throws InterruptedException {
        String itemName;
        double totalPrice;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID),"search error");
        Customer.SelectCustomer(customerID);
        double valueBeforeOrder = Customer.getLastOrderedValue();
        Customer.clickOnOrderGuideInProfile();
        itemName = Customer.getItemNameFirstRow();
        totalPrice = Customer.getItemPriceFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.submitOrder();
        Customer.clickClose();
        Customer.searchCustomerByCode(customerID);
        Customer.clickOnCustomerCode(customerID);
        double valueAfterOrder = Customer.getLastOrderedValue();
        softAssert.assertEquals(valueAfterOrder,valueBeforeOrder+totalPrice,"last ordered total price");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
