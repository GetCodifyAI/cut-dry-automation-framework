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

public class VerifyCashAndCarryAppSimulatingAVSMatchTest extends TestBase {
    static CashAndCarryAppUser user;
    static String appURL = "https://dicarlo-uat.staging.cutanddry.com/market/dicarlo";
    static String itemName = "Alfalfa Sprouts Cups";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readCashAndCarryAppUserDetails();
    }

    @Test(groups = "DOT-TC-226")
    public void verifyCashAndCarryAppSimulatingAVSMatch() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        CashAndCarry.navigateToCashAndCarryApp(appURL);
        softAssert.assertTrue(CashAndCarry.isUserNavigatedToCashAndCarry(),"navigation error");
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.addItemToCartCatalog(itemName);
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        CashAndCarry.enterFullName(user.getFull_name());
        CashAndCarry.enterEmail(user.getEmail());
        CashAndCarry.enterMobilePhone(user.getMobile());
        CashAndCarry.enterCardNum(user.getCard_number_valid());
        CashAndCarry.enterExpDate(user.getExpiration());
        CashAndCarry.enterCVV(user.getCvv());
        CashAndCarry.enterStreetAddress(user.getAlt_address());
        CashAndCarry.enterCity(user.getCity());
        CashAndCarry.enterState(user.getState());
        CashAndCarry.enterZip(user.getAlt_code());
        CashAndCarry.submitOrder();
        //
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
