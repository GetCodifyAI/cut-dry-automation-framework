package com.cutanddry.qa.tests.catalog_view;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyCreatingUnitOfMeasureOfItemTest {
    static User user;
    String DistributerName ="456592422 - QA ONLY : test distributor - QA ONLY : test distributor";
    String UOM = "Bag";
    String itemCode = "00475";
    String itemPrice = "20.00";
    String saleValue = "30.00";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-372")
    public void VerifyCreatingUnitOfMeasureOfItem() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.navigateToPricingAndPromotions();
        int UOMCount = Catalog.getUnitOfMeasureCount();
        Catalog.addUnitOfMeasure(UOM);
        Catalog.selectUnitFromDropdown(UOM);
        Catalog.setItemUnitPrice(itemPrice);
        Catalog.selectPercentageAsSalesTypeFrmDropdown();
        Catalog.setSaleValue(saleValue);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in creating UOM");
        softAssert.assertEquals(Catalog.getUnitOfMeasureCount(),UOMCount+1,"Error in increasing UOM");
        softAssert.assertTrue(Catalog.isAddedUOMDisplayed(),"Error in deleting the UOM");

        Catalog.deleteUOMFromCatalog();
        softAssert.assertTrue(Catalog.deleteUOMOverlayDisplayed(),"UOM delete overlay displaying ERROR");
        Catalog.DeleteConfirm();
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving the changes after UOM Delete");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }



}
