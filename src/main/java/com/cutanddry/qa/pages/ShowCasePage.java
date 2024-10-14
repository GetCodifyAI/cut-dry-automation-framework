package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ShowCasePage extends LoginPage {
    By productShowcaseTxt = By.xpath("//h1[contains(text(),'Cut+Dry Product Showcase')]");
    By productSearchBar = By.xpath("//input[@id='order_flow_search']");
    By productDisplayed = By.xpath("//div[contains(@class,'_3quvq7') and contains(@class,'_1vlidrf') and contains(text(),'Andy Capps Hot Fries, 0.85 Oz')]");
    By ManufacturerTxt = By.xpath("(//div[contains(text(),'Conagra Foodservice')])[1]");
    By ConagraFoodServiceTxt = By.xpath("//div[contains(@class,'mt-5 mb-1 _mojmdw')]");
    By OurBrandBtn = By.xpath("//img[contains(@class,'img-fluid') and contains(@alt,'Brand logo 134266051')]");
    By OurBrandPageTxt = By.xpath("//div[contains(@class,'_1g8obcq mont _vk0dbk mt-2') and contains(text(),'Angela Mia')]");
    By HungerfordSmithBrand = By.xpath("//img[@alt='Brand logo 87504485' and @class='img-fluid']");
    By HungerfordSmithText =By.xpath("//div[@class='_1g8obcq mont _vk0dbk mt-2' and contains(text(),'J Hungerford Smith')]");


    public boolean isProductShowcaseTxtDisplayed(){
        return distributorUI.isDisplayed(productShowcaseTxt);
    }

    public void SearchInProductSearchBar(String productName){
        distributorUI.sendKeys(productSearchBar,productName);
    }

    public void SelectProductInShowCase(){
        distributorUI.isDisplayed(productDisplayed);
        distributorUI.click(productDisplayed);
    }

    public void ClickOnManufacturer(){
        distributorUI.isDisplayed(ManufacturerTxt);
        distributorUI.click(ManufacturerTxt);
    }

    public boolean isConagraFoodServiceTxtDisplayed(){
        return distributorUI.isDisplayed(ConagraFoodServiceTxt);
    }

    public void ClickOnOurBrandBtn(){
        distributorUI.click(OurBrandBtn);
    }

    public boolean isOurBrandPageisDisplayed(){
        distributorUI.isDisplayed(OurBrandPageTxt);
        return distributorUI.isDisplayed(OurBrandPageTxt);
    }

    public void ClickOnHungerfordSmith(){
        distributorUI.click(HungerfordSmithBrand);
    }

    public boolean isHungerFordSmithtxtDisplayed(){
        distributorUI.isDisplayed(HungerfordSmithText);
        return distributorUI.isDisplayed(HungerfordSmithText);
    }

}
