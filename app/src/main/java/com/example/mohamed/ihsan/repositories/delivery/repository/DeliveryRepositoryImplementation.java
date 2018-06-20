package com.example.mohamed.ihsan.repositories.delivery.repository;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.api.general.GenericErrorResponse;
import com.example.mohamed.ihsan.repositories.delivery.listeners.OnDeliveryReadyListener;
import com.example.mohamed.ihsan.repositories.delivery.responses.SingleDeliveryResponse;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;
import com.example.mohamed.ihsan.repositories.meal.responses.SingleMealResponse;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class DeliveryRepositoryImplementation implements DeliveryRepository {

    private IhsanApiEndpoints ihsanApi;

    public DeliveryRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void deliverMeal(String token, int mealId, int kitchenId, final OnDeliveryReadyListener listener) {

        Call<SingleDeliveryResponse> call = ihsanApi.deliverMeal(mealId, kitchenId, token);

        call.enqueue(new Callback<SingleDeliveryResponse>() {
            @Override
            public void onResponse(Call<SingleDeliveryResponse> call, Response<SingleDeliveryResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getDelivery());
                        break;
                    case 470:
                        GenericErrorResponse errorResponse = null;
                        try {
                            errorResponse = new Gson().fromJson(response.errorBody().string(), GenericErrorResponse.class);
                            listener.onFailure(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<SingleDeliveryResponse> call, Throwable t) {
                Log.e("err :  ", t.getMessage());
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
