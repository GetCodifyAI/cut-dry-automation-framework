package com.cutanddry.qa.tests.integration;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.CashAndCarryAppUser;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.CashAndCarry;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCashAndCarryDraftCreationTest extends TestBase {
    static CashAndCarryAppUser user;
    static String appURL = CatalogData.AllSTAR_APP_URL;
    static String itemName = CatalogData.CC_ITEM_NAME_1;
    static String itemName2 = CatalogData.CC_ITEM_NAME_2;
    static double itemPrice1 ,item2PriceUOM1,item2PriceUOM2,totalPDPItemPrice;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    int randomNum = (int) (Math.random() * 5)+1 ;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readCashAndCarryAppUserDetails();
    }

    @Test(groups = "DOT-TC-2403")
    public void VerifyCashAndCarryDraftCreation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        CashAndCarry.navigateToCashAndCarryApp(appURL);
        Assert.assertTrue(CashAndCarry.isUserNavigatedToAllStarCashAndCarry(),"navigation error");
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        itemPrice1 = Customer.getCatalogFirstItemPrice(itemName);
        Customer.addItemToCartCatalog(itemName);
        Customer.searchItemOnCatalog(itemName2);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName2).contains(itemName2.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemName2);
        softAssert.assertTrue(CashAndCarry.isProductDetailsDisplayedDicarlo(),"The user is unable to land on the Product Details page.");
        item2PriceUOM1 = CashAndCarry.getDicarloPDPPriceUOM(uom1);
        item2PriceUOM2 = CashAndCarry.getDicarloPDPPriceUOM(uom2);
        CashAndCarry.clickDicarloAddToCartPlusIcon(randomNum, uom1);
        CashAndCarry.clickDicarloAddToCartPlusIcon(1, uom2);
        Thread.sleep(3000);
        totalPDPItemPrice = Customer.getItemPriceOnCheckoutButtonViaPDP();
        softAssert.assertEquals(Math.round(totalPDPItemPrice*100.0)/100.0,
                (Math.round(itemPrice1*100.0)/100.0)+(Math.round(item2PriceUOM1*randomNum*100.0)/100.0)+(Math.round(item2PriceUOM2*100.0)/100.0),
                0.001, "The item has not been selected.");
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        Login.closeCurrentTabAndSwitchToNew();
        CashAndCarry.navigateToCashAndCarryApp(appURL);
        Assert.assertTrue(CashAndCarry.isUserNavigatedToAllStarCashAndCarry(),"navigation error");
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.getItemNameFirstRow().toLowerCase().contains(itemName.toLowerCase()),"item mismatch");
        System.out.println(Customer.getItemNameFirstRow());
        softAssert.assertTrue(Customer.getItemNameSecondRow().toLowerCase().contains(itemName2.toLowerCase()),"item mismatch");
        System.out.println(Customer.getItemNameSecondRow());

        CashAndCarry.enterFullName(user.getFull_name());
        CashAndCarry.enterEmail(user.getEmail());
        CashAndCarry.enterMobilePhone(user.getMobile());
        CashAndCarry.submitOrder();
        softAssert.assertTrue(CashAndCarry.isOrderSubmittedScreenDisplayedForAllStar(),"submit error");
        CashAndCarry.clickOK();

        Login.closeCurrentTabAndSwitchToNew();
        CashAndCarry.navigateToCashAndCarryApp(appURL);
        softAssert.assertFalse(CashAndCarry.isCheckoutBtnEnabled(),"Checkout btn is enabled");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
