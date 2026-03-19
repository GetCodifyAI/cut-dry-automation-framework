package com.cutanddry.qa.tests.standing_orders;

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

public class VerifyStandingOrderCRUDNoDraftsCreatedOPSideTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String deliveryDay = "Tuesday";
    static String title = "Test Standing Order";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2194")
    public void verifyStandingOrderCRUDNoDraftsCreatedOPSide() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();

        // Step 1: Log in to OP portal
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        // Step 2: Click on place order from OP side - navigate to the order guide
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "navigation to order guide error");

        // Step 3-5: Click on more options, verify and click manage standing orders CTA
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();

        // Step 6-7: Verify Manage Standing Orders overlay and clean up existing standing orders
        Customer.deleteTheExistingStandingOrdersInManageIFAvailable();

        // Step 8: Click on Create standing orders button
        Customer.clickOnManageCreateStandingOrder();

        // Step 9: Select delivery dates and items from the order guide
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();

        // Step 10: Click on Checkout button
        Customer.checkoutItems();
        Customer.typeOnStandingOrderTitle(title);
        Customer.selectDeliveryDate(deliveryDay);
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "item mismatch on review");

        // Step 11: Click on place order (set standing order)
        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(), "email popup not displayed");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(), "standing order creation error");
        Customer.clickOK();

        // Step 12: Navigate to draft section and verify no drafts for newly submitted standing order
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "navigation to drafts error after creation");
        softAssert.assertTrue(Draft.isEmptyStateDisplayed(), "drafts should be empty after creating standing order");

        // Step 13-14: Navigate back to order guide > More options > Manage standing orders
        Dashboard.navigateToOrder();
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();

        // Step 15-16: Verify info and click edit option from the manage standing orders overlay
        Customer.clickOnStandingOrderEditIcon();

        // Step 17: Click on edit order from review screen
        Customer.editOrderFromReviewScreen();

        // Step 18: Change the quantity
        Customer.increaseFirstRowQtyByOne();

        // Step 19: Checkout from the order
        Customer.checkoutItems();
        Customer.selectDeliveryDateAsLast();

        // Step 20: Submit the edited standing order
        Customer.resetStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(), "email popup not displayed after edit");
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(), "standing order edit error");
        Customer.clickOK();

        // Step 21: Navigate to draft section and verify no drafts for edited standing order
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "navigation to drafts error after edit");
        softAssert.assertTrue(Draft.isEmptyStateDisplayed(), "drafts should be empty after editing standing order");

        // Step 22-24: Navigate to OG again > More options > Manage standing orders
        Dashboard.navigateToOrder();
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();

        // Step 25-26: Verify info and click on trash icon to delete
        Customer.clickOnStandingOrderDeleteIcon();

        // Step 27-28: Verify the standing order is deleted
        softAssert.assertFalse(Customer.isStandingOrdersDeletedIconDisplay(), "standing order not deleted");

        // Step 29: Navigate to draft section and verify no drafts for deleted standing order
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "navigation to drafts error after delete");
        softAssert.assertTrue(Draft.isEmptyStateDisplayed(), "drafts should be empty after deleting standing order");

        // Step 30: Login to DP portal and verify no draft orders created for OP side standing orders
        Login.switchIntoNewTab();
        Login.navigateToDistributor();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "DP login error");
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "navigation to DP drafts error");
        softAssert.assertFalse(Draft.isDraftOrderReferenceNotDisplayedInOPSide(), "DP side should not have standing order drafts");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
