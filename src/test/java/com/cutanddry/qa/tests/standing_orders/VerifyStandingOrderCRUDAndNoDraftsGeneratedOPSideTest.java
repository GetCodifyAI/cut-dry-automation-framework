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

public class VerifyStandingOrderCRUDAndNoDraftsGeneratedOPSideTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String deliveryDay = "Tuesday";
    static String title = "Test Standing Order OP";
    static String DistributorName = "Independent Foods Co";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2194")
    public void verifyStandingOrderCRUDAndNoDraftsGeneratedOPSide() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Step 1: Log in to OP portal
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        // Step 2: Click on place order from OP side - navigate to order guide
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        Assert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "navigation to order guide error");

        // Step 3-5: Click on more options and verify manage standing orders CTA is displayed
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();

        // Step 6: Verify the Manage Standing Orders overlay is displayed
        softAssert.assertTrue(Customer.isManageStandingOrdersPopupDisplayed(), "Manage Standing Orders overlay not displayed");

        // Clean up existing standing orders if any
        Customer.deleteTheExistingStandingOrdersInManageIFAvailable();

        // Step 7-8: Verify Create standing orders button is displayed and click on it
        Customer.clickOnManageCreateStandingOrder();

        // Step 9: Select delivery dates and items from the order guide
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();

        // Step 10-11: Checkout and place the standing order
        Customer.typeOnStandingOrderTitle(title);
        Customer.selectDeliveryDate(deliveryDay);
        softAssert.assertTrue(Customer.isReviewStandingOrdersDisplayed(), "Review Standing Orders screen not displayed");
        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(), "Standing order email popup not displayed");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(), "Standing order success popup not displayed");
        Customer.clickOK();

        // Step 12: Navigate to draft section and verify no standing orders drafts exist
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "navigation to drafts error");
        softAssert.assertTrue(Draft.isEmptyStateDisplayed(), "Draft section should not contain draft standing orders for submitted standing orders");

        // Step 13-14: Navigate back to OG and click on manage standing orders
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        Assert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "navigation to order guide error after create");

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();

        // Step 15: Verify the information on the manage standing orders overlay
        softAssert.assertTrue(Customer.isManageStandingOrdersPopupDisplayed(), "Manage Standing Orders overlay not displayed after create");

        // Step 16: Click edit option from the manage standing orders overlay
        Customer.clickOnStandingOrderEditIcon();

        // Step 17: Click on edit order - navigate to order guide
        Customer.editOrderFromReviewScreen();

        // Step 18: Change the quantity
        Customer.increaseFirstRowQtyByOne();

        // Step 19: Checkout from the order
        Customer.checkoutItems();
        Customer.selectDeliveryDateAsLast();

        // Step 20: Submit the edited standing order
        Customer.resetStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(), "Standing order email popup not displayed after edit");
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(), "Standing order success popup not displayed after edit");
        Customer.clickOK();

        // Step 21: Navigate to draft section and verify no standing orders drafts for edited standing order
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "navigation to drafts error after edit");
        softAssert.assertTrue(Draft.isEmptyStateDisplayed(), "Draft section should not contain draft standing orders for edited standing orders");

        // Step 22-25: Navigate to OG again > More options > Manage standing orders and verify info
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        Assert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "navigation to order guide error after edit");

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
        softAssert.assertTrue(Customer.isManageStandingOrdersPopupDisplayed(), "Manage Standing Orders overlay not displayed after edit");

        // Step 26-28: Click on trash icon and confirm deletion
        Customer.clickOnStandingOrderDeleteIcon();
        softAssert.assertFalse(Customer.isStandingOrdersDeletedIconDisplay(), "Standing order should be deleted");

        // Step 29: Navigate to draft section and verify no standing orders drafts for deleted standing order
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "navigation to drafts error after delete");
        softAssert.assertTrue(Draft.isEmptyStateDisplayed(), "Draft section should not contain draft standing orders for deleted standing orders");

        // Step 30: Login to DP portal and verify no draft orders created for OP side orders
        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "DP portal login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "customer not found");
        Customer.SelectCustomer(customerId);
        Customer.clickDraftsTab();
        softAssert.assertFalse(Customer.isOrderDraftDisplayed(), "DP side draft section should not contain standing draft orders for OP side orders");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
