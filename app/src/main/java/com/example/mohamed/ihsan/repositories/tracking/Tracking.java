package com.example.mohamed.ihsan.repositories.tracking;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class Tracking implements Serializable {

    @SerializedName("meals")
    @Expose
    ArrayList<Meal> meals;

    @SerializedName("deliveries")
    @Expose
    ArrayList<Delivery> deliveries;

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }
}
