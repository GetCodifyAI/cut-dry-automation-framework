package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
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

public class VerifySubstituteItemsNotDisplayedIfTurnedOffCustomerProfileTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    String DistributorName = CustomerData.DISTRIBUTOR_NAME_IFC;
    String CustomerCode = CustomerData.CUSTOMER_CODE2;
    String disabledSub = CustomerData.SUB_STATUS_DISABLE;
    String enabledSub = CustomerData.SUB_STATUS_ENABLE;
    String searchItemCode = CustomerData.SEARCH_ITEM_CODE;
    String searchItemName = CustomerData.SEARCH_ITEM_NAME;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-727")
    public void VerifySubstituteItemsNotDisplayedIfTurnedOffCustomerProfile() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.SelectCustomer(CustomerCode);
        Customer.editStatusSubstitutionsAccess(disabledSub);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(CustomerCode);

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(searchItemName).contains(searchItemName.toLowerCase()), "item not found");
        Customer.addItemToCartCatalog(searchItemName);
        Customer.clickOnPlusIconInCatalogPDP(1, searchItemName);
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertFalse(Customer.isSubstitutionTextDisplayed(),"Substitution add error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.SelectCustomer(CustomerCode);
        Customer.editStatusSubstitutionsAccess(enabledSub);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
