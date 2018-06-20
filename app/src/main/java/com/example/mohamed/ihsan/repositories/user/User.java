package com.example.mohamed.ihsan.repositories.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohamed on 21/04/2018.
 */

public class User implements Serializable {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("gender")
    @Expose
    String gender;

    @SerializedName("points")
    @Expose
    int points;

    @SerializedName("level")
    @Expose
    int level;

    @SerializedName("photo")
    @Expose
    String photo;

    @SerializedName("bio")
    @Expose
    String bio;

    @SerializedName("meals_donated_for_an_organizations_count")
    @Expose
    int mealsDonatedForAnOrganizationsCount;

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("fcm_token")
    @Expose
    String fcmToken;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public int getPoints() {
        return points;
    }

    public int getLevel() {
        return level;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBio() {
        return bio;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMealsDonatedForAnOrganizationsCount() {
        return mealsDonatedForAnOrganizationsCount;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public User() {

    }

    /**
     * Local user constructor
     * */
    public User(int id, String email, String name, String password, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.token = token;
    }

}
