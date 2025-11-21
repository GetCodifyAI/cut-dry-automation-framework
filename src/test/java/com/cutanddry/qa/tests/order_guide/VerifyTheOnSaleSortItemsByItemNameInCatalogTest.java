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

public class VerifyTheOnSaleSortItemsByItemNameInCatalogTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String onSaleFilter = "On Sale";
    static String onSaleFilterTag = "Sale";
    static String sortItemName = "Item Name";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1549")
    public void VerifyTheOnSaleSortItemsByItemNameInCatalog() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(onSaleFilter),"catalog filter not display");
        Customer.clickCatalogFilter(onSaleFilter);
        softAssert.assertTrue(Customer.isCatalogFilterTagDisplayed(onSaleFilterTag),"catalog filter tag not display");
        Customer.clickCatalogListView();
//        Customer.clickCatalogListViewSort(sortItemName);
        softAssert.assertTrue(Customer.areFirstFiveItemNamesSortedAscending("2"),"error in item name sort");
        Customer.clickCatalogListViewSort(sortItemName);
        Thread.sleep(4000);
        softAssert.assertTrue(Customer.areFirstFiveItemNamesSortedDescending("2"),"error in item name sort z->A");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
