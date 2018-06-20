package com.example.mohamed.ihsan.dagger;


import android.content.Context;

import com.example.mohamed.ihsan.repositories.user.repository.UserRepository;
import com.example.mohamed.ihsan.ui.auth.contracts.AuthContract;
import com.example.mohamed.ihsan.ui.auth.presenter.AuthPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed on 21/04/2018.
 */

@Module
public class AuthModule {

    @Provides
    public AuthContract.Presenter provideAuthContractPresenter(Context context, UserRepository repository) {
        return new AuthPresenter(context, repository);
    }

}
