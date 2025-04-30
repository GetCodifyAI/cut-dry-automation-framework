package com.cutanddry.qa.tests.price;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheSplitWeightTest extends TestBase{
    static User user;
    static String distributorTarantino = PriceData.DISTRIBUTOR_TARANTINO;
    static String customerId = PriceData.CUSTOMER_ID_4;
    static double itemPrice ;
    static String searchItemCode;
    static String itemName = PriceData.ITEM_NAME_SPLIT_WEIGHT;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1084")
    public void ValidateTheSplitWeight() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorTarantino);

        //pre-req
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemName);
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.clickCheckOutOrderGuide();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        //test
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Customer.clickOrder();
        softAssert.assertTrue(Customer.isOrderSectionDisplayed(),"order section not navigate");
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();
//        softAssert.assertTrue(Orders.isNavigatedToOrderReviewPage(),"edit error(Review Page)");
        Orders.clickOnEditOrderInReviewStable();
        softAssert.assertTrue(Orders.isNavigatedToEditOrder(),"edit error");
        Customer.searchItemOnOrderGuide(searchItemCode);

        Customer.splitWeight();
        softAssert.assertTrue(Customer.isSplitWeightPopupDisplayed(),"popup error");
        Customer.enterCasesValue("20");
        Customer.enterWeightValue("10");
        Customer.clickUpdateWeight();
        softAssert.assertEquals(Customer.getItemQtyFirstRow(),"20", "item count error");
        softAssert.assertEquals(Customer.getItemSplitFinalWeight(),"200 LB", "item weight error");
        softAssert.assertEquals(Customer.getEditSplitFinalWeightPrice(),"$520.00", "item price error");

        itemPrice=Customer.getSplitFinalWeightPrice();

        String priceText = Customer.getItemPriceOnEditOrderReviewCheckout().replace("$", "").replace(",", "");
        Double actualPrice = Double.valueOf(priceText);
        softAssert.assertEquals(actualPrice, itemPrice, "The item has not been selected.");
//        softAssert.assertEquals(Customer.getItemPriceOnEditOrderReviewCheckout(),itemPrice,"The item has not been selected.");

        Customer.clickCheckOutOrderGuide();

        if (Customer.isEditOrderCheckout()) {
            Customer.clickEditOrderCheckout();
            softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(), "update popup error");
        } else {
            softAssert.assertTrue(Orders.isSubmitPopupDisplayed(), "submit pop up not display");
            Orders.clickOnConfirm();
            softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(), "update popup error");
        }
        Orders.clickOnClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Customer.OrderDateSort();
        Customer.OrderDateSort();


        String priceText1 = Customer.getPriceInCustomerOrder().replace("$", "");
        Double actualPrice1 = Double.valueOf(priceText1);

//        String priceText2 = Customer.getPriceInCustomerOrder().replace("$", "").replace(",", "");
//        Double actualPrice2 = Double.valueOf(priceText2);
        softAssert.assertEquals(actualPrice1, itemPrice, "The item has not been selected.");
//        softAssert.assertEquals(Customer.getPriceInCustomerOrder(),itemPrice,"The item has not been selected.");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
