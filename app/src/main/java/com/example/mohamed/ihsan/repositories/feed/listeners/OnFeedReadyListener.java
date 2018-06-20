package com.example.mohamed.ihsan.repositories.feed.listeners;

import com.example.mohamed.ihsan.repositories.feed.Feed;
import com.example.mohamed.ihsan.repositories.feed.MetaFeed;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/06/2018.
 */

public interface OnFeedReadyListener {
    void onSuccess(ArrayList<Feed> feeds, int totalDonatedMeals, int totalDeliveries, int totalKitchens);
    void onFailure(String msg);
}
