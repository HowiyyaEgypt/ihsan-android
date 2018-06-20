package com.example.mohamed.ihsan.ui.tracking.presenter;

import android.content.Context;

import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.delivery.listeners.OnDeliveryReadyListener;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.repositories.meal.listeners.OnMealReadyListener;
import com.example.mohamed.ihsan.repositories.tracking.listeners.OnTrackingReadyListener;
import com.example.mohamed.ihsan.repositories.tracking.repository.TrackingRepository;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.tracking.contracts.OnTrackingItemUpdatedListener;
import com.example.mohamed.ihsan.ui.tracking.contracts.TrackingContract;

import java.util.ArrayList;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class TrackingPresenter implements TrackingContract.Presenter {

    private Context context;
    private TrackingContract.View view;
    private TrackingRepository tracking_repository;

    public TrackingPresenter(Context context, TrackingRepository tracking_repository) {
        this.context = context;
        this.tracking_repository = tracking_repository;
    }

    @Override
    public void setView(TrackingContract.View view) {
        this.view = view;
    }

    @Override
    public void requestTrackingData() {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        tracking_repository.requestUserTrackingData(localAuthUser.getToken(), new OnTrackingReadyListener() {
            @Override
            public void onSuccess(ArrayList<Meal> meals, ArrayList<Delivery> deliveries) {
                view.displayTrackingData(meals, deliveries);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void cancelDelivery(int mealId, int deliveryId) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        tracking_repository.cancelMealDelivery(localAuthUser.getToken(), mealId, deliveryId, new OnMealReadyListener() {
            @Override
            public void onSuccess(Meal meal) {
                view.displayMessage("Meal delivery has been cancelled");
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void confirmMealReception(int mode, int mealId, int deliveryId, final OnTrackingItemUpdatedListener listener) {

        User localAuthUser = LocalAuthUserHelper.getLocalAuthUser(context);

        tracking_repository.confirmMealReception(localAuthUser.getToken(), 1, mealId, deliveryId, new OnDeliveryReadyListener() {
            @Override
            public void onSuccess(Delivery delivery) {
                listener.onSuccess();
                view.displayMessage("Thanks, The kitchen admin will be notified");
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
