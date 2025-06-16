package com.cutanddry.qa.tests.purchase_history;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.data.testdata.PurchaseHistoryData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyPurchaseHistoryWithPriceNotDisplayInOrderGuideAndPDPWhenDisplayPurchasePriceToggleDisableTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName =PurchaseHistoryData.DISTRIBUTOR_NAME_IFC;
    static String CompanyName = PurchaseHistoryData.SUPPLIER_IFC;
    static String customerId =PurchaseHistoryData.CUSTOMER_ID_IFC;
    static String searchItemCode = PurchaseHistoryData.ITEM_CODE_IFC;
    static String itemName = PurchaseHistoryData.ITEM_NAME_IFC;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    static String priceColumn = "Price";



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1346")
    public void VerifyPurchaseHistoryWithPriceNotDisplayInOrderGuideAndPDPWhenDisplayPurchasePriceToggleDisable() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.displayMarginOnPortalToggle(true);
        InternalTools.restrictMarginOnPortalToggle(false);
        InternalTools.displayPurchasePriceToggle(false);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(searchItemCode);
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");

        Customer.clickLastOrderOG(searchItemCode);
        softAssert.assertTrue(Customer.isPurchaseHistoryDisplay(),"Purchase history window display error");
        softAssert.assertFalse(Catalog.isPriceColumnDisplay(priceColumn),"price column not display");
        Customer.clickClose();

        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        Catalog.ClickOnMultiUomDropDown(itemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Catalog.clickPurchaseHistory();
        softAssert.assertTrue(Customer.isPurchaseHistoryDisplay(),"Purchase history window display error");
        softAssert.assertFalse(Catalog.isPriceColumnDisplay(priceColumn),"price column not display");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.displayPurchasePriceToggle(true);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
