package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.CashAndCarryAppUser;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.CashAndCarry;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheSelectingMultiUOMFromCashAndCarryFlowTest extends TestBase {
    static CashAndCarryAppUser user;
    static String appURL = CatalogData.APP_URL;
    static String itemName = CatalogData.ITEM_NAME_DICARLO;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static double itemPriceUOM1 ,itemPriceUOM2,totalPDPItemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readCashAndCarryAppUserDetails();
    }

    @Test(groups = "DOT-TC-763")
    public void VerifyTheSelectingMultiUOMFromCashAndCarryFlow() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        CashAndCarry.navigateToCashAndCarryApp(appURL);
        Assert.assertTrue(CashAndCarry.isUserNavigatedToCashAndCarry(),"navigation error");
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(CashAndCarry.isProductDetailsDisplayedDicarlo(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = CashAndCarry.getDicarloPDPPriceUOM(uom1);
        itemPriceUOM2 = CashAndCarry.getDicarloPDPPriceUOM(uom2);
        CashAndCarry.clickDicarloAddToCartPlusIcon(1, uom1);
        CashAndCarry.clickDicarloAddToCartPlusIcon(1, uom2);
        totalPDPItemPrice = Customer.getItemPriceOnCheckoutButtonViaPDP();
        softAssert.assertEquals(Math.round(totalPDPItemPrice * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)),0.001, "The item has not been selected.");
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        CashAndCarry.enterFullName(user.getFull_name());
        CashAndCarry.enterEmail(user.getEmail());
        CashAndCarry.enterMobilePhone(user.getMobile());
        CashAndCarry.enterCardNum(user.getCard_number_valid());
        CashAndCarry.enterExpDate(user.getExpiration());
        CashAndCarry.enterCVV(user.getCvv());
        CashAndCarry.enterStreetAddress(user.getStreet_address());
        CashAndCarry.enterCity(user.getCity());
        CashAndCarry.enterState(user.getState());
        CashAndCarry.enterZip(user.getCode());
        CashAndCarry.submitOrder();
        softAssert.assertTrue(CashAndCarry.isTransactionRejectPopupDisplayed(),"submit error");
        CashAndCarry.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
