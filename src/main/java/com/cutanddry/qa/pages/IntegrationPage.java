package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class IntegrationPage extends LoginPage {
    
    By btn_integration = By.xpath("//a[@href='/integration']");
    
    By txt_24HourSnapshot = By.xpath("//*[contains(text(), '24 Hour Snapshot')]");
    By txt_completedTasks = By.xpath("//*[contains(text(), 'Completed')]");
    By txt_inProgressTasks = By.xpath("//*[contains(text(), 'In Progress')]");
    By txt_snapshotPercentage = By.xpath("//div[contains(text(), '%')]");
    
    By txt_lastSuccessfulSync = By.xpath("//*[contains(text(), 'Last Successful Sync')]");
    By txt_catalogSync = By.xpath("//*[contains(text(), 'Catalog :')]");
    By txt_customerSync = By.xpath("//*[contains(text(), 'Customer :')]");
    By txt_invoiceSync = By.xpath("//*[contains(text(), 'Invoice :')]");
    By txt_termsSync = By.xpath("//*[contains(text(), 'Terms :')]");
    
    By table_integration = By.xpath("//table");
    By txt_integrationStatus = By.xpath("//*[contains(text(), 'Integration Status')]");
    
    /**
     * Click on Integration tab from sidebar
     */
    public void clickOnIntegration() {
        distributorUI.click(btn_integration);
    }
    
    /**
     * Check if Integration Status page is displayed
     * @return true if Integration Status text is visible
     */
    public boolean isIntegrationStatusDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_integrationStatus);
            return distributorUI.isDisplayed(txt_integrationStatus);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if 24 Hour Snapshot section is displayed
     * @return true if 24 Hour Snapshot text is visible
     */
    public boolean is24HourSnapshotDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_24HourSnapshot);
            return distributorUI.isDisplayed(txt_24HourSnapshot);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if Completed tasks text is displayed in 24 Hour Snapshot
     * @return true if Completed text is visible
     */
    public boolean isCompletedTasksDisplayed() {
        try {
            return distributorUI.isDisplayed(txt_completedTasks);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if In Progress tasks text is displayed in 24 Hour Snapshot
     * @return true if In Progress text is visible
     */
    public boolean isInProgressTasksDisplayed() {
        try {
            return distributorUI.isDisplayed(txt_inProgressTasks);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if snapshot percentage graph is displayed
     * @return true if percentage text is visible
     */
    public boolean isSnapshotPercentageDisplayed() {
        try {
            return distributorUI.isDisplayed(txt_snapshotPercentage);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get the snapshot percentage value
     * @return percentage value as string
     */
    public String getSnapshotPercentage() {
        try {
            return distributorUI.getText(txt_snapshotPercentage);
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Check if Last Successful Sync section is displayed
     * @return true if Last Successful Sync text is visible
     */
    public boolean isLastSuccessfulSyncDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_lastSuccessfulSync);
            return distributorUI.isDisplayed(txt_lastSuccessfulSync);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if Catalog sync data is displayed
     * @return true if Catalog sync text is visible
     */
    public boolean isCatalogSyncDisplayed() {
        try {
            return distributorUI.isDisplayed(txt_catalogSync);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if Customer sync data is displayed
     * @return true if Customer sync text is visible
     */
    public boolean isCustomerSyncDisplayed() {
        try {
            return distributorUI.isDisplayed(txt_customerSync);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if Invoice sync data is displayed
     * @return true if Invoice sync text is visible
     */
    public boolean isInvoiceSyncDisplayed() {
        try {
            return distributorUI.isDisplayed(txt_invoiceSync);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if Terms sync data is displayed
     * @return true if Terms sync text is visible
     */
    public boolean isTermsSyncDisplayed() {
        try {
            return distributorUI.isDisplayed(txt_termsSync);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if integration table is displayed
     * @return true if integration table is visible
     */
    public boolean isIntegrationTableDisplayed() {
        try {
            return distributorUI.isDisplayed(table_integration);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get Catalog sync timestamp
     * @return Catalog sync timestamp as string
     */
    public String getCatalogSyncTimestamp() {
        try {
            return distributorUI.getText(txt_catalogSync);
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Get Customer sync timestamp
     * @return Customer sync timestamp as string
     */
    public String getCustomerSyncTimestamp() {
        try {
            return distributorUI.getText(txt_customerSync);
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Get Invoice sync timestamp
     * @return Invoice sync timestamp as string
     */
    public String getInvoiceSyncTimestamp() {
        try {
            return distributorUI.getText(txt_invoiceSync);
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Get Terms sync timestamp
     * @return Terms sync timestamp as string
     */
    public String getTermsSyncTimestamp() {
        try {
            return distributorUI.getText(txt_termsSync);
        } catch (Exception e) {
            return "";
        }
    }
}
