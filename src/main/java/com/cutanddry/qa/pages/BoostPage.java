package com.cutanddry.qa.pages;



import com.cutanddry.qa.data.models.Broadcast;
import com.cutanddry.qa.utils.JsonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    By type_message = By.xpath("//textarea[@placeholder='Type your message here...']");
    By add_url = By.xpath("//input[@placeholder='Add a URL to your message...']");
    By btn_clear = By.xpath("//div[contains(@class, 'themed_select__clear-indicator')]");
    By txt_broadcastSuccess = By.xpath("//div[text()='Broadcast message created successfully']");
    By btn_ok = By.xpath("//button[text()='OK']");
    By btn_X = By.xpath("//tr[td[contains(text(),'Test Broadcast Message')]]//td[contains(@class,'text-right')]//button//*[name()='svg' and @data-icon='circle-xmark']");
    By btn_yes = By.xpath("//button[text()='Yes']");
    By tab_broadcast = By.xpath("//a[text()='Broadcasts']");
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
    By tab_catalogHome = By.xpath("//a[text()='Catalog Home']");
    By txt_primaryBanner = By.xpath("//div[text()='Primary Banners']");
    By togglePrimaryBanner = By.xpath("(//div[@class='react-switch-handle'])[1]");
    By btn_saveChange = By.xpath("//button[text()='Save Changes']");
    By txt_active = By.xpath("//div[text()='Active']");
    By txt_hidden = By.xpath("//div[text()='Hidden']");
    By img_banner = By.xpath("//div[contains(@class, 'carousel-item')]//img");
    By featuredListTab = By.xpath("//a[contains(@class,'_ngcfan text-center nav-item nav-link') and text()='Featured Lists']");
    By createNewFeaturedListBtn = By.xpath("//button[contains(text(),'Create New Featured List')]");
    By createListTxt = By.xpath("//div[contains(text(),'Create List')]");
    By featuredListNameTxtField = By.xpath("//div[@class='my-4 py-3 form-group']/input");
    String addedFeaturedList = "//table[@class='_6rf0k0 table']//tbody//td[contains(text(),'FEATUREDLIST')]";
    String viewAndConfigureBtn = "//tr[td[contains(text(), 'FEATUREDLISTNAME')]]//button[contains(., 'View & Configure')]";
    By configureItemOverlay = By.xpath("//div[contains(@class,'_ringdvy lato') and contains(text(),'Configure items for this featured list')]");
    By editListNameBtn = By.xpath("//button[contains(text(),'Edit List Name')]");
    By editListNameOverlay = By.xpath("//div[@class='mont modal-title h4' and contains(text(),'Edit List Name')]");
    By saveChangesBtn = By.xpath("//button[contains(text(),'Save Changes')]");
    By editOverlayCloseBtn = By.xpath("//span[contains(text(),'×')]");
    String featuredListDeleteBtn = "//tr[td[contains(text(), 'FEATUREDLIST')]]//button[contains(@class, 'cdbutton ml-2 _1fibbi88 btn btn-outline-primary btn-sm')]";
    By deleteListOverlayTxt = By.xpath("//div[contains(@class,'_1j771rs _5x3l6t mt-3 mb-2') and text()='Delete List?']");
    By OverlayDeleteBtn = By.xpath("//button[contains(@class,'w-100 _len1zh btn btn-danger') and text()='Delete']");
    By toggleCarouselDisplayStatus = By.xpath("//div[@class='ml-4']//input[@type='checkbox' and @role='switch' and @checked]");
    By dropdown_status = By.xpath("//div[contains(text(),'Status')]");
    String dropdown_statusOption = "//div[contains(text(), 'option')]";
    By table_boost = By.xpath("//table[@class='mt-4 table table-hover']");
    By firstRow_tableBost = By.xpath("//table[contains(@class, 'table-hover')]/tbody/tr[1]");
    By status_firstRow = By.xpath("//table[@class='mt-4 table table-hover']/tbody/tr[1]/td[5]");
    By displayStatusToggle = By.xpath("//div[contains(@class, 'ml-4')]");
    By btn_addNewMessage = By.xpath("//a[normalize-space(text()) = 'Add a new message']");
    By tbx_newMessage = By.xpath("//textarea[@placeholder='Type your message here...']");
    By dropdown_notificationTime = By.xpath("//div[contains(text(),'Select time')]");
    By btn_OK = By.xpath("//button[text()='OK']");
    By btn_submit = By.xpath("//button[text()='Submit']");
    By sourceRowDragHandle = By.xpath("//tr[@data-rbd-draggable-id='340446843']//td[5]//*[name()='svg']");
    By targetRowDragHandle = By.xpath("//tr[@data-rbd-draggable-id='294915282']//td[5]//*[name()='svg']");

    public void changeOrderDragAndDrop(){
        distributorUI.dragAndDrop(sourceRowDragHandle,targetRowDragHandle);
    }

    public void clickBtnOK(){
        distributorUI.click(btn_OK);
    }

    public void clickBtnSubmit(){
        distributorUI.click(btn_submit);
    }

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
    public boolean isBroadcastTabDisplayed() {
        return distributorUI.isDisplayed(tab_broadcast);
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
    public void clickCatalogHome() {
        distributorUI.click(tab_catalogHome);
    }
    public boolean isCatalogHomeTabDisplayed() {
        return distributorUI.isDisplayed(txt_primaryBanner);
    }
    public void toggleOnPrimaryBanner() {
        if (distributorUI.isDisplayed(txt_hidden)){
            distributorUI.click(togglePrimaryBanner);
        }
    }
    public void toggleOffPrimaryBanner() {
        if (distributorUI.isDisplayed(txt_active)){
            distributorUI.click(togglePrimaryBanner);
        }
    }
    public void clickOnSaveChanges() {
        distributorUI.waitForClickability(btn_saveChange);
        distributorUI.click(btn_saveChange);
        distributorUI.waitForElementEnabledState(btn_saveChange, false);
    }
    public boolean isActiveDisplayed() {
        return distributorUI.isDisplayed(txt_active);
    }
    public boolean isHiddenDisplayed() {
        return distributorUI.isDisplayed(txt_hidden);
    }
    public boolean isPrimaryBannerDisplayed() {
        distributorUI.waitForVisibility(img_banner);
        return distributorUI.isDisplayed(img_banner);
    }

    public void clickFeaturedListTab(){
        distributorUI.click(featuredListTab);
    }

    public void clickOnCreateNewFeaturedListBtn(){
        distributorUI.click(createNewFeaturedListBtn);
    }

    public boolean CreateListOverlayDisplayed(){
        return distributorUI.isDisplayed(createListTxt);
    }

    public void typeFeaturedListName(String featuredListName){
        distributorUI.sendKeys(featuredListNameTxtField,featuredListName);
    }

    public void clickSubmitBtnInCreateList(){
        distributorUI.click(btn_submit);
    }

    public boolean isFeaturedListDisplayed(String featuredList){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(By.xpath(addedFeaturedList.replace("FEATUREDLIST", featuredList)));
    }

    public void clickViewAndConfigureBtn(String featuredListName){
        distributorUI.click(By.xpath(viewAndConfigureBtn.replace("FEATUREDLISTNAME", featuredListName)));
    }

    public boolean isConfigureItemOverlayDisplayed(){
        return distributorUI.isDisplayed(configureItemOverlay);
    }

    public void clickOnEditListName(){
        distributorUI.click(editListNameBtn);
    }

    public boolean isEditListNameOverlayDisplayed(){
        return distributorUI.isDisplayed(editListNameOverlay);
    }

    public void clickSaveChangesBtn(){
        distributorUI.click(saveChangesBtn);
    }

    public void clickOnCloseEditOverlayBtn(){
        distributorUI.click(editOverlayCloseBtn);
    }

    public void clickListDeleteBtn(String featuredList){
        distributorUI.click(By.xpath(featuredListDeleteBtn.replace("FEATUREDLIST",featuredList)));
    }

    public boolean isDeleteFeaturedListOverlayDisplayed(){
        return distributorUI.isDisplayed(deleteListOverlayTxt);
    }

    public void clickDeleteBtnInDeleteListOverlay(){
        distributorUI.click(OverlayDeleteBtn);
    }

    public void ensureCarouselDisplayStatus(boolean enable) throws InterruptedException {
        if (distributorUI.isCheckboxOrRadioBtnSelected(toggleCarouselDisplayStatus ) != enable){
            distributorUI.click(toggle_carouselDisplayStatus);
        }
        distributorUI.waitForCustom(2000);
    }

    public String getStatusFirstRow(){
        return distributorUI.getText(status_firstRow);
    }

    public boolean isAddNewMessageDisplayed(){
        return distributorUI.isDisplayed(btn_addNewMessage);
    }

    public void clickDropDownStatus () {
            distributorUI.click(dropdown_status);
        }

    public String getNextRoundedTime() {
        // Get the current time
        Calendar calendar = Calendar.getInstance();

        // Round to the next hour
        calendar.add(Calendar.MINUTE, 30); // Add 30 minutes to decide the next hour
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Format the time
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"); // Format as "5:00 PM" or "11:00 AM"
        return sdf.format(calendar.getTime());
    }

    public void clickDropDownStatus(String status) {
        By option = By.xpath(dropdown_statusOption.replace("option",status));
        distributorUI.click(option);
    }

    public void selectNotificationTime(String time){
//        By notificationTime = By.xpath(dropdown_notificationTimeOption.replace("TIME_OPTION",time));
        distributorUI.sendKeysAndEnter(dropdown_notificationTime,time);
//        distributorUI.click(notificationTime);
    }

    public void clickDropDownNotificationTime(){
        distributorUI.click(dropdown_notificationTime);
    }

    public void typeNewMessage(String testMessage){
        distributorUI.waitForVisibility(tbx_newMessage);
        distributorUI.sendKeys(tbx_newMessage,testMessage);
    }

    public void clickBtnAddNewMessage(){
        distributorUI.waitForVisibility(btn_addNewMessage);
        distributorUI.click(btn_addNewMessage);
    }
    public void clickOnDisplayStatusToggle(){
        distributorUI.click(displayStatusToggle);
    }

}
