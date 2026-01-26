package com.cutanddry.qa.tests.customer_orderguide;

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

public class VerifyOrderGuideRefreshAfterCatalogItemAddRemoveTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemCode = "01407";
    static String itemName = "Carrot Rainbow -25 LB";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1599")
    public void VerifyOrderGuideRefreshAfterCatalogItemAddRemove() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(itemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.addItemFromCatalogStable(itemName);
        Customer.clickOnOrderGuideTab();
        Customer.refreshOrderGuide();
        Customer.searchItemOnOrderGuide(itemCode);
        softAssert.assertTrue(Customer.getItemNameFirstRow().toLowerCase().contains(itemName.toLowerCase()),"item mismatch og");

        Customer.goToCatalog();

        Customer.searchItemOnCatalog(itemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.removeItemFromCatalogStable(itemName);
        Customer.clickOnOrderGuideTab();
        Customer.refreshOrderGuide();
        Customer.searchItemOnOrderGuide(itemCode);
        softAssert.assertFalse(Customer.getItemNameFirstRow().toLowerCase().contains(itemName.toLowerCase()),"item mismatch og error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
