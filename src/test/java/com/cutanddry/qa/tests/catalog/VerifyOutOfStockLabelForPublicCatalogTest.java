package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyOutOfStockLabelForPublicCatalogTest {
    static User user;
    SoftAssert softAssert;
    static String Prime_Source_Public_Catalog = CatalogData.PRIME_SOURCE_PUBLIC_CATALOG_URL;
    static String DistributorName = "Prime Source Foods";
    static String ItemCode = "1010";
    static String ItemName = "CHICKEN WHOLE WOG 3.5 LB BONE-IN SKIN-ON *SPECIAL ORDER";
    static String ItemBrand = "Allen Harim";
    static String tag = "Out of stock";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1605")
    public void VerifyHideAvailabilityFilterInPublicCatalog() throws InterruptedException {
        softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(DistributorName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.hideOutOfStockToggleOnPublicCatalog(true);
        InternalTools.clickSave();
        InternalTools.clickOKOnSucessOverlay();

        Catalog.navigateToPublicCatalog(Prime_Source_Public_Catalog);
        softAssert.assertTrue(Catalog.isNavigatedToPublicCatalog(DistributorName),"ERROR in navigating to public catalog");
        Customer.searchItemOnCatalog(ItemCode);
        softAssert.assertFalse(Customer.isCatalogFilterDisplayTag(ItemName,tag),"Out of stock tag displayed in public catalog");

        Customer.clickOnItemBrandOnCatalog(ItemName,ItemBrand);
        softAssert.assertFalse(Customer.isCatalogFilterDisplayTag(ItemName,tag),"Out of stock tag not displayed in public catalog");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(DistributorName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.hideOutOfStockToggleOnPublicCatalog(false);
        InternalTools.clickSave();
        InternalTools.clickOKOnSucessOverlay();

        Catalog.navigateToPublicCatalog(Prime_Source_Public_Catalog);
        softAssert.assertTrue(Catalog.isNavigatedToPublicCatalog(DistributorName),"ERROR in navigating to public catalog");
        Customer.searchItemOnCatalog(ItemCode);
        softAssert.assertTrue(Customer.isCatalogFilterDisplayTag(ItemName,tag),"Out of stock tag not displayed in public catalog");

        Customer.clickOnItemBrandOnCatalog(ItemName,ItemBrand);
        softAssert.assertTrue(Customer.isCatalogFilterDisplayTag(ItemName,tag),"Out of stock tag not displayed in public catalog");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
