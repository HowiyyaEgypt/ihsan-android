package com.example.mohamed.ihsan.repositories.user.repository;

import android.util.Log;

import com.example.mohamed.ihsan.api.IhsanApiEndpoints;
import com.example.mohamed.ihsan.api.general.GenericErrorResponse;
import com.example.mohamed.ihsan.repositories.user.listeners.OnLoginAttemptListener;
import com.example.mohamed.ihsan.repositories.user.listeners.OnSignupAttemptListener;
import com.example.mohamed.ihsan.repositories.user.requests.LoginRequest;
import com.example.mohamed.ihsan.repositories.user.requests.SignupRequest;
import com.example.mohamed.ihsan.repositories.user.responses.LoginResponse;
import com.example.mohamed.ihsan.repositories.user.responses.SignupResponse;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed on 25/04/2018.
 */

public class UserRepositoryImplementation implements UserRepository {

    private IhsanApiEndpoints ihsanApi;

    public UserRepositoryImplementation(IhsanApiEndpoints ihsanApiEndpoints) {
        this.ihsanApi = ihsanApiEndpoints;
    }

    @Override
    public void attemptToLogin(String email, String password, final OnLoginAttemptListener listener) {

        LoginRequest loginRequest = new LoginRequest(email, password);

        Call<LoginResponse> call = ihsanApi.login(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getUser());
                        break;
                    case 470:
                        try {
                            GenericErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), GenericErrorResponse.class);
                            listener.onFailure(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void attemptToSignup(String username, String email, String password, int gender, final OnSignupAttemptListener listener) {

        SignupRequest signupRequest = new SignupRequest(username, email, password, gender);

        Call<SignupResponse> call = ihsanApi.signup(signupRequest);

        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getUser());
                        break;
                    case 470:
                        try {
                            GenericErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), GenericErrorResponse.class);
                            listener.onFailure(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                Log.d("response", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

            }
        });
    }
}
