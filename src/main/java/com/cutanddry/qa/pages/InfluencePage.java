package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class InfluencePage extends LoginPage{
    By influenceTxt = By.xpath("//div[normalize-space(text()) = 'Influence']");
    String campaignMatrices = "//div[normalize-space(text()) = 'MATRICENAME']";

    public boolean isInfluenceTxtDisplayed(){
        return distributorUI.isDisplayed(influenceTxt);
    }

    public boolean isCampaignMatricesDisplayed(String matricesName){
        return distributorUI.isDisplayed(By.xpath(campaignMatrices.replace("MATRICENAME",matricesName)));
    }



}
