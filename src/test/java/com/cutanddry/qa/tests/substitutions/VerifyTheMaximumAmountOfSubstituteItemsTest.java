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

public class VerifyTheMaximumAmountOfSubstituteItemsTest extends TestBase {
    static User user;
    static String customer = "32404837";
    static String itemCode = "F43495";
    static String itemCodeSub1 = "43137";
    static String itemCodeSub2 = "365922";
    static String itemCodeSub3 = "1001445";
    static String itemCodeSub4 = "1528211";
    String DistributerName = "30227908 - Cut+Dry Agent - Southwest Traders";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-218")
    public void verifyTheMaximumAmountOfSubstituteItems() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();

        //Adding substitute item if it's not there
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(itemCode);
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.navigateToSubstituteTab();
        Catalog.checkAndAddNecessarySubstituteItems(itemCodeSub1);
        Catalog.checkAndAddNecessarySubstituteItems(itemCodeSub2);
        Catalog.checkAndAddNecessarySubstituteItems(itemCodeSub3);
        Catalog.checkAndAddNecessarySubstituteItems(itemCodeSub4);
        Catalog.showSubstituteBtnIfNotSelected();
        Catalog.saveChanges();

        Login.navigateToLoginAsPortal(customer);
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Customer.clickSouthwestTraders();
        Customer.searchItemOnOrderGuide(itemCode);
        Customer.addItemFromCatalogIfNotAvailableInOG(itemCode);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutSubstituteItems();
        softAssert.assertTrue(Customer.isSubstitutesPopupDisplayed(),"substitutes popup error");
//        softAssert.assertEquals(Customer.getSubstituteItemsCount(4), 4, "max sub items count error");
        softAssert.assertEquals(Customer.getSubstituteItemsCount(), 4, "max sub items count error");
//        Customer.clickOnItem(itemCodeSub1);
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
