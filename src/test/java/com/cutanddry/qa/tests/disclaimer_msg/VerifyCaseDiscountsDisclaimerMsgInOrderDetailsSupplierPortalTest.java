package com.cutanddry.qa.tests.disclaimer_msg;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCaseDiscountsDisclaimerMsgInOrderDetailsSupplierPortalTest extends TestBase {
    static User user;
    static String DP = "Jordan Paige";
    static String customerId = "ZTEST";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-111")
    public void verifyCaseDiscountsDisclaimerMsgInOrderDetailsSupplierPortal() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");

        Customer.clickOnOrderGuide(customerId);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        Customer.submitOrder();
        Customer.clickClose();

        Customer.searchCustomerByCode(customerId);
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnOrdersTab();
        Customer.clickFirstOrderFrmOrderTab();
        softAssert.assertTrue(Customer.isDiscountDisclaimerOrderDetailsMsgDisplayed(),"disclaimer msg display error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
