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

public class UpdateCustomerAndLocationTest extends TestBase {
    User user;


    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-131")
    public void UpdateCustomerAndLocation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToDraftOrders();
        OrderDesk.navigateToDraftOrderReviewPage();
        OrderDesk.SelectCustomerFromDropdown();
        OrderDesk.SelectLocationFromDropdown();
        Thread.sleep(5000);
        // User Delivery Date
        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = today.plusDays(4);
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDeliveryDate = fullFormatter.format(deliveryDate);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
        String deliveryDay = dayFormatter.format(deliveryDate);
        int todayMonth = today.getMonthValue();
        int deliveryMonth = deliveryDate.getMonthValue();
        boolean isNextMonth = deliveryMonth != todayMonth;
        Customer.selectDeliveryDateLineStablePickOrderDesk(deliveryDay, isNextMonth);
        Thread.sleep(5000);
        OrderDesk.AddItemQuantityDraftOrderReviewPage();
        OrderDesk.SaveDraftOrder();
        softAssert.assertTrue(OrderDesk.isCustomerLocationSucessfullySaved(),"Error in saving Customer/Location");
        OrderDesk.CloseTheSucessfulOverlayByOK();

        softAssert.assertAll();

    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
