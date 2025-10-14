package com.cutanddry.qa.tests.purchase_history;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PurchaseHistoryData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyPurchaseHistoryInPDPSectionInDPTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName = PurchaseHistoryData.DISTRIBUTOR_NAME_IFC;
    static String CompanyName = PurchaseHistoryData.SUPPLIER_IFC;
    static String customerId =PurchaseHistoryData.CUSTOMER_ID_IFC;
    static String searchItemCode = PurchaseHistoryData.ITEM_CODE_IFC_2;
    static String itemName = PurchaseHistoryData.ITEM_NAME_IFC_2;
    static String margin = "N/A";
    static String marginColumn = "Margin";



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1339")
    public void VerifyPurchaseHistoryInPDPSectionInDP() throws InterruptedException {

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
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Catalog.clickPurchaseHistory();
        softAssert.assertTrue(Catalog.isMarginColumnDisplay(marginColumn),"Margin column not display");
        softAssert.assertTrue(Catalog.isLastOrderMarginDisplay(margin),"PDP last order margin not display");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
