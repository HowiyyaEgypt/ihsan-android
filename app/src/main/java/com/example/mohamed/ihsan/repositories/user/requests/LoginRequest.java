package com.example.mohamed.ihsan.repositories.user.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 27/04/2018.
 */

public class LoginRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;


    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
