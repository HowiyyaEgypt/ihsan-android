package com.example.mohamed.ihsan.repositories.meal.repository;

import android.net.Uri;

import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;

import java.util.ArrayList;

/**
 * Created by Mohamed on 05/05/2018.
 */

public interface MealRepository {
    void donateMeal(String token, int mode, Integer myLocationId, int bellies, String comment, int kitchenId, ArrayList<Uri> fileUris, OnMealReadyListener listener);
}
