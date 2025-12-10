package com.cutanddry.qa.tests.order_approval;

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

import java.util.Random;

public class VerifyDpOrderApprovalsAndRejectionsDisplayedCorrectlyTest extends TestBase {
    static User user;
    String DistributerName = "46505655 - Kevin - Independent Foods Co";
    String CustomerCode = "23445";
    String OrderGuideName = "Independent Foods Co";
    String EmployeeUserCode1 = "280815154";
    String EmployeeUserCode2 = "381410447";
    String SupplierName = "Independent Foods Co";
    String ItemCode = "01700";
    String employee1Draft, employee2Draft,orderId;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2225")
    public void VerifyDpOrderApprovalsAndRejectionsDisplayedCorrectly() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Random random = new Random();
        int quantity1 = random.nextInt((35 - 15) + 1) + 15;
        int quantity2 = random.nextInt(6) + 15;
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "navigation error");

        //Turning the order approval on for specific order guide
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.orderApprovalTxtDisplayed(), "Order approval option is not displayed");
        Customer.ifHasHoldsRemoveHoldsFromCustomer();
        Customer.orderApprovalEdit();
        softAssert.assertTrue(Customer.orderApprovalSettingsOverlayDisplayed(), "Order approval overlay is not displayed");
        softAssert.assertTrue(Customer.existingOrderGuideDisplayed(OrderGuideName), "Error in displaying the existing order guids");
        Customer.orderApprovalTurnOnForTheOrderGuide(OrderGuideName);
        Customer.saveOrderApprovalSettings();

        //Log in as Employee user to place the Order
        Login.switchIntoNewTab();
        Login.navigateToLoginAsPortal(EmployeeUserCode1);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Orders.selectOrderGuide(OrderGuideName);
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, quantity1);
        Orders.checkOutFromOperatorCart();
        Orders.submitForApproval();
        softAssert.assertTrue(Orders.sendToApprovalOverlayDisplayed(), "order send to approval doesn't displayed");
        employee1Draft = Orders.getApprovalOrderRefID();
        Orders.viewOrderInDraft();
        softAssert.assertTrue(Draft.isOrderDraftDisplayed(employee1Draft),"Draft not displayed");
        softAssert.assertTrue(Orders.orderDraftDisplayedForApproval(), "Error in displaying the order approval");

        //Log in as Employee two user to place the Order
        Login.navigateToLoginAsPortal(EmployeeUserCode2);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Orders.selectOrderGuide(OrderGuideName);
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, quantity1);
        Orders.checkOutFromOperatorCart();
        Orders.submitForApproval();
        softAssert.assertTrue(Orders.sendToApprovalOverlayDisplayed(), "order send to approval doesn't displayed");
        employee2Draft = Orders.getApprovalOrderRefID();
        Orders.viewOrderInDraft();
        softAssert.assertTrue(Draft.isOrderDraftDisplayed(employee2Draft),"Draft not displayed");
        softAssert.assertTrue(Orders.orderDraftDisplayedForApproval(), "Error in displaying the order approval");

        Login.closeCurrentTabAndSwitchBack();
        Dashboard.navigateToApprovalsTab();
        softAssert.assertTrue(Approvals.isApprovalOrderDisplayed(employee1Draft),"Draft not displayed");
        softAssert.assertTrue(Approvals.isApprovalOrderDisplayed(employee2Draft),"Draft not displayed");

        //Approval flow check
        Approvals.clickApprovalOrder(employee1Draft);
        Approvals.approveAndSubmitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode), "Unable to find the customer Id");
        Customer.SelectCustomer(CustomerCode);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isOrderDisplayedInOrderScreen(orderId),"Order Not Displayed");

        //Rejection flow check
        Dashboard.navigateToApprovalsTab();
        Approvals.clickApprovalOrder(employee2Draft);
        Approvals.RejectApprovalOrder();
        softAssert.assertTrue(Approvals.isRejectApprovalOverlayDisplayed(),"Reject Approval overlay not displayed");
        Approvals.RejectApprovalOrder();
        softAssert.assertTrue(Approvals.isApprovalOrderDisplayed(employee2Draft),"Draft not displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
