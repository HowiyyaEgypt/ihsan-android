package com.example.mohamed.ihsan.repositories.tracking.repository;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.repositories.delivery.listeners.OnDeliveryReadyListener;
import com.example.mohamed.ihsan.repositories.delivery.responses.SingleDeliveryResponse;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;
import com.example.mohamed.ihsan.repositories.meal.responses.SingleMealResponse;
import com.example.mohamed.ihsan.repositories.tracking.listeners.OnTrackingReadyListener;
import com.example.mohamed.ihsan.repositories.tracking.responses.UserTrackingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class TrackingRepositoryImplementation implements TrackingRepository {

    private IhsanApiEndpoints ihsanApi;

    public TrackingRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void requestUserTrackingData(String token, final OnTrackingReadyListener listener) {

        Call<UserTrackingResponse> call = ihsanApi.getUserTrackingData(token);

        call.enqueue(new Callback<UserTrackingResponse>() {
            @Override
            public void onResponse(Call<UserTrackingResponse> call, Response<UserTrackingResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getTracking().getMeals(), response.body().getTracking().getDeliveries());
                        break;
                }
            }

            @Override
            public void onFailure(Call<UserTrackingResponse> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });

    }

    @Override
    public void cancelMealDelivery(String token, int mealId, int deliveryId, final OnMealReadyListener listener) {

        Call<SingleMealResponse> call = ihsanApi.cancelMealDelivery(mealId, deliveryId, token);

        call.enqueue(new Callback<SingleMealResponse>() {
            @Override
            public void onResponse(Call<SingleMealResponse> call, Response<SingleMealResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getMeal());
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleMealResponse> call, Throwable t) {
                Log.e("error: ", t.getMessage());
            }
        });

    }

    @Override
    public void confirmMealReception(String token, int mode, int mealId, int deliveryId, final OnDeliveryReadyListener listener) {

        Call<SingleDeliveryResponse> call = ihsanApi.confirmMealReception(mealId, deliveryId, token, mode);

        call.enqueue(new Callback<SingleDeliveryResponse>() {
            @Override
            public void onResponse(Call<SingleDeliveryResponse> call, Response<SingleDeliveryResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getDelivery());
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleDeliveryResponse> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }
}
