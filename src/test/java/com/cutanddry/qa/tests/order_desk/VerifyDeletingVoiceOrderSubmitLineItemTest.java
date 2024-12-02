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

public class VerifyDeletingVoiceOrderSubmitLineItemTest extends TestBase {
    User user;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-499")
    public void VerifyDeletingVoiceOrderSubmitLineItem(){
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToSubmittedOrders();
        OrderDesk.navigateToSubmitOrdersReviewPage();
        softAssert.assertTrue(OrderDesk.navigatedToSubmittedOrders(),"Error Navigating to Drafted Orders Page");
        OrderDesk.DeleteItem();
        OrderDesk.ConfirmDeleteItem();
        softAssert.assertTrue(OrderDesk.isItemDeleteSucessfullySaved(),"Error in Deleting Item");
        OrderDesk.CloseTheSucessfulOverlayByOK();

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }
}
