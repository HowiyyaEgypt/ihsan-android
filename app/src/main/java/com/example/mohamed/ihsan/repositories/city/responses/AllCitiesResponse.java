package com.example.mohamed.ihsan.repositories.city.responses;

import com.example.mohamed.ihsan.repositories.city.City;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/05/2018.
 */

public class AllCitiesResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<City> cities;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}
