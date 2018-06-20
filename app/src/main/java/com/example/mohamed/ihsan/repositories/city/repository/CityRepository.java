package com.example.mohamed.ihsan.repositories.city.repository;

import com.example.mohamed.ihsan.repositories.city.listener.OnCitiesReadyListener;

/**
 * Created by Mohamed on 01/05/2018.
 */

public interface CityRepository {
    void requestAllCities(String lang, OnCitiesReadyListener listener);
}
