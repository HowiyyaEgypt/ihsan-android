package com.example.mohamed.ihsan.repositories.city.listener;

import com.example.mohamed.ihsan.repositories.city.City;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/05/2018.
 */

public interface OnCitiesReadyListener {
    void onSuccess(ArrayList<City> cities);
    void onFailure(String msg);
}
