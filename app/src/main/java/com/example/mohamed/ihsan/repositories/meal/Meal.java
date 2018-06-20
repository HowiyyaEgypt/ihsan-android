package com.example.mohamed.ihsan.repositories.meal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohamed on 05/05/2018.
 */

public class Meal implements Serializable {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("donor_id")
    @Expose
    int donorId;

    @SerializedName("donor_name")
    @Expose
    String donorName;

    @SerializedName("bellies")
    @Expose
    int bellies;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("deliverer_id")
    @Expose
    int delivererId;

    @SerializedName("deliverer_name")
    @Expose
    String delivererName;

    @SerializedName("kitchen_id")
    @Expose
    int kitchenId;

    @SerializedName("kitchen_name")
    @Expose
    String kitchenName;

    @SerializedName("delivery_id")
    @Expose
    Integer deliveryId;

    @SerializedName("stage")
    @Expose
    int stage;

    @SerializedName("pickup_mode")
    @Expose
    int pickupMode;

    @SerializedName("is_donated_by_me")
    @Expose
    boolean isDonatedByMe;

    @SerializedName("is_delivered_by_me")
    @Expose
    boolean isDeliveredByMe;

    @SerializedName("pickup_date")
    @Expose
    String pickupDate;

    @SerializedName("delivery_date")
    @Expose
    String deliveryDate;

    public int getId() {
        return id;
    }

    public int getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public int getBellies() {
        return bellies;
    }

    public String getDescription() {
        return description;
    }

    public int getKitchenId() {
        return kitchenId;
    }

    public String getKitchenName() {
        return kitchenName;
    }

    public int getStage() {
        return stage;
    }

    public int getPickupMode() {
        return pickupMode;
    }

    public boolean isDonatedByMe() {
        return isDonatedByMe;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public boolean isDeliveredByMe() {
        return isDeliveredByMe;
    }

    public int getDelivererId() {
        return delivererId;
    }

    public String getDelivererName() {
        return delivererName;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public void resetDeliveryId() {
        this.deliveryId = null;
    }

    public void setDeliveredByMe(boolean deliveredByMe) {
        isDeliveredByMe = deliveredByMe;
    }
}
