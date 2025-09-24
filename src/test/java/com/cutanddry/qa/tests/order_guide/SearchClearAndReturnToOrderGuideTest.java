package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.checkerframework.checker.units.qual.C;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchClearAndReturnToOrderGuideTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemName,itemCode,itemCode2;
    static String searchItemCodeOG = "01409";
    static String itemNameSearchOG = "Carrot - Baby Peeled - 1 LB";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1824")
    public void SearchClearAndReturnToOrderGuide() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.searchItemOnOrderGuide(searchItemCodeOG);
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemNameSearchOG,"item mismatch");
        itemCode = Customer.getOrderGuideSearchValue();
        softAssert.assertEquals(Customer.getOrderGuideSearchValue(),searchItemCodeOG,"item code display on OG search");

        Customer.goToCatalog();
        Thread.sleep(5000);
        softAssert.assertEquals(Customer.getCatalogSearchValue(),searchItemCodeOG,"item code display catalog search");
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemNameSearchOG).contains(itemNameSearchOG.toLowerCase()), "item not found catalog");
        Customer.deleteSearchField();
        Thread.sleep(5000);
        softAssert.assertTrue(Customer.isTopCategoryPicksDisplayed(),"top picks missing error");
        softAssert.assertTrue(Customer.isCatalogAllItemsTxtDisplayed(),"all items display error");

        Customer.clickOnOrderGuideTab();
        Thread.sleep(5000);
        itemCode2 = Customer.getOrderGuideSearchValue();
        softAssert.assertEquals(Customer.getOrderGuideSearchValue(),"","item code display on OG search");
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch og");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
