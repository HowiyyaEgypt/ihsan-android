package com.example.mohamed.ihsan.repositories.user.repository;

import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.repositories.user.listeners.OnLoginAttemptListener;
import com.example.mohamed.ihsan.repositories.user.listeners.OnSignupAttemptListener;

/**
 * Created by Mohamed on 25/04/2018.
 */

public interface UserRepository {
    void attemptToLogin(String email, String password, OnLoginAttemptListener listener);
    void attemptToSignup(String username, String email, String password, int gender, OnSignupAttemptListener listener);
}
