package com.cutanddry.qa.pages;

import com.cutanddry.qa.data.models.Broadcast;
import com.cutanddry.qa.utils.JsonUtil;
import org.openqa.selenium.By;

public class BoostPage extends LoginPage {
    By txt_boost = By.xpath("//li[contains(text(),'Boost')]");
    By btn_addMessage = By.xpath("//button[text()='Add Message']");
    By txt_step1 = By.xpath("//div[text()='Step 1 - Select your message recepients']");
    By dropdown_customers = By.xpath("//div[contains(@class, 'themed_select__single-value') and text()='All Customers']");
    By option_customList = By.xpath("//div[text()='Custom List']");
    By txt_selectCustomers = By.xpath("//label[text()='Select Customers:']");
    By dropdown_customList = By.xpath("//div[contains(@class, 'themed_select__placeholder') and text()='Search...']");
    String txt_customerName = "//div[@class='d-flex align-items-center' and contains(., 'NAME')]";
    By txt_selectionCount = By.xpath("//div[text()='1 customers selected for your broadcast.']");
    By btn_continue = By.xpath("//button[text()='Continue']");
    By dropdown_search = By.xpath("//div[contains(@class, 'themed_select__placeholder') and text()='Search...']/following-sibling::div//input");
    By txt_step2 = By.xpath("//div[text()='Step 2 - Customise your message']");
    By btn_submit = By.xpath("//button[text()='Submit']");
    By type_message = By.xpath("//textarea[@placeholder='Type your message here...']");
    By add_url = By.xpath("//input[@placeholder='Add a URL to your message...']");
    By btn_clear = By.xpath("//div[contains(@class, 'themed_select__clear-indicator')]");
    By txt_broadcastSuccess = By.xpath("//div[text()='Broadcast message created successfully']");
    By btn_ok = By.xpath("//button[text()='OK']");
    By btn_X = By.xpath("//tr[td[contains(text(),'Test Broadcast Message')]]//button//*[name()='svg' and @data-icon='times-circle']");
    By btn_yes = By.xpath("//button[text()='Yes']");
    By tab_suggestiveSales = By.xpath("//a[text()='Suggestive Sales']");
    By txt_topCategoryPicks = By.xpath("//td[text()='Top Category Picks']");
    By btn_topPicks_config = By.xpath("//tr[td[contains(text(), 'Top Category Picks')]]//button[contains(text(), 'View & Configure')]");
    By txt_popupTopCategoryPick = By.xpath("//div[text()='Select a category to configure']");
    By btn_allItemConfig = By.xpath("//tr[td[text()='All Items']]//button[contains(text(), 'Configure')]");
    By btn_addItems = By.xpath("//button[text()='Add Items']");
    By input_addItem = By.xpath("//div[text()='Search items by name or code']/following-sibling::div//input");
    String txt_itemAdded = "//div[contains(text(), 'CODE')]";
    By btn_add = By.xpath("//button[text()='Add']");
    By btn_close = By.xpath("//button[@class='close']");
    String btn_removeItem = "//tr[td[text()='CODE']]//button[text()='Remove']";
    By btn_similar_config = By.xpath("//tr[td[contains(text(), 'Compare Similar Items')]]//button[contains(text(), 'View & Configure')]");
    By txt_popupCompareSimilarItems = By.xpath("//div[text()='Configure item recommendation carousel']");
    By toggle_carouselDisplayStatus = By.xpath("//div[@class='react-switch-handle']");
    By btn_recCustomer_config = By.xpath("//tr[td[contains(text(), 'Recommended for Customer')]]//button[contains(text(), 'View & Configure')]");
    By txt_popupRecommendForCustomer = By.xpath("//div[text()='Configure item recommendation carousel']");
    By btn_recSales_config = By.xpath("//tr[td[contains(text(), 'Recommended by Sales Rep')]]//button[contains(text(), 'View & Configure')]");
    By txt_popupRecommendedBySalesRep = By.xpath("//div[text()='Select a sales rep to configure']");
    String btn_salesRepConfig = "//tr[td[text()='NAME']]//button[text()='Configure']";
    By txt_popupSalesRepConfig = By.xpath("//div[text()='Configure item recommendation carousel']");
    By btn_dontforget_config = By.xpath("//tr[td[contains(text(), \"Don't Forget to Order\")]]//button[contains(text(), \"View & Configure\")]");
    By txt_popupDontForgetToOrder = By.xpath("//div[text()='Configure item recommendation carousel']");
    By btn_morefrom_config = By.xpath("//tr[td[contains(text(), 'More from this Brand')]]//button[contains(text(), 'View & Configure')]");
    By txt_popupMoreFromThis = By.xpath("//div[text()='Configure item recommendation carousel']");
    String txt_inactive_state =  "//tr[td[contains(text(), \"Type\")] and td[contains(text(), 'Inactive')]]";



