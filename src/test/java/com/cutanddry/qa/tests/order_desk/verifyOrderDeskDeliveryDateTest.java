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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class verifyOrderDeskDeliveryDateTest extends TestBase {
    User user;


    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups="DOT-TC-171")
    public void verifyOrderDeskDeliveryDate(){
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToDraftOrders();
        OrderDesk.navigateToDraftOrderReviewPage();

        //Today Delivery Date
        LocalDate today = LocalDate.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("EEE, MMM d");
        String TodayDate = customFormatter.format(today);
        OrderDesk.SelectDeliveryDate(TodayDate);
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(),"Error in Saving Delivery Date Draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();

        //Date after DayAfter Tomorrow Delivery Date
        LocalDate ThreeDaysLater = today.plusDays(3);
        String DayAfterTodayDate = customFormatter.format(ThreeDaysLater);
        OrderDesk.SelectDeliveryDate(DayAfterTodayDate);
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(),"Error in Saving Delivery Date Draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
