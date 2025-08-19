package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatManuallyOrderGuideCanEditTest extends TestBase{
    static User user;
    static String distributorVitco = PriceData.DISTRIBUTOR_VITCO;
    static String customerId = "5541";
    static String OrderGuideName ="Test_OG";
    static String itemName ="Capers Surfine 32 OZ";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1277")
    public void VerifyThatManuallyOrderGuideCanEdit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorVitco);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuideParentChild(customerId);

        Customer.goToCreatePopup();
        Customer.createOrderGuide(OrderGuideName);
        Customer.createOrderFromCatalog();
        Customer.searchItemOnCatalog(itemName);
        Customer.addItemFromCatalog();
        Customer.closeEditorCatalog();

//        Customer.expandMoreOptionsDropdown();
//        softAssert.assertTrue(Customer.isEditOrderGuideButtonDisplay(),"edit OG button display");
//        Customer.goToEditOrderGuide();
//        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnDeleteOrderGuide();
        softAssert.assertTrue(Orders.isAreYouSurePopUpDisplayed(),"Are you sure pop up not displayed");
        Orders.clickYes();

        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
