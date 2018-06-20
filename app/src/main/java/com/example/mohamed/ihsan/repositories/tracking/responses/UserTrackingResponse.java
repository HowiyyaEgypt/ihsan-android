package com.example.mohamed.ihsan.repositories.tracking.responses;

import com.example.mohamed.ihsan.repositories.tracking.Tracking;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class UserTrackingResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Tracking tracking;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Tracking getTracking() {
        return tracking;
    }
}
