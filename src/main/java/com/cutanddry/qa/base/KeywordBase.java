package com.cutanddry.qa.base;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.List;

import java.time.format.DateTimeFormatter;


@SuppressWarnings("UnusedReturnValue")
public class KeywordBase {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private static final Logger logger = LoggerFactory.getLogger(KeywordBase.class);
    private String originalTab;
    private String secondTab;

    public KeywordBase(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);
    }

    // Open a URL
    public KeywordBase openURL(String url) {
        try {
            driver.get(url);
            logger.info("Opened URL: {}", url);
        } catch (Exception e) {
            logger.error("Failed to open URL: {}", url, e);
        }
        return this;
    }

    // Click on an element using By object
    public KeywordBase click(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
            logger.info("Clicked on element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", by, e);
        }
        return this;
    }

    // Click on an element using By object with Actions class
    public KeywordBase clickAction(By by) {
        try {
            Actions actions = new Actions(driver);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            actions.moveToElement(element).click().perform();
            logger.info("Clicked on element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", by, e);
        }
        return this;
    }

    // Click on an element using By object
    public KeywordBase clickWithFallback(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
            logger.info("Clicked on element: {}", by);
        } catch (Exception e1) {
            logger.warn("Standard click failed on element: {}. Attempting JavaScript click...", by, e1);
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                logger.info("Clicked using JavaScript on element: {}", by);
            } catch (Exception e2) {
                logger.error("Failed to click on element: {} using both standard and JavaScript methods.", by, e2);
            }
        }
        return this;
    }

    // Click on an element using By object with fallback to JavaScript click, scrolling, and hover
    public KeywordBase clickWithScrollAndHover(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
            logger.info("Clicked on element: {}", by);
        } catch (Exception e1) {
            logger.warn("Standard click failed on element: {}. Attempting hover and JavaScript click...", by, e1);
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

                Actions actions = new Actions(driver);
                actions.moveToElement(element).perform();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                logger.info("Clicked using JavaScript on element: {}", by);
            } catch (Exception e2) {
                logger.error("Failed to click on element: {} using standard, hover, and JavaScript methods.", by, e2);
            }
        }
        return this;
    }

    //Get row count in a table
    public int getRowCount(By tableXPath) {
        int rowCount = 0;
        try {
            // Locate the table and its rows
            WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(tableXPath));
            List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
            rowCount = rows.size();
            logger.info("Number of rows in table {}: {}", tableXPath, rowCount);
        } catch (Exception e) {
            logger.error("Failed to get row count for table: {}", tableXPath, e);
        }
        return rowCount;
    }


    // Send keys to an element using By object
    public KeywordBase sendKeys(By by, String data) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element.clear(); // Clear the field before typing
            element.sendKeys(data);
            logger.info("Sent keys to element: {} with data: {}", by, data);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {} with data: {}", by, data, e);
        }
        return this;
    }

    // Send keys to an element character by character using By object
    public KeywordBase sendKeysCharByChar(By by, String data) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element.clear(); // Clear the field before typing
            for (char ch : data.toCharArray()) {
                element.sendKeys(String.valueOf(ch)); // Send each character one by one
                Thread.sleep(100);
            }
            logger.info("Sent keys to element character by character: {} with data: {}", by, data);
        } catch (Exception e) {
            logger.error("Failed to send keys character by character to element: {} with data: {}", by, data, e);
        }
        return this;
    }

    public KeywordBase sendKeysToHiddenElements(By by, String data) {
        try {
            WebElement element =driver.findElement(by);
            element.sendKeys(data);
            logger.info("Sent keys to hidden element: {} with data: {}", by, data);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {} with data: {}", by, data, e);
        }
        return this;
    }
    // Send keys to an element using the id
    public KeywordBase sendKeysToField(String id_element, String data) {
        try {
            Actions actions = new Actions(driver);
            WebElement element = driver.findElement(By.id(id_element));
            actions.moveToElement(element).click().sendKeys(data).perform();
            logger.info("Sent keys to field: {} with data: {}", id_element, data);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {} with data: {}", id_element, data, e);
        }
        return this;
    }

    // Send keys to an element using By object and press enter
    public KeywordBase sendKeysAndEnter(By by, String data) {
        try {
            Actions actions = new Actions(driver);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
//            Thread.sleep(500);
            actions.moveToElement(element).click().sendKeys(data).sendKeys(Keys.ENTER).perform();
            logger.info("Sent keys to element: {} with data: {} and enter", by, data);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {} with data: {}", by, data, e);
        }
        return this;
    }
    // Send keys to an element using By object and press enter
    public KeywordBase sendKeysAndEnterMac(By by, String data) {
        try {
            Actions actions = new Actions(driver);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
//            Thread.sleep(500);

            actions.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE).sendKeys(data).sendKeys(Keys.ENTER).perform();
            logger.info("Sent keys to element: {} with data: {} and enter", by, data);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {} with data: {}", by, data, e);
        }
        return this;
    }

    // Clear an input field
    public KeywordBase clear(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element.clear();
            logger.info("Cleared element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to clear element: {}", by, e);
        }
        return this;
    }

    // Get text from an element
    public String getText(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            String text = element.getText();
            logger.info("Got text from element: {} - Text: {}", by, text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", by, e);
            return null;
        }
    }

    public String getText(By by, int index) {
        try {
            List<WebElement> elements = driver.findElements(by);
            if (index >= 0 && index < elements.size()) {
                String text = elements.get(index).getText();
                logger.info("Got text from element at index {}: {} - Text: {}", index, by, text);
                return text;
            } else {
                logger.warn("Index {} is out of bounds for elements located by: {}", index, by);
                return null;
            }
        } catch (Exception e) {
            logger.error("Failed to get text from element at index {} located by: {}", index, by, e);
            return null;
        }
    }

    public String getText(By by, String attribute) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            String value = element.getAttribute(attribute);
            logger.info("Got attribute '{}' value from element: {} - Value: {}", attribute, by, value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get attribute '{}' value from element: {}", attribute, by, e);
            return null;
        }
    }

    // Verify if an element is displayed
    public boolean isDisplayed(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element is displayed: {}", by);
            return isDisplayed;
        } catch (NoSuchElementException e) {
            logger.warn("Element not found: {}", by, e);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check if element is displayed: {}", by, e);
            return false;
        }
    }

    // Verify if an element is displayed with a custom wait time
    public boolean isDisplayed(By by, int timeoutInSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = customWait.until(ExpectedConditions.visibilityOfElementLocated(by));

            boolean isDisplayed = element.isDisplayed();
            logger.info("Element is displayed: {}", by);
            return isDisplayed;
        } catch (TimeoutException e) {
            logger.warn("Element not found within {} seconds: {}", timeoutInSeconds, by);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check if element is displayed: {}", by, e);
            return false;
        }
    }

    // Verify if an element is present
    public boolean isElementPresent(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info("Element is displayed: {}", by);
            return element.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Element not found within timeout: {}", by);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check if element is displayed: {}", by, e);
            return false;
        }
    }

    // Wait for an element to be visible
    public KeywordBase waitForVisibility(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info("Waited for visibility of element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of element: {}", by, e);
        }
        return this;
    }

    public KeywordBase waitForVisibility(By by, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info("Element is visible: {}", by);
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of element: {}", by, e);
        }
        return this;
    }

    // Wait for an element to be clickable
    public KeywordBase waitForClickability(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            logger.info("Waited for clickability of element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to wait for clickability of element: {}", by, e);
        }
        return this;
    }

    public KeywordBase waitForElementEnabledState(By by, boolean shouldBeEnabled) {
        try {
            wait.until((ExpectedCondition<Boolean>) driver -> {
                assert driver != null;
                WebElement element = driver.findElement(by);
                return shouldBeEnabled == element.isEnabled();
            });
            logger.info("Waited for element {} to be {}.", by, shouldBeEnabled ? "enabled" : "disabled");
        } catch (Exception e) {
            logger.error("Failed to wait for element {} to be {}.", by, shouldBeEnabled ? "enabled" : "disabled", e);
        }
        return this;
    }

    public KeywordBase waitForInvisibility(By by) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            logger.info("Waited for invisibility of element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to wait for invisibility of element: {}", by, e);
        }
        return this;
    }

    public KeywordBase waitForCustom(long time) throws InterruptedException {
        Thread.sleep(time);
        return this;
    }


    // Select from a dropdown by visible text
    public KeywordBase selectByVisibleText(By by, String visibleText) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            logger.info("Selected by visible text: {} from element: {}", visibleText, by);
        } catch (Exception e) {
            logger.error("Failed to select by visible text: {} from element: {}", visibleText, by, e);
        }
        return this;
    }

    // Select from a dropdown by value
    public KeywordBase selectByValue(By by, String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            Select select = new Select(element);
            select.selectByValue(value);
            logger.info("Selected by value: {} from element: {}", value, by);
        } catch (Exception e) {
            logger.error("Failed to select by value: {} from element: {}", value, by, e);
        }
        return this;
    }

    // Select from a dropdown by index
    public KeywordBase selectByIndex(By by, int index) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            Select select = new Select(element);
            select.selectByIndex(index);
            logger.info("Selected by index: {} from element: {}", index, by);
        } catch (Exception e) {
            logger.error("Failed to select by index: {} from element: {}", index, by, e);
        }
        return this;
    }

    // Handle alert - accept
    public KeywordBase acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            logger.info("Accepted alert");
        } catch (Exception e) {
            logger.error("Failed to accept alert", e);
        }
        return this;
    }

    // Handle alert - dismiss
    public KeywordBase dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
            logger.info("Dismissed alert");
        } catch (Exception e) {
            logger.error("Failed to dismiss alert", e);
        }
        return this;
    }

    // Get alert text
    public String getAlertText() {
        try {
            String alertText = driver.switchTo().alert().getText();
            logger.info("Got alert text: {}", alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Failed to get alert text", e);
            return null;
        }
    }

    // Switch to a frame by index
    public KeywordBase switchToFrameByIndex(int index) {
        try {
            driver.switchTo().frame(index);
            logger.info("Switched to frame by index: {}", index);
        } catch (Exception e) {
            logger.error("Failed to switch to frame by index: {}", index, e);
        }
        return this;
    }

    // Switch to a frame by name or ID
    public KeywordBase switchToFrameByNameOrId(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
            logger.info("Switched to frame by name or ID: {}", nameOrId);
        } catch (Exception e) {
            logger.error("Failed to switch to frame by name or ID: {}", nameOrId, e);
        }
        return this;
    }

    // Switch to a frame by WebElement
    public KeywordBase switchToFrameByElement(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            driver.switchTo().frame(element);
            logger.info("Switched to frame by element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to switch to frame by element: {}", by, e);
        }
        return this;
    }

    // Switch back to the default content (out of the frame)
    public KeywordBase switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            logger.info("Switched to default content");
        } catch (Exception e) {
            logger.error("Failed to switch to default content", e);
        }
        return this;
    }

    // Scroll to an element
    public KeywordBase scrollToElement(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", by, e);
        }
        return this;
    }

    // Scroll by pixels
    public KeywordBase scrollByPixels(int x, int y) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ");");
            logger.info("Scrolled by pixels: x={}, y={}", x, y);
        } catch (Exception e) {
            logger.error("Failed to scroll by pixels: x={}, y={}", x, y, e);
        }
        return this;
    }

    // Scroll to the top of the page
    public KeywordBase uiScrollTop() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0, 0);");
            logger.info("Scrolled to the top of the page.");
        } catch (Exception e) {
            logger.error("Failed to scroll to the top of the page.", e);
        }
        return this;
    }

    // Scroll to the bottom of the page
    public KeywordBase uiScrollBottom() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            logger.info("Scrolled to the bottom of the page.");
        } catch (Exception e) {
            logger.error("Failed to scroll to the bottom of the page.", e);
        }
        return this;
    }

    // Scroll to the bottom of the page
    public KeywordBase uiScrollBottomOfPage() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

            while (true) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                long newHeight = (long) js.executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }

            logger.info("Scrolled to the bottom of the page successfully.");
        } catch (Exception e) {
            logger.error("Failed to scroll to the bottom of the page.", e);
        }
        return this;
    }

    // Click using JavaScript (useful when normal click doesn't work)
    public KeywordBase clickUsingJavaScript(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.info("Clicked using JavaScript on element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to click using JavaScript on element: {}", by, e);
        }
        return this;
    }

    // Hover over an element
    public KeywordBase hoverOverElement(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            actions.moveToElement(element).perform();
            logger.info("Hovered over element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to hover over element: {}", by, e);
        }
        return this;
    }

    // Drag and drop from one element to another
    public KeywordBase dragAndDrop(By sourceBy, By targetBy) {
        try {
            Actions actions = new Actions(driver);
            WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceBy));
            WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(targetBy));
            actions.dragAndDrop(sourceElement, targetElement).build().perform();
            logger.info("Dragged and dropped from element: {} to element: {}", sourceBy, targetBy);
        } catch (Exception e) {
            logger.error("Failed to drag and drop from element: {} to element: {}", sourceBy, targetBy, e);
        }
        return this;
    }

    // Drag and drop from one element to another element
    public KeywordBase dragAndDropAction(By sourceBy, By targetBy) {
        try {
            Actions actions = new Actions(driver);

            WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceBy));
            WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(targetBy));

            actions.clickAndHold(sourceElement)
                    .moveToElement(targetElement)
                    .pause(Duration.ofSeconds(1)) // Pause for stability
                    .release(targetElement)
                    .build()
                    .perform();

            logger.info("Successfully dragged and dropped from element: {} to element: {}", sourceBy, targetBy);
        } catch (Exception e) {
            logger.error("Failed to drag and drop from element: {} to element: {}", sourceBy, targetBy, e);
        }

        return this;
    }

    // Double-click on an element
    public KeywordBase doubleClick(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            actions.doubleClick(element).perform();
            logger.info("Double-clicked on element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to double-click on element: {}", by, e);
        }
        return this;
    }

    // Right-click (context click) on an element
    public KeywordBase rightClick(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            actions.contextClick(element).perform();
            logger.info("Right-clicked on element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to right-click on element: {}", by, e);
        }
        return this;
    }

    // Refresh the page
    public KeywordBase refreshPage() {
        try {
            driver.navigate().refresh();
            logger.info("Refreshed the page");
        } catch (Exception e) {
            logger.error("Failed to refresh the page", e);
        }
        return this;
    }

    // Navigate to a URL
    public KeywordBase navigateToURL(String url) {
        try {
            driver.navigate().to(url);
            logger.info("Navigated to URL: {}", url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
        }
        return this;
    }

    // Go back in browser history
    public KeywordBase goBack() {
        try {
            driver.navigate().back();
            logger.info("Navigated back in browser history");
        } catch (Exception e) {
            logger.error("Failed to navigate back in browser history", e);
        }
        return this;
    }

    // Go forward in browser history
    public KeywordBase goForward() {
        try {
            driver.navigate().forward();
            logger.info("Navigated forward in browser history");
        } catch (Exception e) {
            logger.error("Failed to navigate forward in browser history", e);
        }
        return this;
    }

    // Maximize the browser window
    public KeywordBase maximizeWindow() {
        try {
            driver.manage().window().maximize();
            logger.info("Maximized the browser window");
        } catch (Exception e) {
            logger.error("Failed to maximize the browser window", e);
        }
        return this;
    }

    // Close the current browser window
    public KeywordBase closeBrowser() {
        try {
            driver.close();
            logger.info("Closed the browser window");
        } catch (Exception e) {
            logger.error("Failed to close the browser window", e);
        }
        return this;
    }

    // Quit the browser (close all windows)
    public KeywordBase quitBrowser() {
        try {
            driver.quit();
            logger.info("Quit the browser and closed all windows");
        } catch (Exception e) {
            logger.error("Failed to quit the browser", e);
        }
        return this;
    }

    // Method to capture a screenshot
    public void captureScreenshot(String testName) {
        try {
            // Generate a timestamp to make the filename unique
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            // Cast driver to TakesScreenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Define the destination path
            File destFile = new File("target/surefire-reports/screenshots/" + testName + "_" + timestamp + ".png");
            // Copy the screenshot to the destination
            FileUtils.copyFile(srcFile, destFile);
            logger.info("Screenshot taken for test: " + testName + " at " + timestamp);
        } catch (IOException e) {
            logger.error("Failed to take screenshot for test: " + testName, e);
        }
    }

    // Clear using JavaScript (useful when normal clear doesn't work)
    public KeywordBase clearUsingJavaScript(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
            logger.info("Clear using JavaScript on element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to Clear using JavaScript on element: {}", by, e);
        }
        return this;
    }

    // Method to select a random option
    public void selectRandomOptionFromDropDown(By optionsLocator) {
        // Wait for the options to be present
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsLocator));

        // Locate the options within the dropdown
        List<WebElement> options = driver.findElements(optionsLocator);
        int totalOptions = options.size();

        if (totalOptions > 0) {
            // Generate a random index to select a random option
            Random random = new Random();
            int randomIndex = random.nextInt(totalOptions);

            // Click on the randomly selected option
            WebElement selectedOption = options.get(randomIndex);
            selectedOption.click();
        } else {
            System.out.println("No options available to select.");
        }
    }

    public boolean isDatesSorted(By by) {
        List<WebElement> dateElements = driver.findElements(by);

        List<Date> dates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Retrieve text from all first column cells and parse to Date
        for (WebElement element : dateElements) {
            try {
                Date date = dateFormat.parse(element.getText());
                dates.add(date);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle parsing error
            }
        }

        // Create a copy of the list and sort it
        List<Date> sortedList = new ArrayList<>(dates);
        Collections.sort(sortedList);

        // Check if the original list is equal to the sorted list
        boolean isSorted = dates.equals(sortedList);

        return isSorted;
    }

    public KeywordBase SwitchToNewTab(By by) {

        // Store the current window handle
        String originalTab = driver.getWindowHandle();

        // Store the current set of window handles (before opening the new tab)
        Set<String> existingWindows = driver.getWindowHandles();

        // Click the element that opens the new
        driver.findElement(by).click();

        // Wait until the number of windows increases (indicating a new tab is opened)
        wait.until(ExpectedConditions.numberOfWindowsToBe(existingWindows.size() + 1));

        // Get the updated set of window handles after the new tab opens
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            if (!existingWindows.contains(windowHandle)) {
                driver.switchTo().window(windowHandle);
                logger.info("Switched to new tab: {}", windowHandle);
                break; // Exit the loop once you've switched
            }
        }

        return this;

    }

    // Method to count the number of elements matching a given locator
    public int countElements(By elementsLocator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocator));


            List<WebElement> elements = driver.findElements(elementsLocator);


            // Return the size of the list (i.e., the number of elements)
            return elements.size();
        } catch (TimeoutException e) {
            // If no elements are visible within the timeout, return 0
            return 0;
        }
    }

    public KeywordBase sendKeysWaitAndSelectDropdownOptionByEnter(By inputFieldLocator, String text) {
        try {
            Actions actions = new Actions(driver);

            //Wait until the input field is visible and send keys
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputFieldLocator));
            actions.moveToElement(inputField).click().sendKeys(text).perform();

            //Wait for 2 seconds to allow the dropdown to load
            Thread.sleep(2000);

            //Press Enter to select the option
            actions.sendKeys(Keys.ENTER).perform();

            logger.info("Sent keys to input field: {} with text: {} and selected the dropdown option with Enter key", inputFieldLocator, text);
        } catch (Exception e) {
            logger.error("Failed to send keys to input field: {} with text: {} and select the dropdown option", inputFieldLocator, text, e);
        }
        return this;
    }


    public KeywordBase OpenNewTabAndSwitchToIt() {

        // Store the current window handle as the original tab
        originalTab = driver.getWindowHandle();

        // Store the current set of window handles (before opening the new tab)
        Set<String> existingWindows = driver.getWindowHandles();

        // Use JavaScript to open a new blank tab
        ((JavascriptExecutor) driver).executeScript("window.open();");

        // Wait until the number of windows increases (indicating a new tab is opened)
        wait.until(ExpectedConditions.numberOfWindowsToBe(existingWindows.size() + 1));

        // Get the updated set of window handles after the new tab opens
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            if (!existingWindows.contains(windowHandle)) {
                secondTab = windowHandle;  // Store the handle of the new tab
                driver.switchTo().window(secondTab);
                logger.info("Switched to new tab: {}", secondTab);
                break; // Exit the loop once you've switched
            }
        }

        return this;
    }

    public KeywordBase pasteUrlFromClipboard() {
        try {
            // Get the URL from the clipboard
            String url = Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();

            // Navigate directly to the URL in the clipboard
            driver.get(url);

            logger.info("Navigated to the URL from clipboard: {}", url);
        } catch (Exception e) {
            logger.error("Failed to retrieve URL from clipboard or navigate", e);
        }
        return this;
    }

    public boolean validateFilteredElements(By locator, String filterOption) {
        try {
            // Find all elements using the provided locator
            List<WebElement> elements = driver.findElements(locator);

            // Log total elements found
            logger.info("Found " + elements.size() + " elements for locator: " + locator);

            // Validate that all elements match the filter option
            for (WebElement element : elements) {
                String elementText = element.getText();
                if (!elementText.equals(filterOption)) {
                    logger.error("Validation failed for element text: " + elementText + " (Expected: " + filterOption + ")");
                    return false; // Validation failed
                }
            }

            // Log validation success
            logger.info("All elements match the filter option: " + filterOption);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while validating elements for locator: " + locator, e);
            return false;
        }
    }

    // Method to check if a checkbox is selected, given its locator
    public boolean isCheckboxOrRadioBtnSelected(By checkboxLocator) {
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxLocator));

            WebElement checkbox = driver.findElement(checkboxLocator);

            return checkbox.isSelected();
        } catch (TimeoutException e) {

            return false;
        }
    }

    public WebElement getElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Successfully located element: {}", locator);
            return element;
        } catch (Exception e) {
            logger.error("Failed to locate element: {}", locator, e);
            return null;
        }
    }

    /**
     * Gets the current date in the format "Day MM/dd/yyyy".
     * Example: "Tuesday 12/17/2024"
     *
     * @return formatted current date as a String
     */
    public String getCurrentDateFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE MM/dd/yyyy");
        Date today = new Date();
        return formatter.format(today);
    }

    public String getDateAfterDaysFormatted(int daysToAdd) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysToAdd);
        Date futureDate = calendar.getTime();
        return formatter.format(futureDate);
    }

    public boolean isFileDownloaded(String downloadPath, String expectedFileName, String fromDate, String toDate) throws ParseException {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        if (files != null) {
            SimpleDateFormat currentFormatter = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat dashFormatter = new SimpleDateFormat("MM-dd-yyyy");
            SimpleDateFormat underscoreFormatter = new SimpleDateFormat("yyyy-MM-dd");

            String formattedFromDate = fromDate != null ? dashFormatter.format(currentFormatter.parse(fromDate)) : null;
            String formattedToDateDash = toDate != null ? dashFormatter.format(currentFormatter.parse(toDate)) : null;
            String formattedToDateUnderscore = toDate != null ? underscoreFormatter.format(currentFormatter.parse(toDate)) : null;

            String fileNameCase1 = String.format("%s - %s - %s.xlsx", expectedFileName, formattedFromDate, formattedToDateDash); // Case 1
            String fileNameCase2 = String.format("%s_%s.xlsx", expectedFileName, formattedToDateDash); // Case 2
            String fileNameCase3 = String.format("%s_%s.xlsx", expectedFileName, formattedToDateUnderscore); // Case 3
            String fileNameCase4 = String.format("%s.xlsx", expectedFileName); // Case 4

            for (File file : files) {
                String fileName = file.getName();
                if (fileName.equals(fileNameCase1) || fileName.equals(fileNameCase2) ||
                        fileName.equals(fileNameCase3) || fileName.equals(fileNameCase4)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void cleanUpDownloads(String downloadPath) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();  // Delete each file
                }
            }
        }
    }
    public boolean isDraftOrdersNotOlder30Days(By locator) {
        try {
            // Find all elements using the provided locator
            List<WebElement> elements = driver.findElements(locator);

            // Log total elements found
            logger.info("Found " + elements.size() + " elements for locator: " + locator);

            // Validate that all elements match the filter option
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            // Define the date range
            ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);
            ZonedDateTime before32DateUTC = nowUTC.minusDays(33);
            for (WebElement element : elements) {
                String elementText = element.getText();

                // Parse elementText into LocalDate using the correct format
                LocalDate elementLocalDate = LocalDate.parse(elementText, formatter);

                // Convert LocalDate to ZonedDateTime at UTC for comparison
                ZonedDateTime elementDate = elementLocalDate.atStartOfDay(ZoneOffset.UTC);


                if (elementDate.isBefore(before32DateUTC) || elementDate.isAfter(nowUTC)) {
                    logger.error("Validation failed for element text: " + elementText +
                            " (Date out of range. Expected between: " +
                            before32DateUTC.format(formatter) + " and " +
                            nowUTC.format(formatter) + ")");
                    return false; // Validation failed
                }
            }
            // Log validation success
            logger.info("All elements match the filter option: " + before32DateUTC);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while validating elements for locator: " + locator, e);
            return false;
        }
    }

    public boolean isDraftOrdersNotOlder30DaysStable(By draftRowCount) {
        try {
            // Find all rows
            List<WebElement> rows = driver.findElements(draftRowCount);
            logger.info("Found " + rows.size() + " elements for locator: " + draftRowCount);

            // Fetch all dates and statuses in a single JavaScript call
            JavascriptExecutor js = (JavascriptExecutor) driver;
            List<Map<String, String>> rowData = (List<Map<String, String>>) js.executeScript(
                    "return Array.from(document.querySelectorAll('table tbody tr')).map(row => { " +
                            "    let dateCell = row.querySelector('td:nth-child(6)'); " +
                            "    let statusCell = row.querySelector('td:nth-child(9) span'); " +
                            "    return { " +
                            "        date: dateCell ? dateCell.innerText.trim() : '', " +
                            "        status: statusCell ? statusCell.innerText.trim() : '' " +
                            "    }; " +
                            "});"
            );

            // Date format and range setup
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);
            ZonedDateTime before32DateUTC = nowUTC.minusDays(33);

            // Validate all rows
            for (Map<String, String> row : rowData) {
                String draftDate = row.get("date");
                String draftStatus = row.get("status");

                // Skip if date is empty (row might be malformed)
                if (draftDate.isEmpty()) {
                    logger.warn("td[6] not found in this row. Skipping...");
                    continue;
                }

                // Parse date and compare
                LocalDate elementLocalDate = LocalDate.parse(draftDate, formatter);
                ZonedDateTime elementDate = elementLocalDate.atStartOfDay(ZoneOffset.UTC);

                // If date is out of range AND status is empty, validation fails
                if ((elementDate.isBefore(before32DateUTC) || elementDate.isAfter(nowUTC)) && draftStatus.isEmpty()) {
                    logger.error("Validation failed for element date: " + draftDate +
                            " (Date out of range. Expected between: " +
                            before32DateUTC.format(formatter) + " and " +
                            nowUTC.format(formatter) + ")");
                    return false;
                }
            }

            logger.info("All elements match the filter option: " + before32DateUTC);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while validating elements for locator: " + draftRowCount, e);
            return false;
        }
    }

    public static String getLastWorkingDayUST() {

        ZonedDateTime yesterdayUTC = ZonedDateTime.now(ZoneOffset.UTC).minusDays(1);
        if (yesterdayUTC.getDayOfWeek() == DayOfWeek.SUNDAY) {
            yesterdayUTC = yesterdayUTC.minusDays(2);
        } else if (yesterdayUTC.getDayOfWeek() == DayOfWeek.SATURDAY) {
            yesterdayUTC = yesterdayUTC.minusDays(1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return yesterdayUTC.format(formatter);
    }

    public static String getLastWorkingDayEST() {
        ZonedDateTime yesterdayEST = ZonedDateTime.now(ZoneId.of("America/New_York")).minusDays(1);

        if (yesterdayEST.getDayOfWeek() == DayOfWeek.SUNDAY) {
            yesterdayEST = yesterdayEST.minusDays(2); // Move to Friday
        } else if (yesterdayEST.getDayOfWeek() == DayOfWeek.SATURDAY) {
            yesterdayEST = yesterdayEST.minusDays(1); // Move to Friday
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return yesterdayEST.format(formatter);
    }

    /*public void scrollToElementStable(By by) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean elementFound = false;

            while (!elementFound) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(1000);

                if (driver.findElements(by).size() > 0) {
                    elementFound = true;
                }
            }

            WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            js.executeScript("arguments[0].scrollIntoView(true);", targetElement);

            logger.info("Scrolled to and found the element: {}", by);
        } catch (Exception e) {
            logger.error("Failed to find and scroll to the element: {}", by, e);
        }
    }*/

    public void scrollToElementStable(By by, int timeoutInSeconds) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean elementFound = false;
            int maxAttempts = 20;
            int attempt = 0;
            long lastHeight = 0;

            while (!elementFound && attempt < maxAttempts) {
                long newHeight = (long) js.executeScript("return document.documentElement.scrollHeight");

                if (newHeight == lastHeight) {
                    break;
                }

                js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
                Thread.sleep(1000);
                lastHeight = newHeight;
                attempt++;

                if (!driver.findElements(by).isEmpty()) {
                    elementFound = true;
                    break;
                }
            }

            if (elementFound) {
                WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
                WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
                logger.info("Scrolled to and found the element: {}", by);
            } else {
                logger.warn("Element not found after scrolling {} times: {}", attempt, by);
            }
        } catch (Exception e) {
            logger.error("Failed to find and scroll to the element: {}", by, e);
        }
    }

    public void scrollToElementStpByStep(By by, int timeoutInSeconds) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            int maxAttempts = 20;
            int attempt = 0;
            int scrollStep = 300; // pixels per scroll
            long lastScrollTop = -1;

            while (attempt < maxAttempts) {
                if (!driver.findElements(by).isEmpty()) {
                    WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
                    WebElement targetElement = customWait.until(ExpectedConditions.visibilityOfElementLocated(by));
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", targetElement);
                    logger.info("Scrolled to and found the element: {}", by);
                    return;
                }
                js.executeScript("window.scrollBy(0, arguments[0]);", scrollStep);
                Thread.sleep(500);
                long scrollTop = (long) js.executeScript("return window.pageYOffset;");
                if (scrollTop == lastScrollTop) {
                    break; // No more scrolling possible
                }
                lastScrollTop = scrollTop;
                attempt++;
            }
            logger.warn("Element not found after scrolling {} times: {}", attempt, by);
        } catch (Exception e) {
            logger.error("Failed to find and scroll to the element: {}", by, e);
        }
    }

    public String getCssValue(By locator, String propertyName) {
        WebElement element = driver.findElement(locator);
        return element.getCssValue(propertyName);
    }
    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();

        // Wait for the new tab to open (optional, depending on your application)
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.getWindowHandles().size() > 1);

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break; // Exit loop once switched to new tab
            }
        }
    }

    public boolean isElementEnabled(By locator){
        try{
            WebElement element = driver.findElement(locator);
            boolean isEnabled = element.isEnabled();
            logger.info("Element {} is {}", locator, isEnabled ? "ENABLED" : "DISABLED");
            return isEnabled;
        }catch (Exception e) {
            logger.error("Failed to check if element {} is enabled", locator, e);
            return false;
        }
    }

