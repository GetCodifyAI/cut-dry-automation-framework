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

public class VerifyMultipleSearchTermsAndNavigationTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemName,itemCode,itemCode2;
    static String searchItemCodeOG = "01409";
    static String itemNameSearchVegetable = "Brussels Sprouts - 12 OZ";
    static String beefSearchTerm = "beef";
    static String vegetablesSearchTerm = "vegetables";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1825")
    public void VerifyMultipleSearchTermsAndNavigation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Customers section not displayed");

        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "Order guide navigation failed");
        itemName = Customer.getItemNameFirstRow();

        Customer.searchItemOnOrderGuide(beefSearchTerm);
        String firstRowItem = Customer.getItemNameFirstRow();

        softAssert.assertTrue(
                firstRowItem != null && !firstRowItem.isEmpty() && firstRowItem.toLowerCase().contains("beef"),
                "Step 1: Beef search in order guide failed - no search results found or first row item is incorrect"
        );

        Customer.goToCatalog();
        Thread.sleep(5000);
        softAssert.assertEquals(Customer.getCatalogSearchValue(),beefSearchTerm,"item code display catalog search");
        Customer.searchItemOnCatalog(vegetablesSearchTerm);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemNameSearchVegetable).contains(itemNameSearchVegetable.toLowerCase()), "item not found catalog");
        Customer.deleteSearchField();
        Thread.sleep(5000);
        softAssert.assertTrue(Customer.isCatalogAllItemsTxtDisplayed(),"all items display error");

        Customer.clickOnOrderGuideTab();
        Thread.sleep(5000);
        itemCode2 = Customer.getOrderGuideSearchValue();
        softAssert.assertEquals(Customer.getOrderGuideSearchValue(),"","item code display on OG search");
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch order guide");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
