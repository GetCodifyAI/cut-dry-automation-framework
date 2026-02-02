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
import java.util.Map;
import java.util.LinkedHashMap;


public class VerifyThatTheSortItemsByDropDownIsAvailableInTheSimpleListViewTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName ="Independent Foods Co";
    static String CompanyName = "Independent Foods Co";
    static String customerId =PurchaseHistoryData.CUSTOMER_ID_IFC;
    static String simpleListView = "Enabled on DP Portal & Operator App";


    Map<String, String> sortOptionsMap = new LinkedHashMap<>() {{
        put("Item Code", "00036");
        put("UPC", "052100324050");
        put("Brand", "Brakebush");
        put("Description", "Allspice Ground*");
        put("Pack Size", "1");
    }};




    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1524")
    public void VerifyThatTheSortItemsByDropDownIsAvailableInTheSimpleListView() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.clickOnSimpleListViewDropdown(simpleListView);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(),"simple list view section not display");
        Customer.clickSortOptionsDropdown();
        for (Map.Entry<String, String> entry : sortOptionsMap.entrySet()) {
            String sortOption = entry.getKey();
            String expectedSortResult = entry.getValue();

            Customer.clickSortOptionsOG(sortOption);
            softAssert.assertTrue(Customer.isSortOptionDisplay(expectedSortResult),
                    "Sort option: " + sortOption + " - Expected result: " + expectedSortResult + " is displayed");
        }


        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
