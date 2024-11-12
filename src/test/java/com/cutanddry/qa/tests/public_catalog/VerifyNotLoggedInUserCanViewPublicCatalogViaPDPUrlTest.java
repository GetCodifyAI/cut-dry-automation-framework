package com.cutanddry.qa.tests.public_catalog;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyNotLoggedInUserCanViewPublicCatalogViaPDPUrlTest {
    static User user;
    String DistributerName ="Crook Brothers";
    String ItemCode = "05130";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-453")
    public void VerifyNotLoggedInUserCanViewPublicCatalogViaPDPUrl() {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.clickOnPreviewCatalog();
        softAssert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"Error in navigating to preview catalog");
        Catalog.searchItemInCatalogPreview(ItemCode);
        Catalog.clickOnItemInCatalogPreview(ItemCode);
        softAssert.assertTrue(Catalog.isItemDetailsDisplayedInPDP(ItemCode),"Error in displaying the item details");
        Catalog.copyPDPUrl();
        softAssert.assertTrue(Catalog.linkCopiedOverlayDisplayed(),"Error in copying the PDP URL");

        Customer.clickOK();
        Catalog.navigateToPublicCatalog();
        softAssert.assertTrue(Catalog.PublicCatalogDisplayed(ItemCode),"Error in navigating to public catalog");
        Catalog.clickOnAddToCart();
        softAssert.assertTrue(Catalog.alreadyACustomerOrJoinOverlayDisplayed(),"Error in displaying the overlay ");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeMultipleTabs();
    }

}
