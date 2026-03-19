package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyRealTimeUpdatesDraftCountChangesTest extends TestBase {
    static User user;
    String DistributorName = "Independent Foods Co";
    String customerId = "16579";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1896")
    public void verifyRealTimeUpdatesDraftCountChanges() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to supplier dashboard");

        softAssert.assertTrue(Dashboard.isDraftsOptionVisible(), "Drafts menu item should be visible in navigation");
        int initialBadgeCount = 0;
        if (Dashboard.isDraftsBadgeVisible()) {
            initialBadgeCount = Dashboard.getDraftsBadgeCount();
        }

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Customers section not displayed");
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search result not displayed for code: " + customerId);
        Customer.clickOnOrderGuide(customerId);
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, "Product quantity not increased");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed");

        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "Drafts page should load successfully after creating draft");
        Dashboard.refreshDashBoardPage();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "Drafts page should load after refresh");
        int countAfterCreate = Dashboard.getDraftsBadgeCount();
        softAssert.assertTrue(countAfterCreate > initialBadgeCount, "Badge count should increase after creating a new draft. Initial: " + initialBadgeCount + ", After create: " + countAfterCreate);

        Draft.clickTrashIcon();
        softAssert.assertTrue(Draft.isDraftsDeleteTextDisplayed(), "Delete draft confirmation popup not displayed");
        Draft.clickYesButton();

        Dashboard.refreshDashBoardPage();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "Drafts page should load after deletion refresh");
        int countAfterDelete = Dashboard.getDraftsBadgeCount();
        softAssert.assertTrue(countAfterDelete < countAfterCreate, "Badge count should decrease after deleting a draft. After create: " + countAfterCreate + ", After delete: " + countAfterDelete);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
