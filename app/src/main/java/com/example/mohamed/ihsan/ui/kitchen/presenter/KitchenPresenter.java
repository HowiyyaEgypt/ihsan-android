package com.example.mohamed.ihsan.ui.kitchen.presenter;

import android.content.Context;

import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.delivery.listeners.OnDeliveryReadyListener;
import com.example.mohamed.ihsan.repositories.delivery.repository.DeliveryRepository;
import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.kitchen.listeners.OnKitchenReadyListener;
import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepository;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenContract;

/**
 * Created by Mohamed on 18/05/2018.
 */

public class KitchenPresenter implements KitchenContract.Presenter {

    private Context context;
    private KitchenContract.View view;
    private KitchenRepository kitchen_repository;
    private DeliveryRepository delivery_repository;

    public KitchenPresenter(Context context, KitchenRepository kitchenRepository, DeliveryRepository deliveryRepository) {
        this.context = context;
        this.kitchen_repository = kitchenRepository;
        this.delivery_repository = deliveryRepository;
    }

    @Override
    public void setView(KitchenContract.View view) {
        this.view = view;
    }

    @Override
    public void requestKitchenData(int kitchenId) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        kitchen_repository.requestSingleKitchenData(localAuthUser.getToken(), kitchenId, new OnKitchenReadyListener() {
            @Override
            public void onSuccess(Kitchen kitchen) {
                view.displayKitchenData(kitchen);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    @Override
    public void deliverMeal(int mealId, int kitchenId) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        delivery_repository.deliverMeal(localAuthUser.getToken(), mealId, kitchenId, new OnDeliveryReadyListener() {
            @Override
            public void onSuccess(Delivery delivery) {
                view.navigateToTracking(1, delivery);
            }

            @Override
            public void onFailure(String msg) {
                view.displayMsg(msg);
            }
        });
    }

    @Override
    public void cancelMealDelivery(int mode, int mealId, int deliveryId) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        delivery_repository.cancelMealDelivery(localAuthUser.getToken(), mealId, deliveryId, new OnMealReadyListener() {
            @Override
            public void onSuccess(Meal meal) {
                view.returnMealBackToBeDelivered(meal);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void confirmMealReception(int mode, int mealId, int deliveryId) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        delivery_repository.confirmMealReception(localAuthUser.getToken(), mode, mealId, deliveryId, new OnDeliveryReadyListener() {
            @Override
            public void onSuccess(Delivery delivery) {
                view.navigateToTracking(2, delivery);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
