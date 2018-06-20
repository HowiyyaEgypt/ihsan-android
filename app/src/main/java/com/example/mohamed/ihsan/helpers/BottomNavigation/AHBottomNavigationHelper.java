package com.example.mohamed.ihsan.helpers.BottomNavigation;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.ui.home.fragments.DonateFragment;
import com.example.mohamed.ihsan.ui.home.fragments.HomeFragment;
import com.example.mohamed.ihsan.ui.home.fragments.VolunteerFragment;

/**
 * Created by Mohamed on 28/04/2018.
 */

public abstract class AHBottomNavigationHelper {

    public static void initBottomNavigation(Context context, AHBottomNavigation bottomNavigation, final NoSwipeViewPager viewPager, FragmentManager fragmentManager, int activity_number) {

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Donate", R.drawable.ic_restaurant);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Volunteer", R.drawable.ic_favorite);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#CCCCCC"));

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#3F51B5"));
        bottomNavigation.setInactiveColor(Color.parseColor("#333333"));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        // disabling auto-hide when scroll
        bottomNavigation.setBehaviorTranslationEnabled(true);

        // setting up fragments

        switch (activity_number) {
            case 1:
                initHomeActivityBottomNavbar(bottomNavigation, viewPager, fragmentManager);
                break;
        }

    }

    private static void initHomeActivityBottomNavbar(AHBottomNavigation bottomNavigation, final NoSwipeViewPager viewPager, FragmentManager fragmentManager) {

        BottomNavigationAdapter pagerAdapter = new BottomNavigationAdapter(fragmentManager);

        // disabling swipe
        viewPager.setPagingEnabled(false);

        HomeFragment homeFragment = new HomeFragment();
        DonateFragment donateFragment = new DonateFragment();
        VolunteerFragment volunteerFragment = new VolunteerFragment();

        pagerAdapter.addFragments(homeFragment);
        pagerAdapter.addFragments(donateFragment);
        pagerAdapter.addFragments(volunteerFragment);

        viewPager.setAdapter(pagerAdapter);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected) {
                    viewPager.setCurrentItem(position);
                }

                return true;
            }
        });
    }
}
