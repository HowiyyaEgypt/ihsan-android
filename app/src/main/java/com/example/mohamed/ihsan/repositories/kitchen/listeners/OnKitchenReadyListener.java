package com.example.mohamed.ihsan.repositories.kitchen.listeners;

import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;

/**
 * Created by Mohamed on 18/05/2018.
 */

public interface OnKitchenReadyListener {
    void onSuccess(Kitchen kitchen);
    void onFailure(String msg);
}