    public boolean isBoostTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_boost);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_boost);
    }
    public void clickAddMessage() {
        distributorUI.click(btn_addMessage);
    }
    public boolean isStepOneDisplayed() {
        distributorUI.waitForClickability(btn_continue);
        return distributorUI.isDisplayed(txt_step1);
    }
    public void clickCustomersDropdown() {
        distributorUI.click(dropdown_customers);
    }
    public void selectCustomList() {
        distributorUI.click(option_customList);
    }
    public boolean isSelectCustomersDisplayed() {
        return distributorUI.isDisplayed(txt_selectCustomers);
    }
    public void clickSelectCustomersDropdown() {
        distributorUI.click(dropdown_customList);
    }
    public void selectCustomer(String name) {
        distributorUI.sendKeys(dropdown_search, name);
        distributorUI.click(By.xpath(txt_customerName.replace("NAME", name)));
    }
    public boolean isSelectionCountDisplayed() {
        return distributorUI.isDisplayed(txt_selectionCount);
    }
    public void clickContinue() {
        distributorUI.waitForClickability(btn_continue);
        distributorUI.click(btn_continue);
    }
    public boolean isStepTwoDisplayed() {
        distributorUI.waitForClickability(btn_submit);
        return distributorUI.isDisplayed(txt_step2);
    }
    public void typeMessage(String msg) {
        distributorUI.waitForVisibility(type_message);
        distributorUI.sendKeys(type_message, msg);
    }
    public void addURL() {
        Broadcast url = JsonUtil.readUrl();
        distributorUI.sendKeys(add_url, url.getUrl());
    }
    public void removeNotifications() {
        distributorUI.click(btn_clear);
    }
    public void clickSubmitButton() {
        distributorUI.click(btn_submit);
    }
    public boolean isBroadcastSuccess() {
        distributorUI.waitForVisibility(txt_broadcastSuccess);
        return distributorUI.isDisplayed(txt_broadcastSuccess);
    }
    public void clickOkButton() {
        distributorUI.click(btn_ok);
    }
    public void clickXButton() {
        distributorUI.waitForVisibility(btn_X);
        distributorUI.click(btn_X);
    }
    public void clickYes() {
        distributorUI.waitForVisibility(btn_yes);
        distributorUI.click(btn_yes);
    }
    public boolean isDeactivated() {
        distributorUI.waitForInvisibility(btn_X);
        return distributorUI.isDisplayed(btn_X);
    }
    public void clickSuggestiveSales() {
        distributorUI.click(tab_suggestiveSales);
    }
    public boolean isSuggestiveTabDisplayed() {
        return distributorUI.isDisplayed(txt_topCategoryPicks);
    }
    public void clickTopCategoryPicksConfig() {
        distributorUI.click(btn_topPicks_config);
    }
    public boolean isTopCategoryPopupDisplayed() {
        return distributorUI.isDisplayed(txt_popupTopCategoryPick);
    }
    public void clickAllItemsConfig() {
        distributorUI.waitForVisibility(btn_allItemConfig);
        distributorUI.click(btn_allItemConfig);
    }
    public void clickAddItems() {
        distributorUI.click(btn_addItems);
    }
    public void addItem(String code) {
        distributorUI.waitForVisibility(input_addItem);
        distributorUI.sendKeys(input_addItem, code);
        distributorUI.waitForVisibility(By.xpath(txt_itemAdded.replace("CODE", code+" : ")));
        distributorUI.hoverOverElement(By.xpath(txt_itemAdded.replace("CODE", code+" : ")));
        distributorUI.click(By.xpath(txt_itemAdded.replace("CODE", code+" : ")));
    }
    public void clickAdd() {
        distributorUI.waitForVisibility(btn_add);
        distributorUI.click(btn_add);
    }
    public boolean isItemAdded(String code) {
        return distributorUI.isDisplayed(By.xpath(txt_itemAdded.replace("CODE", code)));
    }
    public void clickClose() throws InterruptedException {
        distributorUI.click(btn_close);
        distributorUI.waitForCustom(2000);
    }
    public void removeItem(String code) {
        distributorUI.click(By.xpath(btn_removeItem.replace("CODE", code)));
    }
    public boolean isItemInCarouselPreview(String code) {
        return distributorUI.isDisplayed(By.xpath(txt_itemAdded.replace("CODE", code)));
    }
    public void clickCompareSimilarItemsConfig() {
        distributorUI.click(btn_similar_config);
    }
    public boolean isCompareSimilarPopupDisplayed() {
        return distributorUI.isDisplayed(txt_popupCompareSimilarItems);
    }
    public void toggleOnCarouselDisplayStatus(boolean inactive) throws InterruptedException {
        if (inactive){
            distributorUI.click(toggle_carouselDisplayStatus);
        }
        distributorUI.waitForCustom(1000);
    }
    public void toggleOffCarouselDisplayStatus() {
            distributorUI.click(toggle_carouselDisplayStatus);
    }
    public void clickRecommendForCustomerConfig() {
        distributorUI.click(btn_recCustomer_config);
    }
    public boolean isRecommendForCustomerPopupDisplayed() {
        return distributorUI.isDisplayed(txt_popupRecommendForCustomer);
    }
    public void clickRecommendBySalesRepConfig() {
        distributorUI.click(btn_recSales_config);
    }
    public boolean isRecommendBySalesRepPopupDisplayed() {
        return distributorUI.isDisplayed(txt_popupRecommendedBySalesRep);
    }
    public void clickSalesRepConfig(String name) {
        distributorUI.waitForVisibility(By.xpath(btn_salesRepConfig.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_salesRepConfig.replace("NAME", name)));
    }
    public boolean isSalesRepConfigPopupDisplayed() {
        return distributorUI.isDisplayed(txt_popupSalesRepConfig);
    }
    public void clickDontForgetToOrderConfig() {
        distributorUI.click(btn_dontforget_config);
    }
    public boolean isDontForgetPopupDisplayed() {
        return distributorUI.isDisplayed(txt_popupDontForgetToOrder);
    }
    public void clickMoreFromThisConfig() {
        distributorUI.click(btn_morefrom_config);
    }
    public boolean isMoreFromThisPopupDisplayed() {
        return distributorUI.isDisplayed(txt_popupMoreFromThis);
    }
    public boolean checkInactive(String type) throws InterruptedException {
        distributorUI.waitForCustom(1000);
        return distributorUI.isDisplayed(By.xpath(txt_inactive_state.replace("Type", type)));
    }
}
