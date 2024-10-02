package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class CatalogPage extends LoginPage{
    By txt_catalog = By.xpath("//li[contains(text(),'Catalog')]");
    By txt_catalogFirstItem = By.xpath("//tbody/tr[contains(@class, '_du1frc')][1]");
    By txt_editItem = By.xpath("//li[contains(text(),'Edit Item')]");
    By btn_preview = By.xpath("//a[.//button[contains(text(), 'Preview')]]");
    By txt_preview = By.xpath("//a[contains(text(),'Preview')]");
    By btn_downloadPdf = By.xpath("//div[text()='Export PDP (Pdf)']");
    By btn_dropdown = By.xpath("//button[@aria-haspopup='true']");
    By ItemCatalogSearchBtn = By.xpath("//input[@placeholder=\"Find Item in Catalog\"]");
    String SearchedItemItemCode = "//tr[contains(@class, '_du1frc')][td[1]='ITEMCODE']";
    By PreviewBtn = By.xpath("//button[@class='_lzbys2 mx-2 btn btn-primary']");
    By ItemPreviewTxt = By.xpath("//div[contains(text(),'Topping Chocolate Fudge Mellocream')]");
    By Manufacturer = By.xpath("//div[contains(text(),'Conagra Foodservice')]");
    By OtherBrandBtn = By.xpath("//img[@class='_kfc3ia img-fluid' and contains(@src,\"2b4b2013cb03bd26957893f39d0783bd.jpg\")]");
    By ConagaraBrandPage= By.xpath("//div[contains(text(),'Conagra Foodservice ') and @class='mt-5 mb-1 _mojmdw']");
    By OtherBrandText = By.xpath("//h2[contains(text(),'Andy Capp’s®')]");
    By ShowCaseBtn = By.xpath("//a[contains(@data-tip,'Cut+Dry Product Showcase')]");

    public boolean isCatalogTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_catalog);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_catalog);
    }
    public void clickFirstItem() {
        distributorUI.click(txt_catalogFirstItem);
    }
    public boolean isSelectedProductDisplayed() {
        return distributorUI.isDisplayed(txt_editItem);
    }
    public void clickPreview() {
        String url = distributorUI.getText(btn_preview,"href");
        distributorUI.navigateToURL(url);
    }
    public boolean isNavigatedToPreview() {
        return distributorUI.isDisplayed(txt_preview);
    }
    public void clickExportPdf() {
        distributorUI.click(btn_dropdown);
        distributorUI.waitForVisibility(btn_downloadPdf);
        distributorUI.click(btn_downloadPdf);
        distributorUI.waitForVisibility(btn_downloadPdf);
    }

    public void TypeSearchInCatalogSearch(String ItemName){
        distributorUI.sendKeys(ItemCatalogSearchBtn,ItemName);
        distributorUI.waitForVisibility(By.xpath(SearchedItemItemCode.replace("ITEMCODE",ItemName)));

    }

    public void ClickOnItemCode(String ItemCode){
        distributorUI.click(By.xpath(SearchedItemItemCode.replace("ITEMCODE",ItemCode)));
    }

    public void ClickOnPreviewBtn(){
        distributorUI.SwitchToNewTab(PreviewBtn);
    }

    public boolean isNavigateToItemPreview(){
        distributorUI.waitForVisibility(ItemPreviewTxt);
        return distributorUI.isDisplayed(ItemPreviewTxt);
    }

    public void ClickOnManufacture(){
        distributorUI.click(Manufacturer);
    }

    public boolean isNavigatedtoConagaraBrandPage(){
        distributorUI.waitForVisibility(ConagaraBrandPage);
        return distributorUI.isDisplayed(ConagaraBrandPage);
    }

    public boolean isNavigatedtoOtherBrandPage() throws InterruptedException {
         distributorUI.SwitchToNewTab(OtherBrandBtn);
         distributorUI.waitForCustom(6000);
         distributorUI.waitForVisibility(OtherBrandText);
         return distributorUI.isDisplayed(OtherBrandText);
    }

    public void ClickOnShowCaseBtn(){
        distributorUI.click(ShowCaseBtn);
    }

}

