package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class PlaceOrdersFromMultipleOrderGuidesTest extends TestBase {
    static User user;
    static String customerCode = "5456";
    static String newOrderGuideName = "TestOG_Automation_" + System.currentTimeMillis();
    static String existingOrderGuideName = "History Order Guide";
    static String DP = CatalogData.DP_VICTO;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1235")
    public void verifyPlaceOrdersFromMultipleOrderGuides() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToLoginAs();
        Login.logInToDP(DP);

        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to distributor dashboard");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Failed to navigate to customers section - 'Customers' title not displayed");
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), "Customer with code " + customerCode + " not found in search results");

        Customer.clickOnOrderGuide(customerCode);
        Customer.selectOrderGuideIfOverlayDisplayed(existingOrderGuideName);
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(), "Order Guide not displayed after clicking Order Guide button");
        Customer.goToCreatePopup();
        Customer.createOrderGuide(newOrderGuideName);

        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(), "Edit Order Guide screen not displayed after creating new order guide");
        Customer.createOrderByUploading();
        Customer.uploadFile(Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("excelFiles/Vitco_Automation.xlsx")).toURI()).toString());
        softAssert.assertTrue(Customer.isOrderGuideCreateSuccessPopupDisplayed(),"order guide create error");
        softAssert.assertTrue(Customer.isOrderGuideCreateSuccessPopupDisplayed(), "Order guide upload success popup not displayed");
        Customer.clickOK();
        Customer.closeEditor();
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(newOrderGuideName), "Newly created order guide '" + newOrderGuideName + "' not displayed in dropdown");

        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(newOrderGuideName);
        Thread.sleep(2000);
        String itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, "Product quantity not increased in newly created order guide");

        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed after checkout");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Thank you for your order popup not displayed - order submission failed from new OG");
        String referenceID = Customer.getSuccessOrderId();
        Customer.clickClose();

        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), "Customer with code " + customerCode + " not found in search results");
        Customer.SelectCustomer(customerCode);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isOrderDisplayedInOrderScreen(referenceID),"Order is not displayed.");
        Catalog.clickSubmittedOrder(referenceID);
        softAssert.assertTrue(Customer.isItemsDisplayedInsideOrderCorrectly(itemName),"Item is not displayed in the order");
        Customer.clickOnBackButton();

        Customer.clickOnOrderGuideInProfile();
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(existingOrderGuideName);
        Thread.sleep(2000);
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(existingOrderGuideName), "Previously existing order guide '" + existingOrderGuideName + "' not displayed");

        String itemName2 = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, "Product quantity not increased in existing order guide");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed after checkout from existing OG");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Thank you for your order popup not displayed - order submission failed from existing OG");
        String referenceID2 = Customer.getSuccessOrderId();
        Customer.clickClose();

        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), "Customer with code " + customerCode + " not found in search results");
        Customer.SelectCustomer(customerCode);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isOrderDisplayedInOrderScreen(referenceID2),"Order is not displayed.");
        Catalog.clickSubmittedOrder(referenceID2);
        softAssert.assertTrue(Customer.isItemsDisplayedInsideOrderCorrectly(itemName2),"Item is not displayed in the order");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
