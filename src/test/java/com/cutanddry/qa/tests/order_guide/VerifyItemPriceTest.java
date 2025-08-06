package com.cutanddry.qa.tests.order_guide;

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

public class VerifyItemPriceTest extends TestBase {
    static User user;
    static String customerId = "16579";
    String itemName;
    static double itemPriceOG,itemPricePDP;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-296")
    public void VerifyItemPrice() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        itemName = Customer.getItemNameFirstRow();
        itemPriceOG = Customer.getItemPriceFirstRow();
        Customer.clickOnItem(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"navigation error");
        itemPricePDP = Customer.getItemPricePDP();
        Customer.clickOnBack();
        softAssert.assertEquals(Customer.getItemPriceFirstRow(),itemPricePDP,"price error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
