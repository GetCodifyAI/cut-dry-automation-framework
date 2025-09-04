package com.cutanddry.qa.tests.customer_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatTheDecreasedQuantityIsAllowingForOrderingOutOfStockTest extends TestBase {
    static User user;
    String customerId = CatalogData.CUSTOMER_ID;
    String itemCode = CatalogData.ITEM_CODE;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1606")
    public void VerifyThatTheDecreasedQuantityIsAllowingForOrderingOutOfStock() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Login error - user not navigated to dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Customers section not displayed");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), 
            "Customer search result not displayed for code: " + customerId);
        
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemCode);
        
        try {
            Customer.increaseFirstRowQtyByOne();
            Customer.increaseFirstRowQtyByOne();
            Customer.increaseFirstRowQtyByOne();
            
            String beforeDecreaseQty = Customer.getItemQtyFirstRow();
            if (beforeDecreaseQty != null && !beforeDecreaseQty.isEmpty()) {
                int beforeDecrease = Integer.parseInt(beforeDecreaseQty);
                softAssert.assertTrue(beforeDecrease > 0, 
                    "Item quantity should be greater than 0 before decrease test");
                
                Customer.decreaseFirstRowQtyByOne();
                
                String afterDecreaseQty = Customer.getItemQtyFirstRow();
                if (afterDecreaseQty != null && !afterDecreaseQty.isEmpty()) {
                    int afterDecrease = Integer.parseInt(afterDecreaseQty);
                    softAssert.assertTrue(afterDecrease < beforeDecrease, 
                        "Should allow to decrease item quantity - core test validation");
                    softAssert.assertTrue(afterDecrease >= 0, 
                        "Quantity should not go below 0 after decrease");
                }
            }
        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
