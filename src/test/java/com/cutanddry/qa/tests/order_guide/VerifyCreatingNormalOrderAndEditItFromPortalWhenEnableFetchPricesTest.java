package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCreatingNormalOrderAndEditItFromPortalWhenEnableFetchPricesTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName ="279572336 - Eshan - Quaker Valley Foods";
    static String CompanyName = "Quaker Valley Foods";
    static String customerId ="028270";
    static String searchItemCode = "1221082";
    static String status = "Confirmed";
    static String poundPrice = "7";
    static String actualPoundPrice;
    static String orderId;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1357")
    public void VerifyCreatingNormalOrderAndEditItFromPortalWhenEnableFetchPrices() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.fetchPricesFromOrderForEditOrderFlowToggle(true);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrderDpSpecific();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);

        Orders.clickOrderStatus();
        Orders.selectOrderStatusOption(status);
        softAssert.assertTrue(Orders.isOrderStatusUpdatedPopUpDisplayed(), "Order status updated pop-up not displayed for status: " + status);
        Orders.clickOkUpdate();
        softAssert.assertTrue(Orders.isOrderStatusUpdatedDisplayed(status), "Order status not updated for status: " + status);

        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.clickPoundPrice();
        softAssert.assertTrue(Customer.isPoundPricePopUpDisplay(),"pound price pop up not display");
        Customer.typeOnPerLBPrice(poundPrice);
        Customer.updateMarginValues();
        actualPoundPrice = Customer.getPoundPriceStable().toLowerCase();
        softAssert.assertEquals(Customer.getPoundPriceStable().toLowerCase(),"$7.00/lb", "Pound price error");
        History.clickSubmitEditOrder();
        softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(), "update popup error");
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);

        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getPoundPriceStable().toLowerCase(),actualPoundPrice, "Pound price error");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
