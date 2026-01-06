package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.InfluenceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheActiveBoostFilterDisplayStatusTest extends TestBase {
    static User user;
    String DP = InfluenceData.DISTRIBUTOR_BIRITE;
    static String StockedInHouse = "Stocked (in-house)";
    static String outOfStock = "Out of Stock";
    static String BEPRebates = "BEP Rebates";
    static String customerId = "20230561";
    static String itemType = "Item Type";
    static String availability = "Availability";
    static String deals = "Deals";
    static String allItemsSection = "All Items";
    static String RestaurantUserCode = "133014684";
    static String SupplierName = "BiRite Foodservice Distributors";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-3696")
    public void VerifyTheActiveBoostFilterDisplayStatus() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickBoostFilters();
        softAssert.assertTrue(Boost.isFiltersTestDisplay(),"navigate to boost filters error");
        Thread.sleep(4000);
        Boost.TurnOnBoostFilterToggle(StockedInHouse,true);
        Boost.TurnOnBoostFilterToggle(outOfStock,true);
        Boost.TurnOnBoostFilterToggle(BEPRebates,true);
        Boost.saveBrandBoost();
        Thread.sleep(4000);

        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.clickOnPreviewCatalog();
        Assert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        Customer.clickCatalogFilterDropDown(itemType);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(itemType,StockedInHouse),"filter display error StockedInHouse");

        Customer.clickCatalogFilterDropDown(availability);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(availability,outOfStock),"filter display error outOfStock");

        Customer.clickCatalogFilterDropDown(deals);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(deals,BEPRebates),"filter display error BEPRebates");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        Customer.clickCatalogFilterAllItems(allItemsSection);
        Thread.sleep(5000);
        Customer.clickCatalogFilterDropDown(itemType);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(itemType,StockedInHouse),"filter display error StockedInHouse");

        Customer.clickCatalogFilterDropDown(availability);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(availability,outOfStock),"filter display error outOfStock");

        Customer.clickCatalogFilterDropDown(deals);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(deals,BEPRebates),"filter display error BEPRebates");

        // Restaurant Flows
        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrder(SupplierName);
        Assert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        Customer.goToCatalog();

        Customer.goToCatalog();
        Customer.clickCatalogFilterAllItems(allItemsSection);
        Thread.sleep(5000);
        Customer.clickCatalogFilterDropDown(itemType);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(itemType,StockedInHouse),"filter display error StockedInHouse");

        Customer.clickCatalogFilterDropDown(availability);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(availability,outOfStock),"filter display error outOfStock");

        Customer.clickCatalogFilterDropDown(deals);
        softAssert.assertTrue(Customer.isCatalogFilterOptionDisplayed(deals,BEPRebates),"filter display error BEPRebates");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
