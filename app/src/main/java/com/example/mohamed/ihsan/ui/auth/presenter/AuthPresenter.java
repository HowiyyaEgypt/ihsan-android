package com.example.mohamed.ihsan.ui.auth.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.repositories.user.listeners.OnLoginAttemptListener;
import com.example.mohamed.ihsan.repositories.user.listeners.OnSignupAttemptListener;
import com.example.mohamed.ihsan.repositories.user.repository.UserRepository;
import com.example.mohamed.ihsan.ui.auth.contracts.AuthContract;

/**
 * Created by Mohamed on 25/04/2018.
 */

public class AuthPresenter implements AuthContract.Presenter {

    private AuthContract.View view;
    private UserRepository repository;
    private Context context;

    public AuthPresenter(Context context, UserRepository repository) {
        this.repository = repository;
        this.context = context;
    }

    @Override
    public void setView(AuthContract.View view) {
        this.view = view;
    }

    @Override
    public void attemptToLogin(String email, final String password) {
        repository.attemptToLogin(email, password, new OnLoginAttemptListener() {
            @Override
            public void onSuccess(User user) {
                if (view != null) {
                    view.navigateToHomeAfterSuccess();
                    LocalAuthUserHelper.storeLocalAuthUserData(context, user, password);
                } else {
                    Log.e("err", "view is null!!");
                }
            }

            @Override
            public void onFailure(String msg) {
                if (view != null) {
                    view.showErrorToast(msg);
                } else {
                    Log.e("err", "view is null!!");
                }
            }
        });
    }

    @Override
    public void attemptToSignup(String username, String email, final String password, int gender) {
        repository.attemptToSignup(username, email, password, gender, new OnSignupAttemptListener() {
            @Override
            public void onSuccess(User user) {
                if (view != null) {
                    view.navigateToHomeAfterSuccess();
                    LocalAuthUserHelper.storeLocalAuthUserData(context, user, password);
                } else {
                    Log.e("err", "view is null!!");
                }
            }

            @Override
            public void onFailure(String msg) {
                if (view != null) {
                    view.showErrorToast(msg);
                } else {
                    Log.e("err", "view is null!!");
                }
            }
        });
    }
}
