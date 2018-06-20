package com.example.mohamed.ihsan.ui.auth.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.dagger.App;
import com.example.mohamed.ihsan.ui.auth.contracts.AuthContract;
import com.example.mohamed.ihsan.ui.auth.contracts.SwitchAuthFragmentsContract;
import com.example.mohamed.ihsan.ui.home.view.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mohamed on 21/04/2018.
 */

public class LoginFragment extends Fragment implements AuthContract.View {

    // saving state variables
    private static final String PERSISTENT_EMAIL_VARIABLE = "persistentEmailValue";

    public LoginFragment() {
        setArguments(new Bundle());
    }

    @BindView(R.id.et_email_login)
    EditText et_email;

    @BindView(R.id.et_password_login)
    EditText et_password;

    @BindView(R.id.tv_alt_signup)
    TextView tv_alt_signup;

    @OnClick(R.id.tv_alt_signup)
    public void switchToLogin() {
        switchFragments.switchFragments(1);
    }

    @BindView(R.id.btn_login)
    Button btn_login;

    @OnClick(R.id.btn_login)
    public void attemptToLogin() { validateInputs(); }

    private SwitchAuthFragmentsContract switchFragments;

    @Inject
    public AuthContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);

        presenter.setView(this);

        Bundle mySavedInstanceState = getArguments();
        String persistentVariable = mySavedInstanceState.getString(PERSISTENT_EMAIL_VARIABLE);

        if (persistentVariable != null) {
            et_email.setText(persistentVariable);
        }

        return root;
    }

    @Override
    public void onAttach(Context context) {
        ((App) context.getApplicationContext()).getComponent().inject(this);
        super.onAttach(context);
        switchFragments = (SwitchAuthFragmentsContract) context;
    }

    @Override
    public void validateInputs() {

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        boolean all_is_clear = true;

        if (email.isEmpty()) {
            et_email.setError("Email is required!");
            all_is_clear = false;
        }

        if (password.isEmpty()) {
            et_password.setError("Password is required!");
            all_is_clear = false;
        }

        // all clear - try to login
        if (all_is_clear) {
            presenter.attemptToLogin(email, password);
        }
    }

    @Override
    public void showErrorToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHomeAfterSuccess() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    @Override
    public void switchFragments(SwitchAuthFragmentsContract switchAuthFragmentsContract) {

    }

    @Override
    public void onPause() {
        super.onPause();
        String persistentVariable = et_email.getText().toString();
        getArguments().putString(PERSISTENT_EMAIL_VARIABLE, persistentVariable);
    }
}
