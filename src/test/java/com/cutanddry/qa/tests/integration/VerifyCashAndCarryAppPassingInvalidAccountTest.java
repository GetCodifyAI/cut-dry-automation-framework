package com.cutanddry.qa.tests.integration;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.CashAndCarryAppUser;
import com.cutanddry.qa.functions.CashAndCarry;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCashAndCarryAppPassingInvalidAccountTest extends TestBase {
    static CashAndCarryAppUser user;
    static String appURL = "https://dicarlo-uat.staging.cutanddry.com/market/dicarlo";
    static String itemName = "Alfalfa Sprouts";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readCashAndCarryAppUserDetails();
    }

    @Test(groups = "DOT-TC-225")
    public void verifyCashAndCarryAppPassingInvalidAccount() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        CashAndCarry.navigateToCashAndCarryApp(appURL);
        softAssert.assertTrue(CashAndCarry.isUserNavigatedToCashAndCarry(),"navigation error");
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName), "item not found");
        Customer.addItemToCartCatalog();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
//        Thread.sleep(6000);
//        CashAndCarry.enterFullName(user.getFull_name());
//        CashAndCarry.enterEmail(user.getEmail());
//        CashAndCarry.enterMobilePhone(user.getMobile());
        CashAndCarry.enterCardNum(user.getCard_number_invalid());
        CashAndCarry.enterExpDate(user.getExpiration());
        CashAndCarry.enterCVV(user.getCvv());
//        CashAndCarry.enterStreetAddress(user.getStreet_address());
//        CashAndCarry.enterCity(user.getCity());
//        CashAndCarry.enterState(user.getState());
//        CashAndCarry.enterZip(user.getCode());
//        CashAndCarry.submitOrder();
//        softAssert.assertTrue(CashAndCarry.isInvalidCardDetailsPopupDisplayed(),"submit error");
//        CashAndCarry.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
