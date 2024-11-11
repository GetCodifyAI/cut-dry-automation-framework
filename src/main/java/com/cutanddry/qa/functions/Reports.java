package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ReportsPage;

public class Reports {
    static ReportsPage reportsPage = new ReportsPage();

    public static boolean isUserNavigatedToReports(){
        return reportsPage.isReportingTextDisplayed();
    }
}
