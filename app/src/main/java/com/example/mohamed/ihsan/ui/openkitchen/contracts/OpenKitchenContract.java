package com.example.mohamed.ihsan.ui.openkitchen.contracts;

import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.location.Location;

import java.util.ArrayList;

/**
 * Created by Mohamed on 17/05/2018.
 */

public interface OpenKitchenContract {

    interface View {
        void displayCitiesAndLocations(ArrayList<City> cities, ArrayList<Location> locations);
        void navigateToKitchenAfterAddition(Kitchen kitchen);
        void displayErrorMessage(String msg);
    }

    interface Presenter {
        void setView(OpenKitchenContract.View view);
        void requestOrganizationLocationsUtil(int organizationId);
        void requestOpeningNewKitchenInExistingLocation(
                int organizationId,
                long fullOpeningDateTimeUnix,
                long fullClosingDateTimeUnix,
                String kitchenName,
                String kitchenDescription,
                int cityId,
                int locationId);
        void requestOpeningNewKitchenInNewLocation(
                int organizationId,
                long fullOpeningDateTimeUnix,
                long fullClosingDateTimeUnix,
                String kitchenName,
                String kitchenDescription,
                int cityId,
                double lat,
                double lng
        );
    }
}
