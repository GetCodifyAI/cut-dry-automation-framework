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

public class VerifyEmptySearchAndStatePersistenceTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemName,itemCode2;
    static String searchItemCodeOG = "xyz123nonexistent";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1826")
    public void VerifyEmptySearchAndStatePersistence() throws InterruptedException {
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
        softAssert.assertTrue(Customer.isNoSearchResultOG(),"filter processing and formulation not work OG");

        Customer.goToCatalog();
        Thread.sleep(5000);
        softAssert.assertEquals(Customer.getCatalogSearchValue(),searchItemCodeOG,"item code display catalog search");
        softAssert.assertTrue(Customer.isNoSearchResultCatalog(),"filter processing and formulation not work Catalog");
        Customer.deleteSearchField();
        Thread.sleep(5000);
        softAssert.assertTrue(Customer.isCatalogAllItemsTxtDisplayed(),"all items display error");

        Customer.clickOnOrderGuideTab();
        Thread.sleep(5000);
        itemCode2 = Customer.getOrderGuideSearchValue();
        softAssert.assertEquals(Customer.getOrderGuideSearchValue(),"","item code display on OG search");
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
