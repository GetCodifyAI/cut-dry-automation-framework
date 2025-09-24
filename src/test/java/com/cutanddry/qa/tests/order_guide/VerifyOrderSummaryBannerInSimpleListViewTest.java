package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PurchaseHistoryData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderSummaryBannerInSimpleListViewTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = PurchaseHistoryData.DISTRIBUTOR_NAME_IFC;
    static String customerId = PurchaseHistoryData.CUSTOMER_ID_IFC;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1867")
    public void VerifyOrderSummaryBannerInSimpleListView() throws InterruptedException {
        softAssert = new SoftAssert();
        
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Login failed - user not navigated to restaurant dashboard");

        Login.navigateToInternalToolsPage();
        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Customers section not displayed");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search result not displayed for customer ID: " + customerId);
        
        Customer.clickOnOrderGuide(customerId);
        
        
        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(), "Simple List View not displayed");
        
        softAssert.assertTrue(Customer.isOrderSummaryBannerDisplayedInSimpleList(), "Order Summary banner not displayed in Simple List view");
        
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, "Product quantity not increased");
        
        softAssert.assertTrue(Customer.isOrderSummaryValuesUpdatedInSimpleList(), "Order Summary values not updated after adding items");
        
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed");
        
        softAssert.assertTrue(Customer.isOrderSummaryValuesCorrectInReviewPage(), "Order Summary values not correct in review page");
        
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Order submission confirmation not displayed");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
