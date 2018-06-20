package com.example.mohamed.ihsan.repositories.delivery.repository;

import com.example.mohamed.ihsan.repositories.delivery.listeners.OnDeliveryReadyListener;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;

/**
 * Created by Mohamed on 22/05/2018.
 */

public interface DeliveryRepository {
    void deliverMeal(String token, int mealId, int kitchenId, OnDeliveryReadyListener listener);
    void cancelMealDelivery(String token, int mealId, int deliveryId, OnMealReadyListener listener);
    void confirmMealReception(String token, int mode, int mealId, int deliveryId, OnDeliveryReadyListener listener);
}
