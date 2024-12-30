package com.cutanddry.qa.tests.catalog_view;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDeletingUnitOfMeasureOfItemTest extends TestBase {
    static User user;
    String DistributerName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String itemCode = "00475";
    String UOM = "Bag";
    String itemPrice = "20.00";
    String saleValue = "30.00";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-373")
    public void VerifyDeletingUnitOfMeasureOfItem() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.navigateToPricingAndPromotions();

        // Pre-request
        int UOMCount = Catalog.getUnitOfMeasureCount();
        Catalog.addUnitOfMeasure();
        Catalog.selectUnitFromDropdown(UOM);
        Catalog.setItemUnitPrice(itemPrice);
        Catalog.selectPercentageAsSalesTypeFrmDropdown();
        Catalog.setSaleValue(saleValue);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in creating UOM");

        // Testing flow
        Catalog.deleteUOMFromCatalog();
        softAssert.assertTrue(Catalog.deleteUOMOverlayDisplayed(),"UOM delete overlay displaying ERROR");
        Catalog.DeleteConfirm();
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving the changes after UOM Delete");
        softAssert.assertFalse(Catalog.isDeletedUOMDisplayed(),"Error in deleting the UOM");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
