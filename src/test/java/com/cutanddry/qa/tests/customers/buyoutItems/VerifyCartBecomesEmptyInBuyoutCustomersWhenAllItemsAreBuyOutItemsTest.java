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

public class VerifyCartBecomesEmptyInBuyoutCustomersWhenAllItemsAreBuyOutItemsTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String canonicalProduct1_Node = BuyoutsData.BUYOUT_PRODUCT1_CANONICAL_NODE;
    static String canonicalProduct2_Node = BuyoutsData.BUYOUT_PRODUCT2_CANONICAL_NODE;
    static String featureKey = BuyoutsData.BUYOUT_PRODUCT_KEY;
    static String featureValue = BuyoutsData.BUYOUT_PRODUCT_VALUE;
    static String DistributorName = "BiRite Foodservice Distributors";
    static String customerId = BuyoutsData.BUYOUT_NOT_ALLOWED_CUSTOMER;
    static String buyoutItemItemCode = BuyoutsData.BUYOUT_PRODUCT_ITEMCODE;
    static String buyoutItemItemCode2 = BuyoutsData.BUYOUT_PRODUCT_ITEMCODE2;
    static String buyoutItemItemName = BuyoutsData.BUYOUT_PRODUCT_ITEMNAME;
    static String buyoutItemItemName2 = BuyoutsData.BUYOUT_PRODUCT_ITEMNAME2;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderMinimumSetting = "Exempt from Order Minimum";
    static String customerLoginCode = "186162079";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2817")
    public void VerifyCartBecomesEmptyInBuyoutCustomersWhenAllItemsAreBuyOutItems() throws InterruptedException{
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToNode(canonicalProduct1_Node);
        Login.setValueToNode(featureKey,featureValue);

        Login.navigateToNode(canonicalProduct2_Node);
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
        Customer.searchItemOnCatalog(buyoutItemItemCode2);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(buyoutItemItemCode2),"Prop Item not displayed in catalog");
        Customer.clickOnPlusIconInCatalog(1,buyoutItemItemName2);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isAddedItemDisplayed(buyoutItemItemName), "The buyout item not display");
        softAssert.assertTrue(Customer.isAddedItemDisplayed(buyoutItemItemName2), "The non buyout item not display");
        Customer.submitOrderRebate();
        softAssert.assertTrue(Customer.isCanNotSubmitEmptyOrdersErrorDisplayed(),"Empty order submission error message is not displayed");
        Customer.clickOK();
        softAssert.assertFalse(Customer.isSubmitOrderBtnEnabled(),"Submit btn not disabled");

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
        Customer.searchItemOnCatalog(buyoutItemItemCode2);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(buyoutItemItemCode2),"non buyout Item not displayed op side catalog");
        Customer.clickOnPlusIconInCatalog(1,buyoutItemItemName2);
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.isAddedItemDisplayed(buyoutItemItemName), "The buyout item not display");
        softAssert.assertTrue(Customer.isAddedItemDisplayed(buyoutItemItemName2), "The non buyout item not display");
        Customer.submitOrderMinimum();
        softAssert.assertTrue(Customer.isCanNotSubmitEmptyOrdersErrorDisplayed(),"Empty order submission error message is not displayed");
        Customer.clickOK();
        softAssert.assertFalse(Customer.isSubmitOrderBtnEnabled(),"Submit btn not disabled");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
