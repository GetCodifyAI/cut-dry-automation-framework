package com.cutanddry.qa.tests.customer_catalog;

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

public class VerifyThatTheNewArrivalsSectionIsAvailableInCustomerCatalogTest extends TestBase {
    static User user;
    String CustomerCode = "16579";
    static String filter = "New Arrivals";
    static String itemName = "Organic Bananas - 2 LB";
    static String tag = "New";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1382")
    public void VerifyThatTheNewArrivalsSectionIsAvailableInCustomerCatalog() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(filter),"catalog filter not display");
        Customer.clickCatalogFilter(filter);
        softAssert.assertTrue(Customer.isCatalogFilterDisplayTag(itemName,tag),"new tag display error");
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
