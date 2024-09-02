package com.cutanddry.qa.tests.poc_api_test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class LoginAPITest   {

    @Test
    public void loginAPI(){
        // Define the base URI for the GraphQL endpoint
        RestAssured.baseURI = "https://supplier-uat.staging.cutanddry.com/GraphQLController";

        // Define the GraphQL mutation query as a JSON string
        String mutation = "{\n" +
                "  \"query\": \"mutation LoginMutation($emailOrPhoneNumber: String!, $password: String!, $token: String!) { " +
                "LoginMutation(emailOrPhoneNumber: $emailOrPhoneNumber, password: $password, token: $token) { " +
                "id name email createdTimestamp phone picture canEdit employee isEmployeeEmail role hasPassword passwordStage supplierPortalRole customerAccessRestricted supplierInvitationStatus menuOptions { name nameMobile uri spa platform dataTip __typename } visibleLocations { id name archived __typename } company { id name whiteLabel whiteLabelVendor { id verifiedvendor { id logoURL logoURLAlternate supplierportalvendordata { disableAddContactButton disableEditContactInformation disableEditNotification modules disableEditRestaurantInformation disableEditLocationInformation __typename } __typename } __typename } activeVendors { id verifiedvendor { id logoURL logoURLAlternate __typename } __typename } verifiedvendor { id __typename } invitedVendor { id __typename } vendorcampaign { id logoURL logoURLAlternate __typename } __typename } companies { id name __typename } additionalCompanies { id name isVendorCompany whiteLabel whiteLabelVendor { id verifiedvendor { id __typename } __typename } __typename } authUser { id appPermissions employee vendorUser hasPassword email inviteCampaign __typename } authRedirect vendorUser picture lastlocation { id name archived __typename } paymentOptions { name enabled __typename } timezoneCode timezoneForTrack timezoneString __typename } }\",\n" +
                "  \"variables\": {\n" +
                "    \"emailOrPhoneNumber\": \"415-505-5531\",\n" +
                "    \"password\": \"NovaN@123\",\n" +
                "    \"token\": \"03AFcWeA6nTQbhmlsY9MgLARN2XIWDabBU55vAJAH87k4F21qphgrHmbUC78ji5l8TUeN6tj4f98nDj2vKnXiwSx08yuY1az5vAaTn92P5DSTu-AP05AXVlaxbmCVZyYpetQRl_-ztn1PZMU_QTxtkRtJxq9V_kxHfI-qfGmNJ-gaPGoj9MLeZqcyvWTnAaocZpdT4XqzkZsa11i8UFNmiE50VwD2f0VUezK9lYCIHJE8CRaJ6Pt4z47Mcyl0DjdOZdSNrjOrB41TZHQONfddDjpiX9UFmO0aPDiL8D4rcWjBpQz6lX3dZr0BA3NRFloa8E3w5bS8C2x_VLUUVnJlkN8lOJYR6M6Xzbyx4vKMobEX4csgjiIQkcj-ElFEc-TbxUXSgcHWgTQ2uuBxuP74AquUyzkFHPxYt9Ey_3bLlAZ_iVmSSJOz7kPEnsqSXx6JvrT73cAOX-QE3czk65HMRzHYLUMArWHW3_cIJm8DVREfZzADGzepqnE2ec10c82Rd4Y466pfCXpF1tEzFgIYqtC2KZ7OWB5w5Xk6kesUeH2p7iBdSa8hQ2SUv7hvC6P_LZY60_POR6Tsi4mp3Ho9XRaVMtAMZA1v317kB2AjGZBYGNhPNRQdsriI8me6dzpfCEI-6MNRh626Mi4wxbqz5hEdJnqgvWr997hmtF9WMwWg3QfAezK-72aPzOFFT7XZLv6UaBwqtuLiE9vvlXFyC0MxwzKOQd0N0pi1igUnvtma2TNAwVAE8nqVFe80tigm0aaAYXIl9TP6Rqb9MBV_gSOZBf3E3FkhOzjw7dS1pXasdjAkH1otxRqVluvUy_H03HoyScPJJ0IhNeY9wNC5eIFMlsvW5iEFBGiuBzH_KLCq74GYs9n4zCyBbX3zUqbZ9LKTom18gGfUHidnZj-B-h_sHwmR7OlyephHmIhS4GHLe-DrOFwaNOkukVizbEmQa8CrPm_9Z1S1zZOeZiLWMjPLpY8zaOMeg8vPfKjZ465nROv_4YwN7Jl8rE1QvSJMYJeLH7yM5DkGHQL-SvJE-5r_gPY31OpE2ir2vE8sH6_wqOALKzvCjTEhDrahY--oMDdofI7zrfKHRXAqub9G5IHgkwS4ISn22Z7ui7OwAz-lTcdieuvJC1yM5mAqd-BfT44NfZUn1rYdY0CskWrkgjA8ZN_WNpD5E_SWXdgGfh1Nf9HjVQCSNlK6GpoFhpPhlcwbm7MEncscJnKOwogpqlcJkUhBhBDIS_mAnHodftrQdQF8Eu2cEKUUQh9rVWdcbHOJMsLH6612ml9h_W20SMsV5bWxh-lp-qhXGR3yCP_76uUZvosi28Apq2h2zweTvNojrPaw7lte2LFUFUpa30nIzxW0rR-bazcS9trtE5lwltwSU9A3Hg1nS4IOIVVN5LeAD0Ggo1cDyQFAPAwSVhCsor_wnC1cEknrJfmY6DRllRRm2tQWXR6ABpoPQoVs_StUjxjiJC-Ch9sCwbnCa2OWcC0pkKc-tIYYiQ-kPywHI2wrMud1yNPoZYShV9HeDXrHLSXY29Vxao4ButkFyZFvSlRKTP_QACbPDvRFiqi6cLJVLvTpucVcQOn_b_1sq7sdHuvoJUySa9I4t2OA3FqAKW3YGVp8RMUn2LYklzahQDwFvf0NyJpD84CLifPid4HnzETbVotVnGIZWkPIkY9kXHEuPQGn8JgqTdbRNaLovdEJJohsVPYQfQsbtTWHLbgopVosuHJcxiL8DKUjJY3Q1gieLdaVBipgArsZUy_nAbKXn-xivgC6OeTNAm3UU0peGWb4vwB2OSQHuNop0LQMXtEKFarwn4YvCt7JAm0GDxHluIzzTuYnJM7AN9wFGojLxdaXtibRj4i8mmxG8SJClE1j5M-4RbQwVt5wFekJwSMDxIi7Cgwbtw35L_EXi7wkmO4vGHMLfgpjyUM2IVF7TmVlJp5pHzciq-g-pPInkcI9i1jtlYyA_7_zA4pl73aw1XwY6sy0JWjYL53gOKuI_1mXrZ1p33Q\"\n" +
                "  }\n" +
                "}";

        // Perform the POST request
        Response response = given()
                .header("Content-Type", "application/json")
                .body(mutation)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("data.LoginMutation.name", equalTo("mashan"))  // Example assertion
                .body("data.LoginMutation.email", equalTo("mashan@cutanddry.com"))  // Example assertion
                .extract().response();

        // Print the response for debugging
        System.out.println("Response: " + response.asString());
    }
}
