package com.example.mohamed.ihsan.repositories.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class Location {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("lat")
    @Expose
    double lat;

    @SerializedName("lng")
    @Expose
    double lng;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("city_id")
    @Expose
    int cityId;

    @SerializedName("city_name")
    @Expose
    String cityName;

    @SerializedName("is_visible")
    @Expose
    boolean isVisible;

    @SerializedName("is_favorite")
    @Expose
    boolean isFavorite;

    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getDescription() {
        return description;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
