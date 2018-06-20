package com.example.mohamed.ihsan.repositories.kitchen.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 18/05/2018.
 */

public class OpenNewKitchenRequest {

    @SerializedName("mode")
    private int mode;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("location_id")
    private int locationId;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("opening_time")
    private long fullOpeningDateTimeUnix;

    @SerializedName("closing_time")
    private long fullClosingDateTimeUnix;

    @SerializedName("name")
    private String kitchenName;

    @SerializedName("description")
    private String kitchenDescription;

    public OpenNewKitchenRequest(
            int mode,
            long fullOpeningDateTimeUnix,
            long fullClosingDateTimeUnix,
            String kitchenName,
            String kitchenDescription,
            int cityId,
            int locationId
    )
    {
        this.mode = mode;
        this.fullOpeningDateTimeUnix = fullOpeningDateTimeUnix;
        this.fullClosingDateTimeUnix = fullClosingDateTimeUnix;
        this.kitchenName = kitchenName;
        this.kitchenDescription = kitchenDescription;
        this.cityId = cityId;
        this.locationId = locationId;
    }

    public OpenNewKitchenRequest(
            int mode,
            long fullOpeningDateTimeUnix,
            long fullClosingDateTimeUnix,
            String kitchenName,
            String kitchenDescription,
            int cityId,
            double lat,
            double lng
    )
    {
        this.mode = mode;
        this.fullOpeningDateTimeUnix = fullOpeningDateTimeUnix;
        this.fullClosingDateTimeUnix = fullClosingDateTimeUnix;
        this.kitchenName = kitchenName;
        this.kitchenDescription = kitchenDescription;
        this.cityId = cityId;
        this.lat = lat;
        this.lng = lng;
    }
}
