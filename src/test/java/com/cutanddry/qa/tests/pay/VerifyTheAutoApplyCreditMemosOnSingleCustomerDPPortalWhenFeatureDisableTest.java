package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheAutoApplyCreditMemosOnSingleCustomerDPPortalWhenFeatureDisableTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    static String customerName = PayInvoiceData.CUSTOMER_NAME2;
    static String status_payment = PayInvoiceData.OPTION_FUNDS;
    String CustomerFilterOption = CustomerInvoiceData.CUSTOMER_FILTER_OPTION_PAST_DUE;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-971")
    public void VerifyTheAutoApplyCreditMemosOnSingleCustomerDPPortalWhenFeatureDisable() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToPayDetailsTab();
        InternalTools.clickCreditMemoCheckbox(false);
        InternalTools.clickSave();
      //  softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToPay();
        Pay.searchCustomer(customerName);
        softAssert.assertTrue(Pay.isSearchCustomerDisplayed(customerName),"Search customer by customer Name not display");
        Pay.clickSearchCustomer(customerName);
       // softAssert.assertTrue(Pay.isCustomerInvoiceSectionDisplayed(customerName),"navigate customer invoice section error");
        Customer.clickOnDropDownFilter();
        Customer.selectFilterDropDown(CustomerFilterOption);
        softAssert.assertTrue(Customer.isFilterSelectedCorrectly(CustomerFilterOption.replace("- ","").trim()),"The filter hasn't selected correctly");

        Pay.clickOnInvoiceRecord(2);
        Pay.clickOnInvoiceBatchOperationButton();
        Pay.selectTheBatchOperationOption(status_payment);

        softAssert.assertTrue(Pay.isInvoiceCaptureFundPopupDisplayed(), "Unable to see the Capture Funds overlay");
        softAssert.assertTrue(Pay.isCreditMemoDisplayed(),"credit memo not display");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
