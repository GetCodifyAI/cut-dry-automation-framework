package com.cutanddry.qa.tests.customers.buyoutItems;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.BuyoutsData;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.NumberFormat;
import java.util.Locale;

public class VerifySoftOrderMinimumNotMetAfterBuyoutItemRemovalTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = "BiRite Foodservice Distributors";
    static String canonicalNodeName = BuyoutsData.BUYOUT_PRODUCT1_CANONICAL_NODE;
    static String featureKey = BuyoutsData.BUYOUT_PRODUCT_KEY;
    static String featureValue = BuyoutsData.BUYOUT_PRODUCT_VALUE;
    static String HardOrderMinimumType = "Soft Order Minimum";
    static String orderMinInternal = "5000000";
    static String orderMinIn$ = "50,000.00";
    static String customerId = BuyoutsData.BUYOUT_NOT_ALLOWED_CUSTOMER;
    static String orderMinimumSetting = "Use Global Settings";
    static String buyoutItemItemCode = BuyoutsData.BUYOUT_PRODUCT_ITEMCODE;
    static String buyoutItemItemName = BuyoutsData.BUYOUT_PRODUCT_ITEMNAME;
    static String nonBuyoutItemItemCode = BuyoutsData.NON_BUYOUT_PRODUCT_ITEMCODE;
    static String nonBuyoutItemItemName = BuyoutsData.NON_BUYOUT_PRODUCT_ITEMNAME;
    static String orderMinimumLabel = BuyoutsData.ORDER_MINIMUM_LABEL;
    static String orderShortLabel = BuyoutsData.ORDER_SHORT;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    String itemUOM1 = BuyoutsData.ITEM_UOM1;
    String itemUOM2 = BuyoutsData.ITEM_UOM2;
    String itemCount = "1";
    double currentOrdersValue;
    double Short;
    static String customerLoginCode = "186162079";
    static String orderId,orderId2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2816")
    public void VerifySoftOrderMinimumNotMetAfterBuyoutItemRemoval() throws InterruptedException{
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToNode(canonicalNodeName);
        Login.setValueToNode(featureKey,featureValue);


        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(DistributorName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnAllowSupplierToSetMinimumToggle(true);
        InternalTools.TurnOnOrderMinimumGloballyToggle(true);
        InternalTools.clickOnOrderMinimumDropdown(HardOrderMinimumType);
        InternalTools.enterOrderMinimumAmount(orderMinInternal);
        InternalTools.clickSave();
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.orderMinimumCheckBox(false);
        Settings.clickOnSaveChanges();

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
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(buyoutItemItemCode),"buyout Item not displayed in catalog");
        Catalog.ClickOnMultiUomDropDown(buyoutItemItemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);

        Customer.clickOnBackButton();
        Customer.searchItemOnCatalog(nonBuyoutItemItemCode);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(nonBuyoutItemItemCode),"non buyout Item not displayed in catalog");
        Customer.clickOnPlusIconInCatalog(1,nonBuyoutItemItemName);
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.isAddedItemDisplayed(buyoutItemItemName), "The buyout item not display");
        softAssert.assertTrue(Customer.isAddedItemDisplayed(nonBuyoutItemItemName), "The non buyout item not display");
        Customer.submitOrderMinimum();
        softAssert.assertTrue(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertTrue(Customer.isBuyoutHardOrderMinCorrectlyDisplayed(orderMinimumLabel,orderMinIn$));
        currentOrdersValue = Customer.getCurrentOrdersValue();

        Short = Double.parseDouble(orderMinIn$.replace(",", "")) - currentOrdersValue;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedShort = currencyFormat.format(Short);

        softAssert.assertTrue(Customer.isBuyoutHardOrderMinCorrectlyDisplayed(orderShortLabel,String.valueOf(formattedShort)));
        softAssert.assertTrue(Customer.isRemovedBuyoutItemAndUOMsCorrectlyDisplayedInHarOrderMinOverlay(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM1),"Buyout item removed is not correctly displayed in order Min Overlay");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemAndUOMsCorrectlyDisplayedInHarOrderMinOverlay(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM2),"Buyout item removed is not correctly displayed in order Min Overlay");
        softAssert.assertFalse(Customer.isAddedItemDisplayed(buyoutItemItemName), "The buyout item not display");
        Customer.clickPlaceOrderSoftOrderMinimum();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);
        softAssert.assertTrue(Customer.isItemsDisplayedInsideOrderCorrectly(nonBuyoutItemItemName),"non buyout item is not displayed");

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
        Customer.submitOrderMinimum();
        softAssert.assertTrue(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertTrue(Customer.isBuyoutHardOrderMinCorrectlyDisplayed(orderMinimumLabel,orderMinIn$));
        currentOrdersValue = Customer.getCurrentOrdersValue();

        Short = Double.parseDouble(orderMinIn$.replace(",", "")) - currentOrdersValue;
        String formattedShortOpSide = currencyFormat.format(Short);

        softAssert.assertTrue(Customer.isBuyoutHardOrderMinCorrectlyDisplayed(orderShortLabel,String.valueOf(formattedShortOpSide)));
        softAssert.assertTrue(Customer.isRemovedBuyoutItemAndUOMsCorrectlyDisplayedInHarOrderMinOverlay(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM1),"Buyout item removed is not correctly displayed in order Min Overlay");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemAndUOMsCorrectlyDisplayedInHarOrderMinOverlay(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM2),"Buyout item removed is not correctly displayed in order Min Overlay");
        softAssert.assertFalse(Customer.isAddedItemDisplayed(buyoutItemItemName), "The buyout item not display");
        Customer.clickPlaceOrderSoftOrderMinimum();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId2 = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToHistory();
        Assert.assertTrue(History.isUserNavigatedToHistory(),"There has been an error navigating to history section");
        History.ensureOrderDateSortedDescending();
        Catalog.clickSubmittedOrder(orderId);
        softAssert.assertTrue(Customer.isItemsDisplayedInsideOrderCorrectly(nonBuyoutItemItemName),"non buyout item is not displayed");


        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }

}
