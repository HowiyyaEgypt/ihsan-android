package com.example.mohamed.ihsan.repositories.tracking.listeners;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;

import java.util.ArrayList;

/**
 * Created by Mohamed on 22/05/2018.
 */

public interface OnTrackingReadyListener {
    void onSuccess(ArrayList<Meal> meals, ArrayList<Delivery> deliveries);
    void onFailure(String msg);
}
