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

import java.util.List;

public class VerifyCatalogPriceFormattingTest extends TestBase {
    static User user;
    static String itemCode = "200510";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3684")
    public void VerifyCatalogPriceFormatting() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");

        Catalog.clickOnPreviewCatalog();
        Assert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");

        softAssert.assertTrue(Catalog.arePricesDisplayedOnCatalogCards(), "Prices not displayed on catalog cards");

        List<String> prices = Catalog.getCatalogPreviewCardPrices();
        softAssert.assertTrue(prices.size() > 0, "No prices found on catalog cards");

        for (int i = 0; i < Math.min(prices.size(), 3); i++) {
            String price = prices.get(i);
            System.out.println("Verifying price format for: " + price);

            softAssert.assertTrue(Catalog.hasDollarSign(price),
                    "Price at index " + i + " does not have dollar sign: " + price);

            softAssert.assertTrue(Catalog.hasTwoDecimalPlaces(price),
                    "Price at index " + i + " does not have two decimal places: " + price);
        }

        String firstPrice = Catalog.getCatalogPreviewCardPriceByIndex(1);
        System.out.println("First card price: " + firstPrice);
        softAssert.assertTrue(Catalog.hasDollarSign(firstPrice), "First price missing dollar sign");
        softAssert.assertTrue(Catalog.hasTwoDecimalPlaces(firstPrice), "First price missing two decimal places");

        String secondPrice = Catalog.getCatalogPreviewCardPriceByIndex(2);
        System.out.println("Second card price: " + secondPrice);
        softAssert.assertTrue(Catalog.hasDollarSign(secondPrice), "Second price missing dollar sign");
        softAssert.assertTrue(Catalog.hasTwoDecimalPlaces(secondPrice), "Second price missing two decimal places");

        String thirdPrice = Catalog.getCatalogPreviewCardPriceByIndex(3);
        System.out.println("Third card price: " + thirdPrice);
        softAssert.assertTrue(Catalog.hasDollarSign(thirdPrice), "Third price missing dollar sign");
        softAssert.assertTrue(Catalog.hasTwoDecimalPlaces(thirdPrice), "Third price missing two decimal places");

        Catalog.searchItemInCatalogPreview(itemCode);
        Catalog.clickOnItemInCatalogPreview(itemCode);
        softAssert.assertTrue(Catalog.isItemDetailsDisplayedInPDP(itemCode), "PDP not displayed for item");

        String initialPdpPrice = Catalog.getPDPPrice();
        System.out.println("Initial PDP price: " + initialPdpPrice);
        softAssert.assertTrue(Catalog.hasDollarSign(initialPdpPrice), "PDP price missing dollar sign");
        softAssert.assertTrue(Catalog.hasTwoDecimalPlaces(initialPdpPrice), "PDP price missing two decimal places");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
