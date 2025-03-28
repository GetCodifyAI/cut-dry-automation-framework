package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class InternalToolsPage extends LoginPage {
    By configureSupplierTxt = By.xpath("//a[contains(text(),'Configure Supplier')]");
    By IndependentCompEditDetailsBtn = By.xpath("//tr[td[contains(text(),'Independent Foods Co')]]//a[contains(text(),'Edit Details')]");
    By OrderingSettingsTab = By.xpath("//a[contains(text(),'Ordering Settings')]");
    By PortalStockLevelToggle = By.xpath("//div[contains(text(), 'Display stock levels on Portal:')]/following-sibling::div//div[@class='react-switch-bg']");
    By toggleSwitchHandle = By.xpath("//div[contains(text(), 'Display stock levels on Portal:')]/following-sibling::div//div[contains(@class, 'react-switch-handle')]");
    By SaveBtn = By.xpath("//div[@class='text-right col']//button[text()='Save']");
    By SuccessOkBtn = By.xpath("//button[text()='OK']");
    By CatalogSettingsTab = By.xpath("//a[contains(text(),'Catalog Settings')]");
    By disableCatalogDropDown = By.xpath("(//div[contains(text(), 'Disable catalog for:')]/following-sibling::div//div)[2]");
    String catalogOptionSelect = "(//div[text()='CATALOGDROPDOWNOPTION'])[last()]";
    By catalogSettingsSaveBtn = By.xpath("//div[h5[text()='Catalog']]/following-sibling::div//button[contains(text(), 'Save')]");
    By lastOrderedPoundPriceToggle = By.xpath("//div[contains(text(), 'Show Last Ordered Pound Price in Order Guide')]/following-sibling::div//div[@class='react-switch-bg']");
    By sponsoredAdsRebatesTab = By.xpath("//a[contains(text(),'Sponsored Ads + Rebates')]");
    By sponsoredProductAdsToggle =By.xpath("//div[contains(text(), 'Enable Sponsored Product Advertisements')]/../following-sibling::div//div[@class='react-switch-bg']");
    By btn_saveProductAds = By.xpath("//div[contains(h4, 'Sponsored Ads')]/button[contains(@class, 'btn') and text()='Save']");
    By buyerEdgePlatformRebateToggle =By.xpath("//div[contains(text(), 'Buyers Edge Platform Rebates')]/../following-sibling::div//div[@class='react-switch-bg']");
    By buyerEdgePlatformRebateHandle = By.xpath("//div[contains(text(), 'Buyers Edge Platform Rebates')]/../following-sibling::div//div[@class='react-switch-handle']");
    By btn_saveRebate = By.xpath("//div[contains(h4, 'Rebates')]/button[contains(@class, 'btn') and text()='Save']");
    By lastOrderedPoundPriceToggleStable = By.xpath("//div[contains(text(), 'Show Last Ordered Pound Price in Order Guide')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By lastOrderedPoundPriceToggleStable1 = By.xpath("//div[contains(text(), 'Show Last Ordered Pound Price in Order Guide')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By orderMinimumGloballyToggleStable = By.xpath("//div[contains(text(), 'Enable Soft/Hard order minimum globally')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By orderMinimumGloballyToggleStable1 = By.xpath("//div[contains(text(), 'Enable Soft/Hard order minimum globally')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By orderMinimumDropDown = By.xpath("//div[text()='Order minimum type: ']/following-sibling::div/div");
    String orderMinimumDropDownOption = "(//div[text()='TYPE'])[last()]";
    By addOrderMinimum = By.xpath("//div[contains(text(),'Soft order Minimum Surcharge')]/following-sibling::div/input");
    By txt_success = By.xpath("//h2[contains(text(),'Success')]");
    By payDetailsTab = By.xpath("//a[contains(text(),'Pay Details')]");
    By payDetailsToggleStable = By.xpath("//label[contains(text(), 'Pay Enabled For All Users: ')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By payDetailsToggleStable1 = By.xpath("//label[contains(text(), 'Pay Enabled For All Users: ')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    String payEnableRestaurant = "//label[contains(text(), 'Pay Enabled Restaurants')]/following-sibling::div//div[text()='NAME']";
    String payDisableRestaurant = "//label[contains(text(), 'Pay Disabled Restaurants')]/following-sibling::div//div[text()='NAME']";
    String payEnableRestaurantDelete = "//label[contains(text(), 'Pay Enabled Restaurants')]/following-sibling::div//div[text()='NAME']/following-sibling::div";
    String payDisableRestaurantDelete = "//label[contains(text(), 'Pay Disabled Restaurants')]/following-sibling::div//div[text()='NAME']/following-sibling::div";
    By addCustomerToPayDisable = By.xpath("//label[contains(text(), 'Pay Disabled Restaurants')]/following-sibling::div/div");
    String selectDisableCustomer = "//div[contains(text(), 'NAME')]";
    By addCustomerToPayEnable = By.xpath("//label[contains(text(), 'Pay Enabled Restaurants')]/following-sibling::div/div");
    By checkboxLocatorCreditMemo = By.xpath("//label[contains(text(),'Enable Auto Apply Credit Memos')]/..//input");




    public void clickConfigureSupplier(){
        distributorUI.click(configureSupplierTxt);
    }

    public void clickOnIndependentCompEditDetails(){
        distributorUI.waitForVisibility(IndependentCompEditDetailsBtn);
        distributorUI.click(IndependentCompEditDetailsBtn);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnOrderingSettings(){
        distributorUI.waitForVisibility(OrderingSettingsTab);
        distributorUI.click(OrderingSettingsTab);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickSTurnOffPortalStockLevelToggle(){
        distributorUI.click(PortalStockLevelToggle);
    }

    public void ensurePortalStockLevelToggle(boolean enable) throws InterruptedException {
        distributorUI.waitForVisibility(toggleSwitchHandle);
        distributorUI.waitForCustom(3000);
        String transformValue = distributorUI.getCssValue(toggleSwitchHandle, "transform");
        boolean isCurrentlyEnabled = transformValue != null && transformValue.contains("matrix(1, 0, 0, 1, 29, 0)");
        if (enable && !isCurrentlyEnabled) {
            distributorUI.click(toggleSwitchHandle);
            distributorUI.waitForCustom(3000);
            System.out.println("Change into true");
        } else if (!enable && isCurrentlyEnabled) {
            distributorUI.click(toggleSwitchHandle);
            distributorUI.waitForCustom(3000);
            System.out.println("Change into false");
        }
    }


    public void clickTurnOffPortalStockSave(){
        distributorUI.scrollToElement(SaveBtn);
        distributorUI.waitForVisibility(SaveBtn);
        distributorUI.clickUsingJavaScript(SaveBtn);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public void clickTurnOnLastOrderedPoundPriceToggle(){
        distributorUI.click(lastOrderedPoundPriceToggle);
    }

    public void clickTurnOnLastOrderedPoundPriceToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(lastOrderedPoundPriceToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(lastOrderedPoundPriceToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(lastOrderedPoundPriceToggleStable1);
        }
    }

    public void clickTurnOnLastOrderedPoundPriceSave(){
        distributorUI.scrollToElement(SaveBtn);
        distributorUI.waitForVisibility(SaveBtn);
        distributorUI.clickUsingJavaScript(SaveBtn);
    }

    public void clickTurnOffLastOrderedPoundPriceToggle(){
        distributorUI.click(lastOrderedPoundPriceToggle);
    }
    public void clickOnSponsoredAdsRebates(){
        distributorUI.waitForVisibility(sponsoredAdsRebatesTab);
        distributorUI.click(sponsoredAdsRebatesTab);
    }
    public void clickSponsoredProductAdsToggle(){
        distributorUI.click(sponsoredProductAdsToggle);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickProductAdsSave(){
        distributorUI.waitForVisibility(btn_saveProductAds);
        distributorUI.click(btn_saveProductAds);
    }
    public void clickBuyerEdgePlatformRebateToggle(){
        distributorUI.click(buyerEdgePlatformRebateToggle);
    }

    public void clickBuyerEdgePlatformRebateToggle(boolean enable) {
        String handlePosition = distributorUI.getElement(buyerEdgePlatformRebateHandle).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(buyerEdgePlatformRebateToggle);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(buyerEdgePlatformRebateToggle);
        }
    }

    public void clickRebateSave(){
        distributorUI.clickWithScrollAndHover(btn_saveRebate);
    }
    public void clickTurnOnOrderMinimumGloballyToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(orderMinimumGloballyToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(orderMinimumGloballyToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(orderMinimumGloballyToggleStable1);
        }
    }
    public void clickOnOrderMinimumDropdown(String type){
        distributorUI.click(orderMinimumDropDown);
        distributorUI.click(By.xpath(orderMinimumDropDownOption.replace("TYPE",type)));
    }
    public void enterOrderMinimum(String minimum){
        distributorUI.click(addOrderMinimum);
        distributorUI.clear(addOrderMinimum);
        distributorUI.sendKeys(addOrderMinimum,minimum);
    }
    public void clickSave(){
        distributorUI.scrollToElement(SaveBtn);
        distributorUI.waitForVisibility(SaveBtn);
        distributorUI.clickUsingJavaScript(SaveBtn);
    }
    public boolean isSuccessPopUpDisplayed(){
        return distributorUI.isDisplayed(txt_success);
    }
    public void navigateToPayDetailsTab(){
        distributorUI.waitForVisibility(payDetailsTab);
        distributorUI.click(payDetailsTab);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickPayEnabledToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(payDetailsToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(payDetailsToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(payDetailsToggleStable1);
        }
    }
    public boolean isPayEnableRestaurantDisplayed(String name){
        return distributorUI.isDisplayed(By.xpath(payEnableRestaurant.replace("NAME", name)));
    }
    public boolean isPayDisableRestaurantDisplayed(String name){
        return distributorUI.isDisplayed(By.xpath(payDisableRestaurant.replace("NAME", name)));
    }
    public void deleteRestaurantInPayEnable(String name){
        distributorUI.click(By.xpath(payEnableRestaurantDelete.replace("NAME", name)));
    }
    public void addCustomerToPayDisable(String name)throws InterruptedException{
        distributorUI.click(addCustomerToPayDisable);
        distributorUI.scrollToElement(By.xpath(selectDisableCustomer.replace("NAME", name)));
        distributorUI.click(By.xpath(selectDisableCustomer.replace("NAME", name)));
        distributorUI.waitForCustom(3000);
    }
    public void deleteRestaurantInPayDisable(String name){
        distributorUI.click(By.xpath(payDisableRestaurantDelete.replace("NAME", name)));
    }
    public void addCustomerToPayEnable(String name)throws InterruptedException{
        distributorUI.click(addCustomerToPayEnable);
        distributorUI.scrollToElement(By.xpath(selectDisableCustomer.replace("NAME", name)));
        distributorUI.click(By.xpath(selectDisableCustomer.replace("NAME", name)));
        distributorUI.waitForCustom(3000);
    }
    public void clickCreditMemoCheckbox(boolean enable) {

        boolean isChecked = distributorUI.getElement(checkboxLocatorCreditMemo).isSelected();

        if (enable && !isChecked) {
            distributorUI.click(checkboxLocatorCreditMemo); // Check the box if not checked
        } else if (!enable && isChecked) {
            distributorUI.click(checkboxLocatorCreditMemo); // Uncheck the box if already checked
        }
    }

}
