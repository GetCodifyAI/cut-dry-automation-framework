package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorSpecificData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
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

public class Verify1RegularItemAnd1SpecialOrderItemSubmittedAsTwoSeparateOrdersForTheSelectedDeliveryDateTest extends TestBase {
    static User user;
    static String DP = DistributorSpecificData.DISTRIBUTOR_MANSON;
    static String customerId = DistributorSpecificData.CUSTOMER_ID_MANSON;
    static String itemName, searchItemCode;
    static double itemPrice;
    static String itemName2 =DistributorSpecificData.ITEM_NAME_CATALOG1;
    static String searchItemCode2 = DistributorSpecificData.ITEM_CODE_CATALOG1;
    static int orderCount = 2;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-598")
    public void Verify1RegularItemAnd1SpecialOrderItemSubmittedAsTwoSeparateOrdersForTheSelectedDeliveryDate() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        softAssert.assertEquals(Customer.getItemPriceOnMultiOUMCheckout(),itemPrice,"The item has not been selected.");

        Customer.goToCatalog();
        Customer.clickItemType();
        Customer.searchItemOnCatalog(searchItemCode2);
        Customer.clickOnPlusIconInCatalogDP(1, itemName2);
        Customer.clickCheckOutPDP();
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        //User Delivery Date
        LocalDate today = LocalDate.now();
        LocalDate DeliveryDate = today.plusDays(2);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d");
        String userDeliveryDate = customFormatter.format(DeliveryDate);
        Customer.selectDeliveryDateLine(userDeliveryDate);

        Customer.submitOrderDpSpecific();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        softAssert.assertEquals(Customer.getOrderCount(orderCount), orderCount, "multi order submit error error");
        Customer.clickClose();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
