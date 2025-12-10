package com.cutanddry.qa.tests.order_approval;

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

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyMultiLocationSummaryWidgetTest {
    static User user;
    String DistributerName = "46505655 - Kevin - Independent Foods Co";
    String CustomerCode = "23445";
    String OrderGuideName = "Independent Foods Co";
    String SupplierName = "Independent Foods Co";
    String ManagerUserCode = "226431917";
    String EmployeeUserCode1 = "280815154";
    String EmployeeUserCode2 = "381410447";
    String ItemCode = "01700";
    String RestuarentName = "Bellota Restaurant — Sacramento ";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1828")
    public void VerifyMultiLocationSummaryWidget() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Random random = new Random();
        int quantity1 = random.nextInt((35 - 15) + 1) + 15;
        int quantity2 = random.nextInt(6) + 15;
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        //clear out existing approvals
        Login.navigateToLoginAsPortal(ManagerUserCode);
        Dashboard.navigateToApprovalsTab();
        Approvals.clearAllApprovalOrder();
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
        Login.navigateToLoginAsPortal(EmployeeUserCode1);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Orders.selectOrderGuide(OrderGuideName);
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, quantity1);
        Orders.checkOutFromOperatorCart();
        Orders.submitForApproval();
        softAssert.assertTrue(Orders.sendToApprovalOverlayDisplayed(), "order send to approval doesn't displayed");
        Orders.viewOrderInDraft();
        softAssert.assertTrue(Orders.orderDraftDisplayedForApproval(), "Error inn displaying the order approval");

        //Log in as Employee two user to place the Order
        Login.navigateToLoginAsPortal(EmployeeUserCode2);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Orders.selectOrderGuide(OrderGuideName);
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, quantity1);
        Orders.checkOutFromOperatorCart();
        Orders.submitForApproval();
        softAssert.assertTrue(Orders.sendToApprovalOverlayDisplayed(), "order send to approval doesn't displayed");
        Orders.viewOrderInDraft();
        softAssert.assertTrue(Orders.orderDraftDisplayedForApproval(), "Error inn displaying the order approval");

        //Login as manager and view the order summery
        Login.navigateToLoginAsPortal(ManagerUserCode);
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isApprovalRequestedOrderDisplayed(), "Approval Requested order doesn't get displayed");
        Dashboard.navigateToApprovalsTab();
        softAssert.assertTrue(Approvals.isNavigatedToApprovalPage(), "ERROR in navigating to approvals Page");
        softAssert.assertEquals(Approvals.getOrderApprovalCountWidgetDetails(),"2 Pending Approvals","Error in displaying the order count details in widget");
        softAssert.assertEquals(Approvals.getOrderApprovalAccountCountWidgetDetails(),"From 1 Accounts", "Error in displaying the account details in widget");
        Approvals.selectViewAll();
        softAssert.assertEquals(Approvals.TotalPendingCount(RestuarentName),"Total Pending: 2","Order count details displayed in view all widget");

        //Clearing out the pending orders
        Approvals.closeSummeryOverlay();
        Approvals.clearAllApprovalOrder();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
