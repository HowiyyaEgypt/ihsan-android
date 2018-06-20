package com.example.mohamed.ihsan.repositories.city.repository;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.repositories.city.listener.OnCitiesReadyListener;
import com.example.mohamed.ihsan.repositories.city.responses.AllCitiesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 01/05/2018.
 */

public class CityRepositoryImplementation implements CityRepository {

    private IhsanApiEndpoints ihsanApi;

    public CityRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void requestAllCities(String lang, final OnCitiesReadyListener listener) {

        Call<AllCitiesResponse> call = ihsanApi.getAllCities(lang);

        call.enqueue(new Callback<AllCitiesResponse>() {
            @Override
            public void onResponse(Call<AllCitiesResponse> call, Response<AllCitiesResponse> response) {
                Log.e("code", String.valueOf(response.code()));
                switch (response.code()) {
                    case 200:
                        Log.e("eeee","22222222");
                        listener.onSuccess(response.body().getCities());
                        break;
                }
            }

            @Override
            public void onFailure(Call<AllCitiesResponse> call, Throwable t) {
                Log.e("1111","1111111111");
                Log.e("1111",t.getMessage());
            }
        });

    }
}
