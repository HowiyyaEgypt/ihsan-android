package com.example.mohamed.ihsan.ui.donatemeal.contracts;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.location.Location;

import java.util.ArrayList;

/**
 * Created by Mohamed on 30/04/2018.
 */

public interface DonateMealContract {

    interface View {
        void displayAllCities(ArrayList<City> cities);
        void displayAvailableKitchens(ArrayList<Kitchen> kitchens);
        void displayAllUserLocations(ArrayList<Location> locations);
        void proceedToMealFormAfterAddingNewLocation(Location location);
        void showNoAvailableKitchensError(String msg);
        void showFinalSuccessMessage(String msg);
    }

    interface Presenter {
        void setView(DonateMealContract.View view);
        void requestAllCities();
        void requestAvailableKitchens(int mode, int cityId, double lat, double lng);
        void requestUserLocations();
        void addNewUserLocation(double lat, double lng, String locationDescription);
        void submitMeal(int mode, int pickupLocatiodId, int bellies, String description, int kitchenId, ArrayList<Uri> fileUris);
        void submitMeal(int mode, int bellies, String description, int kitchenId, ArrayList<Uri> fileUris);
    }

}
