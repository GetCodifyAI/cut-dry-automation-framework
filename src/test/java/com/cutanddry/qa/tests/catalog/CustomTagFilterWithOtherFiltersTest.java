package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CustomTagFilterWithOtherFiltersTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String brand = "Brand";
    static String brandOption = "Almond Breeze";
    static String brandItem = "Milk Almond Barista Unsweetened";
    static String tagFilter = "Item Type";
    static String tagOption = "Stocked";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4431")
    public void CustomTagFilterWithOtherFilters() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "customer search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();
        Customer.clickCatalogFilterSectionDropDown(brand);
        Customer.clickCatalogFilterBrandDropDownOption(brandOption);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(brandItem).contains(brandItem.toLowerCase()), "brand filter not applied");
        Customer.clickCatalogFilterSectionDropDown(tagFilter);
        Customer.clickCatalogFilterBrandDropDownOption(tagOption);
        softAssert.assertTrue(Customer.isCatalogFilterTagDisplayed(tagOption), "custom tag filter not applied with brand filter");
        Customer.clickCatalogFilterBrandDropDownOption(tagOption);
        Thread.sleep(5000);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(brandItem).contains(brandItem.toLowerCase()), "brand products not displayed after removing tag filter");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
