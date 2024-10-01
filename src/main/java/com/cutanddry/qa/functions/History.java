package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.HistoryPage;

public class History {
    static HistoryPage historyPage = new HistoryPage();

    public static boolean isUserNavigatedToHistory(){
        return historyPage.isOrderHistoryTextDisplayed();
    }
    public static void clickFirstItemFrmHistory(){
         historyPage.clickFirstItemFrmHistory();
    }
}
