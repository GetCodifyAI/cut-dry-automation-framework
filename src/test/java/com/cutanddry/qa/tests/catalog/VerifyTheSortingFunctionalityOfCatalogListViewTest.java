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

public class VerifyTheSortingFunctionalityOfCatalogListViewTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String sortItemCode = "Item Code";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1290")
    public void VerifyTheSortingFunctionalityOfCatalogListView() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - user not navigated to dashboard");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search failed - customer not found");

        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();
        Customer.clickCatalogListView();

        Customer.clickCatalogListViewSort(sortItemCode);
        softAssert.assertTrue(Customer.areFirstThreeItemCodesSortedAscending(), "Items are not sorted in ascending order after first click on Item Code header");

        Customer.clickCatalogListViewSort(sortItemCode);
        softAssert.assertTrue(Customer.areFirstThreeItemCodesSortedDescending(), "Items are not sorted in descending order after second click on Item Code header");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}