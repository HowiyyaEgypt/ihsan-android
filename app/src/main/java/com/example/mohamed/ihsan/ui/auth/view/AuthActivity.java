package com.example.mohamed.ihsan.ui.auth.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.ui.auth.contracts.SwitchAuthFragmentsContract;
import com.example.mohamed.ihsan.ui.auth.fragments.LoginFragment;
import com.example.mohamed.ihsan.ui.auth.fragments.SignupFragment;

import butterknife.ButterKnife;

public class AuthActivity extends AppCompatActivity implements SwitchAuthFragmentsContract {

    LoginFragment loginFragment;
    SignupFragment signupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_acitivty);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        LocalAuthUserHelper.navigateToHomeIfLoggedIn(this);

        loginFragment = new LoginFragment();
        signupFragment = new SignupFragment();

        initFragmentTransaction(1);

    }

    @Override
    public void switchFragments(int mode) {
        switch (mode) {
            // we are @ the login fragment, requesting to signup
            case 1:
                initFragmentTransaction(2);
                break;

            // we are @ the signup fragment, requesting to login
            case 2:
                initFragmentTransaction(3);
                break;
        }
    }

    private void initFragmentTransaction(int mode) {

        FragmentManager manager;
        FragmentTransaction transaction;

        switch (mode) {
            case 1:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                transaction.add(R.id.rl_auth_container, loginFragment, "fragment_login");
                transaction.commit();
                break;

            case 2:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_enter_from_left, R.anim.frag_exit_to_right);
                transaction.replace(R.id.rl_auth_container, signupFragment, "fragment_signup").commit();
                break;

            case 3:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.frag_enter_from_right, R.anim.frag_exit_to_left);
                transaction.replace(R.id.rl_auth_container, loginFragment, "fragment_login").commit();
                break;
        }

    }

}
