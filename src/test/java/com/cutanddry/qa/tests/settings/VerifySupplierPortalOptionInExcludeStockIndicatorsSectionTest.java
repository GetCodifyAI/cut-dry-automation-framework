package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifySupplierPortalOptionInExcludeStockIndicatorsSectionTest extends TestBase {
    static User user;
    static String supplierName = "Bonollo Provisions";
    static String UOM1 = "Case";
    static String UOM2 = "Each";
    static String UOM3 = "Piece";
    static String customerCode = "001229";
    static String filter = "Availability";
    static String UOM2itemCode, UOM3itemCode, UOM1itemCode;



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1949")
    public void verifySupplierPortalOptionInExcludeStockIndicatorsSection() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Login error - user not navigated to dashboard");

        Login.navigateToConfigSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(supplierName);
        InternalTools.navigateToOrderingSettingsTab();
        ConfigSupplier.checkExcludeStockSupplierPortal(false);
        ConfigSupplier.clickOnOrderSettingSave();

        Login.navigateToDistributorPortal(supplierName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode),"Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerCode);
        Customer.goToCatalog();
        Customer.clickCatalogFilter(filter);
        Customer.selectOutOfStockFilter();
        UOM1itemCode = Customer.getOutOfStockItemsForEachUOMFromCatalog(UOM1);
        Customer.addItemFromHeartIconIfNotAvailableInOG(UOM1itemCode);
        UOM2itemCode = Customer.getOutOfStockItemsForEachUOMFromCatalog(UOM2);
        Customer.addItemFromHeartIconIfNotAvailableInOG(UOM2itemCode);
        UOM3itemCode = Customer.getOutOfStockItemsForEachUOMFromCatalog(UOM3);
        Customer.addItemFromHeartIconIfNotAvailableInOG(UOM3itemCode);
        Customer.clickOnOrderGuideTab();
        Customer.refreshOrderGuide();

        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(), "Navigation error - not navigated to Config Supplier");
        InternalTools.clickOnInternalToolCompanyEditDetails(supplierName);
        InternalTools.navigateToOrderingSettingsTab();

        softAssert.assertTrue(ConfigSupplier.isSupplierPortalCheckboxDisplayed(), "Supplier Portal checkbox is not displayed in the Exclude Stock indicators section");
        softAssert.assertTrue(ConfigSupplier.isSelectedUOMDisplayed(), "Selected UOM field is not displayed in the Exclude Stock indicators section");

        ConfigSupplier.checkExcludeStockSupplierPortal(true);
        ConfigSupplier.selectUOMSForExcludeStock(UOM2);
        ConfigSupplier.selectUOMSForExcludeStock(UOM3);
        ConfigSupplier.clickOnOrderSettingSave();

        Login.navigateToDistributorPortal(supplierName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode),"Unable to find the customer Id");

        //Order Guide verification
        Customer.clickOnOrderGuide(customerCode);
        Customer.searchItemOnOrderGuide(UOM1itemCode);
        softAssert.assertTrue(Customer.isOutOfStockTagDisplayedInOrderGuide(UOM1itemCode),"Out of stock Tag Displayed in OG");
        Customer.searchItemOnOrderGuide(UOM2itemCode);
        softAssert.assertFalse(Customer.isOutOfStockTagDisplayedInOrderGuide(UOM2itemCode),"Out of stock Tag Displayed in OG");
        Customer.searchItemOnOrderGuide(UOM3itemCode);
        softAssert.assertFalse(Customer.isOutOfStockTagDisplayedInOrderGuide(UOM3itemCode),"Out of stock Tag Displayed in OG");

        //Catalog and PDP verification
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(UOM1itemCode);
        softAssert.assertTrue(Customer.isOutOfStockTagDisplayedInCatalog(UOM1itemCode),"Out of stock Tag Displayed in Catalog");
        Customer.clickOnProduct(UOM1itemCode);
        softAssert.assertTrue(Customer.isOutOfStockTagDisplayedInPDP(),"Out of stock Tag Displayed in PDP");
        Customer.clickOnBackButton();
        Customer.searchItemOnCatalog(UOM2itemCode);
        softAssert.assertFalse(Customer.isOutOfStockTagDisplayedInCatalog(UOM2itemCode),"Out of stock Tag Displayed in Catalog");
        Customer.clickOnProduct(UOM2itemCode);
        softAssert.assertFalse(Customer.isOutOfStockTagDisplayedInPDP(),"Out of stock Tag Displayed in PDP");
        Customer.clickOnBackButton();
        Customer.searchItemOnCatalog(UOM3itemCode);
        softAssert.assertFalse(Customer.isOutOfStockTagDisplayedInCatalog(UOM3itemCode),"Out of stock Tag Displayed in Catalog");
        Customer.clickOnProduct(UOM3itemCode);
        softAssert.assertFalse(Customer.isOutOfStockTagDisplayedInPDP(),"Out of stock Tag Displayed in PDP");
        Customer.clickOnBackButton();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
