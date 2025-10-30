package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyUserCanPlaceOrderWithDecimalQuantityMainOrderFlowTest extends TestBase {
    static User user;
    static String distributorName = "Independent Foods Co";
    static String customerCode = "16579";
    String[] decimalQuantities = {"1", "0.0", ".0", "0.00", "0.000", "1.0", "1.00", "1.000", "01.00", "0.1", "1.1", "1.01", "1.010"};

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1347")
    public void verifyUserCanPlaceOrderWithDecimalQuantityMainOrderFlow() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.navigateToInternalToolsPage();
        Login.logInToOperator(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), 
            "Login error - user not navigated to distributor dashboard");

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Failed to navigate to customers section");
        
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), 
            "Customer not found by code");
        
        Customer.clickOnOrderGuide(customerCode);
        softAssert.assertTrue(Customer.isOrderGuideTextDisplay(), 
            "Order guide not displayed");

        String itemName = Customer.getItemNameFirstRow();
        softAssert.assertNotNull(itemName, "Item name not found in order guide");

        for (String quantity : decimalQuantities) {
            Customer.setItemQtyFirstRow(quantity);
            Thread.sleep(1000);
            
            double priceAfterQty = Customer.getItemPriceFirstRow();
            softAssert.assertTrue(priceAfterQty >= 0, 
                "Price not updated correctly for quantity: " + quantity);
        }

        Customer.setItemQtyFirstRow("1");
        Thread.sleep(1000);

        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isUserNavigatedToCatalog(), 
            "Catalog not displayed");

        Customer.searchItemOnCatalog(itemName);
        Thread.sleep(2000);
        
        String catalogItemName = Customer.getFirstElementFrmSearchResults(itemName);
        softAssert.assertTrue(catalogItemName.toLowerCase().contains(itemName.toLowerCase()), 
            "Catalog item not found");

        for (String quantity : decimalQuantities) {
            Customer.setItemQtyFirstRow(quantity);
            Thread.sleep(1000);
            
            double catalogPrice = Customer.getItemPriceFirstRow();
            softAssert.assertTrue(catalogPrice >= 0, 
                "Catalog price not updated correctly for quantity: " + quantity);
        }

        Customer.setItemQtyFirstRow("1");
        Thread.sleep(1000);
        Customer.addItemToCartCatalog(catalogItemName);

        Customer.selectSearchedCatalogItem(catalogItemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),
            "Product details page not displayed");

        for (String quantity : decimalQuantities) {
            Customer.setItemQtyFirstRow(quantity);
            Thread.sleep(1000);
            
            double pdpPrice = Customer.getItemPricePDP();
            softAssert.assertTrue(pdpPrice >= 0, 
                "PDP price not updated correctly for quantity: " + quantity);
        }

        Customer.setItemQtyFirstRow("1");
        Thread.sleep(1000);
        Customer.closeEditorCatalog();

        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), 
            "Review order page not displayed");

        for (String quantity : decimalQuantities) {
            Customer.setItemQtyFirstRow(quantity);
            Thread.sleep(1000);
            
            double reviewPrice = Customer.getReviewTotalPriceCart();
            softAssert.assertTrue(reviewPrice >= 0, 
                "Review price not updated correctly for quantity: " + quantity);
        }

        Customer.setItemQtyFirstRow("1");
        Thread.sleep(1000);

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), 
            "Thank you popup not displayed - order submission failed");

        Customer.clickOK();
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerCode);
        Customer.clickOnCustomerCode(customerCode);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isOrdersTabDisplayed(), 
            "Orders tab not displayed");

        Customer.clickFirstOrderFrmOrderTab();
        softAssert.assertTrue(Customer.isOrderIdTxtDisplayed(), 
            "Order details not displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
