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

public class Verify2RegularItemsSubmittedAsASingleOrderForTheSelectedDeliveryDateTest extends TestBase {
    static User user;
    static String DP = DistributorSpecificData.DISTRIBUTOR_MANSON;
    static String customerId = DistributorSpecificData.CUSTOMER_ID_MANSON;
    static String itemName1,itemName2;
    static double itemPrice1,itemPrice2;
    static int orderCount = 1;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-596")
    public void Verify2RegularItemsSubmittedAsASingleOrderForTheSelectedDeliveryDate() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        itemName1 = Customer.getItemNameFirstRow();
        itemName2 = Customer.getItemNameSecondRow();
        itemPrice1 = Customer.getActiveItemPriceFirstRow();
        itemPrice2 = Customer.getActiveItemPriceSecondRow();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.increaseSecondRowQtyCustom(1);
        softAssert.assertEquals(Math.round(Customer.getItemPriceOnMultiOUMCheckout() * 100.0) / 100.0, (Math.round(itemPrice1 * 100.0) / 100.0) + (Math.round(itemPrice2 * 100.0) / 100.0), 0.001, "The item has not been selected.");
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
