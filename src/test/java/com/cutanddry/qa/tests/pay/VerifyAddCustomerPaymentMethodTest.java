package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyAddCustomerPaymentMethodTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String customerName = PayInvoiceData.CUSTOMER_NAME2;
    String AccountNumber = CustomerInvoiceData.ACCOUNT_NUMBER;
    String RoutingNumber = CustomerInvoiceData.ROUTING_NUMBER;
    String AccountType = CustomerInvoiceData.ACCOUNT_TYPE;
    String DistributorName = CustomerData.DISTRIBUTOR_NAME_IFC;
    String NodeStatus = CustomerData.NODE_STATUS1;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-548")
    public void VerifyAddCustomerPaymentMethod() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);

        Dashboard.navigateToPay();
        Pay.searchCustomer(customerName);
        softAssert.assertTrue(Pay.isSearchCustomerDisplayed(customerName),"Search customer by customer Name not display");
        Pay.clickSearchCustomer(customerName);
        softAssert.assertTrue(Pay.isCustomerInvoiceSectionDisplayed(customerName),"navigate customer invoice section error");
        Customer.clickOnAddPaymentMethod();
        Customer.clickOnAddBankAccount();
        Customer.typeAccountNumber(AccountNumber);
        Customer.typeRoutingNumber(RoutingNumber);
        Customer.selectAccountType(AccountType);
        Customer.clickBtnNext();
        softAssert.assertTrue(Customer.isPaymentMethodAddedSuccessfully(), "There has been an error adding the payment method");
        Customer.clickOK();

        Customer.loginNodeExplorerPortal();
        softAssert.assertTrue(Customer.isAddedPaymentMethodDisplayed(NodeStatus),"nit displayed added payment method");

        // Delete the payment method in customer invoice test flow
        Login.navigateToDistributorPortal(DistributorName);
        Dashboard.navigateToPay();
        Pay.searchCustomer(customerName);
        softAssert.assertTrue(Pay.isSearchCustomerDisplayed(customerName),"Search customer by customer Name not display");
        Pay.clickSearchCustomer(customerName);
        softAssert.assertTrue(Pay.isCustomerInvoiceSectionDisplayed(customerName),"navigate customer invoice section error");
        Customer.editPaymentMethod();
        Customer.clickOnTrashCan();
        Customer.clickOnYes();
        softAssert.assertTrue(Customer.isPaymentMethodRemovedDisplayed(), "Message is not displayed");
        Customer.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
