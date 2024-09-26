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

public class VerifyDeliveryDateTest extends TestBase {
    User user;


    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups="DOT-TC-171")
    public void verifyDeliveryDate(){
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        OrderDesk.navigateToDraftOrders();
        OrderDesk.navigateToDraftOrderReviewPage();

        //Invalid Delivery Date
        LocalDate today = LocalDate.now();
        LocalDate InvalidDate = today.minusDays(7);
        DateTimeFormatter usFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String PastDate = usFormatter.format(InvalidDate);
        OrderDesk.SelectDeliveryDate(PastDate);
        softAssert.assertTrue(OrderDesk.isInvalidDeliveryDateTextDisplayed(),"ERROR in Displaying the Date Invalid Text");

        //Valid Delivery Date
        LocalDate ValidDate = today.plusDays(7);
        DateTimeFormatter usFormatterDateType = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String Futuredate = usFormatterDateType.format(ValidDate);
        OrderDesk.SelectDeliveryDate(Futuredate);
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
