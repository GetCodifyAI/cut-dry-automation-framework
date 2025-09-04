package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDistributorOrderSubmissionTest extends TestBase {

    @Test(groups = "DOT-TC-34")
    public void VerifyDistributorOrderSubmission() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        String customerId = CustomerData.CUSTOMER_CODE3;
        
        Login.loginAsDistributor(JsonUtil.readUserLogin().getEmailOrMobile(), JsonUtil.readUserLogin().getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Customers section not displayed");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search result not displayed for code: " + customerId);
        
        Customer.clickOnOrderGuide(customerId);
        
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, "Product quantity not increased");
        
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed");
        
        String itemName = Customer.getItemNameFirstRow();
        softAssert.assertNotNull(itemName, "Item not displayed in cart section");
        softAssert.assertTrue(!itemName.isEmpty(), "Item name is empty in cart section");
        
        Customer.submitOrder();
        
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Thank you for your order popup not displayed");
        
        softAssert.assertAll();
    }
}
