package com.example.mohamed.ihsan.repositories.delivery.responses;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class SingleDeliveryResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Delivery delivery;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Delivery getDelivery() {
        return delivery;
    }
}
