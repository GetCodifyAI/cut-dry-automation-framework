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

public class VerifyOrderDeskFeatureTest extends TestBase {
    User user;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-79")
    public void verifyOrderDesk() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToSubmittedOrders();
        OrderDesk.navigateToDraftOrders();
        softAssert.assertTrue(OrderDesk.iscustomerTxtDisplayInDraftOrders(),"Error Navigating to Drafted Orders Page");
        OrderDesk.navigateToDraftOrderReviewPage();
        OrderDesk.AddItemQuantityDraftOrderReviewPage();
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(),"Error in saving the draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }




}
