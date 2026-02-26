package com.cutanddry.qa.tests.order_guide;
import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PurchaseHistoryData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class VerifyTheFunctionalityOfSortItemsByDropDownInSimpleListViewTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = "Affiliated Foods";
    static String CompanyName = "Affiliated Foods";
    static String customerId = "05438";
    static String simpleListView = "Enabled on DP Portal & Operator App";

    List<String> expectedSortOptions = Arrays.asList(
            "Item Code",
            "UPC",
            "Description",
            "Category",
            "Pack Size",
            "Last Ordered",
            "Brand"
    );

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1525")
    public void VerifyTheFunctionalityOfSortItemsByDropDownInSimpleListView() throws InterruptedException {
        softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.clickOnSimpleListViewDropdown(simpleListView);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(), "change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(), "simple list view section not display");

        for (String sortOption : expectedSortOptions) {
            softAssert.assertTrue(Customer.isSimpleListSorted(sortOption), "Sorting is not properly working");
        }

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
