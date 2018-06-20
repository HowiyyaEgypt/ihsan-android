package com.example.mohamed.ihsan.helpers.Authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.auth.view.AuthActivity;
import com.example.mohamed.ihsan.ui.home.view.HomeActivity;

/**
 * Created by Mohamed on 27/04/2018.
 */

public abstract class LocalAuthUserHelper {

    public static void storeLocalAuthUserData(Context context, User user, String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences("auth_user_data", Context.MODE_PRIVATE).edit();
        editor.putInt("auth_user_id", user.getId());
        editor.putString("auth_user_name", user.getName());
        editor.putString("auth_user_password", password);
        editor.putString("auth_user_email", user.getEmail());
        editor.putString("auth_user_token", user.getToken());
        editor.commit();

        Log.d("email from helper: ", user.getEmail());

        Log.d("Msg: ", "The local user has been saved");
    }

    public static void destroyLocalAuthUserData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("auth_user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("auth_user_id");
        editor.remove("auth_user_name");
        editor.remove("auth_user_password");
        editor.remove("auth_user_email");
        editor.remove("auth_user_token");
        editor.commit();

        Log.d("Msg: ", "The local user has been destroyed");
    }

    public static User getLocalAuthUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("auth_user_data", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("auth_user_id", 1);
        String name = sharedPreferences.getString("auth_user_name", "");
        String password = sharedPreferences.getString("auth_user_password", "");
        String email = sharedPreferences.getString("auth_user_email", "");
        String token = sharedPreferences.getString("auth_user_token", "");
        User user = new User(id, email, name, password, token);
        return user;
    }

    public static void navigateToLoginOrSignupIfNotLoggedIn(Context context) {
        User user = getLocalAuthUser(context);

        if(user.getName().trim().isEmpty()) {
            Intent intent = new Intent(context, AuthActivity.class);
            ((Activity) context).startActivity(intent);
            ((Activity) context).finish();
        }

    }

    public static void navigateToHomeIfLoggedIn(Context context) {
        User user = getLocalAuthUser(context);

        if(!user.getName().trim().isEmpty()) {
            Intent intent = new Intent(context, HomeActivity.class);
            ((Activity) context).startActivity(intent);
            ((Activity) context).finish();
        }

    }

}
