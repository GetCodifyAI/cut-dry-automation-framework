package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.data.testdata.SettingData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class VerifyTheCustomersCanSubmitOrdersWhenTheAccountIsInHardHoldByEnteringTheCreditCardDetailsDPPortalTest extends TestBase {
    static User user;
    static String customerId = "30275";
    static String orderId;
    static String preAuthMessage = "Pre-authorization Required";
    static String DistributorName = CustomerData.DISTRIBUTOR_NAME_IFC;
    static String card_num = SettingData.CREDIT_CARD_NUMBER;
    static String exp_date = SettingData.EXP_DATE;
    static String cvv = SettingData.CVV;
    static String zipCode = SettingData.ZIPCODE;
    static double totalItemPriceReviewOrder;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2612")
    public void VerifyTheCustomersCanSubmitOrdersWhenTheAccountIsInHardHoldByEnteringTheCreditCardDetailsDPPortal() throws InterruptedException {
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

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.increaseFirstRowQtyCustom(5);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isPreAuthorizationTextDisplay(preAuthMessage),"pre auth pop up display error");
        Customer.clickContinue();
        Customer.addCreditCart();
        Settings.enterCardNumber(card_num);
        Settings.enterExpDate(exp_date);
        Settings.enterCVV(cvv);
        Customer.enterZipCode(zipCode);
        Customer.saveAndConfirm();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"Error in turning the approval off");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalItemPriceReviewOrder,"order not successfully submitted");

        softAssert.assertAll();


    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
