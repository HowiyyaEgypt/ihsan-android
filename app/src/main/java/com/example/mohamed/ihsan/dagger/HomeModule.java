package com.example.mohamed.ihsan.dagger;

import android.content.Context;

import com.example.mohamed.ihsan.repositories.feed.repository.FeedRepository;
import com.example.mohamed.ihsan.repositories.organization.repositories.OrganizationRepository;
import com.example.mohamed.ihsan.ui.home.contracts.HomeContract;
import com.example.mohamed.ihsan.ui.home.presenter.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 11/05/2018.
 */

@Module
public class HomeModule {

    @Provides
    public HomeContract.Presenter provideHomePresenter(Context context, OrganizationRepository organizationRepository, FeedRepository feedRepository) {
        return new HomePresenter(context, organizationRepository, feedRepository);
    }

}
