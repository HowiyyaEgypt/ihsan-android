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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
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
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Mohamed on 21/04/2018.
 */

public class SignupFragment extends Fragment implements AuthContract.View {

    @BindView(R.id.et_email_signup)
    EditText et_email;

    @BindView(R.id.et_username_signup)
    EditText et_username;

    @BindView(R.id.et_password_signup)
    EditText et_password;

    @BindView(R.id.rg_gender)
    RadioGroup rg_gender;

    @OnCheckedChanged({R.id.rb_male, R.id.rb_female})
    public void changeGender(CompoundButton button, boolean checked) {

        if (checked) {
            switch (button.getId()) {
                case R.id.rb_male:
                    gender = 1;
                    break;

                case R.id.rb_female:
                    gender = 2;
                    break;
            }
        }

    }

    @BindView(R.id.tv_alt_login)
    TextView tv_alt_login;

    @OnClick(R.id.tv_alt_login)
    public void switchToLogin() {
        switchFragments.switchFragments(2);
    }

    @BindView(R.id.btn_signup)
    Button btn_signup;

    @OnClick(R.id.btn_signup)
    public void attemptToSignup() { validateInputs(); }

    private SwitchAuthFragmentsContract switchFragments;

    private int gender = 0;

    @Inject
    public AuthContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, root);

        presenter.setView(this);

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

        String username = et_username.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        int gender_radio = rg_gender.getCheckedRadioButtonId();

        boolean all_is_clear = true;

        if (username.isEmpty()) {
            all_is_clear = false;
            et_username.setError("Username is required!");
        }

        if (email.isEmpty()) {
            all_is_clear = false;
            et_email.setError("Email is required!");
        }

        if (password.isEmpty()) {
            all_is_clear = false;
            et_password.setError("Password is required!");
        }

        if (gender_radio == -1 || gender == 0) {
            Toast.makeText(getActivity(), "The gender is required", Toast.LENGTH_LONG).show();
            all_is_clear = false;
        }

        // all clear - try to login
        if (all_is_clear) {
            presenter.attemptToSignup(username, email, password, gender);
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
}
