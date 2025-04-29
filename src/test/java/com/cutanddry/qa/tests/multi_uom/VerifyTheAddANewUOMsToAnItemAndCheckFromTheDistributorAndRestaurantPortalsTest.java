package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.SplitWeightUOMData;
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

public class VerifyTheAddANewUOMsToAnItemAndCheckFromTheDistributorAndRestaurantPortalsTest {
    static User user;
    SoftAssert softAssert;
    static String itemName, searchItemCode;
    static String DistributerName = SplitWeightUOMData.DISTRIBUTOR_NAME_IFC;
    static String customerId = SplitWeightUOMData.CUSTOMER_ID_IFC;
    String UOM = "Case";
    String itemPrice = "3.00";
    String saleValue = "3.00";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-767")
    public void verifyTheAddANewUOMsToAnItemAndCheckFromTheDistributorAndRestaurantPortals() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Customer.ensureCarouselDisplayStatus(false);

        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        itemName = Catalog.getItemNameFirstRowInCatalog().split("\n")[0];
        searchItemCode = Catalog.getItemCodeFirstRowInCatalog();

        Catalog.selectItemFromGrid(searchItemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode,"Error in getting Item Code");
        Catalog.navigateToPricingAndPromotions();
        Catalog.addUnitOfMeasureStable(UOM);
        Catalog.selectUnitFromDropdown(UOM);
        Catalog.setItemUnitPrice(UOM, itemPrice);
        Catalog.selectDollarValueAsSalesTypeFrmDropdown(UOM);
        Catalog.setSaleValue(UOM, saleValue);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in creating UOM");
        softAssert.assertTrue(Catalog.isAddedUOMDisplayed(UOM),"Error in deleting the UOM");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.addItemFromCatalogStable(itemName);
        Customer.clickOnOrderGuideTab();
        Customer.goToCatalog();
        Customer.clickOnOrderGuideTab();
        Customer.searchItemOnOrderGuide(searchItemCode);
        softAssert.assertTrue(Customer.getItemNameFirstRow().toLowerCase().contains(itemName.toLowerCase()),"item mismatch");
        softAssert.assertTrue(Customer.isMultiUomDropDownOGDisplayed(),"Item is not Multi UOM");
        Customer.ClickOnMultiUomDropDownOG(searchItemCode);
        softAssert.assertTrue(Customer.isSpotPriceAdded("1",itemPrice),"Item case price error - ");


        // Restaurant Flows
//        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Login.navigateToRestaurant();
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.addItemFromCatalogStable(itemName);
        Customer.clickOnOrderGuideTab();
        Customer.goToCatalog();
        Customer.clickOnOrderGuideTab();
        Customer.searchItemOnOrderGuide(searchItemCode);
        softAssert.assertTrue(Customer.getItemNameFirstRow().toLowerCase().contains(itemName.toLowerCase()),"item mismatch");
        softAssert.assertTrue(Customer.isMultiUomDropDownOGDisplayed(),"Item is not Multi UOM");

        Customer.ClickOnMultiUomDropDownOG(searchItemCode);
        softAssert.assertTrue(Customer.isSpotPriceAdded("1",itemPrice),"Item case price error");


        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }



}
