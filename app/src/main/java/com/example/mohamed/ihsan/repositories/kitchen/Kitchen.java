package com.example.mohamed.ihsan.repositories.kitchen;

import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mohamed on 30/04/2018.
 */

public class Kitchen implements Serializable {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("city_id")
    @Expose
    int cityId;

    @SerializedName("city_name")
    @Expose
    String cityName;

    @SerializedName("organization_id")
    @Expose
    int organizationId;

    @SerializedName("organization_name")
    @Expose
    String organizationName;

    @SerializedName("location_id")
    @Expose
    int locationId;

    @SerializedName("location_description")
    @Expose
    String locationDescription;

    @SerializedName("location_lat")
    @Expose
    double locationLat;

    @SerializedName("location_lng")
    @Expose
    double locationLng;

    @SerializedName("opening_time")
    @Expose
    String openingTime;

    @SerializedName("closing_time")
    @Expose
    String closingTime;

    @SerializedName("is_opened")
    @Expose
    boolean isOpened;

    @SerializedName("can_manage")
    @Expose
    boolean canManage;

    @SerializedName("meals_count")
    @Expose
    int mealsCount;

    @SerializedName("meals_need_to_be_delivered_count")
    @Expose
    int mealsNeedToBeDeliveredCount;

    @SerializedName("meals_currently_being_delivered_count")
    @Expose
    int mealsCurrentlyBeingDeliveredCount;

    @SerializedName("meals_delivered_count")
    @Expose
    int mealsDeliveredCount;

    @SerializedName("meals_need_to_be_delivered")
    @Expose
    ArrayList<Meal> mealsNeedToBeDelivered;

    @SerializedName("meals_currently_being_delivered")
    @Expose
    ArrayList<Meal> mealsCurrentlyBeingDelivered;

    @SerializedName("meals_delivered")
    @Expose
    ArrayList<Meal> mealsDelivered;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public double getLocationLng() {
        return locationLng;
    }

    public int getMealsCount() {
        return mealsCount;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public boolean isCanManage() {
        return canManage;
    }

    public int getMealsNeedToBeDeliveredCount() {
        return mealsNeedToBeDeliveredCount;
    }

    public int getMealsCurrentlyBeingDeliveredCount() {
        return mealsCurrentlyBeingDeliveredCount;
    }

    public int getMealsDeliveredCount() {
        return mealsDeliveredCount;
    }

    public ArrayList<Meal> getMealsNeedToBeDelivered() {
        return mealsNeedToBeDelivered;
    }

    public ArrayList<Meal> getMealsCurrentlyBeingDelivered() {
        return mealsCurrentlyBeingDelivered;
    }

    public ArrayList<Meal> getMealsDelivered() {
        return mealsDelivered;
    }
}
