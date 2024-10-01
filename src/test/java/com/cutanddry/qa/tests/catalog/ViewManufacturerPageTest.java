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

public class ViewManufacturerPageTest extends TestBase {
    static User user;
    String ItemCode = "12038";
    String DistributerName = "185556964 - Brandon Cheney - Cheney Brothers";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-213")
    public void ViewManufacturerPage(){
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.SearchItemInCatalogByItemCode(ItemCode);
        Catalog.SelectItemAfterSearch(ItemCode);
        Catalog.ClickOnPreview();
        softAssert.assertTrue(Catalog.isItemPreviewDisplayed(),"Error in navigating to Preview Page");
        Catalog.SelectManufacturer();
        softAssert.assertTrue(Catalog.isCongaraBrandPageDisplayed(),"ERROR in Navigating to Congara BrandPage");
        softAssert.assertTrue(Catalog.isOtherBrandsPageDisplayed(),"Error in navigating to Other Brands Page");

        softAssert.assertAll();
    }



    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }




}
