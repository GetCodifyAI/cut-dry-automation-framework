package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheSpecialOrderItemsOrderReviewNoteTest extends TestBase {
    static User user;
    static String distributorWagner = PriceData.DISTRIBUTOR_WAGNER;
    static String customerId10 = PriceData.CUSTOMER_ID_10;
    static String searchItemCode2 = PriceData.ITEM_CODE6;
    static String itemName6 = PriceData.ITEM_NAME6;
    static String specialOrderNote = PriceData.SPECIAL_ORDER_NOTE;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1361")
    public void VerifyTheSpecialOrderItemsOrderReviewNote() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorWagner);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId10);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId10), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId10);

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode2);
        Customer.clickOnPlusIconInCatalogDP(1, itemName6);
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Customer.isSpecialOrderNoteDisplay(specialOrderNote),"special order note not display");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
