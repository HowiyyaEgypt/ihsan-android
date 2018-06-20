package com.example.mohamed.ihsan.repositories.location.listeners;

import com.example.mohamed.ihsan.repositories.location.Location;

import java.util.ArrayList;

/**
 * Created by Mohamed on 07/05/2018.
 */

public interface OnUserAllLocationsReadyListener {
    void onSuccess(ArrayList<Location> locations);
    void onFailure(String msg);
}
