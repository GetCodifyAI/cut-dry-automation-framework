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

public class VerifySoftOrderMinimumCustomerLevelOverrideTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = "278970800 - Asanka - Sonoma County Meat Co.";
    static String DistributorName2 = "Sonoma County Meat";
    static String SoftOrderMinimumType = "Soft Order Minimum";
    static String orderMinInternal = "50000";
    static String orderMinOpSpecific = "40000.00";
    static String customerId = "240014";
    static String customerLoginCode = "313408434";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3457")
    public void VerifySoftOrderMinimumCustomerLevelOverride() throws InterruptedException {
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
        Customer.setOperatorSpecificOrderMin(orderMinOpSpecific);
        Customer.ifHasHoldsRemoveHoldsFromCustomer();
        InternalTools.refreshPage();
        Customer.clickOnOrderGuideInProfile();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrderMinimum();
        softAssert.assertTrue(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertEquals(Customer.getSoftOrderMinimumValueFromOverlay(),orderMinOpSpecific, "Order minimum displayed is not the operator specific minimum");
        Customer.clickPlaceOrderSoftOrderMinimum();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Login.switchIntoNewTab();
        Login.navigateToLoginAsPortal(customerLoginCode);
        Customer.clickOnPlaceOrderWhiteLabel();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrderMinimum();
        softAssert.assertFalse(Customer.isOrderMinPopupDisplayed(),"popup display error");
        softAssert.assertEquals(Customer.getSoftOrderMinimumValueFromOverlay(),orderMinOpSpecific, "Order minimum displayed is not the operator specific minimum");
        Customer.clickPlaceOrderSoftOrderMinimum();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }


}
