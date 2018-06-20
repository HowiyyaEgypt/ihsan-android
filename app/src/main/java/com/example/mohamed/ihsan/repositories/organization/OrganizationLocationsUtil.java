package com.example.mohamed.ihsan.repositories.organization;

import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohamed on 17/05/2018.
 */

public class OrganizationLocationsUtil {

    @SerializedName("cities")
    @Expose
    ArrayList<City> cities;

    @SerializedName("locations")
    @Expose
    ArrayList<Location> locations;

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
