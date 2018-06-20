package com.example.mohamed.ihsan.repositories.kitchen.responses;

import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Mohamed on 18/05/2018.
 */

public class SingleKitchenResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Kitchen kitchen;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }
}
