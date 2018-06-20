package com.example.mohamed.ihsan.repositories.feed.repository;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.repositories.feed.listeners.OnFeedReadyListener;
import com.example.mohamed.ihsan.repositories.feed.responses.LatestFeedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 02/06/2018.
 */

public class FeedRepositoryImplementation implements FeedRepository {

    private IhsanApiEndpoints ihsanApi;

    public FeedRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void requestLatestFeed(String token, final OnFeedReadyListener listener) {

        Call<LatestFeedResponse> call = ihsanApi.getLatestFeed(token);

        call.enqueue(new Callback<LatestFeedResponse>() {
            @Override
            public void onResponse(Call<LatestFeedResponse> call, Response<LatestFeedResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getFeed(),
                                response.body().getMetaFeed().getTotalDonatedMeals(),
                                response.body().getMetaFeed().getTotalDeliveredMeals(),
                                response.body().getMetaFeed().getTotalKitchens());
                        break;
                }
            }

            @Override
            public void onFailure(Call<LatestFeedResponse> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });

    }
}
