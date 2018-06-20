package com.example.mohamed.ihsan.api.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 27/04/2018.
 */

public class GenericErrorResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("error_code")
    private int errorCode;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
