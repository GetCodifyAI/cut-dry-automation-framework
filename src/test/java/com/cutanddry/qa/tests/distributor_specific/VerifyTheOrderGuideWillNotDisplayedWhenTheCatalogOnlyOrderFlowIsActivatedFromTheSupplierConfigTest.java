package com.cutanddry.qa.tests.distributor_specific;

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

public class VerifyTheOrderGuideWillNotDisplayedWhenTheCatalogOnlyOrderFlowIsActivatedFromTheSupplierConfigTest extends TestBase{
    static User user;
    static String distributorTradewell = "445359484 - Michael Gappy - Tradewell Distributors";
    static String CustomerCode = "1792";
    static String DPName = "Tradewell Distributors";
    static String searchItemCode = "00051";
    static String itemName = "101 Ammonia Clear 64OZ";
    static double totalItemPriceReviewOrder;
    static String orderId,totalItemQuantityReviewOrder;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1481")
    public void VerifyTheOrderGuideWillNotDisplayedWhenTheCatalogOnlyOrderFlowIsActivatedFromTheSupplierConfig() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(DPName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.clickCatalogOnlyOrderFlowToggle(true);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(distributorTradewell);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.clickMenu();
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        softAssert.assertTrue(Customer.isPlaceOrderButtonDisplay(CustomerCode),"place order button display");
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isPlaceOrderButtonVisibleInCustomerProfile(),"place order button not display in customer profile");
        Customer.clickPlaceOrderButtonInCustomerProfile();
        Thread.sleep(4000);
        softAssert.assertFalse(Customer.isCustomerOrderGuideDisplayed(),"order guide displayed");
        softAssert.assertTrue(Customer.isCatalogDisplayed(),"catalog text not displayed");

        Customer.searchItemOnCatalog(searchItemCode);
        Thread.sleep(4000);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnPlusIconInCatalogPDP(2, itemName);

        // Add the product via PDP
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        totalItemQuantityReviewOrder = Catalog.getTotalQuantityInReviewOrder();
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Thread.sleep(4000);
        Customer.searchCustomerByCode(CustomerCode);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode), "Unable to find the customer Id");
        Customer.SelectCustomer(CustomerCode);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalItemPriceReviewOrder,"order not successfully submitted");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),totalItemQuantityReviewOrder,"order quantity not successfully submitted");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
