package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyNoMinimumEnforcementOnPickupOrdersTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = "278970800 - Asanka - Sonoma County Meat Co.";
    static String DistributorName2 = "Sonoma County Meat";
    static String SoftOrderMinimumType = "Soft Order Minimum";
    static String HardOrderMinimumType = "Hard Order Minimum";
    static String orderMinInternal = "5000000";
    static String orderHardInternal = "5000000";
    static String customerId = "240014";
    static String orderMinimumSetting = "Use Global Settings";
    static String customerLoginCode = "313408434";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4499")
    public void VerifyNoMinimumEnforcementOnPickupOrders() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(DistributorName2);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnAllowSupplierToSetMinimumToggle(true);
        InternalTools.TurnOnOrderMinimumGloballyToggle(true);
        InternalTools.clickOnOrderMinimumDropdown(SoftOrderMinimumType);
        InternalTools.enterOrderMinimumAmount(orderMinInternal);
        InternalTools.clickSave();
        InternalTools.clickOKOnSucessOverlay();

        Login.switchIntoNewTab();
        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.orderMinimumCheckBox(false);
        Settings.clickOnSaveChanges();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.SelectOrderMinimumFromProfile(orderMinimumSetting);
        Customer.ifHasHoldsRemoveHoldsFromCustomer();
        Customer.clickOnOrderGuideInProfile();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.selectPickUpWillCall();
        Customer.submitOrderMinimum();
        softAssert.assertFalse(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Login.switchIntoNewTab();
        Login.navigateToLoginAsPortal(customerLoginCode);
        Customer.clickOnPlaceOrderWhiteLabel();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.selectPickUpWillCall();
        Customer.submitOrderMinimum();
        softAssert.assertFalse(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Login.switchBetweenTabsUsingIndex(0);
        InternalTools.clickOnOrderMinimumDropdown(HardOrderMinimumType);
        InternalTools.enterOrderMinimumAmount(orderHardInternal);
        InternalTools.clickSave();
        InternalTools.clickOKOnSucessOverlay();

        Login.switchBetweenTabsUsingIndex(1);
        InternalTools.refreshPage();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrderGuideInProfile();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.selectPickUpWillCall();
        Customer.submitOrderMinimum();
        softAssert.assertFalse(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Login.switchBetweenTabsUsingIndex(2);
        InternalTools.refreshPage();
        Login.navigateToLoginAsPortal(customerLoginCode);
        Customer.clickOnPlaceOrderWhiteLabel();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.selectPickUpWillCall();
        Customer.submitOrderMinimum();
        softAssert.assertFalse(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
