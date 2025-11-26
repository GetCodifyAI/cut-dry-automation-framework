package com.cutanddry.qa.tests.catalog_view;

import com.cutanddry.qa.base.TestBase;
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

public class VerifyImageOfItemTest extends TestBase {
    static User user;
    String DistributerName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String itemCode = "00470";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-371")
    public void VerifyImageOfItem() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(itemCode);
        Thread.sleep(4000);
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.navigateToImages();
        softAssert.assertTrue(Catalog.ProductImageDisplayed(),"Product image is not displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }



}
