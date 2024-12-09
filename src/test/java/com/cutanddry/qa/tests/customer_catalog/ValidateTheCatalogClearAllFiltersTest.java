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

public class ValidateTheCatalogClearAllFiltersTest extends TestBase {
    static User user;
    String CustomerCode = "37631";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-706")
    public void ValidateTheCatalogClearAllFilters() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.goToCatalog();
        Customer.clickSection();
        Customer.clickCategory();
        Customer.clickBrand();
        softAssert.assertTrue(Customer.isFilteredBrandDisplayed(),"brand filter not work");
        Customer.clickItemStatus();
        Customer.clickStorageType();
        Customer.clickDietType();
        softAssert.assertTrue(Customer.isFilterItemDisplayed(),"filter not work");
        Customer.clickProcessingFormulation();
        softAssert.assertTrue(Customer.isFilterProcessingTypeWork(),"filter processing and formulation not work");
        Customer.clickClearAllFilters();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
