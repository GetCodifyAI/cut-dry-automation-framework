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

public class ValidateTheNutritionSectionInCategoryGeneralTest extends TestBase {
    static User user;
    String itemCode = "22031";
    String itemCodeRevert = "22031";
    String newDescription = "Test Nutrition Description";
    String previousDescription = "";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4537")
    public void ValidateTheNutritionSectionInCategoryGeneral() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchOnSupplierCatalog(itemCode);
        Catalog.clickFirstSearchResultInSupplierCatalog();
        previousDescription = Catalog.getDescriptionText();
        Catalog.typeNewDescription(newDescription);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving item data in catalog");
        Catalog.clickOnPreview();
        softAssert.assertTrue(Catalog.isNewDescriptionDisplayed(newDescription),"description not updated");
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchOnSupplierCatalog(itemCode);
        Catalog.clickFirstSearchResultInSupplierCatalog();
        Catalog. clearTheDescription();
        Catalog.typeNewDescription(previousDescription);
        Catalog.saveChanges();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}