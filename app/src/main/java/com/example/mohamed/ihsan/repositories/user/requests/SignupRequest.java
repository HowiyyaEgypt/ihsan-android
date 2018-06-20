package com.example.mohamed.ihsan.repositories.user.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 25/04/2018.
 */

public class SignupRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("gender")
    private int gender;

    public SignupRequest(String name, String email, String password, int gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
