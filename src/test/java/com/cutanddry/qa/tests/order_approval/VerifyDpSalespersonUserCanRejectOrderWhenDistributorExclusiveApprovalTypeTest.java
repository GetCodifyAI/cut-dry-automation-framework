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

public class VerifyDpSalespersonUserCanRejectOrderWhenDistributorExclusiveApprovalTypeTest {
    static User user;
    String DistributorName ="46505655 - Kevin - Independent Foods Co";
    String CustomerCode = "23445";
    String approvalType = "Distributor Exclusive Approval";
    String SupplierName = "Independent Foods Co";
    String salesPersonUserCode ="229043874";
    String EmployeeUserCode ="280815154";
    String ItemCode = "01700";
    String employee1Draft;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3044")
    public void VerifyDpSalespersonUserCanRejectOrderWhenDistributorExclusiveApprovalType() throws InterruptedException {
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
        Customer.clickClose();

        Login.navigateToLoginAs();
        Login.logInToDP(salesPersonUserCode);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToApprovalsTab();
        softAssert.assertTrue(Approvals.isNavigatedToApprovalPage(), "ERROR in navigating to approvals Page");
        Approvals.clickApprovalOrder(employee1Draft);
        softAssert.assertTrue(Approvals.isApproveAndSubmitBtnDisplayed(),"approval and submit button not display");
        softAssert.assertTrue(Approvals.isRejectApprovalsBtnDisplayed(),"reject button not display");
        Approvals.RejectApprovalOrder();
        softAssert.assertTrue(Approvals.isRejectApprovalOverlayDisplayed(),"Reject Approval overlay not displayed");
        Approvals.RejectApprovalOrder();
        softAssert.assertTrue(Approvals.isRejectionTabSelected(),"rejection tab not selected");
        softAssert.assertTrue(Approvals.isApprovalOrderDisplayed(employee1Draft),"Draft reject not displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
