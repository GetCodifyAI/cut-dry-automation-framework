package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifySearchFeatureAfterEditingItemNameTest extends TestBase {
    static User user;
    static String customerId = CustomerData.CUSTOMER_CODE2;
    static String itemCode = "01700";
    static String editedNameSuffix = " Edited";
    static String originalItemName;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2770")
    public void verifySearchFeatureAfterEditingItemName() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "customer section not displayed");
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "customer not found");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(), "Edit Order Guide Items page not displayed");
        Customer.editItem(itemCode);
        softAssert.assertTrue(Customer.isEditItemPopupDisplayed(), "Edit Item popup not displayed");
        originalItemName = Customer.getEditItemNameInputValue();
        String editedItemName = originalItemName + editedNameSuffix;
        Customer.enterEditItemName(editedItemName);
        Customer.saveItem();
        Customer.closeEditor();
        Customer.searchItemOnOrderGuide(editedItemName);
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(editedItemName), "Edited item name not found in search results");

        Customer.clearSearchField();
        Customer.goToEdit();
        Customer.editItem(itemCode);
        Customer.enterEditItemName(originalItemName);
        Customer.saveItem();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}