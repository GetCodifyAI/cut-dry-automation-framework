package com.cutanddry.qa.tests.catalog_view;

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

public class VerifySuggestSubstituteForDiscontinuedProductWhenForceShowDisabledTest extends TestBase{
    static User user;
    static String distributorDiCarlo = "72124668 - Kevin Wu - DiCarlo";
    static String CompanyName = "DiCarlo";
    static String searchItemCode1 = "102";
    static String searchItemCodeName1 = "Hibiscus Flowers \"Jamaica\"";
    static String substituteItemName1 = "Alfalfa Sprouts Cups";
    static String substituteItemCode1 = "110";
    static String canonicalNodeName1 = "70504455";
    static String canonicalNodeName2 = "70505428";
    static String featureKey = "unavailable";
    static String featureKey2 = "discontinued";
    static String featureValue = "true";
    static String customerId = "962243";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1587")
    public void VerifySuggestSubstituteForDiscontinuedProductWhenForceShowDisabled() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnForceShowSubstitutesEnabledToggle(false);
        InternalTools.TurnOnAllowOrderingDiscontinuedToggle(true);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(distributorDiCarlo);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        // Add substitute 1
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(searchItemCode1);
        Catalog.selectItemFromGrid(searchItemCode1);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode1,"Error in getting Item Code");
        Catalog.navigateToSubstituteTab();
        Catalog.removeExistingItem(substituteItemName1);
        Catalog.navigateToSubstituteTab();
        Catalog.addSubstitutions();
        Catalog.addSubstitutions();
        Catalog.searchAndAddSubstituteItem(substituteItemCode1);
        Catalog.showSubstituteBtnIfNotSelected();
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving the changes after adding  substitute");
       
        // set canonical node item 1
        Login.navigateToNode(canonicalNodeName1);
        Login.setValueToNode(featureKey,featureValue);

        Login.navigateToDistributorPortal(distributorDiCarlo);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        // Add substitute 2
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(substituteItemCode1);
        Catalog.selectItemFromGrid(substituteItemCode1);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),substituteItemCode1,"Error in getting Item Code");
        Catalog.navigateToSubstituteTab();
        Catalog.removeExistingItem(searchItemCodeName1);
        Catalog.navigateToSubstituteTab();
        Catalog.addSubstitutions();
        Catalog.addSubstitutions();
        Catalog.searchAndAddSubstituteItem(searchItemCode1);
        Catalog.showSubstituteBtnIfNotSelected();
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving the changes after adding  substitute");

        // set canonical node item 2
        Login.navigateToNode(canonicalNodeName2);
        Login.setValueToNode(featureKey2,featureValue);

        Login.navigateToDistributorPortal(distributorDiCarlo);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(substituteItemCode1);
        Customer.clickOnPlusIconInCatalog(1, substituteItemName1);
        Customer.checkoutSubstituteItems();
        softAssert.assertTrue(Customer.isSubstitutesPopupDisplayed(),"substitutes popup error");
        softAssert.assertTrue(Customer.isAddedSubstitutesItemDisplayed(searchItemCodeName1),"substitutes item display error");
        Customer.clickSaveSelection();

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnAllowOrderingDiscontinuedToggle(false);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(distributorDiCarlo);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(substituteItemCode1);


        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
