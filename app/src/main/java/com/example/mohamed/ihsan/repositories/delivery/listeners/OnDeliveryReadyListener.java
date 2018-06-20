package com.example.mohamed.ihsan.repositories.delivery.listeners;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;

/**
 * Created by Mohamed on 22/05/2018.
 */

public interface OnDeliveryReadyListener {
    void onSuccess(Delivery delivery);
    void onFailure(String msg);
}
