package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyBulkActionsUpdateStatusTest extends TestBase {
    static User user;
    String status = "Invoiced";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-797")
    public void VerifyBulkActionsUpdateStatus() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigation error");
        Orders.selectFirstOrder();
        Orders.clickBulkActions();
        Orders.clickUpdateStatus();
        softAssert.assertTrue(Orders.isUpdateStatusDropDownDisplayed(),"Update status pop up not displayed");
        Orders.clickStatusDropDown();
        Orders.clickStatusOption();
        softAssert.assertTrue(Orders.isAreYouSurePopUpDisplayed(),"are you sure change status pop up not displayed");
        Orders.clickYes();
        softAssert.assertTrue(Orders.isOrderStatusUpdatedPopUpDisplayed(),"order status updated pop up not displayed");
        Orders.clickOkUpdate();
        softAssert.assertTrue(Orders.validateOrdersStatus(status),"Error in update order status");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
