package com.example.mohamed.ihsan.repositories.location.repository;

import com.example.mohamed.ihsan.repositories.location.listeners.OnUserAllLocationsReadyListener;
import com.example.mohamed.ihsan.repositories.location.listeners.OnUserLocationReadyListener;

/**
 * Created by Mohamed on 05/05/2018.
 */

public interface LocationRepository {
    void requestAllUserLocations(String token, OnUserAllLocationsReadyListener listener);
    void addNewLocationForUser(String token, double lat, double lng, String locationDescription, OnUserLocationReadyListener listener);
}
