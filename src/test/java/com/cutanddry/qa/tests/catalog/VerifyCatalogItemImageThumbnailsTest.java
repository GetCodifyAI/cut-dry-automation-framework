package com.cutanddry.qa.tests.catalog;

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

public class VerifyCatalogItemImageThumbnailsTest extends TestBase {
    static User user;
    static String itemCode = "00563";
    static String testImagePath = System.getProperty("user.dir") + "/src/test/resources/images/test_image.png";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1253")
    public void verifyCatalogItemImageThumbnails() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(), "navigation to catalog error");

        Catalog.selectItemFromGrid(itemCode);
        Catalog.navigateToImages();
        softAssert.assertTrue(Catalog.ProductImageDisplayed(), "product image is not displayed initially");

        Catalog.deleteMedia();
        Catalog.confirmDelete();
        Catalog.saveChanges();

        Dashboard.navigateToCatalog();
        Catalog.searchItemInCatalog(itemCode);
        softAssert.assertFalse(Catalog.isCatalogItemThumbnailDisplayed(), "image should be removed from catalog");

        Catalog.selectItemFromGrid(itemCode);
        Catalog.navigateToImages();
        softAssert.assertFalse(Catalog.ProductImageDisplayed(), "product image should not be displayed after deletion");

        Catalog.uploadImage(testImagePath);
        Catalog.saveChanges();

        Dashboard.navigateToCatalog();
        Catalog.searchItemInCatalog(itemCode);
        softAssert.assertTrue(Catalog.isCatalogItemThumbnailDisplayed(), "new image should be displayed in catalog");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
