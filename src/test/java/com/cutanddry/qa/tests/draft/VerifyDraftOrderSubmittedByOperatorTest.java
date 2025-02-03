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

public class VerifyDraftOrderSubmittedByOperatorTest extends TestBase{
    static User user;
    static String RestaurantUserCode = DraftsData.RESTAURANT_USER_CODE;
    static String SupplierName = DraftsData.SUPPLIER_NAME;
    static String itemName, searchItemCode,referenceNum;
    static double itemPrice;
    static String distributorName = DraftsData.DISTRIBUTOR_NAME;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-967")
    public void VerifyDraftOrderSubmittedByOperator() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToLoginAsPortal(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.clickOnCheckoutButtonOperator();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");

        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        referenceNum = Draft.getReferenceNumOP().replace("#","");
        softAssert.assertTrue(Draft.isRestaurantLastDraftDisplayed(String.valueOf(itemPrice)),"draft creating error");

        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        Draft.typeOnSearchDrafts(referenceNum);
        softAssert.assertTrue(Draft.isLastDraftDisplayed(String.valueOf(itemPrice)),"draft creating error");
        softAssert.assertEquals(Draft.getReferenceNumDP(), referenceNum, "draft order create not successfully");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
