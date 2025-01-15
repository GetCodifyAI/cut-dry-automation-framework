package com.cutanddry.qa.tests.catalog;

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

public class VerifyPreviewCatalogEditItemPricingPromotionsTest extends TestBase {
    static User user;
    String UOM = "Bag";
    String itemPrice = "20.00";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-622")
    public void VerifyPreviewCatalogEditItemPricingPromotions() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.clickOnPreviewCatalog();
        softAssert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        Catalog.selectFirstEditItem();
        Catalog.navigateToPricingAndPromotions();
        Catalog.addUnitOfMeasure();
        Catalog.selectUnitFromDropdown(UOM);
        Catalog.setItemUnitPrice(itemPrice);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in creating UOM");
        Catalog.deleteUOMFromCatalog();
        softAssert.assertTrue(Catalog.deleteUOMOverlayDisplayed(),"UOM delete overlay displaying ERROR");
        Catalog.DeleteConfirm();
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in deleting UOM");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
