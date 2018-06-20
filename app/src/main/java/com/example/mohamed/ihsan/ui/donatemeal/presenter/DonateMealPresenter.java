package com.example.mohamed.ihsan.ui.donatemeal.presenter;

import android.content.Context;
import android.net.Uri;

import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.city.listener.OnCitiesReadyListener;
import com.example.mohamed.ihsan.repositories.city.repository.CityRepository;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.kitchen.listeners.OnAvailableKitchensReadyListener;
import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepository;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.example.mohamed.ihsan.repositories.location.listeners.OnUserAllLocationsReadyListener;
import com.example.mohamed.ihsan.repositories.location.listeners.OnUserLocationReadyListener;
import com.example.mohamed.ihsan.repositories.location.repository.LocationRepository;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;
import com.example.mohamed.ihsan.repositories.meal.repository.MealRepository;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/05/2018.
 */

public class DonateMealPresenter implements DonateMealContract.Presenter {

    private DonateMealContract.View view;
    private CityRepository city_repository;
    private KitchenRepository kitchen_repository;
    private MealRepository meal_repository;
    private LocationRepository location_repository;
    private Context context;

    public DonateMealPresenter(Context context, CityRepository cityRepository, KitchenRepository kitchenRepository, MealRepository mealRepository, LocationRepository locationRepository) {
        this.context = context;
        this.city_repository = cityRepository;
        this.kitchen_repository = kitchenRepository;
        this.meal_repository = mealRepository;
        this.location_repository = locationRepository;
    }


    @Override
    public void setView(DonateMealContract.View view) {
        this.view = view;
    }

    @Override
    public void requestAllCities() {
        city_repository.requestAllCities("en", new OnCitiesReadyListener() {
            @Override
            public void onSuccess(ArrayList<City> cities) {
                view.displayAllCities(cities);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void requestAvailableKitchens(int mode, int cityId, double lat, double lng) {

        kitchen_repository.requestAvailableKitchens(mode, cityId, lat, lng, new OnAvailableKitchensReadyListener() {
            @Override
            public void onSuccess(ArrayList<Kitchen> kitchens) {
                view.displayAvailableKitchens(kitchens);
            }

            @Override
            public void onFailure(String msg) {
                view.showNoAvailableKitchensError(msg);
            }
        });

    }

    @Override
    public void requestUserLocations() {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        location_repository.requestAllUserLocations(localAuthUser.getToken(), new OnUserAllLocationsReadyListener() {
            @Override
            public void onSuccess(ArrayList<Location> locations) {
                view.displayAllUserLocations(locations);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    @Override
    public void addNewUserLocation(double lat, double lng, String locationDescription) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        location_repository.addNewLocationForUser(localAuthUser.getToken(), lat, lng, locationDescription, new OnUserLocationReadyListener() {
            @Override
            public void onSuccess(Location location) {
                view.proceedToMealFormAfterAddingNewLocation(location);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void submitMeal(int mode, int pickupLocatiodId, int bellies, String description, int kitchenId, ArrayList<Uri> fileUris) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        meal_repository.donateMeal(localAuthUser.getToken(), mode, pickupLocatiodId, bellies, description, kitchenId, fileUris, new OnMealReadyListener() {
            @Override
            public void onSuccess(Meal meal) {
                view.showFinalSuccessMessage("You successfully added a new meal!");
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void submitMeal(int mode, int bellies, String description, int kitchenId, ArrayList<Uri> fileUris) {
        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        meal_repository.donateMeal(localAuthUser.getToken(), mode, null, bellies, description, kitchenId, fileUris, new OnMealReadyListener() {
            @Override
            public void onSuccess(Meal meal) {
                view.showFinalSuccessMessage("You successfully added a new meal!");
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
