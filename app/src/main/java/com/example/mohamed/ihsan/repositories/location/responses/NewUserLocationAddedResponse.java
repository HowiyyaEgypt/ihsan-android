package com.example.mohamed.ihsan.repositories.location.responses;

import com.example.mohamed.ihsan.repositories.location.Location;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class NewUserLocationAddedResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Location location;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Location getLocation() {
        return location;
    }
}
