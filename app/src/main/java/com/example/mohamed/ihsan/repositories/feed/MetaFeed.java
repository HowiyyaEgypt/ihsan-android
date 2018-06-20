package com.example.mohamed.ihsan.repositories.feed;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohamed on 01/06/2018.
 */

public class MetaFeed implements Serializable {

    @SerializedName("total_donated_meals")
    private int totalDonatedMeals;

    @SerializedName("total_delivered_meals")
    private int totalDeliveredMeals;

    @SerializedName("total_kitchens")
    private int totalKitchens;

    public int getTotalDonatedMeals() {
        return totalDonatedMeals;
    }

    public int getTotalDeliveredMeals() {
        return totalDeliveredMeals;
    }

    public int getTotalKitchens() {
        return totalKitchens;
    }
}
