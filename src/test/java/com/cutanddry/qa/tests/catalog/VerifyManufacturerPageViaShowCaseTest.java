package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.ShowCase;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyManufacturerPageViaShowCaseTest extends TestBase {
    static User user;
    String DistributerName = "185556964 - Brandon Cheney - Cheney Brothers";
    String ProductName = "andy capps hot fries, 0.85 oz";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-258")
    public void VerifyManufacturerPageViaShowCase(){
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.NavigateToShowCasePage();
        softAssert.assertTrue(ShowCase.isNavigateToShowCase(),"ERROR in Navigating to Show Case Page");
        ShowCase.SearchProductInProductSearhBar(ProductName);
        ShowCase.SelectProductFromShowCase();
        ShowCase.NavigateToManufacturerPage();
        softAssert.assertTrue(ShowCase.isNavigatedToManufacturerPage(),"ERROR in Navigating to Manufacturer Page");
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