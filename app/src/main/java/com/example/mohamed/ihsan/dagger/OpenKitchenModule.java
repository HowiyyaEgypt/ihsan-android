package com.example.mohamed.ihsan.dagger;

import android.content.Context;

import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepository;
import com.example.mohamed.ihsan.repositories.organization.repositories.OrganizationRepository;
import com.example.mohamed.ihsan.ui.openkitchen.contracts.OpenKitchenContract;
import com.example.mohamed.ihsan.ui.openkitchen.presenter.OpenKitchenPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 17/05/2018.
 */

@Module
public class OpenKitchenModule {

    @Provides
    public OpenKitchenContract.Presenter provideOpenKitchenPresenter(Context context, OrganizationRepository organizationRepository, KitchenRepository kitchenRepository) {
        return new OpenKitchenPresenter(context, organizationRepository, kitchenRepository);
    }
}
