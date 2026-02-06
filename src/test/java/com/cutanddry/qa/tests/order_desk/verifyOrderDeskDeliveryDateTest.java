package com.cutanddry.qa.tests.order_desk;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
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
    public void verifyOrderDeskDeliveryDate() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToDraftOrders();
        OrderDesk.navigateToDraftOrderReviewPage();

        // For Tomorrow Delivery Date
        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = today.plusDays(1);
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");

        String formattedDeliveryDate = fullFormatter.format(deliveryDate);
        String deliveryDay = dayFormatter.format(deliveryDate);

        boolean isNextMonth = deliveryDate.getMonthValue() != today.getMonthValue();
        Customer.selectDeliveryDateLineStablePickOrderDesk(deliveryDay, isNextMonth);
        OrderDesk.AddItemQuantityDraftOrderReviewPage();
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(), "Error in Saving Delivery Date Draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();

       // For Day After Tomorrow Delivery Date
        deliveryDate = today.plusDays(2);
        formattedDeliveryDate = fullFormatter.format(deliveryDate);
        deliveryDay = dayFormatter.format(deliveryDate);

        isNextMonth = deliveryDate.getMonthValue() != today.getMonthValue();
        Customer.selectDeliveryDateLineStablePickOrderDesk(deliveryDay, isNextMonth);
        OrderDesk.AddItemQuantityDraftOrderReviewPage();
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isSaveDraftSucessful(), "Error in Saving Delivery Date Draft");
        OrderDesk.CloseTheSucessfulOverlayByOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
