package com.cutanddry.qa.tests.catalog_view;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheCatalogItemDataTest extends TestBase {
    static User user;
    String DistributerName ="46505655 - Kevin - Independent Foods Co";
    String itemCode = "00563"; // 00475
    String Active = "Active";
    String InActive = "Inactive";
    String All = "All";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-369")
    public void VerifyTheCatalogItemData() {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.selectItemStatus(All);
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.selectProductActiveInactiveStatus(Active);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving item data in catalog");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        Catalog.selectProductActiveInactiveStatus(InActive);
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
