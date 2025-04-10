package com.cutanddry.qa.tests.catalog_data;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
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

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class VerifyNotLoggedInUserCanViewThePublicItemOfTheDistributeViaPDPURLTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    String DistributorName = CustomerData.DISTRIBUTOR_NAME_MAPLEVALE;
    String searchItemCode = CustomerData.SEARCH_ITEM_CODE2;
    static String pdpURL;
    static String becomeCustomer = "Become a Customer";
    static String activeMyAccount = "Activate My Account";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-453")
    public void VerifyNotLoggedInUserCanViewThePublicItemOfTheDistributeViaPDPURL() throws InterruptedException, IOException, UnsupportedFlavorException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);

        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(searchItemCode);
        Catalog.selectItemFromGrid(searchItemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode,"Error in getting Item Code");
        Catalog.clickOnPreview();
        Catalog.copyPDPUrl();
        pdpURL = Catalog.getCopiedPDPUrl();
        Catalog.loginPDPURL(pdpURL);

        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickAddToCartPDP();
        softAssert.assertTrue(Catalog.isAlreadyCustomerPopUpDisplay(),"pop up not display");
        softAssert.assertTrue(Catalog.isAlreadyCustomerButtonDisplay(activeMyAccount),"active my account button not display");
        softAssert.assertTrue(Catalog.isAlreadyCustomerButtonDisplay(becomeCustomer),"become a customer button not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
