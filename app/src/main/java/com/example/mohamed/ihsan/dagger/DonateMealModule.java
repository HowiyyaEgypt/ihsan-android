package com.example.mohamed.ihsan.dagger;

import android.content.Context;

import com.example.mohamed.ihsan.repositories.city.repository.CityRepository;
import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepository;
import com.example.mohamed.ihsan.repositories.location.repository.LocationRepository;
import com.example.mohamed.ihsan.repositories.meal.repository.MealRepository;
import com.example.mohamed.ihsan.ui.donatemeal.contracts.DonateMealContract;
import com.example.mohamed.ihsan.ui.donatemeal.presenter.DonateMealPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 01/05/2018.
 */

@Module
public class DonateMealModule {

    @Provides
    public DonateMealContract.Presenter provideDonateMealPresenter(Context context, CityRepository cityRepository, KitchenRepository kitchenRepository, MealRepository mealRepository, LocationRepository locationRepository) {
        return new DonateMealPresenter(context, cityRepository, kitchenRepository, mealRepository,locationRepository);
    }
}
