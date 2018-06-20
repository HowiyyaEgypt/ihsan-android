package com.example.mohamed.ihsan.repositories.feed.responses;

import com.example.mohamed.ihsan.repositories.feed.Feed;
import com.example.mohamed.ihsan.repositories.feed.MetaFeed;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/06/2018.
 */

public class LatestFeedResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<Feed> feed;

    @SerializedName("meta")
    private MetaFeed metaFeed;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Feed> getFeed() {
        return feed;
    }

    public MetaFeed getMetaFeed() {
        return metaFeed;
    }
}
