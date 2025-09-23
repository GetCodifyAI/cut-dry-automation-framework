package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderSummaryBannerDisplaysOnSimpleListViewTest extends TestBase {
    static User user;
    static String operatorName = "Independent Foods Co";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1866")
    public void verifyOrderSummaryBannerDisplaysOnSimpleListView() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.navigateToInternalToolsPage();
        Login.logInToOperator(operatorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), 
            "Login error - user not navigated to operator dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Failed to navigate to customers section");
        
        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(), 
            "Simple List View not displayed after clicking");
        
        String itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, 
            "Product quantity not increased in Simple List View");
        
        double totalPrice = Customer.getTotalPriceCart();
        softAssert.assertTrue(totalPrice > 0, 
            "Order Summary total price should be greater than 0");
        softAssert.assertTrue(Customer.isOrderSummaryDisplayed(), 
            "Order Summary banner not displayed on Simple List view");
        
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), 
            "Review order page not displayed after checkout");
        
        double reviewTotalPrice = Customer.getReviewTotalPriceCart();
        softAssert.assertTrue(reviewTotalPrice > 0, 
            "Review order total price should be greater than 0");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, 
            "Item name mismatch between cart and review sections");
        
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), 
            "Thank you for your order popup not displayed - order submission failed");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
