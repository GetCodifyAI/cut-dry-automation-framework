package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class InternalToolsPage extends LoginPage {
    By configureSupplierTxt = By.xpath("//a[contains(text(),'Configure Supplier')]");
    By IndependentCompEditDetailsBtn = By.xpath("//tr[td[contains(text(),'Independent Foods Co')]]//a[contains(text(),'Edit Details')]");
    By OrderingSettingsTab = By.xpath("//a[contains(text(),'Ordering Settings')]");
    By PortalStockLevelToggle = By.xpath("//div[contains(text(), 'Display stock levels on Portal:')]/following-sibling::div//div[@class='react-switch-bg']");
    By SaveBtn = By.xpath("//div[@class='text-right col']//button[text()='Save']");
    By SuccessOkBtn = By.xpath("//button[text()='OK']");
    By CatalogSettingsTab = By.xpath("//a[contains(text(),'Catalog Settings')]");
    By disableCatalogDropDown = By.xpath("//div[contains(text(), 'Disable catalog for:')]/following-sibling::div//div[contains(text(), 'Manual Selection')]");
    String catalogOptionSelect = "(//div[text()='CATALOGDROPDOWNOPTION'])[last()]";
    By catalogSettingsSaveBtn = By.xpath("//div[h5[text()='Catalog']]/following-sibling::div//button[contains(text(), 'Save')]");

    public void clickConfigureSupplier(){
        distributorUI.click(configureSupplierTxt);
    }

    public void clickOnIndependentCompEditDetails(){
        distributorUI.waitForVisibility(IndependentCompEditDetailsBtn);
        distributorUI.click(IndependentCompEditDetailsBtn);
    }

    public void clickOnOrderingSettings(){
        distributorUI.waitForVisibility(OrderingSettingsTab);
        distributorUI.click(OrderingSettingsTab);

    }

    public void clickSTurnOffPortalStockLevelToggle(){
        distributorUI.click(PortalStockLevelToggle);
    }

    public void clickTurnOffPortalStockSave(){
        distributorUI.scrollToElement(SaveBtn);
        distributorUI.waitForVisibility(SaveBtn);
        distributorUI.clickUsingJavaScript(SaveBtn);
    }

    public void clickOKOnSucessOverlay(){
        distributorUI.click(SuccessOkBtn);
    }

    public void clickOnCatalogSettingsTab(){
        distributorUI.waitForVisibility(CatalogSettingsTab);
        distributorUI.click(CatalogSettingsTab);
    }

    public void clickOnManualSelectionFromDropdown(String SelectDisableCatOption){
        distributorUI.click(disableCatalogDropDown);
        distributorUI.click(By.xpath(catalogOptionSelect.replace("CATALOGDROPDOWNOPTION",SelectDisableCatOption)));
    }

    public void clickCatalogSettingsSaveBtn(){
        distributorUI.scrollToElement(catalogSettingsSaveBtn);
        distributorUI.waitForVisibility(catalogSettingsSaveBtn);
        distributorUI.clickUsingJavaScript(catalogSettingsSaveBtn);
    }
}
