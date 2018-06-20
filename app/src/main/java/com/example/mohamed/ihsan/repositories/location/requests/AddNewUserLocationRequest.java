package com.example.mohamed.ihsan.repositories.location.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class AddNewUserLocationRequest {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("description")
    private String locationDescription;

    public AddNewUserLocationRequest(double lat, double lng, String locationDescription) {
        this.lat = lat;
        this.lng = lng;
        this.locationDescription = locationDescription;
    }
}
