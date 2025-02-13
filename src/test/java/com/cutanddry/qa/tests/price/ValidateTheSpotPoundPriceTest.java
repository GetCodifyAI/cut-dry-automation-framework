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

public class ValidateTheSpotPoundPriceTest extends TestBase{
    static User user;
    static String distributorKAndK = PriceData.DISTRIBUTOR_KAndK;
    static String customerId2 = PriceData.CUSTOMER_ID_2;
    static String itemPrice ;
    static String itemName,searchItemCode ;
    static String poundPrice = "100";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1080")
    public void ValidateTheSpotPoundPrice() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorKAndK);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        //pre-req
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId2);
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
        Customer.clickOnOrdersTab();
        Customer.clickOrder();
        softAssert.assertTrue(Customer.isOrderSectionDisplayed(),"order section not navigate");
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();
        softAssert.assertTrue(Orders.isNavigatedToEditOrder(),"edit error");
        Customer.clickPoundPrice();
        softAssert.assertTrue(Customer.isPoundPricePopUpDisplay(),"pound price pop up not display");
        Customer.typeOnPerLBPrice(poundPrice);
        Customer.clickSave();
        softAssert.assertEquals(Customer.getPoundPrice(),"$100.00/lb", "item count error");
        softAssert.assertEquals(Customer.getItemQtyFirstRow(),"1.22", "item count error");
        softAssert.assertEquals(Customer.getItemFinalPrice(),"$2,600.00", "item count error");
        itemPrice=Customer.getItemFinalPrice();
        softAssert.assertEquals(Customer.getItemPriceOnEditOrderCheckout(),itemPrice,"The item has not been selected.");
        Customer.clickEditOrderCheckout();
        softAssert.assertTrue(Orders.isSubmitPopupDisplayed(),"submit pop up not display");
        Orders.clickOnConfirm();
        softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(),"update popup error");
        Orders.clickOnClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId2);
        Customer.clickOnOrdersTab();
        softAssert.assertEquals(Customer.getPriceInCustomerOrder(),itemPrice,"The item has not been selected.");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
