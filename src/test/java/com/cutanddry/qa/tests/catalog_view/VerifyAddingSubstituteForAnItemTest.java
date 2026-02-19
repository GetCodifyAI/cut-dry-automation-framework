package com.cutanddry.qa.tests.catalog_view;

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

public class VerifyAddingSubstituteForAnItemTest extends TestBase {
    static User user;
    String DistributerName ="Independent Foods Co";
    String itemCode = "00475";
    String substituteItemCode = "20024";
    String substituteItemName = "Cauliflower - Large";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-374")
    public void VerifyAddingSubstituteForAnItem() {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.searchItemInCatalog(itemCode);
        Catalog.selectItemFromGrid(itemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),itemCode,"Error in getting Item Code");
        Catalog.navigateToSubstituteTab();
        Catalog.removeExistingItem(substituteItemName);
        Catalog.addSubstitutions();
        String SubstituteItemName = Catalog.getSubstituteItemName(substituteItemCode);
        Catalog.addSubstitutions();
        Catalog.searchAndAddSubstituteItem(substituteItemCode);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving the changes after adding  substitute");
        Catalog.navigateToSubstituteTab();
        softAssert.assertTrue(Catalog.isAddedSubstituteItemDisplayedInPage(SubstituteItemName),"Error in adding substitute items");

        softAssert.assertAll();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
