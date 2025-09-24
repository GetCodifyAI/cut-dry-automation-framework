package com.cutanddry.qa.tests.integration;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.common.Constants;
import com.cutanddry.qa.data.models.CashAndCarryAppUser;
import com.cutanddry.qa.functions.CashAndCarry;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.utils.JsonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class VerifyCashAndCarryAppPassingInvalidAccountTest extends TestBase {
    static CashAndCarryAppUser user;
    static String appURL = Constants.APP_URL;
    static String itemName = "Alfalfa Sprouts Cups";

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
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.addItemToCartCatalog(itemName);
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        CashAndCarry.enterFullName(user.getFull_name());
        CashAndCarry.enterEmail(user.getEmail());
        CashAndCarry.enterMobilePhone(user.getMobile());
        CashAndCarry.enterCardNum(user.getCard_number_invalid());
        CashAndCarry.enterExpDate(user.getExpiration());
        CashAndCarry.enterCVV(user.getCvv());
        CashAndCarry.enterStreetAddress(user.getStreet_address());
        CashAndCarry.enterCity(user.getCity());
        CashAndCarry.enterState(user.getState());
        CashAndCarry.enterZip(user.getCode());
        CashAndCarry.submitOrder();
        softAssert.assertTrue(CashAndCarry.isInvalidCardDetailsPopupDisplayed(),"submit error");
        CashAndCarry.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
