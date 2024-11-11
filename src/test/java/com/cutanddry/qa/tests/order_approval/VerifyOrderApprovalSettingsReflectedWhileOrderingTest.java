package com.cutanddry.qa.tests.order_approval;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyOrderApprovalSettingsReflectedWhileOrderingTest {
    static User user;
    String DistributerName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String CustomerCode = "23445";
    String OrderGuideName = "Independent Foods Co";
    String SupplierName = "Independent Foods Co";
    String AdminUserCode = "226431917";
    String EmployeeUserCode ="280815154";
    String ItemCode = "01700";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-438")
    public void VerifyOrderGuideOrderApprovalUIFeature() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Random random = new Random();
        int quantity1 = random.nextInt(10) + 1;
        int quantity2 = random.nextInt(10) + 1;
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        //Turning the order approval on for specific order guide
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.orderApprovalTxtDisplayed(),"Order approval option is not displayed");
        Customer.orderApprovalEdit();
        softAssert.assertTrue(Customer.orderApprovalSettingsOverlayDisplayed(),"Order approval overlay is not displayed");
        softAssert.assertTrue(Customer.existingOrderGuideDisplayed(OrderGuideName),"Error in displaying the existing order guids");
        Customer.orderApprovalTurnOnForTheOrderGuide(OrderGuideName);
        Customer.saveOrderApprovalSettings();

        //Log in as Employee user to place the Order
        Login.navigateToLoginAsPortal(EmployeeUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, quantity1);
        Orders.checkOutFromOperatorCart();
        Orders.submitForApproval();
        softAssert.assertTrue(Orders.sendToApprovalOverlayDisplayed(),"order send to approval doesn't displayed");
        Orders.viewOrderInDraft();
        softAssert.assertTrue(Orders.orderDraftDisplayedForApproval(),"Error inn displaying the order approval");

        //Approve the Order after login as admin
        Login.navigateToLoginAsPortal(AdminUserCode);
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isApprovalRequestedOrderDisplayed(),"Approval Requested order doesn't get displayed");
        Draft.selectApprovalRequestedOrder();
        Draft.approveAndSubmitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"Error in approving the order");

        //Turning the order approval off for specific order guide
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.orderApprovalTxtDisplayed(),"Order approval option is not displayed");
        Customer.orderApprovalEdit();
        softAssert.assertTrue(Customer.orderApprovalSettingsOverlayDisplayed(),"Order approval overlay is not displayed");
        softAssert.assertTrue(Customer.existingOrderGuideDisplayed(OrderGuideName),"Error in displaying the existing order guids");
        Customer.orderApprovalTurnOffForTheOrderGuide(OrderGuideName);
        Customer.saveOrderApprovalSettings();

        //Log in as Employee user to place the Order
        Login.navigateToLoginAsPortal(EmployeeUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, quantity2);
        Orders.checkOutFromOperatorCart();
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"Error in turning the approval off");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
