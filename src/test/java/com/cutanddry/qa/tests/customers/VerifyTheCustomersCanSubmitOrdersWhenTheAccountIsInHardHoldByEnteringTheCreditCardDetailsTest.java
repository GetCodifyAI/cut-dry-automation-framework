package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.data.testdata.DistributorSpecificData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class VerifyTheCustomersCanSubmitOrdersWhenTheAccountIsInHardHoldByEnteringTheCreditCardDetailsTest extends TestBase {
    static User user;
    static String customerId = "30275";
    static String OperatorName="372460856";
    static String preAuthMessage = "Pre-authorization Required";
    static String DistributorName = CustomerData.DISTRIBUTOR_NAME_IFC;
    static int randomNumber = new Random().nextInt(40);

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1592")
    public void VerifyTheCustomersCanSubmitOrdersWhenTheAccountIsInHardHoldByEnteringTheCreditCardDetails() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        //Pre Requisites
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.setEnableAccountHoldAlerts(true);
        InternalTools.navigateToPayDetailsTab();
        InternalTools.setEnablePreAuthFeature(true);

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnEditAccHolds();
        Customer.clickOnAccDropdown();
        Customer.clickOnHardHold();
        Customer.clickOnSave();
        softAssert.assertTrue(Customer.isHardHoldSelected(),"acc select error");

        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperatorAsWhiteLabel(OperatorName);
        Dashboard.navigateToOrder();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        Customer.increaseFirstRowQtySpecificCustomer(randomNumber);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isPreAuthorizationTextDisplay(preAuthMessage),"pre auth pop up display error");
        Customer.clickContinue();
        softAssert.assertTrue(Customer.isConfirmPaymentTextDisplay(),"confirm payment text error");
        Customer.clickConfirm();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"Error in turning the approval off");
        Customer.clickClose();
        softAssert.assertAll();

        //Post Requisites
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToPayDetailsTab();
        InternalTools.setEnablePreAuthFeature(false);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
