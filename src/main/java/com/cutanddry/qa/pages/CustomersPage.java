package com.cutanddry.qa.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    By lbl_itemPriceSearchCatalogList = By.xpath("//span[contains(text(),'$')]");
    By btn_decreaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../preceding-sibling::div");
    By btn_increaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../following-sibling::div");
    By tbx_itemQuantityCartRowOne = By.xpath("//tr[2]/td//input/");
    By lbl_itemPriceCartRowOne = By.xpath("//tr[2]/td//span[contains(text(),'$')]");
    By lbl_cartTotal = By.xpath("//td[contains(text(),'Total')]/following-sibling::td");
    By btn_submitOrder = By.xpath("//button[contains(text(),'Submit Order')]");
    By btn_duplicateOrderYes = By.xpath("//h2[contains(text(),'Duplicate Order')]/../..//button[text()='Yes']");
    By lbl_thankYouForOrder = By.xpath("//*[contains(text(),'Thank you for your order!')]");

    public void clickOnSearchCustomers(){
        driver.findElement(tbx_searchCustomers);
    }
    public void typeOnSearchCustomers(String code){
        driver.findElement(tbx_searchCustomers).sendKeys(code);
    }
    public boolean isCustomerSearchResultByCodeDisplayed(String code){
        return driver.findElement(By.xpath(btnOrderGuide.replace("CODE", code))).isDisplayed();
    }
    public void clickOnOrderGuide(String code) {
        driver.findElement(By.xpath(btnOrderGuide.replace("CODE", code))).click();
    }
    public String getItemNameFirstRow(){
        return driver.findElements(lbl_itemNameList).getFirst().getText();
    }
    public void clickPlusQryFirstRow(){
        driver.findElement(btn_increaseQtyFirstRow).click();
    }
    public void clickMinusQryFirstRow(){
        driver.findElement(btn_decreaseQtyFirstRow).click();
    }
    public void clickMinusQrySecondRow(){
        driver.findElement(btn_decreaseQtySecondRow).click();
    }
    public String getItemNameSecondRow(){
        return driver.findElements(lbl_itemNameList).get(1).getText();
    }
    public void clickPlusQrySecondRow(){
        driver.findElement(btn_increaseQtySecondRow).click();
    }
    public void clickOnCheckoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(btn_checkout));
        driver.findElement(btn_checkout).click();
    }
    public void clickOnCatalogButton(){
        driver.findElement(btn_catalog).click();
    }
    public void typeToSearchOnCatalog(String item){
        driver.findElement(tbx_catalogSearch).sendKeys(item);
    }
    public String getFirstItemNameFrmSearchResults(){
        return driver.findElements(lbl_catalogSearchItemList).getFirst().getText();
    }
    public void clickAddToCartCatalog(){
        wait.until(ExpectedConditions.elementToBeClickable(btn_addToCart));
        driver.findElement(btn_addToCart).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_checkout));
    }
    public String getItemQtyFirstRow(){
        return driver.findElement(tbx_itemQuantityFirstRow).getAttribute("value");
    }
    public Double getItemPriceFirstRow(){
        return Double.valueOf(driver.findElement(lbl_itemPriceFirstRow).getText().replace("$",""));
    }
    public Double getItemPriceOnCheckoutButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_checkout));
        return Double.valueOf(driver.findElement(btn_checkout).getText().replace("$",""));
    }
    public void clickPlusQryCatalogSearchValueOne(){
        driver.findElement(btn_increaseQtyCatalogSearchValueOne).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_checkout));
    }
    public void clickPlusQryCatalogSearchValueTwo(){
        driver.findElement(btn_increaseQtyCatalogSearchValueTwo).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_checkout));
    }
    public void clickMinusQryCatalogSearchValueOne(){
        driver.findElement(btn_decreaseQtyCatalogSearchValueOne).click();
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            WebElement element = driver.findElement(btn_checkout);
            return !element.isEnabled();
        });
    }
    public void clickMinusQryCatalogSearchValueTwo(){
        driver.findElement(btn_decreaseQtyCatalogSearchValueTwo).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_checkout));
    }
    public void clickMinusQryCatalogSearchValueThree(){
        driver.findElement(btn_decreaseQtyCatalogSearchValueThree).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_checkout));
    }
    public String getItemQryCatalogSearch(){
       return driver.findElement(tbx_itemQuantityCatalogSearch).getAttribute("value");
    }
    public Double getItemPriceCatalogSearch() {
        return Double.parseDouble(
                driver.findElements(lbl_itemPriceSearchCatalogList)
                        .get(2)
                        .getText()
                        .replace("$", "")
        );
    }
    public void clickMinusQryCartRowOne(){
        driver.findElement(btn_decreaseQtyCartRowOne).click();
    }
    public void clickPlusQryCartRowOne(){
        driver.findElement(btn_increaseQtyCartRowOne).click();
    }
    public Double getUnitPriceFirstRowCart(){
        return Double.valueOf(driver.findElement(lbl_itemPriceCartRowOne).getText().split("\\$")[1]);
    }
    public Double getTotalPriceCart(){
        return Double.valueOf(driver.findElement(lbl_cartTotal).getText().split("\\$")[1]);
    }
    public void submitOrder(){
        wait.until(ExpectedConditions.elementToBeClickable(btn_submitOrder));
        driver.findElement(btn_submitOrder).click();
    }
    public boolean isDuplicatePopupDisplayed(){
        try {
            return driver.findElement(btn_duplicateOrderYes).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }
    public void clickYesDuplicatePopup(){
        wait.until(ExpectedConditions.elementToBeClickable(btn_duplicateOrderYes));
        driver.findElement(btn_duplicateOrderYes).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(btn_duplicateOrderYes)));
    }
    public boolean isThankingForOrderPopupDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(lbl_thankYouForOrder)));
            return driver.findElement(lbl_thankYouForOrder).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }
}
