package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.OrdersPage;

public class Orders {
    static OrdersPage ordersPage = new OrdersPage();

    public static boolean isUserNavigatedToOrder(){
        return ordersPage.isOrdersTextDisplayed();
    }
}
