package com.cutanddry.qa.tests.customer_catalog;


import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Boost;
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

public class VerifyCatalogCategoryFilterFilterByProductCategoryTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String bakerySection = "Bakery";
    static String seafoodSection = "Seafood";
    static String allItemsSection = "All Items";
    static String bakeryItem = "Bagel - Cinnamon Raisin Frothy Monkey";
    static String seaFoodItem = "Bass Striped Wild Hdon/Drsd/Scaled Wild";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3673")
    public void VerifyCatalogCategoryFilterFilterByProductCategory() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "customer search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        Customer.clickCatalogFilterAllItems(bakerySection);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(bakeryItem).contains(bakeryItem.toLowerCase()), "item not found");

        Customer.clickCatalogFilterAllItems(seafoodSection);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(seaFoodItem).contains(seaFoodItem.toLowerCase()), "item not found");

        Customer.clickCatalogFilterAllItems(allItemsSection);
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
