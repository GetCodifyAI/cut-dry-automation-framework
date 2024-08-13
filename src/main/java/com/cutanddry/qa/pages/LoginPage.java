package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;

public class LoginPage extends TestBase {
    By txt_emailOrMobile = By.xpath("//input[@placeholder='Email or mobile']");
    By txt_password = By.xpath("//input[@placeholder='Password']");
    By btn_signIn = By.xpath("//button[@type='submit']");

    public void typeEmailOrMobile(String emailOrMobile){
        distributorUI.sendKeys(txt_emailOrMobile,emailOrMobile);
    }
    public void typePassword(String password){
        distributorUI.sendKeys(txt_password,password);
    }
    public void clickSubmit(){
        distributorUI.click(btn_signIn);
    }

}
