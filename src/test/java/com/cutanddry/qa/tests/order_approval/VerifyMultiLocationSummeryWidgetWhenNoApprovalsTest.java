package com.cutanddry.qa.tests.order_approval;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyMultiLocationSummeryWidgetWhenNoApprovalsTest {
    static User user;
    String DistributerName = "47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String ManagerUserCode = "226431917";
    String RestuarentName = "Bellota Restaurant — Sacramento ";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1829")
    public void VerifyMultiLocationSummeryWidgetWhenNoApprovals() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "navigation error");

        Login.navigateToLoginAsPortal(ManagerUserCode);
        Dashboard.navigateToApprovalsTab();
        softAssert.assertTrue(Approvals.isNavigatedToApprovalPage(), "ERROR in navigating to approvals Page");

        Approvals.clearAllApprovalOrder();
        softAssert.assertEquals(Approvals.getOrderApprovalCountWidgetDetails(),"0 Pending Approvals","Error in displaying the order count details in widget");
        softAssert.assertEquals(Approvals.getOrderApprovalAccountCountWidgetDetails(),"From 0 Accounts", "Error in displaying the account details in widget");

        Approvals.selectViewAll();
        softAssert.assertFalse(Approvals.isPendingOrdersDisplayed(RestuarentName),"Order count details displayed in view all widget");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
