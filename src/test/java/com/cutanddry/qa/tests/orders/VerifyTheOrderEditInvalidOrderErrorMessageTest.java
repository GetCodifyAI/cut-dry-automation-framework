package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.ParentChildOGData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.History;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerifyTheOrderEditInvalidOrderErrorMessageTest extends TestBase {
    static User user;
    static String DP = "222826632 - Eshan - Mar Pacific";
    static String customerId = "DAVIEN01";
    static String searchItemCode = "205626941";
    static String itemName = "Angel City Cold Brew Concentrate Bib";
    static String orderId;
    static String customerCode = "487310251";
    int maxAttempts = 7;
    static String orderGuide = "Dv - Westminister";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1273")
    public void VerifyTheOrderEditInvalidOrderErrorMessage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        //Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.selectOrderGuideIfOverlayDisplayed(orderGuide);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        Customer.clickOnPlusIconInCatalog(2, itemName);
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        // User Delivery Date
        for (int i = 0; i < maxAttempts; i++) {
        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = today.plusDays(5 + i);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
        String deliveryDay = dayFormatter.format(deliveryDate);
        int todayMonth = today.getMonthValue();
        int deliveryMonth = deliveryDate.getMonthValue();
        boolean isNextMonth = deliveryMonth != todayMonth;
        Customer.selectDeliveryDateLineStablePick(deliveryDay, isNextMonth);

        Customer.submitOrder();
            if (Customer.isSameDeliveryDateErrorPopUpDisplay()) {
                Customer.clickOK();
            } else {
                break;
            }
        }
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Login.navigateToLoginAsPortal(customerCode);
        History.goToHistory();
        softAssert.assertTrue(History.isUserNavigatedToHistory(),"History navigation error");
        History.searchOrderID(orderId);
        softAssert.assertTrue(History.checkIfSearchedElementVisible(orderId), "Order ID not found in the table.");
        History.clickOnFirstItemOfOrderHistory();
        History.clickEditOrder();
        softAssert.assertTrue(History.isEditOrderPopUpDisplayed(),"Edit order pop up window not display");
        History.clickConfirmEditOrder();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.increaseFirstRowQtyCustom(1);
        History.clickSubmitEditOrder();
        softAssert.assertTrue(History.isInvalidProductTextDisplay(),"Invalid product pop up error");
        softAssert.assertTrue(History.isInvalidProductCodeDisplay(searchItemCode),"Invalid product code error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
