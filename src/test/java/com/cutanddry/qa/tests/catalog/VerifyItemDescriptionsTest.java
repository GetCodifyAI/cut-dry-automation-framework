package com.cutanddry.qa.tests.catalog;

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

public class VerifyItemDescriptionsTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String item = "Organic Bananas";
    static String itemName, searchItemCode;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-295")
    public void verifyItemDescriptions() throws InterruptedException {
//        String itemNamePDP;
//        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();

        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        Customer.goToCatalog();

//        Customer.searchItemOnCatalog(item);
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found in catalog");
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
//        itemName = Customer.getFirstElementFrmSearchResults(item);
//        Customer.clickOnCatalogItem(itemName);
//        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"navigation error");
//        itemNamePDP = Customer.getItemNamePDPView();
//        softAssert.assertEquals(itemName,itemNamePDP,"name mismatch");
        softAssert.assertTrue(Customer.getItemNamePDPView(itemName).contains(itemName.toLowerCase()), "item name not found in PDP View");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
