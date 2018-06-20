package com.example.mohamed.ihsan.ui.donatemeal.contracts;

import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.location.Location;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/05/2018.
 */

public interface DonateMealEchoContract {
    void cityWasAutomaticallyDetected();
    void cityWasSelectedFromSpinner(int cityId);
    void kitchenWasSelectedFromRecyclerOrMap(Kitchen kitchen);
    void proceedToMealFormAfterSelectionOldLocation(Location location);
    void proceedToMealFormWithSelfDelivery();
    void newUserLocationWasPickedFromMap(double lat, double lng, String locationDescription);
    void uploadMealPhoto();
    void submitMeal(int bellies, String description, int kitchenId);
}
