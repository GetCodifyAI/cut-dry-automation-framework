package com.cutanddry.qa.tests.customers.buyoutItems;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.BuyoutsData;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyNoItemRemovalsFromBuyoutAllowedCustomersTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String canonicalProduct1_Node = BuyoutsData.BUYOUT_PRODUCT1_CANONICAL_NODE;
    static String featureKey = BuyoutsData.BUYOUT_PRODUCT_KEY;
    static String featureValue = BuyoutsData.BUYOUT_PRODUCT_VALUE;
    static String DistributorName = "BiRite Foodservice Distributors";
    static String customerId = BuyoutsData.BUYOUT_ALLOWED_CUSTOMER;
    static String integrationDataNode = BuyoutsData.INTEGRATION_NODE_BUYOUT_ALLOWED_CUSTOMER;
    static String buyOutAllowed = BuyoutsData.BUYOUT_ALLOWED_KEY;
    static String buyOutAllowedTrue = BuyoutsData.BUYOUT_ALLOWED_VALUE_TRUE;
    static String orderMinimumSetting = "Exempt from Order Minimum";
    static String customerLoginCode = "373999623";
    static String buyoutItemItemCode = BuyoutsData.BUYOUT_PRODUCT_ITEMCODE;
    static String buyoutItemItemName = BuyoutsData.BUYOUT_PRODUCT_ITEMNAME;
    static String nonBuyoutItemItemCode = BuyoutsData.NON_BUYOUT_PRODUCT_ITEMCODE;
    static String nonBuyoutItemItemName = BuyoutsData.NON_BUYOUT_PRODUCT_ITEMNAME;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2820")
    public void VerifyNoItemRemovalsFromBuyoutAllowedCustomers() throws InterruptedException{
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToNode(integrationDataNode);
        Login.setValueToNode(buyOutAllowed,buyOutAllowedTrue);

        Login.navigateToNode(canonicalProduct1_Node);
        Login.setValueToNode(featureKey,featureValue);

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.SelectOrderMinimumFromProfile(orderMinimumSetting);
        Customer.ifHasHoldsRemoveHoldsFromCustomer();
        InternalTools.refreshPage();
        Customer.clickOnOrderGuideInProfile();

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(buyoutItemItemCode);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(buyoutItemItemCode),"Prop Item not displayed in catalog");
        Catalog.ClickOnMultiUomDropDown(buyoutItemItemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        Customer.clickOnBackButton();
        Customer.searchItemOnCatalog(nonBuyoutItemItemCode);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(nonBuyoutItemItemCode),"Prop Item not displayed in catalog");
        Customer.clickOnPlusIconInCatalog(1,nonBuyoutItemItemName);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isAddedItemDisplayed(buyoutItemItemName), "The buyout item not display");
        softAssert.assertTrue(Customer.isAddedItemDisplayed(nonBuyoutItemItemName), "The non buyout item not display");
        Customer.submitOrderRebate();

        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        softAssert.assertFalse(Customer.isRemoveUnavailableItemsMsgHeaderDisplayed(), "unavailable Item removed msg header not displayed");
        softAssert.assertFalse(Customer.isRemoveUnavailableItemsMsgDisplayed(), "unavailable Item removed msg not displayed");
        softAssert.assertFalse(Customer.isRemovedBuyoutItemCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode),"Buyout item removed is not correctly displayed");


        Login.switchIntoNewTab();
        Login.navigateToLoginAsPortal(customerLoginCode);
        Orders.SelectSupplierFromPlaceOrderPage(DistributorName);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(buyoutItemItemCode);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(buyoutItemItemCode),"buyout Item not displayed op side catalog");
        Catalog.ClickOnMultiUomDropDown(buyoutItemItemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);

        Customer.clickOnBackButton();
        Customer.searchItemOnCatalog(nonBuyoutItemItemCode);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(nonBuyoutItemItemCode),"non buyout Item not displayed op side catalog");
        Customer.clickOnPlusIconInCatalog(1,nonBuyoutItemItemName);
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.isAddedItemDisplayed(buyoutItemItemName), "The buyout item not display");
        softAssert.assertTrue(Customer.isAddedItemDisplayed(nonBuyoutItemItemName), "The non buyout item not display");
        Customer.submitOrderRebate();

        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        softAssert.assertFalse(Customer.isRemoveUnavailableItemsMsgHeaderDisplayed(), "unavailable Item removed msg header not displayed");
        softAssert.assertFalse(Customer.isRemoveUnavailableItemsMsgDisplayed(), "unavailable Item removed msg not displayed");
        softAssert.assertFalse(Customer.isRemovedBuyoutItemCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode),"Buyout item removed is not correctly displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
