package com.example.mohamed.ihsan.dagger;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

/**
 * Created by Mohamed on 21/04/2018.
 */

public class App extends MultiDexApplication {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .globalRepositoryModule(new GlobalRepositoryModule())
                .apiModule(new ApiModule())
                .authModule(new AuthModule())
                .trackingModule(new TrackingModule())
                .donateMealModule(new DonateMealModule())
                .openKitchenModule(new OpenKitchenModule())
                .deliverModule(new DeliverModule())
                .build();

    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
