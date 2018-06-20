package com.example.mohamed.ihsan.dagger;

import android.content.Context;

import com.example.mohamed.ihsan.repositories.delivery.repository.DeliveryRepository;
import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepository;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenContract;
import com.example.mohamed.ihsan.ui.kitchen.presenter.KitchenPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 18/05/2018.
 */

@Module
public class KitchenModule {

    @Provides
    public KitchenContract.Presenter provideKitchenPresenter(Context context, KitchenRepository kitchenRepository, DeliveryRepository deliveryRepository) {
        return new KitchenPresenter(context, kitchenRepository, deliveryRepository);
    }
}
