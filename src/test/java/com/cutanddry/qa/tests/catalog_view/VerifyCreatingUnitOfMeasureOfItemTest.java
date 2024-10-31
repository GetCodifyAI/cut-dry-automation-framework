package com.cutanddry.qa.tests.catalog_view;

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

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyCreatingUnitOfMeasureOfItemTest {
    static User user;
    String DistributerName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String itemCode = "00475";
    String itemPrice = "20.00";
    String saleValue = "30.00";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-372")
    public void VerifyCreatingUnitOfMeasureOfItem() {
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
        int UOMCount = Catalog.getUnitOfMeasureCount();
        Catalog.addUnitOfMeasure();
        softAssert.assertEquals(Catalog.getUnitOfMeasureCount(),UOMCount+1,"Error in increasing UOM");
        Catalog.selectUnitFromDropdown();
        Catalog.setItemUnitPrice(itemPrice);
        Catalog.selectPercentageAsSalesTypeFrmDropdown();
        Catalog.setSaleValue(saleValue);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in creating UOM");
        softAssert.assertTrue(Catalog.isAddedUOMDisplayed(),"Error in deleting the UOM");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }



}