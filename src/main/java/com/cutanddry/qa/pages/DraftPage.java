package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

import java.util.Objects;

public class DraftPage extends LoginPage{
    By txt_drafts = By.xpath("//li[contains(text(),'Drafts')]");
    By btn_delete = By.xpath("(//button[contains(text(), 'Delete')])[1]");
    By txt_lastDraft = By.xpath("//tbody/tr[2]/td[5]");


    public boolean isDraftsTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_drafts);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_drafts);
    }
    public void clickOnDelete(){
        distributorUI.waitForVisibility(btn_delete);
        distributorUI.click(btn_delete);
        distributorUI.waitForInvisibility(btn_delete);
    }
    public boolean isLastDraftDisplayed(){
        distributorUI.waitForVisibility(txt_lastDraft);
        return Objects.equals(distributorUI.getText(txt_lastDraft), "just now");
    }
}
