package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class CustomersPage extends LoginPage {
    By tbx_searchCustomers = By.xpath("//input[@placeholder='Search Customers']");
    String btnOrderGuide = "//td[text()='CODE']/../td[8]//button";
    By lbl_itemNameList = By.xpath("//td//span/div[@data-tip='View Item Details']");
    By btn_increaseQtyFirstRow = By.xpath("//tr[1]/td[8]/div/div/div/div[3]");
    By btn_decreaseQtyFirstRow = By.xpath("//tr[1]/td[8]/div/div/div/div[1]");
    By btn_increaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div/div[3]");
    By btn_decreaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div/div[1]");
    By btn_checkout = By.xpath("//button[text()='$']/../button[2]");
    By btn_catalog = By.xpath("//div[text()='Catalog']");
    By tbx_catalogSearch = By.xpath("//input[@placeholder='Search catalog...']");
    By lbl_catalogSearchItemList = By.xpath("//div[contains(text(), 'Artichoke')]");
    By btn_addToCart = By.xpath("//button[text()='Add to Cart']");
    By tbx_itemQuantityFirstRow = By.xpath("//tr[1]//td[8]//input");
    By lbl_itemPriceFirstRow = By.xpath("//tr[1]//td[7]/div");
    By btn_increaseQtyCatalogSearchValueOne = By.xpath("//input[@type='number' and @value='1']/../following-sibling::div");
    By btn_increaseQtyCatalogSearchValueTwo = By.xpath("//input[@type='number' and @value='2']/../following-sibling::div");
    By btn_decreaseQtyCatalogSearchValueOne = By.xpath("//input[@type='number' and @value='1']/../preceding-sibling::div");
    By btn_decreaseQtyCatalogSearchValueTwo = By.xpath("//input[@type='number' and @value='2']/../preceding-sibling::div");
    By btn_decreaseQtyCatalogSearchValueThree = By.xpath("//input[@type='number' and @value='3']/../preceding-sibling::div");
    By tbx_itemQuantityCatalogSearch = By.xpath("//input[@type='number']");
    By lbl_itemPriceSearchCatalogList = By.xpath("//span[contains(text(),'$') and not(contains(text(),' ')) and not(@class='text-muted')]");
    By btn_decreaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../preceding-sibling::div");
    By btn_increaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../following-sibling::div");
    By tbx_itemQuantityCartRowOne = By.xpath("//tr[2]/td//input/");
    By lbl_itemPriceCartRowOne = By.xpath("//tr[2]/td//span[contains(text(),'$')]");
    By lbl_cartTotal = By.xpath("//td[contains(text(),'Total')]/following-sibling::td");
    By btn_submitOrder = By.xpath("//button[contains(text(),'Submit Order')]");
    By btn_duplicateOrderYes = By.xpath("//h2[contains(text(),'Duplicate Order')]/../..//button[text()='Yes']");
    By lbl_thankYouForOrder = By.xpath("//*[contains(text(),'Thank you for your order!')]");
    By btn_print = By.xpath("//button[contains(text(), 'Print')]");
    By lbl_printFriendlyOrderGuide = By.xpath("//h5[contains(text(), 'Print-Friendly Order Guide')]");
    By btn_downloadOrderGuide = By.xpath("//button[contains(text(), 'Download Order Guide')]");
    By tbx_orderGuideSearch = By.xpath("//input[@placeholder='Search order guide...']");
    By btn_create = By.xpath("//button[contains(text(), 'Create')]");
    By tbx_OrderGuideName = By.xpath("//input[@placeholder='Enter Name']");
    By btn_submitOrderGuide = By.xpath("//button[contains(text(), 'Submit')]");
    By btn_addFromCatalog = By.xpath("//div[contains(text(), 'Add from Catalog')]");
    By btn_uploadFile = By.xpath("//button[contains(text(), 'Upload File')]");
    By btn_addToOrderGuide = By.xpath("//button[@data-tip='Add to Order Guide']");
    By btn_closeEditor = By.xpath("//button[contains(text(), 'Close Editor')]");
    By btn_closeEditor_ = By.xpath("//a[contains(text(), 'Close Editor')]");
    By btn_removeFromOrderGuide = By.xpath("//button[@data-tip='Remove from Order Guide']");
    By upload_file = By.xpath("//input[@type='file']");
    By btn_next = By.xpath("//button[text()='Next']");
    By btn_confirm = By.xpath("//button[text()='Confirm']");
    By txt_orderGuideCreateSuccess = By.xpath("//h2[contains(text(), 'Order guide updated successfully')]");
    By btn_OK = By.xpath("//button[text()='OK']");
    By msg_banner = By.xpath("//span[text()='Test Broadcast Message']");
    By lbl_productDetails = By.xpath("//span[text()='Product Details']");
    By lbl_topCategoryPicks = By.xpath("//div[text()='Top Category Picks']");
    By lbl_itemAdded = By.xpath("//div[text()='Top Category Picks']//following-sibling::div//div[text()='#00475']");
    String lbl_searchedItem = "//div[text()='CODE']";
    By section_compareSimilar = By.xpath("//div[text()='Compare Similar Items']");
    By lbl_recommendedForYouItem = By.xpath("//div[text()='Recommended for You']//following-sibling::div//div[text()='#00475']");
    By lbl_recommendedBySalesRep = By.xpath("//div[text()='Recommended by Steve O']//following-sibling::div//div[text()='#00475']");
    By section_dontForget = By.xpath("//div[text()=\"Don't Forget to Order\"]");
    By section_moreFromThisBrand = By.xpath("//div[text()='More From Dole']");
    By btn_companyDropdown = By.xpath("//button[.//span[text()='Company:']]");
    By txt_companyDropdownText = By.xpath("//a[contains(text(), 'Independent Foods Co')]");
    By btn_edit = By.xpath("//button[contains(., 'Edit')]");
    By txt_editOrderGuide= By.xpath("//span[contains(text(), 'Edit Order Guide')]");
    By btn_moreOptions = By.xpath("//span[contains(text(), 'More Options')]");
    By btn_exportOrderGuide = By.xpath("//a[contains(text(), 'Export Order Guide (XLSX)')]");
    By btn_importOrderGuide = By.xpath("//a[contains(text(), 'Import Order Guide (XLSX)')]");
    By btn_uploadToOrder = By.xpath("//a[contains(text(), 'Upload to Order')]");
    By txt_reviewOrder= By.xpath("//div[text()='Review Order']");
    By txt_orderGuideUpdated= By.xpath("//h2[text()='Order guide updated successfully']");


    public void clickOnSearchCustomers(){
        distributorUI.click(tbx_searchCustomers);
    }
    public void typeOnSearchCustomers(String code){
        distributorUI.sendKeys(tbx_searchCustomers, code);
    }
    public boolean isCustomerSearchResultByCodeDisplayed(String code) throws InterruptedException {
        distributorUI.waitForElementEnabledState(By.xpath(btnOrderGuide.replace("CODE", code)), true);
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(btnOrderGuide.replace("CODE", code)));
    }
    public void clickOnOrderGuide(String code) {
        distributorUI.click(By.xpath(btnOrderGuide.replace("CODE", code)));
    }
    public String getItemNameFirstRow() throws InterruptedException {
        distributorUI.waitForElementEnabledState(lbl_itemNameList,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_itemNameList);
    }
    public void clickPlusQryFirstRow(){
        distributorUI.click(btn_increaseQtyFirstRow);
    }
    public void clickMinusQryFirstRow(){
        distributorUI.click(btn_decreaseQtyFirstRow);
    }
    public void clickMinusQrySecondRow(){
        distributorUI.click(btn_decreaseQtySecondRow);
    }
    public String getItemNameSecondRow(){
       return distributorUI.getText(lbl_itemNameList,1);
    }
    public void clickPlusQrySecondRow(){
        distributorUI.click(btn_increaseQtySecondRow);
    }
    public void clickOnCheckoutButton() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout,true);
        distributorUI.click(btn_checkout);
        distributorUI.waitForCustom(4000);
    }
    public void clickOnCatalogButton(){
        distributorUI.click(btn_catalog);
    }
    public void typeToSearchOnCatalog(String item){
        distributorUI.sendKeys(tbx_catalogSearch,item);
    }
    public String getFirstItemNameFrmSearchResults(){
        return distributorUI.getText(lbl_catalogSearchItemList, 0);
    }
    public void clickAddToCartCatalog() throws InterruptedException {
        distributorUI.waitForClickability(btn_addToCart);
        distributorUI.waitForCustom(4000);
        distributorUI.click(btn_addToCart);
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout,true);
    }
    public String getItemQtyFirstRow(){
        return distributorUI.getText(tbx_itemQuantityFirstRow, "value");
    }
    public Double getItemPriceFirstRow(){
        return Double.valueOf(distributorUI.getText(lbl_itemPriceFirstRow).replace("$",""));
    }
    public Double getItemPriceOnCheckoutButton() throws InterruptedException {
        distributorUI.waitForVisibility(btn_checkout);
        distributorUI.waitForCustom(4000);
        return Double.valueOf(distributorUI.getText(btn_checkout).replace("$",""));
    }
    public void clickPlusQryCatalogSearchValueOne(){
        distributorUI.click(btn_increaseQtyCatalogSearchValueOne);
        distributorUI.waitForClickability(btn_checkout);
    }
    public void clickPlusQryCatalogSearchValueTwo() throws InterruptedException {
        distributorUI.click(btn_increaseQtyCatalogSearchValueTwo);
        distributorUI.waitForCustom(2000);
        distributorUI.waitForClickability(btn_checkout);
        distributorUI.waitForCustom(4000);
    }
    public void clickMinusQryCatalogSearchValueOne(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueOne);
        distributorUI.waitForElementEnabledState(btn_checkout, false);
    }
    public void clickMinusQryCatalogSearchValueTwo(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueTwo);
        distributorUI.waitForClickability(btn_checkout);
    }
    public void clickMinusQryCatalogSearchValueThree(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueThree);
        distributorUI.waitForClickability(btn_checkout);
    }
    public String getItemQryCatalogSearch(){
       return distributorUI.getText(tbx_itemQuantityCatalogSearch, "value");
    }
    public Double getItemPriceCatalogSearch() {
        //driver.findElements(lbl_itemPriceSearchCatalogList).get(2).getText()
        return Double.parseDouble(distributorUI.getText(lbl_itemPriceSearchCatalogList,0).replace("$", ""));
    }
    public void clickMinusQryCartRowOne(){
        distributorUI.click(btn_decreaseQtyCartRowOne);
    }
    public void clickPlusQryCartRowOne(){
        distributorUI.click(btn_increaseQtyCartRowOne);
    }
    public Double getUnitPriceFirstRowCart(){
        return Double.valueOf(distributorUI.getText(lbl_itemPriceCartRowOne).split("\\$")[1]);
    }
    public Double getTotalPriceCart() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(lbl_cartTotal);
        return Double.valueOf(distributorUI.getText(lbl_cartTotal).split("\\$")[1]);
    }
    public void submitOrder(){
        distributorUI.waitForClickability(btn_submitOrder);
        distributorUI.click(btn_submitOrder);
    }
    public boolean isDuplicatePopupDisplayed(){
        try {
            return distributorUI.isDisplayed(btn_duplicateOrderYes);
        } catch (Exception e){
            return false;
        }
    }
    public void clickYesDuplicatePopup(){
        distributorUI.waitForClickability(btn_duplicateOrderYes);
        distributorUI.click(btn_duplicateOrderYes);
        distributorUI.waitForInvisibility(btn_duplicateOrderYes);
    }
    public boolean isThankingForOrderPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(lbl_thankYouForOrder);
            return distributorUI.isDisplayed(lbl_thankYouForOrder);
        } catch (Exception e){
            return false;
        }
    }
    public void clickOnPrint(){
        distributorUI.waitForClickability(btn_print);
        distributorUI.click(btn_print);
    }
    public boolean isPrintFriendlyPopupDisplayed(){
        return distributorUI.isDisplayed(lbl_printFriendlyOrderGuide);
    }
    public void clickOnDownloadOrderGuide(){
        distributorUI.waitForClickability(btn_downloadOrderGuide);
        distributorUI.click(btn_downloadOrderGuide);
    }
    public void typeToSearchOnOrderGuide(String item) throws InterruptedException {
        distributorUI.clear(tbx_orderGuideSearch);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_orderGuideSearch,item);
    }
    public void clickOnCreate(){
        distributorUI.waitForClickability(btn_create);
        distributorUI.click(btn_create);
    }
    public void typeOrderGuideName(String orderGuideName){
        distributorUI.sendKeys(tbx_OrderGuideName,orderGuideName);
    }
    public void clickSubmitOrderGuide(){
        distributorUI.waitForClickability(btn_submitOrderGuide);
        distributorUI.click(btn_submitOrderGuide);
    }
    public void clickOnAddFromCatalog(){
        distributorUI.waitForVisibility(btn_addFromCatalog);
        distributorUI.click(btn_addFromCatalog);
    }
    public void clickUploadAList(){
        distributorUI.waitForVisibility(btn_uploadFile);
        distributorUI.click(btn_uploadFile);
    }
    public void clickOnAddToOrderGuide(){
        distributorUI.waitForVisibility(btn_addToOrderGuide);
        distributorUI.click(btn_addToOrderGuide);
    }
    public void giveFilePath(String path){
        distributorUI.sendKeysToHiddenElements(upload_file, path);
    }
    public void clickOnCloseEditorCatalog(){
        distributorUI.click(btn_closeEditor);
    }
    public void clickOnRemoveFromOrderGuide(){
        distributorUI.waitForVisibility(btn_removeFromOrderGuide);
        distributorUI.click(btn_removeFromOrderGuide);
    }
    public boolean isBroadcastMessageDisplayed() {
        return distributorUI.isDisplayed(msg_banner);
    }
    public void clickMessage(){
        distributorUI.click(msg_banner);
    }
    public boolean isProductDetailsDisplayed(){
        return distributorUI.isDisplayed(lbl_productDetails);
    }
    public boolean isTopCategoryPicksDisplayed(){
        return distributorUI.isDisplayed(lbl_topCategoryPicks);
    }
    public boolean isItemInTopCategoryPicks(){
        return distributorUI.isDisplayed(lbl_itemAdded);
    }
    public void clickSearchedItem(String code){
        distributorUI.click((By.xpath(lbl_searchedItem.replace("CODE", '#'+code))));
    }
    public boolean isSelectedItemDisplayed(){
        return distributorUI.isDisplayed(lbl_productDetails);
    }
    public boolean isCompareSimilarItemsDisplayed(){
        return distributorUI.isDisplayed(section_compareSimilar);
    }
    public boolean isRecommendedForYouItemDisplayed(){
        return distributorUI.isDisplayed(lbl_recommendedForYouItem);
    }
    public boolean isRecommendedBySalesRepDisplayed() {
        return distributorUI.isDisplayed(lbl_recommendedBySalesRep);
    }
    public boolean isDontForgetToOrderDisplayed(){
        return distributorUI.isDisplayed(section_dontForget);
    }
    public boolean isMoreFromThisBrandDisplayed(){
        return distributorUI.isDisplayed(section_moreFromThisBrand);
    }
    public void clickNext(){
        distributorUI.waitForClickability(btn_next);
        distributorUI.click(btn_next);
    }
    public void clickConfirm(){
        distributorUI.waitForClickability(btn_confirm);
        distributorUI.click(btn_confirm);
    }
    public void clickOK(){
        distributorUI.waitForClickability(btn_OK);
        distributorUI.click(btn_OK);
    }
    public void closeEditor(){
        distributorUI.waitForClickability(btn_closeEditor_);
        distributorUI.click(btn_closeEditor_);
    }
    public boolean isOrderGuideCreateSuccessPopupDisplayed(){
        return distributorUI.isDisplayed(txt_orderGuideCreateSuccess);
    }
    public void clickCompanyDropdown(){
        distributorUI.waitForClickability(btn_companyDropdown);
        distributorUI.click(btn_companyDropdown);
    }
    public boolean isCompanyDropdownTextDisplayed(){
        return distributorUI.isDisplayed(txt_companyDropdownText);
    }
    public void clickOnEdit(){
        distributorUI.waitForClickability(btn_edit);
        distributorUI.click(btn_edit);
    }
    public boolean isEditOrderGuideTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_editOrderGuide);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_editOrderGuide);
    }
    public void clickOnMoreOptions(){
        distributorUI.waitForClickability(btn_moreOptions);
        distributorUI.click(btn_moreOptions);
    }
    public void clickOnExportOrderGuide(){
        distributorUI.waitForClickability(btn_exportOrderGuide);
        distributorUI.click(btn_exportOrderGuide);
    }
    public void clickOnImportOrderGuide(){
        distributorUI.waitForClickability(btn_importOrderGuide);
        distributorUI.click(btn_importOrderGuide);
    }
    public void clickOnUploadToOrder(){
        distributorUI.waitForClickability(btn_uploadToOrder);
        distributorUI.click(btn_uploadToOrder);
    }
    public boolean isReviewOrderTextDisplayed(){
        distributorUI.waitForVisibility(txt_reviewOrder);
        return distributorUI.isDisplayed(txt_reviewOrder);
    }
    public boolean isOrderGuideUpdatedTextDisplayed(){
        distributorUI.waitForVisibility(txt_orderGuideUpdated);
        return distributorUI.isDisplayed(txt_orderGuideUpdated);
    }
}
