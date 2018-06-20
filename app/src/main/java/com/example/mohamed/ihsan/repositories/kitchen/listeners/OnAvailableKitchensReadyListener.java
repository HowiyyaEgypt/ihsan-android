package com.example.mohamed.ihsan.repositories.kitchen.listeners;

import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;

import java.util.ArrayList;

/**
 * Created by Mohamed on 04/05/2018.
 */

public interface OnAvailableKitchensReadyListener {
    void onSuccess(ArrayList<Kitchen> kitchens);
    void onFailure(String msg);
}
