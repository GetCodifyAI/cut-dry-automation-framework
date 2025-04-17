package com.cutanddry.qa.tests.disclaimer_msg;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCaseDiscountsDisclaimerMsgInOrderDetailsCDAppTest extends TestBase {
    static User user;
    static String customer = "sales@jordanpaige.com";
    static String customerCode = "ZTEST";
    String ItemCode = "4MK008BB";
    static String DP = "Cut+Dry Agent - Jordan Paige";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-112")
    public void verifyCaseDiscountsDisclaimerMsgInOrderDetailsCDApp() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), "Unable to find the customer Id");
        Customer.SelectCustomer(customerCode);
        Customer.ifHasHoldsRemoveHoldsFromCustomer();
        Login.navigateToLoginAsPortal(customer);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboardWhiteLabel(),"white label login error");

        //Placing an order before verification
        Dashboard.navigateToOrderPageWhiteLabel();
        Customer.searchItemOnOrderGuide(ItemCode);
        Orders.increaseItemQuantity(ItemCode, 3);
        Orders.checkOutFromOperatorCart();
        Customer.submitOrder();
        Customer.clickClose();

        Dashboard.navigateToHistory();
        softAssert.assertTrue(History.isUserNavigatedToHistory(),"navigation error");
        History.clickFirstItemFrmHistory();
        softAssert.assertTrue(Customer.isDiscountDisclaimerOrderDetailsMsgDisplayed(),"disclaimer msg display error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
