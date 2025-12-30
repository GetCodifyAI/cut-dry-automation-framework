package com.cutanddry.qa.tests.customers.buyoutItems;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.BuyoutsData;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.NumberFormat;
import java.util.Locale;

public class VerifyHardOrderMinimumNotMetAfterBuyoutItemRemovalTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = "BiRite Foodservice Distributors";
    static String canonicalNodeName = "125770712";
    static String featureKey = BuyoutsData.BUYOUT_PRODUCT_KEY;
    static String featureValue = BuyoutsData.BUYOUT_PRODUCT_VALUE;
    static String HardOrderMinimumType = "Hard Order Minimum";
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


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2711")
    public void VerifyBuyoutItemsRemovedFromNonBuyoutCustomerOrderSubmission() throws InterruptedException{
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
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(buyoutItemItemCode),"Prop Item not displayed in catalog");
        Catalog.ClickOnMultiUomDropDown(buyoutItemItemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);

        Customer.clickOnBackButton();
        Customer.searchItemOnCatalog(nonBuyoutItemItemCode);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(nonBuyoutItemItemCode),"Prop Item not displayed in catalog");
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

        //softAssert.assertTrue(Customer.isRemovedBuyoutItemCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode),"Buyout item removed is not correctly displayed");
        //softAssert.assertTrue(Customer.isRemovedBuyoutItemCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode),"Buyout item removed is not correctly displayed");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemUOMCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM1),"Buyout item removed is not correctly displayed");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemUOMCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM2),"Buyout item removed is not correctly displayed");

        softAssert.assertAll();

    }
}
