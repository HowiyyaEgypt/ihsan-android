package com.example.mohamed.ihsan.dagger;

import android.content.Context;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.repositories.city.repository.CityRepository;
import com.example.mohamed.ihsan.repositories.city.repository.CityRepositoryImplementation;
import com.example.mohamed.ihsan.repositories.feed.repository.FeedRepository;
import com.example.mohamed.ihsan.repositories.feed.repository.FeedRepositoryImplementation;
import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepository;
import com.example.mohamed.ihsan.repositories.kitchen.repository.KitchenRepositoryImplementation;
import com.example.mohamed.ihsan.repositories.location.repository.LocationRepository;
import com.example.mohamed.ihsan.repositories.location.repository.LocationRepositoryImplementation;
import com.example.mohamed.ihsan.repositories.meal.repository.MealRepository;
import com.example.mohamed.ihsan.repositories.meal.repository.MealRepositoryImplementation;
import com.example.mohamed.ihsan.repositories.organization.repositories.OrganizationRepository;
import com.example.mohamed.ihsan.repositories.organization.repositories.OrganizationRepositoryImplementation;
import com.example.mohamed.ihsan.repositories.tracking.repository.TrackingRepositoryImplementation;
import com.example.mohamed.ihsan.repositories.tracking.repository.TrackingRepository;
import com.example.mohamed.ihsan.repositories.user.repository.UserRepository;
import com.example.mohamed.ihsan.repositories.user.repository.UserRepositoryImplementation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 25/04/2018.
 */

@Module
public class GlobalRepositoryModule {

    @Provides
    public UserRepository provideUserRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new UserRepositoryImplementation(ihsanApiEndpoints);
    }

    @Provides
    public CityRepository provideCityRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new CityRepositoryImplementation(ihsanApiEndpoints);
    }

    @Provides
    public KitchenRepository provideKitchenRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new KitchenRepositoryImplementation(ihsanApiEndpoints);
    }

    @Provides
    public MealRepository provideMealRepository(IhsanApiEndpoints ihsanApiEndpoints, Context context) {
        return new MealRepositoryImplementation(ihsanApiEndpoints, context);
    }

    @Provides
    public LocationRepository provideLocationRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new LocationRepositoryImplementation(ihsanApiEndpoints);
    }

    @Provides
    public OrganizationRepository provideOrganizationRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new OrganizationRepositoryImplementation(ihsanApiEndpoints);
    }

    @Provides
    public TrackingRepository provideTrackingRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new TrackingRepositoryImplementation(ihsanApiEndpoints);
    }

    @Provides
    public FeedRepository provideFeedRepository(IhsanApiEndpoints ihsanApiEndpoints) {
        return new FeedRepositoryImplementation(ihsanApiEndpoints);
    }
}
