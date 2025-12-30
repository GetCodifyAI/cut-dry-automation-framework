package com.cutanddry.qa.tests.customers.buyoutItems;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.BuyoutsData;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyBuyoutItemsRemovedFromNonBuyoutCustomerOrderSubmissionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = "120984267 - Cut+Dry Agent - BiRite Foodservice Distributors";
    static String customerId = BuyoutsData.BUYOUT_NOT_ALLOWED_CUSTOMER;
    static String canonicalNodeName = "125770706";
    static String featureKey = BuyoutsData.BUYOUT_PRODUCT_KEY;
    static String featureValue = BuyoutsData.BUYOUT_PRODUCT_VALUE;
    static String buyoutItemItemCode = BuyoutsData.BUYOUT_PRODUCT_ITEMCODE;
    static String buyoutItemItemName = BuyoutsData.BUYOUT_PRODUCT_ITEMNAME;
    static String nonBuyoutItemItemCode = BuyoutsData.NON_BUYOUT_PRODUCT_ITEMCODE;
    static String nonBuyoutItemItemName = BuyoutsData.NON_BUYOUT_PRODUCT_ITEMNAME;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    String itemUOM1 = BuyoutsData.ITEM_UOM1;
    String itemUOM2 = BuyoutsData.ITEM_UOM2;
    String itemCount = "1";

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

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

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
        softAssert.assertTrue(Customer.isRemoveUnavailableItemsMsgHeaderDisplayed(), "unavailable Item removed msg header not displayed");
        softAssert.assertTrue(Customer.isRemoveUnavailableItemsMsgDisplayed(), "unavailable Item removed msg not displayed");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode),"Buyout item removed is not correctly displayed");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode),"Buyout item removed is not correctly displayed");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemUOMCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM1),"Buyout item removed is not correctly displayed");
        softAssert.assertTrue(Customer.isRemovedBuyoutItemUOMCorrectlyDisplayed(buyoutItemItemName,buyoutItemItemCode,itemCount,itemUOM2),"Buyout item removed is not correctly displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }

}
