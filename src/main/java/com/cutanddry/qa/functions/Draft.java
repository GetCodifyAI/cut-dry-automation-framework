package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.DraftPage;

public class Draft {
    static DraftPage draftPage = new DraftPage();

    public static boolean isUserNavigatedToDrafts(){
        return draftPage.isDraftsTextDisplayed();
    }
    public static void clickOnDelete(){
        draftPage.clickOnDelete();
    }
    public static boolean isLastDraftDisplayed(){
        return draftPage.isLastDraftDisplayed();
    }
    public static boolean isDraftDeleted(){
        return draftPage.isDraftDeleted();
    }
}
