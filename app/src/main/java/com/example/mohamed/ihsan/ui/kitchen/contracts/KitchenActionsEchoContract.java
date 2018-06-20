package com.example.mohamed.ihsan.ui.kitchen.contracts;

import com.example.mohamed.ihsan.repositories.meal.Meal;

/**
 * Created by Mohamed on 23/05/2018.
 */

public interface KitchenActionsEchoContract {

    interface ParentView {
        void mealWasPickedForDelivery(Meal meal);
        void cancelMealDelivery(int mode, Meal meal, int deliveryId);
        void confirmMealReception(int mode, Meal meal, int deliveryId);
    }

    interface ViewPagerHolder {
        void requestFragmentsToSyncAfterMealTransaction(int mode, Meal meal);
    }

    interface FragmentSync {
        void requestAdapterToSync(int mode, Meal meal);
    }

    interface AdapterSync {
        void syncMeals(int mode, Meal meal);
    }
}
