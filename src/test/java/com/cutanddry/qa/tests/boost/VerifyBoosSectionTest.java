package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Boost;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyBoosSectionTest extends TestBase {

    static User user;
    static final String CUSTOMER_ID = "16579";
    static final String BROADCAST_MESSAGE = "Test Broadcast Message";
    static final String ITEM_CODE = "00475";
    static final String ITEM_CODE_1 = "00529";
    static final String ITEM_CODE_2 = "17859";
    static final String SALES_REP = "Steve O";
    SoftAssert softAssert;
    String featuredListName = "TestList" + generateDynamicValue();
    String editedFeaturedListName = "EditedList" + generateDynamicValue();
    String customerName;

    @BeforeClass
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error");
    }


    @Test(priority = 1, groups = "DOT-TC-35")
    public void verifyBroadcastMessage() throws InterruptedException {
        navigateToBoost();
        Boost.clearExistingBoostMessageIfExists();
        Boost.addMessage();
        softAssert.assertTrue(Boost.isStepOneDisplayed(), "Add Message display error");

        Boost.selectCustomList();
        softAssert.assertTrue(Boost.isSelectCustomersDisplayed(), "Custom List select error");
        customerName = Boost.selectFirstCustomer();
        softAssert.assertTrue(Boost.isSelectionCountDisplayed(), "Customer selection error");

        Boost.clickContinue();
        softAssert.assertTrue(Boost.isStepTwoDisplayed(), "Continuation error");
        Boost.customizeMessage(BROADCAST_MESSAGE);
        Boost.clickSubmit();
        softAssert.assertTrue(Boost.isBroadcastSuccessDisplayed(), "Broadcast error");
        Boost.clickOk();

        verifyCustomerBroadcastMessage();
        deactivateBoostMessage();
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = "DOT-TC-36")
    public void verifyTopCategoryPicks() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");

        Boost.clickTopCategoryPicksConfig();
        softAssert.assertTrue(Boost.isTopCategoryPopupDisplayed(), "Top category popup error");
        Boost.ensureTopCategoryPicksDisplayStatus(true);
        Boost.clickAllItemsConfig();
        Boost.clickAddItems();
        Boost.addItem(ITEM_CODE);
        softAssert.assertTrue(Boost.isItemAdded(ITEM_CODE), "Item adding error");
        Boost.clickClose();

        verifyCustomerTopCategoryPicks();
        removeTopCategoryItem();
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = "DOT-TC-37")
    public void verifyCompareSimilarItems() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        boolean inactiveState = Boost.checkInactiveState("Compare Similar Items");
        Boost.clickCompareSimilarItemsConfig();
        softAssert.assertTrue(Boost.isCompareSimilarPopupDisplayed(), "Compare similar items popup error");
        Boost.toggleOnCarouselDisplayStatus(inactiveState);
        Boost.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CUSTOMER_ID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CUSTOMER_ID), "Search error");
        Customer.clickOnOrderGuide(CUSTOMER_ID);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(ITEM_CODE_1);
        Customer.selectSearchedCatalogItemStable(ITEM_CODE_1);
        softAssert.assertTrue(Customer.isSelectedItemDisplayed(), "Navigation error");
        softAssert.assertTrue(Customer.isCompareSimilarItemsDisplayed(), "Similar items missing error");

        removeCompareSimilarItems();
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = "DOT-TC-38")
    public void verifyRecommendedForCustomer() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        boolean inactiveState = Boost.checkInactiveState("Recommended for Customer");
        Boost.clickRecommendForCustomerConfig();
        softAssert.assertTrue(Boost.isRecommendForCustomerPopupDisplayed(), "Recommend for customer popup error");
        Boost.toggleOnCarouselDisplayStatus(inactiveState);
        Boost.clickAddItems();
        Boost.addItem(ITEM_CODE);
        softAssert.assertTrue(Boost.isItemAdded(ITEM_CODE), "Item adding error");
        Boost.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CUSTOMER_ID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CUSTOMER_ID), "Search error");
        Customer.clickOnOrderGuide(CUSTOMER_ID);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isRecommendedForYouItemDisplayed(ITEM_CODE), "Recommended for you item missing error");

        removeRecommendedForCustomer();
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = "DOT-TC-39")
    public void verifyRecommendedBySalesRep() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickRecommendBySalesRepConfig();
        softAssert.assertTrue(Boost.isRecommendBySalesRepPopupDisplayed(), "Recommend by sales rep popup error");
        Boost.clickSalesRepConfig(SALES_REP);
        softAssert.assertTrue(Boost.isSalesRepConfigPopupDisplayed(), "Sales rep config popup error");
        Boost.clickAddItems();
        Boost.addItem(ITEM_CODE);
        softAssert.assertTrue(Boost.isItemAdded(ITEM_CODE), "Item adding error");
        Boost.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CUSTOMER_ID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CUSTOMER_ID), "Search error");
        Customer.clickOnOrderGuide(CUSTOMER_ID);
        String itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "Item mismatch");
        softAssert.assertTrue(Customer.isRecommendedBySalesRepDisplayed(ITEM_CODE), "Recommended by sales rep item missing error");

        removeRecommendedBySalesRep();
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = "DOT-TC-40")
    public void verifyDontForgetToOrder() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickDontForgetToOrderConfig();
        softAssert.assertTrue(Boost.isDontForgetPopupDisplayed(), "Don't forget popup error");
        Boost.ensureDontForgetToOrderDisplayStatus(true);
        Boost.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CUSTOMER_ID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CUSTOMER_ID), "Search error");
        Customer.clickOnOrderGuide(CUSTOMER_ID);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isDontForgetToOrderDisplayed(), "Don't forget to order missing error");

        removeDontForgetToOrder();
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = "DOT-TC-41")
    public void verifyMoreFromThisBrand() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        boolean inactiveState = Boost.checkInactiveState("More from this Brand");
        Boost.clickMoreFromThisConfig();
        softAssert.assertTrue(Boost.isMoreFromThisPopupDisplayed(), "More from this brand popup error");
        Boost.toggleOnCarouselDisplayStatus(inactiveState);
        Boost.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CUSTOMER_ID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CUSTOMER_ID), "Search error");
        Customer.clickOnOrderGuide(CUSTOMER_ID);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(ITEM_CODE_2);
        Customer.selectSearchedCatalogItem(ITEM_CODE_2);
        softAssert.assertTrue(Customer.isSelectedItemDisplayed(), "Navigation error");
        softAssert.assertTrue(Customer.isMoreFromThisBrandDisplayed(), "More from this brand missing error");

        removeMoreFromThisBrand();
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = "DOT-TC-376")
    public void VerifyAddingFeaturedList() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        Boost.navigateToFeaturedListTab();

        Boost.createNewFeaturedList();
        softAssert.assertTrue(Boost.isCrateListOverlayDisplayed(), "Error in displaying create list overlay");
        Boost.enterFeaturedListName(featuredListName);
        Boost.submitEnteredListName();
        softAssert.assertTrue(Boost.addedListNameDisplayed(featuredListName), "Error in displaying added list name");

        softAssert.assertAll();
    }

    @Test(priority = 9, groups = "DOT-TC-377", dependsOnMethods = "VerifyAddingFeaturedList")
    public void VerifyEditingFeaturedList() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        Boost.navigateToFeaturedListTab();

        Boost.viewAndConfigure(featuredListName);
        softAssert.assertTrue(Boost.itemConfigureOverlayDisplayed(), "Error in displaying Item Configure Overlay");
        Boost.editListName();
        softAssert.assertTrue(Boost.editListNameDisplayed(), "Error in displaying Item Edit List Name Overlay");
        Boost.enterFeaturedListName(editedFeaturedListName);
        Boost.saveChanges();
        Boost.closeEditOverlay();
        softAssert.assertTrue(Boost.addedListNameDisplayed(editedFeaturedListName), "Error in displaying added list name");

        softAssert.assertAll();
    }

    @Test(priority = 10, groups = "DOT-TC-378", dependsOnMethods = "VerifyEditingFeaturedList")
    public void VerifyRemovingFeaturedList() throws InterruptedException {
        navigateToBoost();
        Boost.clickSuggestiveSales();
        Boost.navigateToFeaturedListTab();

        Boost.deleteFeaturedList(editedFeaturedListName);
        softAssert.assertTrue(Boost.deleteFeaturedListOverlayDisplayed(), "Error in displaying delete List Overlay");
        Boost.deleteFeaturedListFromOverlay();
        softAssert.assertFalse(Boost.deletedListDisplayed(editedFeaturedListName), "Error in displaying added list name");

        softAssert.assertAll();
    }

    private void navigateToBoost() {
        Dashboard.refreshPage();
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
    }

    private void verifyCustomerBroadcastMessage() throws InterruptedException {
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerName);
        softAssert.assertTrue(Customer.isCustomerSearchResultByNameDisplayed(customerName), "Search error");
        Customer.clickOnNameOrderGuide(customerName);
        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(BROADCAST_MESSAGE), "Broadcast error");
        Customer.clickMessage(BROADCAST_MESSAGE);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(), "URL redirect error");
        Customer.clickOnBack();
        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(BROADCAST_MESSAGE), "Back error");
    }

    private void deactivateBoostMessage() {
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
        Boost.clickXButton();
        Boost.clickYes();
        softAssert.assertFalse(Boost.isDeactivated(), "Deactivate error");
    }

    private void verifyCustomerTopCategoryPicks() throws InterruptedException {
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CUSTOMER_ID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CUSTOMER_ID), "Search error");
        Customer.clickOnOrderGuide(CUSTOMER_ID);
        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isTopCategoryPicksDisplayed(), "Top picks missing error");
        softAssert.assertTrue(Customer.isItemInTopCategoryPicks(ITEM_CODE), "Item missing error");
    }

    private void removeTopCategoryItem() {
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickTopCategoryPicksConfig();
        softAssert.assertTrue(Boost.isTopCategoryPopupDisplayed(), "Top category popup error");
        Boost.clickAllItemsConfig();
        Boost.removeItem(ITEM_CODE);
        softAssert.assertFalse(Boost.isItemInCarouselPreview(ITEM_CODE), "Item remove error");
    }

    private void removeCompareSimilarItems() {
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickCompareSimilarItemsConfig();
        softAssert.assertTrue(Boost.isCompareSimilarPopupDisplayed(), "Compare similar items popup error");
        Boost.toggleOffCarouselDisplayStatus();
    }

    private void removeRecommendedForCustomer() {
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickRecommendForCustomerConfig();
        softAssert.assertTrue(Boost.isRecommendForCustomerPopupDisplayed(), "Recommend for customer popup error");
        Boost.removeItem(ITEM_CODE);
        softAssert.assertFalse(Boost.isItemInCarouselPreview(ITEM_CODE), "Item remove error");
    }

    private void removeRecommendedBySalesRep() {
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickRecommendBySalesRepConfig();
        softAssert.assertTrue(Boost.isRecommendBySalesRepPopupDisplayed(), "Recommend by sales rep popup error");
        Boost.clickSalesRepConfig(SALES_REP);
        softAssert.assertTrue(Boost.isSalesRepConfigPopupDisplayed(), "Sales rep config popup error");
        Boost.removeItem(ITEM_CODE);
        softAssert.assertFalse(Boost.isItemInCarouselPreview(ITEM_CODE), "Item remove error");
    }

    private void removeDontForgetToOrder() {
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickDontForgetToOrderConfig();
        softAssert.assertTrue(Boost.isDontForgetPopupDisplayed(), "Don't forget popup error");
        Boost.toggleOffCarouselDisplayStatus();
    }

    private void removeMoreFromThisBrand() {
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to Boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(), "Navigate to suggestive sales error");
        Boost.clickMoreFromThisConfig();
        softAssert.assertTrue(Boost.isMoreFromThisPopupDisplayed(), "More from this brand popup error");
        Boost.toggleOffCarouselDisplayStatus();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
    }

    @AfterClass
    public void cleanUp() {
        closeAllBrowsers();
    }
}
