package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CatalogPage;

public class Catalog {
    static CatalogPage catalogPage = new CatalogPage();

    public static boolean isUserNavigatedToCatalog(){
        return catalogPage.isCatalogTextDisplayed();
    }
    public static void selectFirstProduct() {
        catalogPage.clickFirstItem();
    }
    public static boolean isSelectedProductDisplayed() {
        return catalogPage.isSelectedProductDisplayed();
    }
    public static void clickOnPreview() {
        catalogPage.clickPreview();
    }
    public static boolean isNavigatedToPreview() {
        return catalogPage.isNavigatedToPreview();
    }
    public static void DownloadPDF() {
        catalogPage.clickExportPdf();
    }
    public static void SearchItemInCatalogByItemCode(String ItemCode){catalogPage.TypeSearchInCatalogSearch(ItemCode);}
    public static void SelectItemAfterSearch(String ItemCode){catalogPage.ClickOnItemCode(ItemCode);}
    public static void ClickOnPreview(){
        catalogPage.ClickOnPreviewBtn();
    }
    public static boolean isItemPreviewDisplayed(){
        return catalogPage.isNavigateToItemPreview();
    }
    public static void SelectManufacturer(){
        catalogPage.ClickOnManufacture();
    }

    public static boolean isCongaraBrandPageDisplayed(){
        return catalogPage.isNavigatedtoConagaraBrandPage();

    }
    public static boolean isOtherBrandsPageDisplayed() throws InterruptedException {
        return catalogPage.isNavigatedtoOtherBrandPage();
    }
    public static void NavigateToShowCasePage(){
        catalogPage.ClickOnShowCaseBtn();
    }
}
