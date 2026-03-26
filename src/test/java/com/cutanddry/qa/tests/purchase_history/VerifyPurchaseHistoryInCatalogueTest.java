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

public class VerifyPurchaseHistoryInCatalogueTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName =PurchaseHistoryData.DISTRIBUTOR_NAME_IFC;
    static String CompanyName = PurchaseHistoryData.SUPPLIER_IFC;
    static String customerId =PurchaseHistoryData.CUSTOMER_ID_IFC;
    static String searchItemCode = PurchaseHistoryData.ITEM_CODE_IFC;
    static String itemName = PurchaseHistoryData.ITEM_NAME_IFC;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId;
    static String multiUOMType1 = "1 EA";
    static String multiUOMType2 = "1 Pkg";



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1024")
    public void VerifyPurchaseHistoryInCatalogue() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.displayPurchaseHistoryToggle(true);
        InternalTools.displayMarginOnPortalToggle(true);
        InternalTools.restrictMarginOnPortalToggle(false);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        Catalog.ClickOnMultiUomDropDown(itemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickLastOrderDetailsCatalog(itemName);
        softAssert.assertTrue(Customer.isPurchaseHistoryDisplay(),"Purchase history window display error");
        softAssert.assertTrue(Customer.isLastOrderDisplay(multiUOMType1),"last order multi uom quantity error");
        softAssert.assertTrue(Customer.isLastOrderDisplay(multiUOMType2),"last order multi uom quantity error");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
