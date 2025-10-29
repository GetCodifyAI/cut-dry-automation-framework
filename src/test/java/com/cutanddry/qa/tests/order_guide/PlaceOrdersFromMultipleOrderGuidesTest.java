package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class PlaceOrdersFromMultipleOrderGuidesTest extends TestBase {
    static User user;
    static String customerCode = "21259";
    static String newOrderGuideName = "TestOG_Automation_" + System.currentTimeMillis();
    static String existingOrderGuideName = "CustomOG";
    static String filePath = System.getProperty("user.dir") + "/src/test/resources/excelFiles/Test_Order_Guide_Automation.xlsx";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1235")
    public void verifyPlaceOrdersFromMultipleOrderGuides() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Login error - user not navigated to distributor dashboard");

        Dashboard.navigateToCustomers();

        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), 
            "Failed to navigate to customers section - 'Customers' title not displayed");

        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), 
            "Customer with code " + customerCode + " not found in search results");

        Customer.clickOnOrderGuide(customerCode);
        softAssert.assertTrue(Customer.isOrderGuideTextDisplayed(), 
            "Order Guide not displayed after clicking Order Guide button");

        Customer.clickOnMoreOptions();
        Customer.clickOnCreate();

        Customer.typeOrderGuideName(newOrderGuideName);
        Customer.clickSubmitOrderGuide();

        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(), 
            "Edit Order Guide screen not displayed after creating new order guide");
        Customer.clickUploadAList();
        Customer.giveFilePath(filePath);
        Customer.clickNext();
        Customer.clickConfirm();

        softAssert.assertTrue(Customer.isOrderGuideCreateSuccessPopupDisplayed(), 
            "Order guide upload success popup not displayed");
        Customer.clickOK();

        Customer.closeEditor();
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(newOrderGuideName), 
            "Newly created order guide '" + newOrderGuideName + "' not displayed in dropdown");

        Customer.selectNewlyCreatedOrderGuide(newOrderGuideName);
        Thread.sleep(2000); // Wait for OG to load
        String itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, 
            "Product quantity not increased in newly created order guide");

        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), 
            "Review order page not displayed after checkout");
        Customer.submitOrder();

        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), 
            "Thank you for your order popup not displayed - order submission failed from new OG");

        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrders(), 
            "Failed to navigate to Order History");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerCode);
        Customer.clickOnOrderGuide(customerCode);
        softAssert.assertTrue(Customer.isOrderGuideTextDisplayed(), 
            "Order Guide not displayed when navigating back");

        Customer.selectNewlyCreatedOrderGuide(existingOrderGuideName);
        Thread.sleep(2000); // Wait for OG to load
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(existingOrderGuideName), 
            "Previously existing order guide '" + existingOrderGuideName + "' not displayed");

        String itemName2 = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0, 
            "Product quantity not increased in existing order guide");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), 
            "Review order page not displayed after checkout from existing OG");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), 
            "Thank you for your order popup not displayed - order submission failed from existing OG");

        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrders(), 
            "Failed to navigate to Order History after second order");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
