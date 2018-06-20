package com.example.mohamed.ihsan.dagger;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.repositories.delivery.repository.DeliveryRepository;
import com.example.mohamed.ihsan.repositories.delivery.repository.DeliveryRepositoryImplementation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 22/05/2018.
 */

@Module
public class DeliverModule {

    @Provides
    public DeliveryRepository provideDeliverRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new DeliveryRepositoryImplementation(ihsanApiEndpoints);
    }
}
