package com.example.mohamed.ihsan.repositories.meal.listeners;

import com.example.mohamed.ihsan.repositories.meal.Meal;

/**
 * Created by Mohamed on 05/05/2018.
 */

public interface OnMealReadyListener {
    void onSuccess(Meal meal);
    void onFailure(String msg);
}
