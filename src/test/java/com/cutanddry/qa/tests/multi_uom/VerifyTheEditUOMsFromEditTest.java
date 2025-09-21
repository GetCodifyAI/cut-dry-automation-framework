package com.cutanddry.qa.tests.multi_uom;

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

public class VerifyTheEditUOMsFromEditTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE;
    static String distributor = CustomerData.DISTRIBUTOR_NAME;
    static String itemCode = CustomerData.ITEM_CODE;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-1047")
    public void VerifyTheEditUOMsFromEdit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.editItem(itemCode);
        softAssert.assertTrue(Customer.isEditItemPopupDisplayed()," edit item popup error");
        Customer.clickOnCaseUnit();
        Customer.saveItem();
        Customer.closeEditor();
        Customer.searchItemOnOrderGuide(itemCode);
        softAssert.assertFalse(Customer.isMultiUomDropDownExistDisplayed(itemCode),"multi uom displayed");
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.editItem(itemCode);
        softAssert.assertTrue(Customer.isEditItemPopupDisplayed()," edit item popup error");
        Customer.clickOnCaseUnit();
        Customer.saveItem();
        Customer.closeEditor();
        Customer.searchItemOnOrderGuide(itemCode);
        softAssert.assertTrue(Customer.isMultiUomDropDownExistDisplayed(itemCode),"multi uom not displayed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
