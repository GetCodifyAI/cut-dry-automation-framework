package com.cutanddry.qa.tests.substitutions;

import com.cutanddry.qa.base.TestBase;
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

public class VerifySubstituteItemFor1OrMoreItemsWithSubstitutingTest extends TestBase {
    static User user;
    static String customer = "32404837";
    static String itemCode_1 = "8433";
    static String itemCode_2 = "43137";
    String DistributerName = "30227908 - Cut+Dry Agent - Southwest Traders";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-215")
    public void verifySubstituteItemFor1OrMoreItemsWithSubstituting() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();

        //Adding substitute item if it's not there
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(itemCode_1);
        Catalog.selectItemFromGrid(itemCode_1);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode_1,"Error in getting Item Code");
        Catalog.navigateToSubstituteTab();
        Catalog.checkAndAddNecessarySubstituteItems(itemCode_2);
        Catalog.saveChanges();

        Login.navigateToLoginAsPortal(customer);
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Customer.clickSouthwestTraders();
        Customer.searchItemOnOrderGuide(itemCode_1);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.isSubstitutesPopupDisplayed(),"substitutes popup error");
//        Customer.clickOnItem(itemCode_2);
        Customer.clickOnSingleItem();
        Customer.clickSaveSelection();
        softAssert.assertFalse(Customer.isReplacementNotDisplayed(),"replace error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
