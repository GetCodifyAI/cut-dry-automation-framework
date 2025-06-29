package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.OfflineOrderingData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheOutOfStockLabelsDisplayWhenHideOutOfStockLabelOnSupplierPortalToggleDisabledTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName ="145885743 - Cut+Dry Agent - Lombardi";
    static String CompanyName = "Lombardi";
    static String customerId = "   1000782   S";
    static String itemCode = "2301101";
    static String itemName = "Lobster Tl 7-8 OZ Maine";
    static String tag = "Out of stock";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1492")
    public void VerifyTheOutOfStockLabelsDisplayWhenHideOutOfStockLabelOnSupplierPortalToggleDisabled() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.clickHideOutOfStockToggle(false);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemCode);
        softAssert.assertTrue(Customer.isCatalogFilterDisplayTag(itemName,tag),"new tag display list error");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
