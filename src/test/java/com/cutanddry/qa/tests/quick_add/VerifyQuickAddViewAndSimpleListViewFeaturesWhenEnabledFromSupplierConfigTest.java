package com.cutanddry.qa.tests.quick_add;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.OfflineOrderingData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyQuickAddViewAndSimpleListViewFeaturesWhenEnabledFromSupplierConfigTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemQuantity = "1" ;
    static String itemCode = "02345";
    static String itemDescription = "Lemons 115 CT";
    static String searchItemCode = "01700";
    static String DistributorName = OfflineOrderingData.DISTRIBUTOR_NAME;
    static String CompanyName = OfflineOrderingData.SUPPLIER_NAME;
    static String status ="Enabled only on DP Portal";
    static String simpleListView = "Enabled on DP Portal & Operator App";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1520")
    public void VerifyQuickAddViewAndSimpleListViewFeaturesWhenEnabledFromSupplierConfig() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.clickOnQuickAddViewDropDown(status);
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
        Customer.SelectTestAutomationOrderGuide();

        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isQuickAddOptionDisplay(),"quick add option display error");
        Customer.clickQuickAdd();
        softAssert.assertTrue(Customer.isQuickAddViewDisplay(),"quick add view page display error");
        Customer.enterItemCode(itemCode);
        Customer.enterItemQuantity(itemQuantity);
        Customer.clickVerifyItem();
        Customer.clickClosePopUp();
        Customer.clickSaveAndReview();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemDescription, "The item selected by the user is different from what is shown on the order review page.");
        Catalog.clickBack();

        softAssert.assertTrue(Customer.isQuickAddViewDisplay(),"quick add view page display error");
        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(),"simple list view section not display");
        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyCustom(2);
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        softAssert.assertEquals(Customer.getItemQuantityReviewPage(itemCode),"1","item quantity not equal");
        softAssert.assertEquals(Customer.getItemQuantityReviewPage(searchItemCode),"2","item quantity not equal");

        Catalog.clickBack();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(),"simple list view section not display");
        Customer.clearSearchField();
        Customer.searchItemOnOrderGuide(itemCode);
        softAssert.assertEquals(Customer.getItemQuantitySimpleListView(itemCode),"1","item quantity not equal simple View");
        Customer.searchItemOnOrderGuide(searchItemCode);
        softAssert.assertEquals(Customer.getItemQuantitySimpleListView(searchItemCode),"2","item quantity not equal simple View");
        Customer.expandMoreOptionsDropdown();
        Customer.clickOrderGuideView();
        Assert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        softAssert.assertEquals(Customer.getItemQuantityReviewPage(itemCode),"1","item quantity not equal OG");
        softAssert.assertEquals(Customer.getItemQuantityReviewPage(searchItemCode),"2","item quantity not equal OG");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
