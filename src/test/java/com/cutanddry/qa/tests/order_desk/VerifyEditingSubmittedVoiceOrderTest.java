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

import java.util.Random;

public class VerifyEditingSubmittedVoiceOrderTest extends TestBase {
    User user;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-498")
    public void VerifyEditingSubmittedVoiceOrder() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Random random = new Random();
        int randoNum = random.nextInt(21) + 1;
        String ItemQuantity = Integer.toString(randoNum);

        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToSubmittedOrders();
        softAssert.assertTrue(OrderDesk.navigatedToSubmittedOrders(),"Error Navigating to Drafted Orders Page");
        OrderDesk.navigateToSubmitOrdersReviewPage();

        //Item Increment using button
        OrderDesk.AddItemQuantityDraftOrderReviewPage();
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(),"Error in saving the draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();

        //Item Decrement using button
        OrderDesk.SubstractItemQuantityDraftOrderReviewPage();
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(),"Error in saving the draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();

        //Item Edit by Typing
        OrderDesk.EditItemQuantityDraftOrderReviewPage(ItemQuantity);
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(),"Error in saving the draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();

        softAssert.assertAll();
        softAssert.assertTrue(OrderDesk.navigatedToSubmittedOrders(),"Error Navigating to Drafted Orders Page");

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
