package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyHideAvailabilityFilterInPublicCatalogTest {
    static User user;
    SoftAssert softAssert;
    static String Prime_Source_Public_Catalog = CatalogData.PRIME_SOURCE_PUBLIC_CATALOG_URL;
    static String DistributorName = "Prime Source Foods";
    static String availability = "Availability";
    static String category = "Category";
    static String brand = "Brand";
    static String itemType = "Item Type";
    static String storageType = "Storage Type";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1890")
    public void VerifyHideAvailabilityFilterInPublicCatalog() throws InterruptedException {
        softAssert = new SoftAssert();
        Catalog.navigateToPublicCatalog(Prime_Source_Public_Catalog);
        softAssert.assertTrue(Catalog.isNavigatedToPublicCatalog(DistributorName),"ERROR in navigating to public catalog");
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(category),"Category catalog filter not display");
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(brand),"Brand catalog filter not display");
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(itemType),"Item Type catalog filter not display");
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(storageType),"Storage Type catalog filter not display");
        softAssert.assertFalse(Customer.isCatalogFilterDisplayed(availability),"Availability catalog filter not display");
        softAssert.assertAll();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
