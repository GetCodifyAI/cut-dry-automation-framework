package com.cutanddry.qa.tests.catalog;


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

public class VerifyCatalogSectionsPanelFilterByOnSaleSectionTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String onSaleSection = "On Sale";
    static String onSaleFilterTag = "Sale";
    static String allItemsSection = "All Items";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3672")
    public void verifyCatalogSectionsPanelFilterByOnSaleSection() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "customer search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(onSaleSection), "On Sale section not displayed in Sections panel");

        Customer.clickCatalogFilter(onSaleSection);
        softAssert.assertTrue(Customer.isCatalogFilterTagDisplayed(onSaleFilterTag), "On Sale filter tag not displayed after clicking section");

        Customer.clickCatalogFilter(allItemsSection);
        softAssert.assertTrue(Customer.isCatalogAllItemsTxtDisplayed(), "All Items not displayed after clearing filter");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
