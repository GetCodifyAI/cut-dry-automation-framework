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

public class SortCustomerOrdersTest extends TestBase {
    static User user;
    String CustomerCode = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();

    }

    @Test(groups = "DOT-TC-250")
    public void SortCustomerOrders() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"Error in searching customer by code");
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isOrdersTabDisplayed(),"Error in Displaying Orders Tab");
        Customer.navigateToOrdersPage();
        softAssert.assertTrue(Customer.isOrderIdTxtDisplayed(),"Error in Displaying Orders ");
        Customer.OrderDateSort();
        softAssert.assertTrue(Customer.isOrderDateSorted(),"ERROR in Sorting Order Dates");
        Customer.DeliveryDateSort();
        softAssert.assertTrue(Customer.isDeliveryDateSorted(),"ERROR in Sorting Delivery Dates");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
