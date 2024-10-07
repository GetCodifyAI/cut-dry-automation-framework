package com.cutanddry.qa.tests.customer_groups;

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


public class VerifyCustomerGroupIsAssignedWhenSelectedTest extends TestBase {
    static User user;
    String DistributerName = "47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String CustomerCode = "16579";
    String CustomerGroupName = "NEWGroup01";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-240")
    public void VerifyCustomerGroupIsAssignedWhenSelected() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerGroupOptionAvailable(),"Error in Displaying Customer Group Text");
        softAssert.assertTrue(Customer.isCustomerGroupOptinAvailable(),"Error in Displaying Customer Group Text");
        softAssert.assertTrue(Customer.isCustomerGroupEditBtnAvailable(),"Customer Group Edit Btn Not Available");
        Customer.editCustomerGroups();
        Customer.creatCustomerGroup(CustomerGroupName);
        Customer.customerGroupSave();
        softAssert.assertTrue(Customer.isCustomerGroupNameDisplayed(CustomerGroupName),"Error in Displaying Customer Group Text");
        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){

        //clearing the Created Customer Groups
        Customer.editCustomerGroups();
        Customer.clearAllCustomerGroups();
        Customer.customerGroupSave();
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
