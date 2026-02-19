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

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyOPEmployeeSubmitsOrderForApprovalWhenDistributorExclusiveApprovalTypeTest {
    static User user;
    String DistributorName ="Independent Foods Co";
    String CustomerCode = "23445";
    String approvalType = "Distributor Exclusive Approval";
    String SupplierName = "Independent Foods Co";
    String EmployeeUserCode ="280815154";
    String ItemCode = "01700";
    String employee1Draft;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3021")
    public void VerifyOPEmployeeSubmitsOrderForApprovalWhenDistributorExclusiveApprovalType() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.orderApprovalTxtDisplayed(),"Order approval option is not displayed");
        Customer.ifHasHoldsRemoveHoldsFromCustomer();
        Customer.orderApprovalEdit();
        softAssert.assertTrue(Customer.orderApprovalSettingsOverlayDisplayed(),"Order approval overlay is not displayed");
        Customer.selectOrderApprovalType(approvalType);
        Customer.saveOrderApprovalSettings();

        //Log in as Employee user to place the Order
        Login.navigateToLoginAsPortal(EmployeeUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Orders.selectOrderGuide(approvalType);
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, 2);
        Orders.checkOutFromOperatorCart();
        Orders.submitForApproval();
        softAssert.assertTrue(Orders.sendToApprovalOverlayDisplayed(),"order send to approval doesn't displayed");
        employee1Draft = Orders.getApprovalOrderRefID();
        Orders.viewOrderInDraft();
        softAssert.assertTrue(Draft.isOrderDraftDisplayed(employee1Draft),"Draft not displayed");
        softAssert.assertTrue(Orders.orderDraftDisplayedForApproval(), "Error in displaying the order approval");
        Approvals.clickApprovalOrder(employee1Draft);
        softAssert.assertTrue(Approvals.isDisableSubmitForApprovalBtnDisplayed(),"submit for approval button not disable");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
