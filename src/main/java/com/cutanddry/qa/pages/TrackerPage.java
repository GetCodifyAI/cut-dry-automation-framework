package com.cutanddry.qa.pages;
import org.openqa.selenium.By;

public class TrackerPage extends LoginPage{

    By txt_onboarding = By.xpath("//div[@class='_1ducyq8 mont _1npprf5' and text()='Onboarding Docs']");


    public boolean isOnboardingDocsDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_onboarding);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_onboarding);
    }

}
