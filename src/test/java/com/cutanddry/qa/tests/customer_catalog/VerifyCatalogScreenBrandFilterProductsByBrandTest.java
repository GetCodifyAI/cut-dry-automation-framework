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

public class VerifyCatalogScreenBrandFilterProductsByBrandTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String brand = "Brand";
    static String option1 = "Almond Breeze";
    static String option2 = "Aesops Bagels";
    static String OptionItem1 = "Milk Almond Barista Unsweetened";
    static String OptionItem2 = "Bagel Plain Sliced 99129";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3675")
    public void VerifyCatalogScreenBrandFilterProductsByBrand() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "customer search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        Customer.clickCatalogFilterSectionDropDown(brand);
        Customer.clickCatalogFilterBrandDropDownOption(option1);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(OptionItem1).contains(OptionItem1.toLowerCase()), "item not found");

        Customer.clickCatalogFilterBrandDropDownOption(option2);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(OptionItem2).contains(OptionItem2.toLowerCase()), "item not found");

        Customer.clickClearAllFilters();
        Thread.sleep(5000);
        softAssert.assertTrue(Customer.isCatalogAllItemsTxtDisplayed(), "All Items not displayed after clearing filter");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
