package com.cutanddry.qa.tests.chat;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheChatFeatureDisplayInDPPortalWhenChatModuleEnableTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName ="46505655 - Kevin - Independent Foods Co";
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String customerName = "Hayes";
    static String distributorMessage = "Test Message Distributor";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1255")
    public void VerifyTheChatFeatureDisplayInDPPortalWhenChatModuleEnable() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.clickChatCheckbox(true);
        InternalTools.clickSaveChanges();

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        softAssert.assertTrue(Dashboard.isChatSectionDisplay(), "Chat section on the Dashboard page.");
        Dashboard.navigateToChat();
        softAssert.assertTrue(Chat.isUserNavigatedToChat(),"navigation error");
        Chat.searchCustomerByName(customerName);
        softAssert.assertTrue(Chat.isCustomerSearchResultDisplayed(customerName),"search error");
        Chat.clickOnCustomerChat(customerName);
        Chat.sendDistributorMessage(distributorMessage);
        softAssert.assertEquals(Chat.getLastMessageDisplayed(), distributorMessage,"messaging sending error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        softAssert.assertTrue(Customer.isChatIconDisplay(customerId),"Chat icon display error");
        Customer.clickChatIcon(customerId);
        Chat.sendDistributorMessage(distributorMessage);
        softAssert.assertEquals(Chat.getLastMessageDisplayed(), distributorMessage,"messaging sending error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        softAssert.assertTrue(Customer.isChatButtonDisplayed(),"chat button display in customer profile error");
        Customer.clickChatButtonInCustomerProfile();
        Chat.sendDistributorMessage(distributorMessage);
        softAssert.assertEquals(Chat.getLastMessageDisplayed(), distributorMessage,"messaging sending error");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
