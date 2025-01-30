package com.cutanddry.qa.tests.sections;

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

public class VerifyEditingOrderGuideSectionsTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String sectionName = "dairy";
    static String newSectionName = "disposables";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-281")
    public void verifyEditingOrderGuideSections() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"Error in searching customer by code");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.expandMoreOptionsDropdown();
        Customer.addSection();
//        Customer.clickClose();
        softAssert.assertTrue(Customer.isAddSectionPopupDisplayed()," add section popup error");
        Customer.typeSectionName(sectionName);
        Customer.clickOnSave();
        softAssert.assertTrue(Customer.isAddedSectionDisplayed(sectionName)," add section error");

        Customer.editSection(sectionName);
        softAssert.assertTrue(Customer.isEditSectionPopupDisplayed()," edit section popup error");
        Customer.typeSectionName(newSectionName);
        Customer.clickOnSave();
        softAssert.assertTrue(Customer.isAddedSectionDisplayed(newSectionName)," add section error");
        Customer.editSection(newSectionName);
        Customer.clickOnDelete();
        softAssert.assertTrue(Customer.isAreYouSurePopupDisplayed(),"are you sure popup error");
        Customer.clickOnYes();
        softAssert.assertFalse(Customer.isAddedSectionDisplayed(newSectionName),"delete section error");

//        Customer.clickOnBack();
//        Customer.clickSortOptionsDropdown();
//        Customer.selectItemCategoriesSort();
//        softAssert.assertTrue(Customer.isSectionDisplayed(newSectionName)," add section display error");
//        Customer.clickSortOptionsDropdown();
//        Customer.selectCustomOrderSort();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
