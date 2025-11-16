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

public class VerifyOrderGuideMaximumQuantityValidationTest extends TestBase {
    static User user;
    String customerId = "16579";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2229")
    public void verifyOrderGuideMaximumQuantityValidation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to distributor dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Failed to navigate to customers section");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isOrderGuideTextDisplayed(), "Order guide not displayed");
        
        Customer.typeQuantityInFirstRow("1999");
        
        Customer.clickOutsideQuantityField();
        
        softAssert.assertFalse(Customer.isMaximumQuantityModalDisplayed(), 
            "Maximum quantity modal should not appear for quantity 1999");
        
        String currentQty = Customer.getItemQtyFirstRow();
        softAssert.assertEquals(currentQty, "1999", 
            "Quantity should remain 1999 after validation");
        
        Customer.typeQuantityInFirstRow("2000");
        
        Customer.clickOutsideQuantityField();
        
        softAssert.assertTrue(Customer.isMaximumQuantityModalDisplayed(), 
            "Maximum Quantity Exceeded modal should appear for quantity 2000");
        
        Customer.clickOkOnMaxQuantityModal();
        
        String qtyAfterError = Customer.getItemQtyFirstRow();
        softAssert.assertEquals(qtyAfterError, "0", 
            "Quantity field should display 0 after maximum quantity error");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
