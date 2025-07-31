package com.cutanddry.qa.tests.order_guide;

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

public class VerifyThatTheOnSiteInventoryColumnAppearsWhenTheToggleIsEnabledForTheDPSideTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName ="186818379 - Cut+Dry Agent - Wilcox Foods";
    static String CompanyName = "Wilcox Foods";
    static String customerId ="C000130";
    static String orderId;
    static double itemPrice;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1572")
    public void VerifyThatTheOnSiteInventoryColumnAppearsWhenTheToggleIsEnabledForTheDPSide() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.manualOrderQuantityCalculationToggle(true);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        itemPrice = Customer.getActiveItemPriceFirstRow();

        Customer.editCalculateOrderQty();
        softAssert.assertTrue(Customer.isCalculateOrderQtyDisplayed(),"popup error");
        Customer.enterOnSiteInvValue("2");
        Customer.enterParValue("10");
        softAssert.assertEquals(Customer.getItemPriceTotal(),itemPrice*8,"The item has not been selected par.");
        Customer.clickOnSave();
        softAssert.assertEquals(Customer.getItemQtyFirstRow(),"8", "item count error");
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*8,"The item has not been selected.");
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrderDpSpecific();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.manualOrderQuantityCalculationToggle(false);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
