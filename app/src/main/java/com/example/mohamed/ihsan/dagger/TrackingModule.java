package com.example.mohamed.ihsan.dagger;

import android.content.Context;

import com.example.mohamed.ihsan.repositories.tracking.repository.TrackingRepository;
import com.example.mohamed.ihsan.ui.tracking.contracts.TrackingContract;
import com.example.mohamed.ihsan.ui.tracking.presenter.TrackingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 22/05/2018.
 */

@Module
public class TrackingModule {

    @Provides
    public TrackingContract.Presenter provideTrackingPresenter(Context context, TrackingRepository trackingRepository) {
        return new TrackingPresenter(context, trackingRepository);
    }
}
