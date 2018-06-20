package com.example.mohamed.ihsan.repositories.feed.repository;

import com.example.mohamed.ihsan.repositories.feed.listeners.OnFeedReadyListener;

/**
 * Created by Mohamed on 01/06/2018.
 */

public interface FeedRepository {
    void requestLatestFeed(String token, OnFeedReadyListener listener);
}
