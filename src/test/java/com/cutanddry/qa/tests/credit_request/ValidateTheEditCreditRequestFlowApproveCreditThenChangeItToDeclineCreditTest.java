package com.cutanddry.qa.tests.credit_request;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheEditCreditRequestFlowApproveCreditThenChangeItToDeclineCreditTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-788")
    public void ValidateTheEditCreditRequestFlowApproveCreditThenChangeItToDeclineCredit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        String itemName;
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.clickOnCheckoutButtonOperator();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"order not completed");
        Customer.clickClose();

        Dashboard.navigateToHistory();
        softAssert.assertTrue(History.isUserNavigatedToHistory(),"There has been an error navigating to history section");
        History.clickFirstItemFrmHistory();
        softAssert.assertTrue(History.isUserNavigatedOrder(),"There has been an error navigating to order section");
        History.clickCheckInOrder();
        softAssert.assertTrue(History.isCheckInTextDisplayed(),"Error in navigating to Edit Check In Section");
        History.clickBtnReportIssue();
        softAssert.assertTrue(History.isTxtWhichItemsHasError(),"Error navigating to the page");
        History.clickOnFirstRowTableOrderIssues();
        softAssert.assertTrue(History.isPopupWindowReportIssueDisplayed(),"Pop Up Window not Displayed");
        History.clickOnFirstOptionDropDownWhatIsWrong();
        History.clickBtnContinue();
        History.clickBtnContinue();
        History.clickBtnContinue();
        History.clickBtnSaveCheckIn();
        History.clickOnYes();
        History.clickClose();

       // Distributor Flows
        Login.switchIntoNewTab();
        Login.navigateToDistributor();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCreditRequests();
        CreditRequests.clickOnFirstItemOfCreditRequests();
        softAssert.assertTrue(CreditRequests.isNavigatedToOrderSection(), "There has been an error navigating to order section");
        CreditRequests.clickCreditRequest();
        CreditRequests.process1();
        softAssert.assertTrue(CreditRequests.isTxtCreditApprovedDisplayed(), "Credit Approved Text Not Displayed");
        CreditRequests.clickBtnEditCredit();
        CreditRequests.clickConfirm();
        CreditRequests.clickCheckBox();
        CreditRequests.clickBtnDeclineDraft();
        CreditRequests.clickSubmit();
        CreditRequests.clickConfirm();
        CreditRequests.clickClose();
        softAssert.assertTrue(CreditRequests.isTxtCreditDeclinedDisplayed(),"Credit Declined Text Not Displayed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
