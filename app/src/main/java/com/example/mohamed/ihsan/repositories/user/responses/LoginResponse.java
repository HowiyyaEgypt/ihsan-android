package com.example.mohamed.ihsan.repositories.user.responses;

import com.example.mohamed.ihsan.repositories.user.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 27/04/2018.
 */

public class LoginResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private User user;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
