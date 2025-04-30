package com.cutanddry.qa.tests.price;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.DraftsData;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheFinalWeightTest extends TestBase{
    static User user;
    static String distributorKAndK = PriceData.DISTRIBUTOR_KAndK;
    static String customerId = PriceData.CUSTOMER_ID;
    static String itemPrice ;
    static String itemName, searchItemCode;
    static double stableFinalPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1079")
    public void ValidateTheFinalWeight() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorKAndK);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        //pre-req
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.clickCheckOutOrderGuide();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();
        Customer.searchCustomerByCode(customerId);
        Customer.clickOnCustomerCode(customerId);
        //test
        Customer.clickOnOrdersTab();
        Customer.clickOrder();
        softAssert.assertTrue(Customer.isOrderSectionDisplayed(),"order section not navigate");
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();
        softAssert.assertTrue(Orders.isNavigatedToOrderReviewPage(),"edit error(Review Page)");
        Orders.clickOnEditOrderInReview();
        softAssert.assertTrue(Orders.isNavigatedToEditOrder(),"edit error");

        stableFinalPrice = Customer.getItemFinalPriceStable();

        String finalWeightStr = Customer.getItemFinalWeight();
        double finalWeight = Double.parseDouble(finalWeightStr);
        double weight1 = 2 * finalWeight;
        String weightStr = String.valueOf(weight1);
        String weightStr2 = String.valueOf((int)(3 * finalWeight));
        Customer.typeOnFinalWeight(weightStr);

        softAssert.assertEquals(Customer.getItemQtyFirstRow(),"2", "item count error");
        softAssert.assertEquals(Customer.getItemFinalPriceStable(),stableFinalPrice*2, "item count error");
        Customer.increaseFirstRowQtyCustom(1);
        softAssert.assertEquals(Customer.getItemFinalPriceStable(),stableFinalPrice*3, "item count error");

        softAssert.assertEquals(Customer.getItemFinalWeight(),weightStr2 , "item count error 11");

//        softAssert.assertEquals(Customer.getItemFinalWeight(),"62", "item count error");
        itemPrice=Customer.getItemFinalPrice();
        softAssert.assertEquals(Customer.getItemPriceOnEditOrderReviewCheckout(),itemPrice,"The item has not been selected.");
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
        softAssert.assertEquals(Customer.getPriceInCustomerOrder(),itemPrice,"The item has not been selected.");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
