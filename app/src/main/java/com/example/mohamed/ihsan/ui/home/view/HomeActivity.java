package com.example.mohamed.ihsan.ui.home.view;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.helpers.BottomNavigation.AHBottomNavigationHelper;
import com.example.mohamed.ihsan.helpers.BottomNavigation.NoSwipeViewPager;
import com.example.mohamed.ihsan.helpers.Authentication.LocalAuthUserHelper;
import com.example.mohamed.ihsan.ui.auth.view.AuthActivity;
import com.example.mohamed.ihsan.ui.tracking.view.TrackingActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @BindView(R.id.vp_home)
    NoSwipeViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        LocalAuthUserHelper.navigateToLoginOrSignupIfNotLoggedIn(this);

        AHBottomNavigationHelper.initBottomNavigation(this, bottomNavigation, viewPager, getSupportFragmentManager(), 1);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Firebase token: " , ""+refreshedToken);

//        Toast.makeText(this, "welcome home, " + LocalAuthUserHelper.getLocalAuthUser(this).getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.notification_icon:
                break;

            case R.id.tracking_icon:
                intent = new Intent(this, TrackingActivity.class);
                startActivity(intent);
                break;

            case R.id.logout_icon:
                LocalAuthUserHelper.destroyLocalAuthUserData(this);
                intent = new Intent(this, AuthActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
