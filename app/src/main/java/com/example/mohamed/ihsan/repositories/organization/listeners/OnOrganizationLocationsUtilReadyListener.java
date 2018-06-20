package com.example.mohamed.ihsan.repositories.organization.listeners;

import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.location.Location;

import java.util.ArrayList;

/**
 * Created by Mohamed on 17/05/2018.
 */

public interface OnOrganizationLocationsUtilReadyListener {
    void onSuccess(ArrayList<City> cities, ArrayList<Location> locations);
    void onFailure(String msg);
}
