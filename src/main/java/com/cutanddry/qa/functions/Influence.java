package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.InfluencePage;

public class Influence {
    static InfluencePage influencePage = new InfluencePage();

    public static boolean isInfluenceTxtDisplayed(){
        return influencePage.isInfluenceTxtDisplayed();
    }

    public static boolean isCampaignMatricesDisplayed(String matricesName){
        return influencePage.isCampaignMatricesDisplayed(matricesName);
    }


}
