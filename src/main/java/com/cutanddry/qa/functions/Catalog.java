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
}
