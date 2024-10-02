package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ConfigSupplierPage extends LoginPage{
    By txt_configSupplier = By.xpath("//h2[contains(text(), 'Configure Suppliers')]");
    String btn_editDetails = "//tr[td[contains(text(), 'NAME')]]//a[contains(text(), 'Edit Details')]";
    By btn_catalogSettings = By.xpath("//a[contains(text(), 'Catalog Settings')]");
    By btn_saveCatalog = By.xpath("//h5[text()='Catalog']/following::button[contains(text(), 'Save')]");
    By lbl_OGSugTool= By.xpath("//label[contains(text(), 'OG Suggestive Sales Tool')]/following-sibling::div//div[contains(@class, 'react-switch-handle')]");
    By st_activeOGSugTool = By.xpath("//label[contains(text(), 'OG Suggestive Sales Tool')]/following::div[contains(@style, 'background: rgb(0, 136, 0)')][1]");

    public boolean isConfigTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_configSupplier);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_configSupplier);
    }
    public void clickOnEditDetails(String name) {
        distributorUI.waitForVisibility(By.xpath(btn_editDetails.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_editDetails.replace("NAME", name)));
    }
    public void clickOnCatalogSettings() {
        distributorUI.waitForVisibility(btn_catalogSettings);
        distributorUI.clickUsingJavaScript(btn_catalogSettings);
    }
    public void clickOnSave() {
        distributorUI.waitForVisibility(btn_saveCatalog);
        distributorUI.clickUsingJavaScript(btn_saveCatalog);
        distributorUI.waitForElementEnabledState(btn_saveCatalog, true);
    }
    public void toggleOnOGSuggestiveTool() throws InterruptedException {
        if (!distributorUI.isDisplayed(st_activeOGSugTool)){
            distributorUI.waitForVisibility(lbl_OGSugTool);
            distributorUI.click(lbl_OGSugTool);
            distributorUI.waitForCustom(2000);
        }
    }
    public void toggleOffOGSuggestiveTool() {
        if (distributorUI.isDisplayed(st_activeOGSugTool)){
            distributorUI.waitForVisibility(lbl_OGSugTool);
            distributorUI.click(lbl_OGSugTool);
        }
    }
}
