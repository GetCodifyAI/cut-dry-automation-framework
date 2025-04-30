package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyAddPayoutAccountTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    String AccountNumber = CustomerInvoiceData.ACCOUNT_NUMBER;
    String RoutingNumber = CustomerInvoiceData.ROUTING_NUMBER;
    String DistributorName = CustomerData.DISTRIBUTOR_NAME_IFC;
    String NodeStatus = CustomerData.NODE_STATUS2;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-549")
    public void VerifyAddPayoutAccount() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);

        Dashboard.navigateToPaySettings();
        Assert.assertTrue(Settings.isPaySettingsTextDisplayed(),"navigation to pay settings error");
        //Remove existing bank accounts
        Settings.removeExistingBankAccount();
        Settings.clickOnLinkBank();
        softAssert.assertTrue(Settings.isLinkAccPopupDisplayed(),"link popup error");
        Settings.clickOnLinkBankManually();
        Settings.enterAccountNumber(AccountNumber);
        Settings.enterRoutingNumber(RoutingNumber);
        Settings.clickOnSave();
        softAssert.assertTrue(Settings.isBankDetailsAddedPopupDisplayed(),"bank adding popup error");
        Settings.clickOK();
        softAssert.assertTrue(Settings.isPayoutMethodAdded(),"payout method adding error");

        Customer.loginNodeExplorerPortal();
        softAssert.assertTrue(Customer.isAddedPaymentMethodDisplayed(NodeStatus),"nit displayed added payment method");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