public KeywordBase clickF12Mac() {
    try {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.F12).perform();
        logger.info("Pressed F12 (Developer Tools) on Mac");
    } catch (Exception e) {
        logger.error("Failed to press F12 on Mac", e);
    }
    return this;
}
    // Navigate to a URL using the SAME browser
    public KeywordBase navigateToURLSame(String url) {
        try {
            // This opens the new URL in the SAME tab (does NOT close or remove the current window)
            driver.navigate().to(url);
            logger.info("Navigated to URL: {}", url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
        }
        return this;
    }

    public KeywordBase openNewTabAndClosePreviousTabs() {
        // Get all existing window handles (tabs)
        Set<String> existingTabs = driver.getWindowHandles();

        // Open a new blank tab
        ((JavascriptExecutor) driver).executeScript("window.open();");

        // Wait until the number of tabs increases
        wait.until(ExpectedConditions.numberOfWindowsToBe(existingTabs.size() + 1));

        // Get the updated list of window handles
        Set<String> allTabs = driver.getWindowHandles();

        // Identify the new tab by finding the one not in the original set
        String newTab = null;
        for (String tab : allTabs) {
            if (!existingTabs.contains(tab)) {
                newTab = tab;
                break;
            }
        }

        if (newTab != null) {
            // Switch to the new tab
            driver.switchTo().window(newTab);
            logger.info("Switched to new tab: {}", newTab);

            // Close all old tabs
            for (String tab : existingTabs) {
                try {
                    driver.switchTo().window(tab);
                    driver.close();
                    logger.info("Closed previous tab: {}", tab);
                } catch (Exception e) {
                    logger.warn("Could not close tab: {}", tab, e);
                }
            }

            // Switch back to the new tab again (just in case)
            driver.switchTo().window(newTab);
        } else {
            logger.error("Failed to identify new tab");
        }

        return this;
    }

    public String getValue(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            String value = element.getAttribute("value");
            logger.info("Got value from element: {} - Text: {}", by, value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get value from element: {}", by, e);
            return null;
        }
    }
    public boolean isCheckboxBtnSelected(By checkboxLocator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxLocator));
            WebElement checkbox = driver.findElement(checkboxLocator);
            String className = checkbox.getAttribute("class");
            return className.contains("fa-square-check");
        } catch (TimeoutException e) {
            return false;
        }
    }


}
