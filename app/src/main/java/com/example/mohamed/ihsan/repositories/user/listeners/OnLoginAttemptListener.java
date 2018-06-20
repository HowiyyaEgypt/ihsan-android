package com.example.mohamed.ihsan.repositories.user.listeners;

import com.example.mohamed.ihsan.repositories.user.User;

/**
 * Created by Mohamed on 25/04/2018.
 */

public interface OnLoginAttemptListener {
    void onSuccess(User user);
    void onFailure(String msg);
}
