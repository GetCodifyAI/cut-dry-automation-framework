package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.ShowCase;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyManufacturerPageViaShowCaseTest extends TestBase {
    static User user;
    String DistributerName = "185556964 - Brandon Cheney - Cheney Brothers";
//    String ProductName = "andy capps hot fries, 0.85 oz";
    String BrandPage = "Conagra Foodservice";
    String ProductName = "J Hungerford Smith Sliced Strawberry Topping, 118 Oz"; // Ac Hot On Rng 12/2Z
    String subBrandPage = "J. Hungerford Smith";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-258")
    public void VerifyManufacturerPageViaShowCase() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.NavigateToShowCasePage();
        Assert.assertTrue(ShowCase.isNavigateToShowCase(),"ERROR in Navigating to Show Case Page");
        ShowCase.SearchProductInProductSearhBar(ProductName);
        ShowCase.SelectProductFromShowCase(subBrandPage);
        ShowCase.NavigateToManufacturerPage();
        softAssert.assertTrue(ShowCase.isNavigatedToManufacturerPage(BrandPage),"ERROR in Navigating to Manufacturer Page");
        ShowCase.NavigateToOurBrandsPage();
        softAssert.assertTrue(ShowCase.isNavigatedToOurBrandsPage(),"ERROR in Navigating to Our Brands Page");

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
