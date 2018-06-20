package com.example.mohamed.ihsan.repositories.location.responses;

import com.example.mohamed.ihsan.repositories.location.Location;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class UserLocationsResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<Location> locations;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
