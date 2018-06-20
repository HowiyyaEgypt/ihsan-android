package com.example.mohamed.ihsan.repositories.kitchen.responses;

import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohamed on 04/05/2018.
 */

public class AvailableKitchensResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<Kitchen> kitchens;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Kitchen> getKitchens() {
        return kitchens;
    }
}
