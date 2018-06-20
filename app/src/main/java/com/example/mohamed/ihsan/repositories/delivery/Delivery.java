package com.example.mohamed.ihsan.repositories.delivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class Delivery implements Serializable {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("meal_id")
    @Expose
    int mealId;

    @SerializedName("kitchen_id")
    @Expose
    int kitchenId;

    @SerializedName("kitchen_name")
    @Expose
    String kitchenName;

    @SerializedName("meal_donor_id")
    @Expose
    int mealDonorId;

    @SerializedName("meal_donor_name")
    @Expose
    String mealDonorName;

    @SerializedName("meal_description")
    @Expose
    String mealDescription;

    @SerializedName("pickup_date")
    @Expose
    String pickupDate;

    @SerializedName("delivery_date")
    @Expose
    String deliveryDate;

    @SerializedName("meal_stage")
    @Expose
    int mealStage;

    @SerializedName("meals_bellies")
    @Expose
    int mealBellies;

    @SerializedName("location_description")
    @Expose
    String pickupLocationDescription;


    public int getId() {
        return id;
    }

    public int getMealId() {
        return mealId;
    }

    public int getKitchenId() {
        return kitchenId;
    }

    public int getMealDonorId() {
        return mealDonorId;
    }

    public String getMealDonorName() {
        return mealDonorName;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public int getMealStage() {
        return mealStage;
    }

    public int getMealBellies() {
        return mealBellies;
    }

    public String getPickupLocationDescription() {
        return pickupLocationDescription;
    }

    public String getKitchenName() {
        return kitchenName;
    }
}
