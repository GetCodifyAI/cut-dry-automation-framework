package com.cutanddry.qa.tests.archive;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyHidingAnItemFromTheOrderGuideTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemCode = "1409";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-288")
    public void verifyHidingAnItemFromTheOrderGuide() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"Error in searching customer by code");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.editItem(itemCode);
        softAssert.assertTrue(Customer.isEditItemPopupDisplayed()," edit item popup error");
        Customer.clickOnHideItem();
        softAssert.assertTrue(Customer.isAreYouSurePopupDisplayed(),"are you sure popup error");
        Customer.clickOnYes();
        Customer.closeEditor();
        Customer.searchItemOnOrderGuide(itemCode);
        softAssert.assertNull(Customer.getItemNameFirstRow(),"item hide error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
