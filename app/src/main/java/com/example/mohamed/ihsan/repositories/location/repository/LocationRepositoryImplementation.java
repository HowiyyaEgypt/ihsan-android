package com.example.mohamed.ihsan.repositories.location.repository;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.repositories.location.listeners.OnUserAllLocationsReadyListener;
import com.example.mohamed.ihsan.repositories.location.listeners.OnUserLocationReadyListener;
import com.example.mohamed.ihsan.repositories.location.requests.AddNewUserLocationRequest;
import com.example.mohamed.ihsan.repositories.location.responses.NewUserLocationAddedResponse;
import com.example.mohamed.ihsan.repositories.location.responses.UserLocationsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class LocationRepositoryImplementation implements LocationRepository {

    private IhsanApiEndpoints ihsanApi;

    public LocationRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void requestAllUserLocations(String token, final OnUserAllLocationsReadyListener listener) {

        Call<UserLocationsResponse> call = ihsanApi.getUserLocations(token);

        call.enqueue(new Callback<UserLocationsResponse>() {
            @Override
            public void onResponse(Call<UserLocationsResponse> call, Response<UserLocationsResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getLocations());
                        break;
                }
            }

            @Override
            public void onFailure(Call<UserLocationsResponse> call, Throwable t) {
                Log.e( "err", t.getMessage());
            }
        });

    }

    @Override
    public void addNewLocationForUser(String token, double lat, double lng, String locationDescription, final OnUserLocationReadyListener listener) {

        AddNewUserLocationRequest request = new AddNewUserLocationRequest(lat, lng, locationDescription);

        Call<NewUserLocationAddedResponse> call = ihsanApi.addNewUserLocation(token, request);

        call.enqueue(new Callback<NewUserLocationAddedResponse>() {
            @Override
            public void onResponse(Call<NewUserLocationAddedResponse> call, Response<NewUserLocationAddedResponse> response) {
                switch (response.code()) {
                    case 200:
                        Log.e("id :: ", String.valueOf(response.body().getLocation().getId()));
                        listener.onSuccess(response.body().getLocation());
                        break;
                    case 470:
//                        listener.onFailure();
                        break;
                }
            }

            @Override
            public void onFailure(Call<NewUserLocationAddedResponse> call, Throwable t) {

            }
        });
    }
}
