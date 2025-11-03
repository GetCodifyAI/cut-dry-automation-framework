package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerProfileData;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheItemPricesAreNotVisibleInTheEditOrderGuideItemsSectionWhenPriceVisibilityIsHiddenTest extends TestBase{
    static User user;
    String customerID = "16672";
    static String itemName,itemPriceStr;
    static String searchItemCode = "01700";
    static double itemPrice;
    static String statusVisible = "Visible";
    static String statusHidden = "Hidden";
    static String OperatorName = "496044202";
    String DistributorName = CustomerProfileData.DISTRIBUTOR_NAME_IFC;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2032")
    public void VerifyTheItemPricesAreNotVisibleInTheEditOrderGuideItemsSectionWhenPriceVisibilityIsHidden() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID),"search error");
        Customer.SelectCustomer(customerID);
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        Customer.clickCusAccountVisibilityOption();
        Customer.clickCusAccountVisibilityDropdown();
        Customer.selectCusAccountVisibleOption();
        Customer.editStatusPriceVisibility(statusHidden);

        Customer.clickOnOrderGuideInCustomerProfile();

        // Add the product via Order Guide
        Customer.searchItemOnOrderGuide(searchItemCode);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        itemPriceStr = String.valueOf(Customer.getActiveItemPriceFirstRow());
        Customer.deleteSearchField();
        Thread.sleep(5000);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        softAssert.assertTrue(Customer.isEditOGPriceDisplay(itemName,itemPriceStr),"price display error in edit order guide");


        Login.navigateToLoginAs();
        Login.logInToOperatorAsWhiteLabel(OperatorName);
        Customer.clickOnOrderSection();
        Customer.goToEdit();
        Thread.sleep(3000);
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        softAssert.assertFalse(Customer.isEditOGPriceDisplay(itemName,itemPriceStr),"price display error in edit order guide");
        softAssert.assertAll();
    }

  @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
