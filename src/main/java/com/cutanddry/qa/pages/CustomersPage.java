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
        distributorUI.waitForCustom(2000);
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
    public Double getTotalPriceCart(){
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
}
