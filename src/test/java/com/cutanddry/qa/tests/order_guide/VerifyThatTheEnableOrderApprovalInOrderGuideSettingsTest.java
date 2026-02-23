package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatTheEnableOrderApprovalInOrderGuideSettingsTest extends TestBase {
    static User user;
    static String customerId = "26816";
    String SupplierName = "Independent Foods Co";
    String RestaurantUserCode = "52068374";
    String DistributorName ="Independent Foods Co";
    static String orderMinimumSetting = "Exempt from Order Minimum";
    String approvalType = "General Approval (Operator or Distributor)";
    String location = "Main St - Oakland";



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2543")
    public void VerifyThatTheEnableOrderApprovalInOrderGuideSettings() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.SelectCustomer(customerId);
        Customer.SelectOrderMinimumFromProfile(orderMinimumSetting);
        Customer.ifHasHoldsRemoveHoldsFromCustomer();

        Customer.orderApprovalEdit();
        softAssert.assertTrue(Customer.orderApprovalSettingsOverlayDisplayed(),"Order approval overlay is not displayed");
        Customer.selectOrderApprovalType(approvalType);
        Customer.saveOrderApprovalSettings();
        Customer.clickOnOrderGuideInCustomerProfile();

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        Thread.sleep(4000);
        OrderGuideSettings.enableAccountHoldAlerts(true);
        OrderGuideSettings.clickOnSave();
        Customer.closeEditor();
        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(RestaurantUserCode);
        Orders.SelectOneSupplierFromPlaceOrder(SupplierName,location);
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Orders.isSubmitForApprovalButtonDisplay(),"submit for approval button not display");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        Thread.sleep(4000);
        OrderGuideSettings.enableAccountHoldAlerts(false);
        OrderGuideSettings.clickOnSave();
        Customer.closeEditor();
        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrder(SupplierName);
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertFalse(Orders.isSubmitForApprovalButtonDisplay(),"submit for approval button not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
