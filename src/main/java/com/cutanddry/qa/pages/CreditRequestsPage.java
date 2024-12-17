package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreditRequestsPage extends TestBase {

    By btn_request_data = By.xpath(
            "//label[text()='Request Date:']/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    By btn_search = By.xpath("//input[@placeholder='Search' and contains(@class, 'form-control')]");
    By first_row_credit_requests = By.xpath("//table[@class='table table-hover']//tbody//tr[1]");
    By first_row_credit_view = By.xpath("//table[@class='table table-hover']//tbody//tr[1]");
    By txt_error = By.xpath("//*[contains(translate(text(), 'ERROR', 'error'), 'error')]");
    By btn_items = By.xpath("//a[@role='tab' and @data-rb-event-key='Items']");
    By header_items_table = By.xpath("//thead/tr[@class='_jg41os']");
    By btn_timeline = By.xpath("//a[@role='tab' and @data-rb-event-key='Timeline']");
    By header_timeline_table = By.xpath(
            "//tr[@class='_jg41os' and th[text()='Timestamp'] and th[text()='Status'] and th[text()='Organization'] and th[text()='User'] and th[text()='Action']]");
    By btn_credit_view = By.xpath("//a[@role='tab' and @data-rb-event-key='Credit Request']");
    By header_credit_view_table = By.xpath(
            "//tr[@class='_jg41os' and th[text()='Item Code'] and th[text()='Unit'] and th[text()='Price'] and th[text()='Qty.'] and th[text()='Issue'] and th[text()='Total'] and th[text()='Credit']]");
    By modal_issue_details = By
            .xpath("//div[@class='modal-header']//div[contains(@class, 'modal-title') and text()='Issue Details']");
    By btn_report_issue = By.xpath("//button[@class='mr-3 btn btn-outline-danger']");
    By txt_report_issue = By.xpath("//h2[@class='mb-0 _1vx3fhy' and text()='Which items had an issue?']");
    By btn_first_row = By.xpath("//tr[@class='_du1frc']");
    By txt_tell_us = By.xpath("//div[@class='mont modal-title h4' and text()='Tell us more...']");
    By btn_issue_option = By.xpath("//div[@class='themed_select__value-container css-1hwfws3']");
    By btn_first_option = By.xpath("//div[@id='react-select-2-option-1']");
    By btn_continue = By.xpath("//button[@class='btn btn-primary btn-block']");
    By txt_credit_requested = By.xpath("//td[text()='Credit Requested']");
    By btn_save_checkin = By.xpath("//button[@class = 'btn btn-primary' and text() = 'Save Check-In']");
    String option_timeRange = "//div[contains(@class, 'themed_select__menu')]//div[contains(text(), 'TIME_RANGE')]";
    String searchedElementXPath = "//td[normalize-space(text())='ORDER_ID']";

    public void clickOnRequestDate() {
        // Check if it's in an iFrame
        driver.switchTo().defaultContent(); // Switch to default first if you're in another iFrame
        distributorUI.click(btn_request_data); // Opens dropdown
    }

    public void selectTimeRange(String timeRange) {
        // Replace placeholder 'TIME_RANGE' with the actual value
        By option_dynamic = By.xpath(option_timeRange.replace("TIME_RANGE", timeRange));

        distributorUI.waitForElementEnabledState(option_dynamic, true); // Wait for the element to be enabled
        distributorUI.click(option_dynamic); // Click on the desired option
    }

    public void clickOnSearch() {
        distributorUI.click(btn_search);
    }

    public void typeOnSearch(String orderID) throws InterruptedException {
        distributorUI.clear(btn_search);
        distributorUI.sendKeys(btn_search, orderID);
        distributorUI.waitForCustom(400);
    }

    public boolean checkIfSearchedElementVisible(String orderID) {
        By visibleObject = By.xpath(searchedElementXPath.replace("ORDER_ID", orderID));
        distributorUI.waitForVisibility(visibleObject);
        try {
            // Wait for the element to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(visibleObject));
        } catch (Exception e) {
            return false; // Element is not visible
        }
        return distributorUI.isDisplayed(visibleObject);
    }

    public void clickOnFirstItemOfCreditRequests() {
        distributorUI.waitForVisibility(first_row_credit_requests);
        distributorUI.click(first_row_credit_requests);
    }

    public void clickOnItems() {
        distributorUI.click(btn_items);
    }

    public boolean checkIfItemSectionVisible() {
        return distributorUI.isDisplayed(header_items_table);
    }

    public boolean isErrorTextNotDisplayed() {
        return distributorUI.isDisplayed(txt_error);
    }

    public void clickOnTimeline() {
        distributorUI.click(btn_timeline);
    }

    public boolean checkIfTimelineSectionVisible() {
        return distributorUI.isDisplayed(header_timeline_table);
    }

    public void clickOnCreditView() {
        distributorUI.click(btn_credit_view);
    }

    public boolean checkIfCreditViewSectionVisible() {
            return distributorUI.isDisplayed(header_credit_view_table);
    }

    public void clickOnFirstItemOfCreditView() {
        distributorUI.waitForVisibility(first_row_credit_view);
        distributorUI.click(first_row_credit_view);
    }

    public boolean checkIfIssueDetailsModalDisplayed() {
        return distributorUI.isDisplayed(modal_issue_details);
    }

    public void clickReportIssue() {
        distributorUI.click(btn_report_issue);
    }

    public boolean isReportIssueSectionDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_report_issue);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_report_issue);
    }

    public void clickOneItem() {
        distributorUI.click(btn_first_row);
    }

    public boolean isIssuePopUpDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_tell_us);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_tell_us);
    }

    public void clickIssueOption() throws InterruptedException {
        distributorUI.click(btn_issue_option);
        distributorUI.waitForCustom(2000);
        distributorUI.click(btn_first_option);
    }

    public void clickContinue() {
        distributorUI.click(btn_continue);
    }

    public boolean isCreditRequestedDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_credit_requested);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_credit_requested);
    }

    public void clickSaveCheckIn() {
        distributorUI.click(btn_save_checkin);
    }

}
