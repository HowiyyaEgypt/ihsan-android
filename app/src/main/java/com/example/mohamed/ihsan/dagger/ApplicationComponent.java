package com.example.mohamed.ihsan.dagger;

import com.example.mohamed.ihsan.ui.auth.view.AuthActivity;
import com.example.mohamed.ihsan.ui.auth.fragments.LoginFragment;
import com.example.mohamed.ihsan.ui.auth.fragments.SignupFragment;
import com.example.mohamed.ihsan.ui.donatemeal.view.DonateMealActivity;
import com.example.mohamed.ihsan.ui.home.fragments.HomeFragment;
import com.example.mohamed.ihsan.ui.home.fragments.VolunteerFragment;
import com.example.mohamed.ihsan.ui.kitchen.view.KitchenActivity;
import com.example.mohamed.ihsan.ui.openkitchen.view.OpenKitchenActivity;
import com.example.mohamed.ihsan.ui.tracking.view.TrackingActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohamed on 21/04/2018.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        GlobalRepositoryModule.class,
        ApiModule.class,
        HomeModule.class,
        TrackingModule.class,
        AuthModule.class,
        DonateMealModule.class,
        OpenKitchenModule.class,
        KitchenModule.class,
        DeliverModule.class
})
public interface ApplicationComponent {

    // activities
    void inject(AuthActivity target);
    void inject(DonateMealActivity target);
    void inject(OpenKitchenActivity target);
    void inject(KitchenActivity target);
    void inject(TrackingActivity target);

    // fragments
    void inject(LoginFragment target);
    void inject(SignupFragment target);
    void inject(VolunteerFragment target);
    void inject(HomeFragment target);
}
