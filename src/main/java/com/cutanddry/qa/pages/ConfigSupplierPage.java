package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ConfigSupplierPage extends LoginPage{
    By txt_configSupplier = By.xpath("//h2[contains(text(), 'Configure Suppliers')]");
    String btn_editDetails = "//tr[td[contains(text(), 'NAME')]]//a[contains(text(), 'Edit Details')]";
    By btn_catalogSettings = By.xpath("//a[contains(text(), 'Catalog Settings')]");
    By btn_orderSettings = By.xpath("//a[contains(text(), 'Ordering Settings')]");
    By btn_saveCatalog = By.xpath("//h5[text()='Catalog']/following::button[contains(text(), 'Save')]");
    By lbl_OGSugTool= By.xpath("//label[contains(text(), 'OG Suggestive Sales Tool')]/following-sibling::div//div[contains(@class, 'react-switch-handle')]");
    By st_activeOGSugTool = By.xpath("//label[contains(text(), 'Show only stock available items on OG Suggestive Sales Tool')]/following-sibling::div//input[@type='checkbox' and ../div[contains(@style, 'background: rgb(0, 136, 0)')]]");
    By enableDefaultSearchFilterToggle = By.xpath("//*[contains(text(), 'Enable Default Search Filter')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By enableDefaultSearchFilterToggle1 = By.xpath("//*[contains(text(), 'Enable Default Search Filter')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");

    By lbl_defaultOrderHistory = By.xpath("//*[contains(text(), 'Default Sort for Order History')]/following-sibling::div//*[text()='Order Date']");
    By lbl_OrderHistoryDropDow = By.xpath("//*[contains(text(), 'Default Sort for Order History')]/following-sibling::div//div[contains(@class,'themed_select__indicators')]");
    By lbl_defaultOrderHistoryAsOrderDate = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Order Date']");
    By btn_saveOrderSetting = By.xpath("//h5[text()='Ordering Settings']/following::button[contains(text(), 'Save')]");
    By btn_saveOKOrderSetting = By.xpath("//button[contains(text(),'OK')]");

    By lbl_DefaultViewPortalDropDow = By.xpath("//*[contains(text(), 'Default View for Portal Users')]/following-sibling::div//div[contains(@class,'themed_select__indicators')]");
    By lbl_DefaultViewPortalAsSimpleList = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Simple List']");
    By lbl_DefaultViewPortalAsQuickAdd = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Quick Add']");
    By lbl_DefaultViewPortalAsOrderGuide = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Order Guide']");
    By lbl_DefaultViewPortalAsCatalog = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Catalog']");
    By lbl_DefaultViewPortalAsScanToOrder = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Scan to Order']");

    By lbl_QuickAddViewDropDow = By.xpath("//*[contains(text(), 'Quick Add View:')]/following-sibling::div//div[contains(@class,'themed_select__indicators')]");
    By lbl_QuickAddEnable = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Enabled only on DP Portal']");
    By lbl_SimpleListViewDropDow = By.xpath("//*[contains(text(), 'Simple List View:')]/following-sibling::div//div[contains(@class,'themed_select__indicators')]");
    By lbl_SimpleListEnable = By.xpath("//div[contains(@class, 'themed_select__option') and text()='Enabled only on DP Portal']");

    By lbl_defaultOrderHistoryAsSimpleList = By.xpath("//*[contains(text(), 'Default View for Portal Users')]/following-sibling::div//*[text()='Simple List']");
    By lbl_defaultOrderHistoryAsQuickAdd = By.xpath("//*[contains(text(), 'Default View for Portal Users')]/following-sibling::div//*[text()='Quick Add']");
    By lbl_defaultOrderHistoryAsOrderGuide = By.xpath("//*[contains(text(), 'Default View for Portal Users')]/following-sibling::div//*[text()='Order Guide']");
    By lbl_defaultOrderHistoryAsCatalog = By.xpath("//*[contains(text(), 'Default View for Portal Users')]/following-sibling::div//*[text()='Catalog']");
    By lbl_defaultOrderHistoryAsScanToOrder = By.xpath("//*[contains(text(), 'Default View for Portal Users')]/following-sibling::div//*[text()='Scan to Order']");

    By lbl_defaultQuickAddView = By.xpath("//*[contains(text(), 'Quick Add View:')]/following-sibling::div//*[text()='Enabled on DP Portal & Operator App']");
    By lbl_defaultSimpleListView = By.xpath("//*[contains(text(), 'Simple List View:')]/following-sibling::div//*[text()='Enabled on DP Portal & Operator App']");


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
    public void clickOnOrderSettings() {
        distributorUI.waitForVisibility(btn_orderSettings);
        distributorUI.clickUsingJavaScript(btn_orderSettings);
    }
    public void clickOnSave() {
        distributorUI.waitForVisibility(btn_saveCatalog);
        distributorUI.clickUsingJavaScript(btn_saveCatalog);
        distributorUI.waitForElementEnabledState(btn_saveCatalog, true);
    }
    public void toggleOnOGSuggestiveTool() throws InterruptedException {
        if (!distributorUI.isDisplayed(st_activeOGSugTool)){
            distributorUI.waitForVisibility(lbl_OGSugTool);
            distributorUI.waitForCustom(2000);
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

    public void ensureDefaultSearchFilterStatus(boolean enable) {

        String handlePosition = distributorUI.getElement(enableDefaultSearchFilterToggle).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(29px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(enableDefaultSearchFilterToggle1);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(enableDefaultSearchFilterToggle1);
        }
    }

    public boolean isDefaultOrderHistoryAsOrderDateDisplayed(){
        return distributorUI.isDisplayed(lbl_defaultOrderHistory,5);
    }

    public void clickOnDefaultOrderHistoryAsOrderDate(){
        distributorUI.click(lbl_OrderHistoryDropDow);
        distributorUI.waitForVisibility(lbl_defaultOrderHistoryAsOrderDate);
        distributorUI.click(lbl_defaultOrderHistoryAsOrderDate);
    }

    public void saveOrderSettingChanges() throws InterruptedException {
        distributorUI.clickWithScrollAndHover(btn_saveOrderSetting);
        distributorUI.click(btn_saveOKOrderSetting);
        distributorUI.waitForCustom(4000);
    }

    public void clickOnDefaultViewPortalAsSimpleList() {
        distributorUI.click(lbl_DefaultViewPortalDropDow);
        distributorUI.waitForVisibility(lbl_DefaultViewPortalAsSimpleList);
        distributorUI.click(lbl_DefaultViewPortalAsSimpleList);
    }

    public void clickOnDefaultViewPortalAsQuickAdd() {
        distributorUI.click(lbl_DefaultViewPortalDropDow);
        distributorUI.waitForVisibility(lbl_DefaultViewPortalAsQuickAdd);
        distributorUI.click(lbl_DefaultViewPortalAsQuickAdd);
    }

    public void clickOnDefaultViewPortalAsOrderGuide() {
        distributorUI.click(lbl_DefaultViewPortalDropDow);
        distributorUI.waitForVisibility(lbl_DefaultViewPortalAsOrderGuide);
        distributorUI.click(lbl_DefaultViewPortalAsOrderGuide);
    }

    public void clickOnDefaultViewPortalAsCatalog() {
        distributorUI.click(lbl_DefaultViewPortalDropDow);
        distributorUI.waitForVisibility(lbl_DefaultViewPortalAsCatalog);
        distributorUI.click(lbl_DefaultViewPortalAsCatalog);
    }

    public void clickOnDefaultViewPortalAsScanToOrder() {
        distributorUI.click(lbl_DefaultViewPortalDropDow);
        distributorUI.waitForVisibility(lbl_DefaultViewPortalAsScanToOrder);
        distributorUI.click(lbl_DefaultViewPortalAsScanToOrder);
    }

    public void clickOnDefaultQuickAdd() {
        distributorUI.click(lbl_QuickAddViewDropDow);
        distributorUI.waitForVisibility(lbl_QuickAddEnable);
        distributorUI.click(lbl_QuickAddEnable);
    }

    public void clickOnDefaultSimpleList() {
        distributorUI.click(lbl_SimpleListViewDropDow);
        distributorUI.waitForVisibility(lbl_SimpleListEnable);
        distributorUI.click(lbl_SimpleListEnable);
    }

    public boolean isDefaultViewPortalAsSimpleListDisplayed() {
        return distributorUI.isDisplayed(lbl_defaultOrderHistoryAsSimpleList, 5);
    }

    public boolean isDefaultViewPortalAsQuickAddDisplayed() {
        return distributorUI.isDisplayed(lbl_defaultOrderHistoryAsQuickAdd, 5);
    }

    public boolean isDefaultViewPortalAsOrderGuideDisplayed() {
        return distributorUI.isDisplayed(lbl_defaultOrderHistoryAsOrderGuide, 5);
    }

    public boolean isDefaultViewPortalAsCatalogDisplayed() {
        return distributorUI.isDisplayed(lbl_defaultOrderHistoryAsCatalog, 5);
    }

    public boolean isDefaultViewPortalAsScanToOrderDisplayed() {
        return distributorUI.isDisplayed(lbl_defaultOrderHistoryAsScanToOrder, 5);
    }

    public boolean isDefaultQuickAddDisplayed() {
        return distributorUI.isDisplayed(lbl_defaultQuickAddView, 5);
    }

    public boolean isDefaultSimpleListDisplayed() {
        return distributorUI.isDisplayed(lbl_defaultSimpleListView, 5);
    }

}
