package com.cutanddry.qa.tests.orders;

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

public class VerifyTheOrderViewOrderEditTest extends TestBase {
    static User user;
    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-536")
    public void VerifyTheOrderViewOrderEdit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigation error");
        Orders.clickOnFirstOrder();
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();
        softAssert.assertTrue(Orders.isNavigatedToEditOrder(),"edit error");
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Orders.isSubmitPopupDisplayed(),"submit popup error");
        Orders.clickOnConfirm();
        Orders.clickOnClose();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
