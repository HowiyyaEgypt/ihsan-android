package com.example.mohamed.ihsan.ui.kitchen.contracts;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.meal.Meal;

/**
 * Created by Mohamed on 18/05/2018.
 */

public interface KitchenContract {

    interface View {
        void displayMsg(String msg);
        void displayKitchenData(Kitchen kitchen);
        void navigateToTracking(int mode, Delivery delivery);
        void returnMealBackToBeDelivered(Meal meal);
    }

    interface Presenter {
        void setView(KitchenContract.View view);
        void requestKitchenData(int kitchenId);
        void deliverMeal(int mealId, int kitchenId);
        void cancelMealDelivery(int mode, int mealId, int deliveryId);
        void confirmMealReception(int mode, int mealId, int deliveryId);
    }
}
