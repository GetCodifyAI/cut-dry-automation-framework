package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class IntegrationPage extends LoginPage {
    
    By btn_integration = By.xpath("//a[@href='/integration']");
    
    By txt_24HourSnapshot = By.xpath("//*[contains(text(), '24 Hour Snapshot')]");
    By txt_completedTasks = By.xpath("//*[normalize-space()='24 Hour Snapshot']/following-sibling::div//span[contains(text(),'Completed')]");
    By txt_inProgressTasks = By.xpath("//*[normalize-space()='24 Hour Snapshot']/following-sibling::div//span[contains(text(),'In Progress')]");
    By txt_snapshotPercentage = By.xpath("//*[local-name()='svg']//*[local-name()='text' and contains(normalize-space(.), '%')]");
    
    By txt_lastSuccessfulSync = By.xpath("//*[contains(text(), 'Last Successful Sync')]");
    By txt_catalogSync = By.xpath("//div[normalize-space()='Last Successful Sync'] /following-sibling::div[1] //div[.//text()[contains(., 'Catalog')]]");
    By txt_customerSync = By.xpath("//div[normalize-space()='Last Successful Sync'] /following-sibling::div[1] //div[.//text()[contains(., 'Customer')]]");
    By txt_invoiceSync = By.xpath("//div[normalize-space()='Last Successful Sync'] /following-sibling::div[1] //div[.//text()[contains(., 'Invoice')]]");
    By txt_termsSync = By.xpath("//div[normalize-space()='Last Successful Sync'] /following-sibling::div[1] //div[.//text()[contains(., 'Terms')]]");
    
    By table_integration = By.xpath("//table");
    By txt_integrationStatus = By.xpath("//*[contains(text(), 'Integration Status')]");
    String syncEndTimeFromTable = "(//tr/td[contains(text(),'SYNCNAME')]/following::td/span[contains(text(),'Completed')])[1]/../../td[5]";
    

    public void clickOnIntegration() {
        distributorUI.click(btn_integration);
    }

    public boolean isIntegrationStatusDisplayed() {
        distributorUI.waitForVisibility(txt_integrationStatus);
        return distributorUI.isDisplayed(txt_integrationStatus);
    }

    public boolean is24HourSnapshotDisplayed() {
        distributorUI.waitForVisibility(txt_24HourSnapshot);
        return distributorUI.isDisplayed(txt_24HourSnapshot);
    }

    public boolean isCompletedTasksDisplayed() {
        return distributorUI.isDisplayed(txt_completedTasks);
    }

    public boolean isInProgressTasksDisplayed() {
        return distributorUI.isDisplayed(txt_inProgressTasks);

    }

    public boolean isSnapshotPercentageDisplayed() {
        return distributorUI.isDisplayed(txt_snapshotPercentage);
    }

    public String getSnapshotPercentage() {
        return distributorUI.getText(txt_snapshotPercentage);
    }

    public boolean isLastSuccessfulSyncDisplayed() {
        distributorUI.waitForVisibility(txt_lastSuccessfulSync);
        return distributorUI.isDisplayed(txt_lastSuccessfulSync);
    }

    public boolean isCatalogSyncDisplayed() {
        return distributorUI.isDisplayed(txt_catalogSync);
    }

    public boolean isCustomerSyncDisplayed() {
        return distributorUI.isDisplayed(txt_customerSync);
    }

    public boolean isInvoiceSyncDisplayed() {
        return distributorUI.isDisplayed(txt_invoiceSync);
    }

    public boolean isTermsSyncDisplayed() {
        return distributorUI.isDisplayed(txt_termsSync);
    }

    public boolean isIntegrationTableDisplayed() {
        return distributorUI.isDisplayed(table_integration);
    }

    public String getCatalogSyncTimestamp() {
        return distributorUI.getText(txt_catalogSync);
    }

    public String getCustomerSyncTimestamp() {
        return distributorUI.getText(txt_customerSync);
    }

    public String getInvoiceSyncTimestamp() {
        return distributorUI.getText(txt_invoiceSync);
    }

    public String getTermsSyncTimestamp() {
        return distributorUI.getText(txt_termsSync);
    }

    public String getLastSyncFromTable(String syncName){
        distributorUI.scrollToElementTillFound(By.xpath(syncEndTimeFromTable.replace("SYNCNAME",syncName)));
        return distributorUI.getText(By.xpath(syncEndTimeFromTable.replace("SYNCNAME",syncName)));
    }


}
