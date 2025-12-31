package com.cutanddry.qa.tests.catalog;

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

public class VerifyCatalogListGridViewToggleSwitchToListViewTest extends TestBase {
    static User user;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3676")
    public void verifyCatalogListGridViewToggleSwitchToListView() throws InterruptedException {
        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - user not navigated to dashboard");

        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(), "Navigation to Catalog failed");

        softAssert.assertTrue(Catalog.isSwitchToGridViewButtonDisplayed(),
                "Switch to Grid View button not displayed - catalog should be in List view by default");

        Catalog.clickOnPreviewCatalog();
        Assert.assertTrue(Catalog.isNavigatedToPreviewCatalog(), "Navigation to Catalog Preview failed");

        softAssert.assertTrue(Catalog.isCatalogGridViewDisplayed(),
                "Catalog Preview should display in Grid view with product cards");
        softAssert.assertTrue(Catalog.isSwitchToListViewButtonDisplayed(),
                "Switch to List View button not displayed in Grid view");

        Catalog.clickSwitchToListView();

        softAssert.assertTrue(Catalog.isCatalogListViewDisplayed(),
                "Catalog did not switch to List view after clicking toggle");

        softAssert.assertTrue(Catalog.isListViewProductRowDisplayed(),
                "Products not displayed in list format with rows");

        softAssert.assertTrue(Catalog.isListViewItemCodeColumnDisplayed(),
                "Item Code column not visible in List view");
        softAssert.assertTrue(Catalog.isListViewItemNameColumnDisplayed(),
                "Item Name column not visible in List view");
        softAssert.assertTrue(Catalog.isListViewCategoryColumnDisplayed(),
                "Category column not visible in List view");
        softAssert.assertTrue(Catalog.isListViewUnitColumnDisplayed(),
                "Unit column not visible in List view");
        softAssert.assertTrue(Catalog.isListViewPriceColumnDisplayed(),
                "Price column not visible in List view");
        softAssert.assertTrue(Catalog.isListViewStatusColumnDisplayed(),
                "Status column not visible in List view");

        softAssert.assertTrue(Catalog.isSwitchToGridViewButtonDisplayed(),
                "Switch to Grid View button not displayed after switching to List view");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
