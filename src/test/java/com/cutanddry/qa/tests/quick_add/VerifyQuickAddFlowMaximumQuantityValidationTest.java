package com.cutanddry.qa.tests.quick_add;

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

public class VerifyQuickAddFlowMaximumQuantityValidationTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemCode = "02345";
    static String itemQuantityExceeded = "3000";
    static String itemQuantityValid = "800";
    static String itemQuantitySecondExceeded = "2000";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2251")
    public void verifyQuickAddFlowMaximumQuantityValidation() throws InterruptedException {
        softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        
        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isQuickAddOptionDisplay(), "Quick Add option not displayed in More Options dropdown");
        Customer.clickQuickAdd();
        softAssert.assertTrue(Customer.isQuickAddViewDisplay(), "Quick Add view page not displayed");
        
        Customer.enterItemCode(itemCode);
        Customer.enterItemQuantity(itemQuantityExceeded);
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededErrorDisplay(), "Maximum quantity exceeded error message not displayed for quantity 3000");
        
        Customer.enterItemQuantity(itemQuantityValid);
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isItemVerifiedPopUpDisplay(), "Item verified popup not displayed for valid quantity 800");
        Customer.clickClosePopUp();
        softAssert.assertTrue(Customer.isQuickAddedItemDisplay(itemCode), "Quick added item not displayed with item code");
        softAssert.assertTrue(Customer.isQuickAddedItemQuantityDisplay(itemQuantityValid), "Quick added item quantity not displayed correctly");
        
        Customer.clickSaveAndReview();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed");
        Customer.clickOnBackButton();
        
        Customer.enterItemCode(itemCode);
        Customer.enterItemQuantity(itemQuantitySecondExceeded);
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededErrorDisplay(), "Maximum quantity exceeded error message not displayed for quantity 2000");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
