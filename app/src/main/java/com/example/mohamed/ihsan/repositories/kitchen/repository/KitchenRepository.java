package com.example.mohamed.ihsan.repositories.kitchen.repository;

import com.example.mohamed.ihsan.repositories.kitchen.listeners.OnAvailableKitchensReadyListener;
import com.example.mohamed.ihsan.repositories.kitchen.listeners.OnKitchenReadyListener;

/**
 * Created by Mohamed on 04/05/2018.
 */

public interface KitchenRepository {
    void requestAvailableKitchens(int mode, int cityId, double lat, double lng, OnAvailableKitchensReadyListener listener);
    void requestSingleKitchenData(String token, int kitchenId, OnKitchenReadyListener listener);
    void openNewKitchenInExistingLocation(String token, int organizationId, int mode, long fullOpeningDateTimeUnix, long fullClosingDateTimeUnix, String kitchenName, String kitchenDescription, int cityId, int locationId, OnKitchenReadyListener listener);
    void openNewKitchenInNewLocation(String token, int organizationId, int mode, long fullOpeningDateTimeUnix, long fullClosingDateTimeUnix, String kitchenName, String kitchenDescription, int cityId, double lat, double lng, OnKitchenReadyListener listener);
}
