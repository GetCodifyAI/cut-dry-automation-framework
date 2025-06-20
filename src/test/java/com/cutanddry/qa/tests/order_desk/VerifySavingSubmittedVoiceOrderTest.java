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

public class VerifySavingSubmittedVoiceOrderTest extends TestBase {
    User user;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-497")
    public void VerifySavingSubmittedVoiceOrder(){
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToSubmittedOrders();
        softAssert.assertTrue(OrderDesk.navigatedToSubmittedOrders(),"Error Navigating to Drafted Orders Page");
        OrderDesk.navigateToSubmitOrdersReviewPage();
        softAssert.assertFalse(OrderDesk.isAddItemBtnDisplayed(),"Add Item button is displayed");
        /*OrderDesk.AddItem();
        OrderDesk.SearchItemByname();
        OrderDesk.AddItem();
        softAssert.assertTrue(OrderDesk.isItemAddedSucessfullySaved(),"Error in Adding a Item");
        OrderDesk.CloseTheSucessfulOverlayByOK();*/

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }




}
