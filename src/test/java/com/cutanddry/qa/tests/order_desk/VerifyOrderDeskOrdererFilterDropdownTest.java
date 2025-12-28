package com.cutanddry.qa.tests.order_desk;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.OrderDesk;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderDeskOrdererFilterDropdownTest extends TestBase {
    User user;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3651")
    public void verifyOrderDeskOrdererFilterDropdown() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login Error");

        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(), "Error in navigating to Order Desk page");

        softAssert.assertTrue(OrderDesk.isOrdererFilterLabelDisplayed(), "Orderer filter label is not displayed");

        String defaultValue = OrderDesk.getOrdererFilterDefaultValue();
        softAssert.assertEquals(defaultValue.toUpperCase(), "ALL", "Orderer filter default value is not ALL");

        OrderDesk.clickOrdererFilterDropdown();
        softAssert.assertTrue(OrderDesk.isOrdererFilterDropdownExpanded(), "Orderer filter dropdown did not expand");

        OrderDesk.selectFirstOrdererFromDropdown();
        String selectedOrderer = OrderDesk.getSelectedOrdererValue();
        softAssert.assertFalse(selectedOrderer.isEmpty(), "No orderer was selected from dropdown");

        OrderDesk.clickOrdererFilterDropdown();
        OrderDesk.selectOrdererFromDropdown("ALL");
        String resetValue = OrderDesk.getSelectedOrdererValue();
        softAssert.assertEquals(resetValue.toUpperCase(), "ALL", "Filter was not reset to ALL");

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
