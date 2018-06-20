package com.example.mohamed.ihsan.ui.tracking.contracts;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;

import java.util.ArrayList;

/**
 * Created by Mohamed on 22/05/2018.
 */

public interface TrackingContract {

    interface View {
        void displayMessage(String msg);
        void displayTrackingData(ArrayList<Meal> meals, ArrayList<Delivery> deliveries);
    }

    interface Presenter {
        void setView(TrackingContract.View view);
        void requestTrackingData();
        void cancelDelivery(int mealId, int deliveryId);
        void confirmMealReception(int mode, int mealId, int deliveryId, OnTrackingItemUpdatedListener listener);
    }
}
