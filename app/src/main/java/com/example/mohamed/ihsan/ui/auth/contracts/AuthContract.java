package com.example.mohamed.ihsan.ui.auth.contracts;

/**
 * Created by Mohamed on 21/04/2018.
 */

public interface AuthContract {

    interface View {
        void validateInputs();
        void showErrorToast(String msg);
        void navigateToHomeAfterSuccess();
        void switchFragments(SwitchAuthFragmentsContract switchAuthFragmentsContract);
    }

    interface Presenter {
        void setView(AuthContract.View view);
        void attemptToLogin(String email, String password);
        void attemptToSignup(String username, String email, String password, int gender);
    }


    interface Model {
        void attemptToLogin(String email, String password);
        void attemptToSignup(String username, String email, String password);
    }
}
