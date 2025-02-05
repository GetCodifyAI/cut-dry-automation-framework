package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ShowCasePage extends LoginPage {
    By productShowcaseTxt = By.xpath("//h1[contains(text(),'Cut+Dry Product Showcase')]");
    By productSearchBar = By.xpath("//input[@id='order_flow_search']");
//    By productDisplayed = By.xpath("//div[contains(@class,'_3quvq7') and contains(@class,'_1vlidrf') and contains(text(),'Andy Capps Hot Fries, 0.85 Oz')]");
//    By productDisplayed = By.xpath("//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'andy capps hot fries, 0.85 oz')]");
    String productDisplayed = "//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate('BRANDPAGE', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]";
    By ManufacturerTxt = By.xpath("(//*[contains(text(),'Conagra Foodservice')])[1]");
    String ConagraFoodServiceTxt = "//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate('BRANDPAGE', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]";
    By OurBrandBtn = By.xpath("//img[contains(@class,'img-fluid') and contains(@src,'angela-mia-logo')]");
    By OurBrandPageTxt = By.xpath("//div[contains(@class,'_12lmd5r8') and contains(text(),'Angela Mia')]");
    By OurBrands = By.xpath("//div[contains(text(),'Our Brands')]");
    By HungerfordSmithBrand = By.xpath("//img[contains(@class,'img-fluid') and contains(@src,'e1709cd4888480e1b60d1b64da314fc8')]");
    By HungerfordSmithText =By.xpath("//div[@class='_12lmd5r8' and contains(text(),'J Hungerford Smith')]");
    By icon_OurBrandRightArrow =By.xpath("(//div[contains(text(),'Our Brands')]/following::div//*[name()='svg' and @data-icon='chevron-right']//*[name()='path'])[1]");


    public boolean isProductShowcaseTxtDisplayed(){
        return distributorUI.isDisplayed(productShowcaseTxt);
    }

    public void SearchInProductSearchBar(String productName){
        distributorUI.sendKeys(productSearchBar,productName);
    }

    public void SelectProductInShowCase(String productName){
        distributorUI.isDisplayed(By.xpath(productDisplayed.replace("BRANDPAGE",productName)));
        distributorUI.click(By.xpath(productDisplayed.replace("BRANDPAGE",productName)));
    }

    public void ClickOnManufacturer(){
        distributorUI.isDisplayed(ManufacturerTxt);
        distributorUI.click(ManufacturerTxt);
    }

    public boolean isConagraFoodServiceTxtDisplayed(String brand){
        return distributorUI.isDisplayed(By.xpath(ConagraFoodServiceTxt.replace("BRANDPAGE",brand)));
    }

    public void ClickOnOurBrandBtn() throws InterruptedException {
        if (!distributorUI.isDisplayed(HungerfordSmithBrand)) {
            distributorUI.click(icon_OurBrandRightArrow);
        }
        distributorUI.waitForClickability(HungerfordSmithBrand);
        distributorUI.waitForCustom(3000);
//        distributorUI.scrollToElement(OurBrands);
        distributorUI.clickWithScrollAndHover(OurBrandBtn);
    }

    public boolean isOurBrandPageisDisplayed(){
        distributorUI.isDisplayed(OurBrandPageTxt);
        return distributorUI.isDisplayed(OurBrandPageTxt);
    }

    public void ClickOnHungerfordSmith() throws InterruptedException {
        if (!distributorUI.isDisplayed(HungerfordSmithBrand)) {
            distributorUI.click(icon_OurBrandRightArrow);
        }
        distributorUI.waitForClickability(HungerfordSmithBrand);
        distributorUI.waitForCustom(3000);
        distributorUI.clickWithScrollAndHover(HungerfordSmithBrand);
    }

    public boolean isHungerFordSmithtxtDisplayed(){
        distributorUI.isDisplayed(HungerfordSmithText);
        return distributorUI.isDisplayed(HungerfordSmithText);
    }

}
