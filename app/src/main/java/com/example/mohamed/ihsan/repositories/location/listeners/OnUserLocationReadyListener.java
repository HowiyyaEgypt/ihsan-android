package com.example.mohamed.ihsan.repositories.location.listeners;

import com.example.mohamed.ihsan.repositories.location.Location;

import java.util.ArrayList;

/**
 * Created by Mohamed on 05/05/2018.
 */

public interface OnUserLocationReadyListener {
    void onSuccess(Location location);
    void onFailure(String msg);
}
