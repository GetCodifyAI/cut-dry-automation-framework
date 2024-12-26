package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ShowCasePage;

public class ShowCase {
    static ShowCasePage showCasePage = new ShowCasePage();

    public static boolean isNavigateToShowCase(){
        return showCasePage.isProductShowcaseTxtDisplayed();
    }

    public static void SearchProductInProductSearhBar(String ProductName){
        showCasePage.SearchInProductSearchBar(ProductName);
    }

    public static void SelectProductFromShowCase(){
        showCasePage.SelectProductInShowCase();
    }

    public static void NavigateToManufacturerPage(){
        showCasePage.ClickOnManufacturer();
    }

    public static boolean isNavigatedToManufacturerPage(String brand){
        return showCasePage.isConagraFoodServiceTxtDisplayed(brand);
    }

    public static void NavigateToOurBrandsPage() throws InterruptedException {
        showCasePage.ClickOnOurBrandBtn();
    }

    public static boolean isNavigatedToOurBrandsPage(){
        return showCasePage.isOurBrandPageisDisplayed();
    }

    public static void NavigateToHungerfordSmithPage() throws InterruptedException {
        showCasePage.ClickOnHungerfordSmith();
    }

    public static boolean isNavigatedToHungerfordSmithPage(){
        return showCasePage.isHungerFordSmithtxtDisplayed();
    }

}
