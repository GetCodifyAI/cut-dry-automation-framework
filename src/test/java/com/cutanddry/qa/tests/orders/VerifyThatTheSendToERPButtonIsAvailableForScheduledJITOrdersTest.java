package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorSpecificData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerifyThatTheSendToERPButtonIsAvailableForScheduledJITOrdersTest extends TestBase {
    static User user;
    static String customerId = "2419";
    static String searchItemCode = "2930";
    static String itemName = "1/2 Ham Spiral Sliced, Dearborn";
    static String orderId;
    static String note = "Scheduled to push to the ERP";
    static String step1 = "Submitted";
    static String step3 = "Order pushed to ERP";
    static String DP = DistributorSpecificData.DISTRIBUTOR_CARMELA;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1485")
    public void VerifyThatTheSendToERPButtonIsAvailableForScheduledJITOrders() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.orderMinimumCheckBox(true);
        Settings.deliveryDateCheckBox(true);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnPlusIconInCatalogStable(1, itemName);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        // User Delivery Date
        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = today.plusDays(2);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
        String deliveryDay = dayFormatter.format(deliveryDate);
        int todayMonth = today.getMonthValue();
        int deliveryMonth = deliveryDate.getMonthValue();
        boolean isNextMonth = deliveryMonth != todayMonth;
        Customer.selectDeliveryDateLineStablePick(deliveryDay, isNextMonth);

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);

        Assert.assertTrue(Customer.isSendToERPButtonDisplayed(), "Send to ERP button not display");
        Assert.assertTrue(Customer.isNotesToCustomerDisplayed(note), "customer notes not display");
        Customer.clickSendToERP();
        softAssert.assertTrue(Customer.isSubmitERPPopUpDisplayed(), "submit order to ERP pop up not display");
        Customer.clickConfirm();
        softAssert.assertTrue(Customer.isOrderSentERPPopUpDisplayed(), "order sent to ERP pop up not display");
        Customer.clickOK();
        Thread.sleep(3000);
        softAssert.assertFalse(Customer.isSendToERPButtonDisplayed(), "Send to ERP button display");
        softAssert.assertFalse(Customer.isNotesToCustomerDisplayed(note), "customer notes display");

        Customer.clickOnTimeline();
        softAssert.assertTrue(Customer.isOrderSubmissionStepDisplayed(step1), "order submission step not display");
        softAssert.assertTrue(Customer.isOrderSubmissionStepDisplayed(note), "order submission step not display");
        softAssert.assertTrue(Customer.isOrderSubmissionStepDisplayed(step3), "order submission step not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
