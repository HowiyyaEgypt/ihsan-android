package com.example.mohamed.ihsan.repositories.tracking.repository;

import com.example.mohamed.ihsan.repositories.delivery.listeners.OnDeliveryReadyListener;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;
import com.example.mohamed.ihsan.repositories.tracking.listeners.OnTrackingReadyListener;

/**
 * Created by Mohamed on 22/05/2018.
 */

public interface TrackingRepository {
    void requestUserTrackingData(String token, OnTrackingReadyListener listener);
    void cancelMealDelivery(String token, int mealId, int deliveryId, OnMealReadyListener listener);
    void confirmMealReception(String token, int mode, int mealId, int deliveryId, OnDeliveryReadyListener listener);
}
