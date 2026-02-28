package com.cutanddry.qa.tests.parent_child_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.ParentChildOGData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class VerifyThatTheImportOrderGuideWillReflectForTheChildAccountTest extends TestBase {
    static User user;
    static String DP = ParentChildOGData.DISTRIBUTOR_INDIANHEAD;
    static String parentCustomerId = ParentChildOGData.CUSTOMER_ID_INDIANHEAD;
    static String childCustomerId = ParentChildOGData.CUSTOMER_ID_INDIANHEAD_2;
    static String status = "Parent Account";
    static String OrderGuideName = "TestOG" + "_" + ThreadLocalRandom.current().nextInt(100000, 1_000_000);
    static String childSettingMessage = "Child account settings updated successfully";
    static String formID;
    static String itemName1 = ParentChildOGData.OG_ITEM_NAME1;
    static String itemName2 = ParentChildOGData.OG_ITEM_NAME2;
    static String itemName3 = ParentChildOGData.OG_ITEM_NAME3;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1589")
    public void VerifyThatTheImportOrderGuideWillReflectForTheChildAccount() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());

        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(parentCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(parentCustomerId),"search error");
        Customer.clickOnOrderGuideParentChild(parentCustomerId);
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(),"user has navigated to the Order Guide");

        Customer.goToCreatePopup();
        Customer.createOrderGuide(OrderGuideName);
        Customer.createOrderByUploading();
        Customer.uploadFile(Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("excelFiles/IFD_OG_FILE.xlsx")).toURI()).toString());
        softAssert.assertTrue(Customer.isOrderGuideCreateSuccessPopupDisplayed(),"order guide create success popup not displayed");
        Customer.clickOK();
        Customer.closeEditor();
        Customer.refreshCustomersPage();
        formID = Customer.getOrderGuideFormID();
        System.out.println(formID);

        Login.switchIntoNewTab();
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToTaskManagementTab();
        InternalTools.runParentChildTask(formID);
        softAssert.assertTrue(InternalTools.isPCTaskAttemptedDisplayed(),"Parent child task not run successfully");
        Customer.clickOK();
        Login.closeCurrentTabAndSwitchBack();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(parentCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(parentCustomerId),"search error");
        Customer.SelectCustomer(parentCustomerId);
        softAssert.assertTrue(Customer.isLinkedAccountDisplayed(),"linked account section not displayed");
        softAssert.assertTrue(Customer.isAccountStatusDisplayed(status),"parent account status not displayed");
        Customer.clickEditChildAccount();
        softAssert.assertTrue(Customer.isManageChildAccountPopUpDisplayed(),"manage child account pop up not displayed");

        softAssert.assertTrue(Customer.isOrderGuideAdded(childCustomerId, OrderGuideName),"order guide not added to child account dropdown");
        Customer.selectNewlyAddedOrderGuide(childCustomerId, OrderGuideName);
        Orders.clickSaveButton();
        softAssert.assertTrue(Customer.isChildSettingUpdated(childSettingMessage),"child setting not updated");
        Customer.clickOK();

        Login.switchIntoNewTab();
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToTaskManagementTab();
        InternalTools.clickRunLocallyOnParentChildRelationshipTask();
        softAssert.assertTrue(InternalTools.isPCTaskAttemptedDisplayed(),"Parent child task not run successfully");
        Customer.clickOK();
        Login.closeCurrentTabAndSwitchBack();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(childCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(childCustomerId),"search error for child customer");
        Customer.clickOnOrderGuideParentChild(childCustomerId);
        Customer.clickOGDropdown();
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(OrderGuideName),"newly created order guide not displayed in child account dropdown");
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
        softAssert.assertTrue(Customer.isAddedItemDisplayed(itemName1), "Item not display in the order guide");
        softAssert.assertTrue(Customer.isAddedItemDisplayed(itemName2), "Item not display in the order guide");
        softAssert.assertTrue(Customer.isAddedItemDisplayed(itemName3), "Item not display in the order guide");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(parentCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(parentCustomerId),"search error");
        Customer.clickOnOrderGuideParentChild(parentCustomerId);
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(),"user has navigated to the Order Guide");
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnDeleteOrderGuide();
        softAssert.assertTrue(Orders.isAreYouSurePopUpDisplayed(),"Are you sure pop up not displayed");
        Orders.clickYes();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
