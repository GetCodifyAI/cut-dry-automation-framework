package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCreditHoldModalAndBannerDisplayOnFirstOrderSessionTest extends TestBase {
    static User user;
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    static String customerId = "30275";
    static String OperatorName = "372460856";
    static String creditHoldMessage = "Your order has been successfully sent to your supplier.       However, it has been noted your account is on hold due to outstanding payments.       Please get in touch with your supplier to make necessary payments.";
    String itemName,searchItemCode;
    static String accHoldMessage = "Your account is currently on hold. Order submission and processing may be impacted. Please review details.";




    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2178")
    public void VerifyCreditHoldModalAndBannerDisplayOnFirstOrderSession() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnEditAccHolds();
        Customer.clickOnAccDropdown();
        Customer.clickOnCreditHold();
        Customer.clickOnSave();
        softAssert.assertTrue(Customer.isCreditHoldSelected(),"acc select error");

        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperatorAsWhiteLabel(OperatorName);
        Dashboard.navigateToOrder();
        softAssert.assertTrue(Customer.isAccountHoldPopUpDisplay(),"account not hold");
        softAssert.assertTrue(Customer.isAccountHoldMessageDisplay(creditHoldMessage),"account hold message not display");
        Customer.clickCloseHardHoldPopup();
        Assert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        softAssert.assertTrue(Customer.isAccountHoldBannerDisplay(accHoldMessage),"account hold banner not display og");

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        Customer.increaseFirstRowQtySpecificCustomer(15);

        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isAccountHoldBannerDisplay(accHoldMessage),"account hold banner not display Catalog");
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Customer.isAccountHoldBannerDisplay(accHoldMessage),"account hold banner not display review page");

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isAccountHoldPopUpDisplay(),"account not hold");
        softAssert.assertTrue(Customer.isAccountHoldMessageDisplay(creditHoldMessage),"account hold message not display");


        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
