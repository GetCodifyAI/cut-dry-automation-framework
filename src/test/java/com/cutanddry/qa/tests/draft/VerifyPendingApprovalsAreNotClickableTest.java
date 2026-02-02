package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DraftsData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyPendingApprovalsAreNotClickableTest extends TestBase{
    static User user;
    static String RestaurantUserName = DraftsData.RESTAURANT_USER_NAME;
    static String referenceNum;
    static String distributorName = DraftsData.DISTRIBUTOR_NAME;
    static String status = DraftsData.STATUS;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-994")
    public void VerifyPendingApprovalsAreNotClickable() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToLoginAsPortal(RestaurantUserName);
        Dashboard.navigateToOrder();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        Customer.increaseFirstRowQtyInClassic(3);
        Customer.clickOnCheckoutButtonOperator();
        Customer.submitOrderForApproval();
        softAssert.assertTrue(Customer.isSentApprovalDisplayed(),"sent approval pop up not display");
        Customer.clickViewOrderInDraft();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        referenceNum = Draft.getReferenceNum();


        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        Draft.typeOnSearchDrafts(referenceNum);
        softAssert.assertEquals(Draft.getReferenceNumDP(), referenceNum, "draft order create not successfully");
        softAssert.assertTrue(Draft.isPendingApprovalDraftDisplayed(status),"pending approval draft not display");
        Draft.pendingApprovalDraftClick(status);
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "pending approval draft clickable");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
