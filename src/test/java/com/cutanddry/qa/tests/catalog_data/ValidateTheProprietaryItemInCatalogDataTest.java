package com.cutanddry.qa.tests.catalog_data;

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

public class ValidateTheProprietaryItemInCatalogDataTest extends TestBase {
    static User user;
    String itemCode = "00563"; // 00475
    String ProprietaryYes = "Proprietary";
    String ProprietaryNo = "Non-Proprietary";
    String All = "All";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-742")
    public void ValidateTheProprietaryItemInCatalogData() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.selectItemStatus(All);
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.selectEditFromProductConfig();
        Catalog.clickOnProprietaryItemStatus(ProprietaryYes);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving item data in catalog");
        Dashboard.navigateToCatalog();
        Catalog.clickOnMoreFilters();
        softAssert.assertTrue(Catalog.isFilterCatalogPopupDisplayed(),"popup error");
        Catalog.selectProprietaryItem();
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.selectEditFromProductConfig();
        Catalog.clickOnProprietaryItemStatus(ProprietaryNo);
        Catalog.saveChanges();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
