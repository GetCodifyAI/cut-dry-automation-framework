package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerProfileData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCustomerProfileVisibilityTest extends TestBase{
    static User user;
    String CustomerCode = "16579";
    static String nameCus = "209223241";
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";

    
    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1861")
    public void VerifyCustomerProfileVisibility() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"Error in displaying the customer");
        String BusinessName = Customer.getBusinessNameFromCustomers(CustomerCode);

        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerProfileDisplayed(BusinessName),"Error in navigation to customer page");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountHiddenOption();

        Dashboard.navigateToOrderGuide();
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(), "Order Guide should be displayed");
        
        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(nameCus);
        Customer.clickCustomerPortalOrderIcon();
        softAssert.assertFalse(Customer.isVisibleAddSupplierButton(), "Add Supplier button should not be visible when customer visibility is Hidden");
        
        softAssert.assertTrue(Customer.isOrderGuideVisibleCustomerPortal(), "Order Guide should be visible");
        
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, "Item quantity should be increased");
        
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order screen should be displayed");
        
        
        Customer.goToCatalog();
        Customer.searchItemOnCatalog("test");
        Customer.addItemToCartCatalog("test");
        
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Thank you popup should be displayed without total amount");
        
        Dashboard.navigateToHistory();
        
        

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomerListTextDisplayed(), "text error");
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountVisibleOption();
        softAssert.assertTrue(Customer.isAccountVisibleOptionDisplayed(), "error");

        Dashboard.navigateToOrderGuide();
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(), "Order Guide should be displayed");
        
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        
        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(nameCus);
        Customer.clickCustomerPortalOrderIcon();
        softAssert.assertTrue(Customer.isOrderGuideVisibleCustomerPortal(), "error in visibility");
        softAssert.assertTrue(Customer.isVisibleAddSupplierButton(), "Add Supplier button should be visible when customer visibility is Visible");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
