package com.cutanddry.qa.tests.performance;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.openqa.selenium.Cookie;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeleniumLighthouseTest extends TestBase {
    static User user;
    SoftAssert softAssert;
    static String customerId = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test
    public void OrderGuideUiProfiling() throws InterruptedException, IOException {
        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Search error");

        // Start profiling for Order Guide at selenium
        long startTime = System.nanoTime();
        Customer.clickOnOrderGuide(customerId);
        Customer.scrollBottomOfPage();
        long endTime = System.nanoTime();
        long durationInMs = (endTime - startTime) / 1_000_000;
        System.out.println("Total Load Time for Order Guide: " + durationInMs + " ms");

        // Assertion (optional, fails if load time exceeds 10 sec)
        softAssert.assertTrue(durationInMs < 10000, "Order Guide load time is too high!");

        // Get authentication cookies
        StringBuilder cookieHeader = new StringBuilder();
        for (Cookie cookie : driver.manage().getCookies()) {
            cookieHeader.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
        }
        String cookies = cookieHeader.toString().trim();
        runLighthouseNodeScript(driver.getCurrentUrl(), cookies);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

    // Method to invoke the Node.js Lighthouse script using ProcessBuilder
    private static void runLighthouseNodeScript(String url, String cookies) throws IOException {
        String scriptPath = System.getProperty("user.dir") + "/src/test/java/com/cutanddry/qa/tests/performance/runLighthouse.js";  // Set the correct path to the Node.js script
        ProcessBuilder processBuilder = new ProcessBuilder("node", scriptPath, url, cookies);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Node Output: " + line);
            }
        } catch (IOException e) {
            System.err.println("Error reading Node.js script output: " + e.getMessage());
        }

        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Lighthouse script execution failed with exit code: " + exitCode);
            } else {
                System.out.println("Lighthouse script executed successfully.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Lighthouse process was interrupted.");
        }

    }
}