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
    By sponsoredAdsRebatesTab = By.xpath("//a[contains(text(),'Cut+Dry Influence')]");
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
    By deleteEmailNotificationToggleStable = By.xpath("//label[contains(text(), 'Customer User Deletion Email Notifications:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By deleteEmailNotificationToggleStable1 = By.xpath("//label[contains(text(), 'Customer User Deletion Email Notifications:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By btn_notificationSave = By.xpath("//h5[text()='Notifications']/../following-sibling::div/button[text()='Save']");
    By enableSponsoredProductAdvertisementsToggle = By.xpath("//*[contains(text(), 'Enable Cut+Dry Sponsored Product Advertisements')]/../following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By instacartSponsoredProductAdvertisementsToggle = By.xpath("//*[contains(text(), 'Enable Instacart Sponsored Product Advertisements')]/../following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By enableSponsoredProductAdvertisementsToggle1 = By.xpath("//*[contains(text(), 'Enable Cut+Dry Sponsored Product Advertisements')]/../following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    String InternalToolCompanyEditDetailsBtn = "//tr[td[contains(text(),'NAME')]]//a[contains(text(),'Edit Details')]";
    By restrictSpotPricesToggleStable = By.xpath("//div[contains(text(), 'Restrict spot prices below minimum product price')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By restrictSpotPricesToggleStable1 = By.xpath("//div[contains(text(), 'Restrict spot prices below minimum product price')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By offlineOrderingToggleStable = By.xpath("//div[contains(text(), 'Offline Ordering Enabled:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By offlineOrderingToggleStable1 = By.xpath("//div[contains(text(), 'Offline Ordering Enabled:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By checkboxLocatorChat = By.xpath("//label[contains(text(),'Chat')]/..//input");
    By btn_saveChanges = By.xpath("(//button[text()='Save Changes'])[1]");
    By addDisableOperatorCatalog = By.xpath("//div[contains(text(), 'Select operators to disable catalog')]/following-sibling::div/div");
    String catalogDisableRestaurantDelete = "//div[contains(text(), 'Select operators to disable catalog')]/following-sibling::div//div[text()='NAME']/following-sibling::div";
    String catalogDisableRestaurant = "//div[contains(text(), 'Select operators to disable catalog')]/following-sibling::div//div[text()='NAME']";
    By displayMarginOnPortalToggleStable = By.xpath("//div[contains(text(), 'Display margin on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By displayMarginOnPortalToggleStable1 = By.xpath("//div[contains(text(), 'Display margin on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By restrictMarginOnPortalToggleStable = By.xpath("//div[contains(text(), 'Restrict margin on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By restrictMarginOnPortalToggleStable1 = By.xpath("//div[contains(text(), 'Restrict margin on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By spotPricingOnPortalToggleStable = By.xpath("//div[contains(text(), 'Spot pricing on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By spotPricingOnPortalToggleStable1 = By.xpath("//div[contains(text(), 'Spot pricing on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By displayPurchaseHistoryToggleStable = By.xpath("//div[contains(text(), 'Display Purchase History')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By displayPurchaseHistoryToggleStable1 = By.xpath("//div[contains(text(), 'Display Purchase History')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By displayPurchasePriceToggleStable = By.xpath("//div[contains(text(), 'Display Purchase Price on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By displayPurchasePriceToggleStable1 = By.xpath("//div[contains(text(), 'Display Purchase Price on Portal')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By specialItemsDropDown = By.xpath("(//label[contains(text(), 'Special items')]/following-sibling::div//div)[2]");
    String specialItemsDropDownOption = "(//div[text()='OPTION'])[last()]";
    By hideOutOfStockToggleStable = By.xpath("//div[contains(text(), 'Hide out of stock label on Supplier Portal:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By hideOutOfStockToggleStable1 = By.xpath("//div[contains(text(), 'Hide out of stock label on Supplier Portal:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By simpleListViewDropDown = By.xpath("//div[text()='Simple List View:']/following-sibling::div/div");
    String simpleListViewDropDownOption = "(//div[text()='TYPE'])[last()]";
    By fetchPricesFromOrderForEditOrderFlowToggleStable = By.xpath("//div[contains(text(), 'Fetch prices from order for edit order flow:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By fetchPricesFromOrderForEditOrderFlowToggleStable1 = By.xpath("//div[contains(text(), 'Fetch prices from order for edit order flow:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By quickAddViewDropDown = By.xpath("//div[text()='Quick Add View:']/following-sibling::div/div");
    String quickAddViewDropDownOption = "(//div[text()='TYPE'])[last()]";
    By defaultViewForOperatorDropDown = By.xpath("Default View for Operators:']/following-sibling::div/div");
    String defaultViewForOperatorDropDownOption = "(//div[text()='TYPE'])[last()]";
    By defaultViewForPortalUsersDropDown = By.xpath("//div[text()='Default View for Operators:']/following-sibling::div/div");
    String defaultViewForPortalUsersDropDownOption = "(//div[text()='TYPE'])[last()]";
    By manualOrderQuantityCalculationToggleStable = By.xpath("//div[contains(text(), 'Enable manual Order Quantity calculation in OG:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By manualOrderQuantityCalculationToggleStable1 = By.xpath("//div[contains(text(), 'Enable manual Order Quantity calculation in OG:')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By supplierPortalAccountHoldAlertsStatus = By.xpath("//div[contains(text(),'Enable Account Hold Alerts for Supplier Portal')]/following-sibling::div//div[@class='react-switch-handle']");
    By supplierPortalAccountHoldAlerts = By.xpath("//div[contains(text(),'Enable Account Hold Alerts for Supplier Portal')]/following-sibling::div//div[@class='react-switch-handle']/parent::div/div[1]");
    By preAuthfeature = By.xpath("(//*[contains(text(),'Allow hard hold customer to place order by pre-auth Credit Card')]/following-sibling::div//input)[1]");
    By preAuthDpPortal =By.xpath("//*[contains(text(),'Allow hard hold customer to place order by pre-auth Credit Card on DP portal')]/following-sibling::div//input");
    By btn_DPGroupManager = By.xpath("//a[contains(text(),'DP Group Manager')]");
    By txt_DPGroupManager = By.xpath("//h5[contains(text(),'DP Group Manager')]");
    By btn_createGroup = By.xpath("//button[contains(text(),'Create Group')]");
    By txt_createNewDPGroup = By.xpath("//div[contains(text(),'Create New DP Group')]");
    By lbl_groupName = By.xpath("//label[text()='Group Name']/following-sibling::input");
    By lbl_description = By.xpath("//label[text()='Description']/following-sibling::textarea");
    By lbl_attached_DPs = By.xpath("//label[text()='Attached DPs']/following-sibling::div");
    String attachedDps = "//label[text()='Attached DPs']/following-sibling::*//div[text()='DISTRIBUTOR']";
    By allowCompanySwitching = By.xpath("//label[text()='Allow Company Switching']/preceding-sibling::input[@type='checkbox']");
    By createGroup = By.xpath("(//button[contains(text(),'Create Group')])[2]");
    String companyNameDisplay = "(//td/strong[text()='NAME'])[1]";
    String companyDescriptionDisplay = "(//td[text()='DESCRIPTION'])[1]";
    String companySwitchingDisplay = "(//td/span[text()='STATUS'])[1]";
    String vendorsDisplay = "(//td/div/span[text()='VENDOR'])[1]";
    By btn_editDPGroup = By.xpath("(//button[text()='Edit'])[1]");
    By btn_updateDPGroup = By.xpath("//button[text()='Update Group']");
    By txt_editNewDPGroup = By.xpath("//div[contains(text(),'Edit DP Group')]");
    By btn_deleteDPGroup = By.xpath("(//button[text()='Delete'])[1]");
    By task_management = By.xpath("//*[normalize-space()='Task Management']");
    String taskManagerParentChildTask = "//div[normalize-space()='copyToChildForms']/../following-sibling::td[normalize-space()='FORMID']";
    String ParentChildTaskRunLocallyBtn =  "//div[normalize-space()='copyToChildForms']/../following-sibling::td[normalize-space()='FORMID']//ancestor::td/following::td//button[contains(text(),'Run Locally')]";
    By isTaskRunAttemptedDisplayed = By.xpath("//*[contains(text(),'Task run attempted')]");
    By parentChildRelationshipTask = By.xpath("//div[normalize-space()='createParentChildOrderGuideRelationships']//ancestor::td/following::td//button[contains(text(),'Run Locally')]");
    By catalogOnlyOrderFlowToggleStable = By.xpath("//div[contains(text(), 'Catalog Only Order Flow')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By catalogOnlyOrderFlowToggleStable1 = By.xpath("//div[contains(text(), 'Catalog Only Order Flow')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");










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

    public void clickCatalogSettingsSaveBtn() throws InterruptedException {
        distributorUI.scrollToElement(catalogSettingsSaveBtn);
        distributorUI.waitForVisibility(catalogSettingsSaveBtn);
        distributorUI.clickUsingJavaScript(catalogSettingsSaveBtn);
        distributorUI.waitForCustom(3000);
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
//        distributorUI.waitForVisibility(sponsoredAdsRebatesTab);
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

    public void clickSponsoredProductAdsToggle(boolean enable) {
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String handlePosition = distributorUI.getElement(enableSponsoredProductAdvertisementsToggle).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(enableSponsoredProductAdvertisementsToggle1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(enableSponsoredProductAdvertisementsToggle1);
        }
    }

    public void clickInstacartSponsoredProductAdsToggle(boolean enable) {
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String handlePosition = distributorUI.getElement(instacartSponsoredProductAdvertisementsToggle).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithFallback(instacartSponsoredProductAdvertisementsToggle);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithFallback(instacartSponsoredProductAdvertisementsToggle);
        }
    }

    public void clickProductAdsSave() throws InterruptedException {
//        distributorUI.waitForVisibility(btn_saveProductAds);
        distributorUI.waitForCustom(2000);
        distributorUI.scrollToElementStable(sponsoredAdsRebatesTab,2000);
        distributorUI.click(btn_saveProductAds);
        distributorUI.waitForCustom(5000);
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

    public void clickRebateSave() throws InterruptedException {
        distributorUI.clickWithScrollAndHover(btn_saveRebate);
        distributorUI.waitForCustom(5000);
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
    public void clickSave() {
        distributorUI.clickWithScrollAndHover(SaveBtn);
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
    public void clickUserDeletionEmailNotificationsToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(deleteEmailNotificationToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(deleteEmailNotificationToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(deleteEmailNotificationToggleStable1);
        }
    }
    public void clickNotificationSave()throws InterruptedException{
        distributorUI.scrollToElement(btn_notificationSave);
        distributorUI.waitForVisibility(btn_notificationSave);
        distributorUI.clickUsingJavaScript(btn_notificationSave);
        distributorUI.waitForCustom(3000);
    }
    public void clickOnInternalToolCompanyEditDetails(String name){
        distributorUI.waitForVisibility(By.xpath(InternalToolCompanyEditDetailsBtn.replace("NAME",name)));
        distributorUI.click(By.xpath(InternalToolCompanyEditDetailsBtn.replace("NAME",name)));
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void restrictSpotPricesToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(restrictSpotPricesToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(restrictSpotPricesToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(restrictSpotPricesToggleStable1);
        }
    }
    public boolean isRestrictSpotPricesToggleOn() {
        String handlePosition = distributorUI.getElement(restrictSpotPricesToggleStable).getAttribute("style");
        return handlePosition.contains("translateX(29px)");
    }
    public void clickOfflineOrderingToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(offlineOrderingToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(offlineOrderingToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(offlineOrderingToggleStable1);
        }
    }
    public void clickChatCheckbox(boolean enable) {

        boolean isChecked = distributorUI.getElement(checkboxLocatorChat).isSelected();

        if (enable && !isChecked) {
            distributorUI.click(checkboxLocatorChat); // Check the box if not checked
        } else if (!enable && isChecked) {
            distributorUI.click(checkboxLocatorChat); // Uncheck the box if already checked
        }
    }
    public void clickSaveChanges()throws InterruptedException{
        distributorUI.click(btn_saveChanges);
    }
    public void addCustomerToCatalogDisable(String name)throws InterruptedException{
        distributorUI.click(addDisableOperatorCatalog);
        distributorUI.scrollToElement(By.xpath(selectDisableCustomer.replace("NAME", name)));
        distributorUI.clickUsingJavaScript(By.xpath(selectDisableCustomer.replace("NAME", name)));
        distributorUI.waitForCustom(3000);
    }
    public void deleteRestaurantInCatalogDisable(String name){
        distributorUI.click(By.xpath(catalogDisableRestaurantDelete.replace("NAME", name)));
    }
    public boolean isCatalogDisableRestaurantDisplay(String name){
        return distributorUI.isDisplayed(By.xpath(catalogDisableRestaurant.replace("NAME", name)));
    }
    public void displayMarginOnPortalToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(displayMarginOnPortalToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(displayMarginOnPortalToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(displayMarginOnPortalToggleStable1);
        }
    }
    public void restrictMarginOnPortalToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(restrictMarginOnPortalToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(restrictMarginOnPortalToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(restrictMarginOnPortalToggleStable1);
        }
    }
    public void spotPricingOnPortalToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(spotPricingOnPortalToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(spotPricingOnPortalToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(spotPricingOnPortalToggleStable1);
        }
    }
    public void displayPurchaseHistoryToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(displayPurchaseHistoryToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(displayPurchaseHistoryToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(displayPurchaseHistoryToggleStable1);
        }
    }
    public void displayPurchasePriceToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(displayPurchasePriceToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(displayPurchasePriceToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(displayPurchasePriceToggleStable1);
        }
    }
    public void selectSpecialItemsDropdown(String option){
        distributorUI.click(specialItemsDropDown);
        distributorUI.click(By.xpath(specialItemsDropDownOption.replace("OPTION",option)));
    }
    public void clickHideOutOfStockToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(hideOutOfStockToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(hideOutOfStockToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(hideOutOfStockToggleStable1);
        }
    }
    public void clickOnSimpleListViewDropdown(String type){
        distributorUI.click(simpleListViewDropDown);
        distributorUI.click(By.xpath(simpleListViewDropDownOption.replace("TYPE",type)));
    }
    public void fetchPricesFromOrderForEditOrderFlowToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(fetchPricesFromOrderForEditOrderFlowToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(fetchPricesFromOrderForEditOrderFlowToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(fetchPricesFromOrderForEditOrderFlowToggleStable1);
        }
    }
    public void clickOnQuickAddViewDropDown(String type){
        distributorUI.click(quickAddViewDropDown);
        distributorUI.click(By.xpath(quickAddViewDropDownOption.replace("TYPE",type)));
    }
    public void clickOnDefaultViewForOperatorDropDown(String type){
        distributorUI.click(defaultViewForOperatorDropDown);
        distributorUI.click(By.xpath(defaultViewForOperatorDropDownOption.replace("TYPE",type)));
    }
    public void clickOnDefaultViewForPortalUsersDropDown(String type){
        distributorUI.click(defaultViewForPortalUsersDropDown);
        distributorUI.click(By.xpath(defaultViewForPortalUsersDropDownOption.replace("TYPE",type)));
    }
    public void manualOrderQuantityCalculationToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(manualOrderQuantityCalculationToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(manualOrderQuantityCalculationToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(manualOrderQuantityCalculationToggleStable1);
        }
    }

    public void enableAccountHoldAlerts(boolean enable) {
        String handlePosition = distributorUI.getElement(supplierPortalAccountHoldAlertsStatus).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(supplierPortalAccountHoldAlerts);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(supplierPortalAccountHoldAlerts);
        }
    }

    public void enablePreAuth(boolean enable) {
        boolean isCheckedFeature = distributorUI.isCheckboxOrRadioBtnSelected(preAuthfeature);
        boolean isCheckedDpPortal = distributorUI.isCheckboxOrRadioBtnSelected(preAuthDpPortal);

        if (enable && !isCheckedDpPortal ) {
            distributorUI.click(preAuthDpPortal); // Check the box if not checked
        }if (enable && !isCheckedFeature ) {
            distributorUI.click(preAuthfeature); // Check the box if not checked
        }
        if (!enable && isCheckedDpPortal) {
            distributorUI.click(preAuthDpPortal); // Uncheck the box if already checked
        }
        if (!enable && isCheckedFeature) {
            distributorUI.click(preAuthfeature); // Uncheck the box if already checked
        }

    }
    public void navigateToDPGroupManager(){
        distributorUI.click(btn_DPGroupManager);
    }
    public boolean isDPGroupManageTextDisplayed(){
        return distributorUI.isDisplayed(txt_DPGroupManager);
    }
    public void clickCreateButton(){
        distributorUI.click(btn_createGroup);
    }
    public boolean isCreateNewDPGroupTextDisplayed(){
        return distributorUI.isDisplayed(txt_createNewDPGroup);
    }
    public void enterGroupName(String name){
        distributorUI.click(lbl_groupName);
        distributorUI.clear(lbl_description);
        distributorUI.sendKeys(lbl_groupName,name);
    }
    public void enterDescription(String description){
        distributorUI.click(lbl_description);
        distributorUI.clear(lbl_description);
        distributorUI.sendKeys(lbl_description,description);
    }
    public void clickaAttachedDPs(String dp){
        distributorUI.click(lbl_attached_DPs);
        distributorUI.clickUsingJavaScript(By.xpath(attachedDps.replace("DISTRIBUTOR",dp)));
    }
    public void clickAllowCompanySwitching(){
        distributorUI.click(allowCompanySwitching);
    }
    public void clickGroupCreate(){
        distributorUI.click(createGroup);
    }
    public boolean isCompanyNameDisplay(String name){
        return distributorUI.isDisplayed(By.xpath(companyNameDisplay.replace("NAME",name)));
    }
    public boolean isCompanyDescriptionDisplay(String description){
        return distributorUI.isDisplayed(By.xpath(companyDescriptionDisplay.replace("DESCRIPTION",description)));
    }
    public boolean isCompanySwitchingDisplay(String status){
        return distributorUI.isDisplayed(By.xpath(companySwitchingDisplay.replace("STATUS",status)));
    }
    public boolean isVendorsDisplay(String vendor){
        return distributorUI.isDisplayed(By.xpath(vendorsDisplay.replace("VENDOR",vendor)));
    }
    public void clickGroupEdit(){
        distributorUI.click(btn_editDPGroup);
    }
    public void clickUpdateDPGroup(){
        distributorUI.click(btn_updateDPGroup);
    }
    public boolean isEditDPGroupTextDisplayed(){
        return distributorUI.isDisplayed(txt_editNewDPGroup);
    }
    public void clickGroupDPDelete(){
        distributorUI.click(btn_deleteDPGroup);
    }

    public void scrollToConfigureSupplierPageHeader() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        distributorUI.uiScrollTop();
    }
    public void clickOnTaskManagement(){
        distributorUI.click(task_management);
    }
    public boolean isParentChildTaskDisplayed(String formID){
        return distributorUI.isDisplayed(By.xpath(taskManagerParentChildTask.replace("FORMID","Form " + formID)));
    }
    public void clickRunLocallyOnParentChildTask(String formID){
        distributorUI.waitForVisibility(By.xpath(ParentChildTaskRunLocallyBtn.replace("FORMID","Form " + formID)));
        distributorUI.click(By.xpath(ParentChildTaskRunLocallyBtn.replace("FORMID","Form " + formID)));
    }
    public boolean isTaskAttemptedDisplayed(){
        return distributorUI.isDisplayed(isTaskRunAttemptedDisplayed);
    }
    public void clickRunLocallyOnParentChildRelationshipTask(){
        distributorUI.waitForVisibility(parentChildRelationshipTask);
        distributorUI.click(parentChildRelationshipTask);
    }

    public void refreshPage(){
        distributorUI.refreshPage();
    }

    public void clickCatalogOnlyOrderFlowToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(catalogOnlyOrderFlowToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(catalogOnlyOrderFlowToggleStable1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(catalogOnlyOrderFlowToggleStable1);
        }
    }




}
