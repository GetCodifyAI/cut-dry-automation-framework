package com.cutanddry.qa.base;

import com.cutanddry.qa.common.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase {
    private static final Logger LOGGER = Logger.getLogger(TestBase.class.getName());
    protected static WebDriver driver;
    protected static JavascriptExecutor js;
    protected static KeywordBase distributorUI;
    protected static WebDriverWait wait;

    // Initialization method to set up the WebDriver and other components
    public static void initialization() {
        if (driver == null) {  // Ensure WebDriver is initialized only once
            if (Constants.BROWSER_NAME.equalsIgnoreCase("chrome")) {
                try {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    if (Constants.RUN_HEADLESS) {
                        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
                    }

                    // Set custom download directory
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("download.default_directory", System.getProperty("user.dir") + "/Downloads");
                    prefs.put("download.prompt_for_download", false);    // Disable the download prompt
                    prefs.put("profile.default_content_settings.popups", 0);
                    prefs.put("safebrowsing.enabled", "true");            // Enable safe browsing
                    chromeOptions.setExperimentalOption("prefs", prefs);

                    driver = new ChromeDriver(chromeOptions);
                    js = (JavascriptExecutor) driver;
                    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                    driver.get(Constants.MAIN_URL);
                    distributorUI = new KeywordBase(driver, wait);  // Initialize KeywordBase here

                    LOGGER.info("WebDriver initialized and navigated to the URL: " + Constants.MAIN_URL);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Failed to initialize WebDriver", e);
                }
            } else {
                LOGGER.warning("Unsupported browser or WebDriver is already initialized.");
            }
        }
    }

    // Method to close the browser and clean up resources
    public static void closeAllBrowsers() {
        if (driver != null) {
            driver.close();
            driver = null;  // Reset the driver to allow re-initialization in future tests
            LOGGER.info("All browsers are closed.");
        }
    }

    // Method to close the browser and clean up resources
    public static void closeAllBrowsersAtOnce() {
        if (driver != null) {
            driver.quit();
            driver = null;  // Reset the driver to allow re-initialization in future tests
            LOGGER.info("All browsers are closed.");
        }
    }

    public static void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String testName = result.getName();
            distributorUI.captureScreenshot(testName);
        }
    }

    //Method to close multiple tabs
    public static void closeMultipleTabs() {
        if (driver != null) {
            // Get all the window handles
            Set<String> windowHandles = driver.getWindowHandles();

            // Iterate over all the open windows and close them
            for (String windowHandle : windowHandles) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }

            driver = null;  // Reset the driver to allow re-initialization in future tests
            LOGGER.info("All browsers are closed.");
        }
    }

    public static int generateDynamicValue() {
        // Generate a random number between 1 and 100 (or any range you want)
        return (int) (Math.random() * 1000) + 1;
    }

    public static String generateUTCCurrentDateFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = new Date();
        return formatter.format(today);
    }

}
