package com.example.mohamed.ihsan.repositories.organization;

import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class Organization implements Serializable {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("bio")
    @Expose
    String bio;

    @SerializedName("total_kitchens_count")
    @Expose
    int totalKitchensCount;

    @SerializedName("opened_kitchens_count")
    @Expose
    int openedKitchensCount;

    @SerializedName("opened_kitchens")
    @Expose
    ArrayList<Kitchen> openedKitchens;

    @SerializedName("closed_kitchens")
    @Expose
    ArrayList<Kitchen> closedKitchens;

    @SerializedName("donated_meals_count")
    @Expose
    int donatedMealsCount;

    @SerializedName("original_meals_count")
    @Expose
    int originalMealsCount;

    @SerializedName("users_count")
    @Expose
    int usersCount;

    @SerializedName("users")
    @Expose
    ArrayList<User> users;

    @SerializedName("locations_count")
    @Expose
    int locationsCount;

    @SerializedName("can_manage")
    @Expose
    boolean canManage;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getTotalKitchensCount() {
        return totalKitchensCount;
    }

    public int getDonatedMealsCount() {
        return donatedMealsCount;
    }

    public int getOriginalMealsCount() {
        return originalMealsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public boolean canManage() {
        return canManage;
    }

    public int getOpenedKitchensCount() {
        return openedKitchensCount;
    }

    public ArrayList<Kitchen> getOpenedKitchens() {
        return openedKitchens;
    }

    public ArrayList<Kitchen> getClosedKitchens() {
        return closedKitchens;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getLocationsCount() {
        return locationsCount;
    }

    public boolean isCanManage() {
        return canManage;
    }
}
