package com.cutanddry.qa.tests.credit_request;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.History;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.CreditRequests;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.DataInput;

public class ValidateTheEditCreditRequestFlowApproveCreditThenChangeItToDeclineCredit extends TestBase {
    static User user;
    String orderID = "316727041";
    String timeRange = "All";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-788")
    public void ValidateTheEditCreditRequestFlowApproveCreditThenChangeItToDeclineCredit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();


        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToHistory();
        softAssert.assertTrue(History.isUserNavigatedToHistory(),"There has been an error navigating to history section");
        History.clickOnOrderFromOrderList();
        softAssert.assertTrue(History.isUserNavigatedOrder(),"There has been an error navigating to order section");
        History.clickBtnEditCheckIn();
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

//        // Distributor Flows
        Login.switchIntoNewTab();
        Login.navigateToDistributor();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCreditRequests();
        CreditRequests.changeRequestDate(timeRange); //Select the "All" option
        CreditRequests.searchOrderID(orderID);
        CreditRequests.clickOnFirstItemOfCreditRequests();
        softAssert.assertTrue(CreditRequests.isNavigatedToOrderSection(), "There has been an error navigating to order section");
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"Error Text Displayed");
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
        closeAllBrowsers();
    }
}
