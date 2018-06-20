package com.example.mohamed.ihsan.ui.tracking.contracts;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;

/**
 * Created by Mohamed on 25/05/2018.
 */

public interface TrackingActionEchoContract {

    interface ParentView {
        void cancelMealDelivery(int mealId, Delivery delivery);
        void confirmMealReception(int mode, int mealId, int deliveryId, OnTrackingItemUpdatedListener listener);
    }

    interface ViewPagerHolder {
        void requestFragmentsToSyncAfterMealCancel(Delivery delivery);
    }

    interface SyncTrackingFragment {
        void syncFragment(Delivery delivery);
        void syncFragment(Meal meal);
    }

    interface SyncTrackingAdapter {
        void syncAdapter(Delivery delivery);
        void syncAdapter(Meal meal);
    }
}
