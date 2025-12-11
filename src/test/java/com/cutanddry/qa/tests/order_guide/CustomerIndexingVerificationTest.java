package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class CustomerIndexingVerificationTest extends TestBase {
    static User user;

    @BeforeClass
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login failed");
    }

    @Test(dataProvider = "customerData")
    public void DistributorInitialSetUp(String testData) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Reporter.getCurrentTestResult().setAttribute("testData", testData);
        softAssert.assertTrue(Login.isUserExistInLoginAs(testData),"Customer not found: " + testData);
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        closeAllBrowsersAtOnce();
    }

    @DataProvider(name = "customerData")
    public Object[][] getCustomerData() {
        return new Object[][]{
                {"Ayendra IFC WL"},
                {"Amir IFC WL"},
                {"274855811"},
                {"Aselabookkeeper4"},
                {"Brandon IFC Cut+Dry Agent"},
                {"32404837"},
                {"1000030"},
                {"katdmejia2@gmail.com"},
                {"(explorer) - Bermuda Biological Station"},
                {"450656323"},
                {"manager@cutanddry.com"},
                {"employee@cutanddry.com"},
                {"377702895"},
                {"flattcobr@gmail"},
                {"32349239"},
                {"Tessa Dispatcher"},
                {"Isuru Test AR"},
                {"103430764"},
                {"Brandon IFC White"},
                {"sales@jordanpaige.com"},
                {"App Testers"},
                {"201465141"},
                {"Jonathan Allen"},
                {"apptesters@test.com"},
                {"226431917"},
                {"280815154"},
                {"112591379"},
                {"102611801"},
                {"81058255"},
                {"432392672"},
                {"176626422"},
                {"Jim Beacom Jr"},
                {"438342864"},
                {"294962910"},
                {"372460856"},
                {"61714031"},
                {"176617582"},
                {"286475302"},
                {"309075134"},
                {"177931291"},
                {"Chef Scott Koranda"},
                {"286512123"},
                {"517030659"},
                {"65436339"},
                {"137524915"},
                {"70351601"},
                {"487417810"},
                {"229366152"},
                {"304633577"},
                {"278357516"},
                {"324221501"},
                {"155947343"},
                {"236140414"},
                {"384083841"},
                {"177850933"},
                {"46505655"},
                {"61233053"},
                {"189993560"},
                {"314254109"},
                {"105556864"},
                {"209223241"},
                {"235564457"},
                {"189993560"},
                {"235564457"},
                {"592155783"},
                {"569046895"},
                {"52557616"},
                {"496044202"},
                {"308247072"},
                {"381410447"},
                {"487310251"},
                {"381410447"},
                {"381410447"},
                {"274671749"},
                {"52068374"},
                {"62459183"},
                {"45808864"},
                {"475793657"},
                {"196795934"},
                {"541616381"}

        };
    }
}
