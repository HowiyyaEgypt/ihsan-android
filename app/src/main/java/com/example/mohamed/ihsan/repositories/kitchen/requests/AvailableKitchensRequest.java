package com.example.mohamed.ihsan.repositories.kitchen.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 04/05/2018.
 */

public class AvailableKitchensRequest {

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    public AvailableKitchensRequest(int cityId) {
        this.cityId = cityId;
    }

    public AvailableKitchensRequest(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
