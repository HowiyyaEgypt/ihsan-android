package com.example.mohamed.ihsan.repositories.kitchen.repository;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.api.general.GenericErrorResponse;
import com.example.mohamed.ihsan.repositories.kitchen.listeners.OnAvailableKitchensReadyListener;
import com.example.mohamed.ihsan.repositories.kitchen.listeners.OnKitchenReadyListener;
import com.example.mohamed.ihsan.repositories.kitchen.requests.OpenNewKitchenRequest;
import com.example.mohamed.ihsan.repositories.kitchen.responses.AvailableKitchensResponse;
import com.example.mohamed.ihsan.repositories.kitchen.responses.SingleKitchenResponse;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 04/05/2018.
 */

public class KitchenRepositoryImplementation implements KitchenRepository {

    private IhsanApiEndpoints ihsanApi;

    public KitchenRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void requestAvailableKitchens(int mode, int cityId, double lat, double lng, final OnAvailableKitchensReadyListener listener) {

        Call<AvailableKitchensResponse> call = ihsanApi.getAvailableKitchensInACityOrLocation(mode, cityId, lat, lng);

        call.enqueue(new Callback<AvailableKitchensResponse>() {
            @Override
            public void onResponse(Call<AvailableKitchensResponse> call, Response<AvailableKitchensResponse> response) {
                switch (response.code()) {

                    case 200:
                        listener.onSuccess(response.body().getKitchens());
                        break;
                    case 470:
                        try {
                            GenericErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), GenericErrorResponse.class);
                            listener.onFailure(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }

            @Override
            public void onFailure(Call<AvailableKitchensResponse> call, Throwable t) {
                Log.e("333333",t.getMessage());
            }
        });

    }

    @Override
    public void requestSingleKitchenData(String token, int kitchenId, final OnKitchenReadyListener listener) {

        Call<SingleKitchenResponse> call = ihsanApi.getKitchenData(kitchenId, token);

        call.enqueue(new Callback<SingleKitchenResponse>() {
            @Override
            public void onResponse(Call<SingleKitchenResponse> call, Response<SingleKitchenResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getKitchen());
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleKitchenResponse> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });

    }

    @Override
    public void openNewKitchenInExistingLocation(String token, int organizationId, int mode, long fullOpeningDateTimeUnix, long fullClosingDateTimeUnix, String kitchenName, String kitchenDescription, int cityId, int locationId, final OnKitchenReadyListener listener) {

        OpenNewKitchenRequest request = new OpenNewKitchenRequest(mode, fullOpeningDateTimeUnix, fullClosingDateTimeUnix, kitchenName, kitchenDescription, cityId,locationId);

        Call<SingleKitchenResponse> call = ihsanApi.openNewKitchen(organizationId, token, request);

        call.enqueue(new Callback<SingleKitchenResponse>() {
            @Override
            public void onResponse(Call<SingleKitchenResponse> call, Response<SingleKitchenResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getKitchen());
                        break;
                    case 470:
                        try {
                            GenericErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), GenericErrorResponse.class);
                            listener.onFailure(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleKitchenResponse> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });

    }

    @Override
    public void openNewKitchenInNewLocation(String token, int organizationId, int mode, long fullOpeningDateTimeUnix, long fullClosingDateTimeUnix, String kitchenName, String kitchenDescription, int cityId, double lat, double lng, final OnKitchenReadyListener listener) {

        OpenNewKitchenRequest request = new OpenNewKitchenRequest(mode, fullOpeningDateTimeUnix, fullClosingDateTimeUnix, kitchenName, kitchenDescription, cityId,lat, lng);

        Call<SingleKitchenResponse> call = ihsanApi.openNewKitchen(organizationId, token, request);

        call.enqueue(new Callback<SingleKitchenResponse>() {
            @Override
            public void onResponse(Call<SingleKitchenResponse> call, Response<SingleKitchenResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getKitchen());
                        break;
                    case 470:
                        try {
                            GenericErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), GenericErrorResponse.class);
                            listener.onFailure(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleKitchenResponse> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }
}
