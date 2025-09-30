package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheAddingSubstituteItemForMultipleUOMTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID;
    String searchItemCode = CatalogData.ITEM_CODE_13;
    String itemName = CatalogData.ITEM_NAME_SUB;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    static double itemPriceUOM1 ,itemPriceUOM2,totalPDPItemPrice ,totalItemPriceReviewOrder;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    String substituteItemCode = "20716"; // 01922 or 00563


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1073")
    public void VerifyTheAddingSubstituteItemForMultipleUOM() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
//        Customer.ensureCarouselDisplayStatus(false);

        // Add substitute
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(searchItemCode);
        Catalog.selectItemFromGrid(searchItemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode,"Error in getting Item Code");
        Catalog.navigateToSubstituteTab();
        Catalog.removeExistingItem(substituteItemCode);
        Catalog.navigateToSubstituteTab();
        Catalog.addSubstitutions();
        String SubstituteItemName = Catalog.getSubstituteItemName(substituteItemCode);
        Catalog.addSubstitutions();
        Catalog.searchAndAddSubstituteItem(substituteItemCode);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving the changes after adding  substitute");
        Catalog.navigateToSubstituteTab();
        softAssert.assertTrue(Catalog.isAddedSubstituteItemDisplayedInPage(SubstituteItemName),"Error in adding substitute items");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Catalog.ClickOnCatalogMultiUomDropDownStable(itemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = Catalog.getPDPPriceUOM(uom1);
        itemPriceUOM2 = Catalog.getPDPPriceUOM(uom2);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        totalPDPItemPrice = Customer.getItemPriceOnCheckoutButtonViaPDP();
        softAssert.assertEquals(Math.round(totalPDPItemPrice * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)), "The item has not been selected.");
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Customer.isSubstitutionTextDisplayed(),"Substitution add error");

        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(searchItemCode);
        Catalog.selectItemFromGrid(searchItemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode,"Error in getting Item Code");
        Catalog.navigateToSubstituteTab();
        Catalog.deleteSubstitute();
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in Removing substitute item");
        Catalog.navigateToSubstituteTab();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
