package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyEntireOrderIsShiftedToDeliveryOnNextAvailableDeliveryForOrderMinimumNotMetOrdersTest extends TestBase {
    static User user;
    static String distributorCheeseImp = PriceData.DISTRIBUTOR_CHEESE_IMP;
    static String customerId3 = PriceData.CUSTOMER_ID_7;
    static String searchItemCode = PriceData.ITEM_CODE;
    static String searchItemCode2 = PriceData.ITEM_CODE2;
    static String itemName2 = PriceData.ITEM_NAME2;
    static String fullyOrderDelay = PriceData.FULLY_ORDER_DELAY;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-423")
    public void VerifyEntireOrderIsShiftedToDeliveryOnNextAvailableDeliveryForOrderMinimumNotMetOrders() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorCheeseImp);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.orderMinimumCheckBox(false);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId3);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId3), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId3);
        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyCustom(2);

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode2);
        Customer.clickOnPlusIconInCatalogDP(1, itemName2);
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.selectActiveDeliveryDateInReview();

        Customer.submitOrderDpSpecific();
        softAssert.assertTrue(Customer.isFullOrderDelayDisplayed(),"fully order delay not display");
        softAssert.assertTrue(Customer.isFullOrderDelayMessageDisplayed(fullyOrderDelay),"fully order delay message not display");
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.orderMinimumCheckBox(true);
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
