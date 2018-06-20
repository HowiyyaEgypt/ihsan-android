package com.example.mohamed.ihsan.repositories.meal.responses;

import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class SingleMealResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Meal meal;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Meal getMeal() {
        return meal;
    }
}
