package com.example.mohamed.ihsan.ui.openkitchen.presenter;

import android.content.Context;

import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.repositories.city.City;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.kitchen.listeners.OnKitchenReadyListener;
import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepository;
import com.example.mohamed.ihsan.repositories.location.Location;
import com.example.mohamed.ihsan.repositories.organization.listeners.OnOrganizationLocationsUtilReadyListener;
import com.example.mohamed.ihsan.repositories.organization.repositories.OrganizationRepository;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.openkitchen.contracts.OpenKitchenContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 17/05/2018.
 */

public class OpenKitchenPresenter implements OpenKitchenContract.Presenter {

    private OpenKitchenContract.View view;
    private Context context;
    private OrganizationRepository organization_repository;
    private KitchenRepository kitchen_repository;

    public OpenKitchenPresenter(Context context, OrganizationRepository organizationRepository, KitchenRepository kitchenRepository) {
        this.context = context;
        this.organization_repository = organizationRepository;
        this.kitchen_repository = kitchenRepository;
    }

    @Override
    public void setView(OpenKitchenContract.View view) {
        this.view = view;
    }

    @Override
    public void requestOrganizationLocationsUtil(int organizationId) {

        organization_repository.getOrganizationLocationUtils(organizationId, new OnOrganizationLocationsUtilReadyListener() {
            @Override
            public void onSuccess(ArrayList<City> cities, ArrayList<Location> locations) {
                view.displayCitiesAndLocations(cities, locations);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    @Override
    public void requestOpeningNewKitchenInExistingLocation(int organizationId, long fullOpeningDateTimeUnix, long fullClosingDateTimeUnix, String kitchenName, String kitchenDescription, int cityId, int locationId) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        kitchen_repository.openNewKitchenInExistingLocation(localAuthUser.getToken(), organizationId, 1, fullOpeningDateTimeUnix, fullClosingDateTimeUnix, kitchenName, kitchenDescription, cityId, locationId, new OnKitchenReadyListener() {
            @Override
            public void onSuccess(Kitchen kitchen) {
                view.navigateToKitchenAfterAddition(kitchen);
            }

            @Override
            public void onFailure(String msg) {
                view.displayErrorMessage(msg);
            }
        });

    }

    @Override
    public void requestOpeningNewKitchenInNewLocation(int organizationId, long fullOpeningDateTimeUnix, long fullClosingDateTimeUnix, String kitchenName, String kitchenDescription, int cityId, double lat, double lng) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        kitchen_repository.openNewKitchenInNewLocation(localAuthUser.getToken(), organizationId, 2, fullOpeningDateTimeUnix, fullClosingDateTimeUnix, kitchenName, kitchenDescription, cityId, lat, lng, new OnKitchenReadyListener() {
            @Override
            public void onSuccess(Kitchen kitchen) {
                view.navigateToKitchenAfterAddition(kitchen);
            }

            @Override
            public void onFailure(String msg) {
                view.displayErrorMessage(msg);
            }
        });
    }
}
