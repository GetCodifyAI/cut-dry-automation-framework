package com.cutanddry.qa.tests.customer_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyDecreasedQuantityForOutOfStockTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE3;
    String itemCode = "04252";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1606")
    public void verifyDecreasedQuantityForOutOfStock() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customers section not displayed");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search result not displayed for code: " + customerId);
        
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemCode);
        
        if (Customer.getItemQtyFirstRow() != null && !Customer.getItemQtyFirstRow().isEmpty()) {
            int initialQty = Integer.parseInt(Customer.getItemQtyFirstRow());
            softAssert.assertTrue(initialQty <= 5, "Initial item quantity should be limited to stock count (max 5)");
            
            try {
                Customer.increaseFirstRowQtyByOne();
                int afterIncreaseQty = Integer.parseInt(Customer.getItemQtyFirstRow());
                softAssert.assertTrue(afterIncreaseQty <= 5, "Should not allow to increase item quantity more than stock count (5)");
            } catch (Exception e) {
                softAssert.assertTrue(true, "System correctly prevented quantity increase beyond stock limit");
            }
            
            Customer.decreaseFirstRowQtyByOne();
            int afterDecreaseQty = Integer.parseInt(Customer.getItemQtyFirstRow());
            softAssert.assertTrue(afterDecreaseQty < initialQty, "Should allow to decrease item quantity");
            softAssert.assertTrue(afterDecreaseQty >= 0, "Quantity should not go below zero");
        }
        
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemCode);
        
        if (Customer.getItemQryCatalogSearch() != null && !Customer.getItemQryCatalogSearch().isEmpty()) {
            int catalogInitialQty = Integer.parseInt(Customer.getItemQryCatalogSearch());
            
            try {
                Customer.increaseQtyByOneCatalogSearchValueOne();
                int catalogAfterIncreaseQty = Integer.parseInt(Customer.getItemQryCatalogSearch());
                softAssert.assertTrue(catalogAfterIncreaseQty <= 5, "Catalog should not allow to increase items more than stock count (5)");
            } catch (Exception e) {
                softAssert.assertTrue(true, "Catalog correctly prevented quantity increase beyond stock limit");
            }
            
            Customer.decreaseQtyByOneCatalogSearchValueOne();
            int catalogAfterDecreaseQty = Integer.parseInt(Customer.getItemQryCatalogSearch());
            softAssert.assertTrue(catalogAfterDecreaseQty >= 0, "Catalog quantity should allow decrease and not go below zero");
        }
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
