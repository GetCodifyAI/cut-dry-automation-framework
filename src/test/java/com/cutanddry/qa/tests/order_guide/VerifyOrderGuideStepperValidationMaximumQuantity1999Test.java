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

public class VerifyOrderGuideStepperValidationMaximumQuantity1999Test extends TestBase {
    static User user;
    static String customerId = "16579";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2228")
    public void verifyOrderGuideStepperValidationMaximumQuantity1999() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        // Step 1: Navigate to order guide from DP portal dashboard
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Login error - user not navigated to distributor dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Failed to navigate to customers section");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),
            "Customer search failed - customer not found");
        
        Customer.clickOnOrderGuide(customerId);
        
        // Step 2: Find product card with quantity stepper (+ and - buttons)
        softAssert.assertTrue(Customer.isOrderGuideDisplayed(),
            "Order guide page not displayed");
        
        // Step 3: Click + button repeatedly to reach quantity 1998
        // Step 4: Click + button one more time to reach 1999
        Customer.setItemQuantityFirstRow(1999);
        softAssert.assertEquals(Customer.getItemQtyFirstRow(), "1999",
            "Failed to set quantity to 1999");
        
        // Step 5: Attempt to click the disabled + button
        // Expected: Button unresponsive, error modal shows "Maximum Quantity Exceeded!"
        Customer.clickPlusQryFirstRow();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededModalDisplayed(),
            "Maximum Quantity Exceeded modal not displayed when attempting to exceed 1999");
        
        softAssert.assertTrue(Customer.getMaximumQuantityExceededModalMessage().contains("1,999"),
            "Modal message does not contain expected text about maximum quantity 1,999");
        
        // Step 6: Dismiss modal and verify quantity remains 1999
        Customer.dismissMaximumQuantityExceededModal();
        softAssert.assertEquals(Customer.getItemQtyFirstRow(), "1999",
            "Quantity changed after dismissing modal - should remain 1999");
        
        // Step 7: Repeat steps 2-6 in PDP, Carousels, Catalog (Grid and List views), Review screen
        
        Customer.goToCatalog();
        String itemName = Customer.getItemNameFirstRow();
        Customer.searchItemOnCatalog(itemName);
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),
            "PDP not displayed");
        
        Customer.setItemQuantityPDP(1999);
        softAssert.assertEquals(Customer.getItemQtyPDP(), "1999",
            "PDP: Failed to set quantity to 1999");
        
        Customer.clickPlusQtyPDP();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededModalDisplayed(),
            "PDP: Maximum Quantity Exceeded modal not displayed");
        
        softAssert.assertTrue(Customer.getMaximumQuantityExceededModalMessage().contains("1,999"),
            "PDP: Modal message does not contain expected text");
        
        Customer.dismissMaximumQuantityExceededModal();
        softAssert.assertEquals(Customer.getItemQtyPDP(), "1999",
            "PDP: Quantity changed after dismissing modal");
        
        Customer.clickOnBack();
        Customer.clickOnBack();
        
        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isCatalogTextDisplayed(),
            "Catalog not displayed");
        
        Customer.setItemQuantityCatalogFirstRow(1999);
        softAssert.assertEquals(Customer.getItemQtyCatalogFirstRow(), "1999",
            "Catalog Grid: Failed to set quantity to 1999");
        
        Customer.clickPlusQtyCatalogFirstRow();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededModalDisplayed(),
            "Catalog Grid: Maximum Quantity Exceeded modal not displayed");
        
        softAssert.assertTrue(Customer.getMaximumQuantityExceededModalMessage().contains("1,999"),
            "Catalog Grid: Modal message does not contain expected text");
        
        Customer.dismissMaximumQuantityExceededModal();
        softAssert.assertEquals(Customer.getItemQtyCatalogFirstRow(), "1999",
            "Catalog Grid: Quantity changed after dismissing modal");
        
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(),
            "Catalog List View not displayed");
        
        Customer.setItemQuantityFirstRow(1999);
        softAssert.assertEquals(Customer.getItemQtyFirstRow(), "1999",
            "Catalog List: Failed to set quantity to 1999");
        
        Customer.clickPlusQryFirstRow();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededModalDisplayed(),
            "Catalog List: Maximum Quantity Exceeded modal not displayed");
        
        softAssert.assertTrue(Customer.getMaximumQuantityExceededModalMessage().contains("1,999"),
            "Catalog List: Modal message does not contain expected text");
        
        Customer.dismissMaximumQuantityExceededModal();
        softAssert.assertEquals(Customer.getItemQtyFirstRow(), "1999",
            "Catalog List: Quantity changed after dismissing modal");
        
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(),
            "Review screen not displayed");
        
        Customer.setItemQuantityReviewFirstRow(1999);
        softAssert.assertEquals(Customer.getItemQtyReviewFirstRow(), "1999",
            "Review Screen: Failed to set quantity to 1999");
        
        Customer.clickPlusQtyReviewFirstRow();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededModalDisplayed(),
            "Review Screen: Maximum Quantity Exceeded modal not displayed");
        
        softAssert.assertTrue(Customer.getMaximumQuantityExceededModalMessage().contains("1,999"),
            "Review Screen: Modal message does not contain expected text");
        
        Customer.dismissMaximumQuantityExceededModal();
        softAssert.assertEquals(Customer.getItemQtyReviewFirstRow(), "1999",
            "Review Screen: Quantity changed after dismissing modal");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
