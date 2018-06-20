package com.example.mohamed.ihsan.ui.tracking.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mohamed.ihsan.repositories.delivery.Delivery;
import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.tracking.contracts.TrackingActionEchoContract;
import com.example.mohamed.ihsan.ui.tracking.fragments.UserDeliveriesTrackingFragment;
import com.example.mohamed.ihsan.ui.tracking.fragments.UserMealsTrackingFragment;

import java.util.ArrayList;

/**
 * Created by Mohamed on 22/05/2018.
 */

public class SimpleTrackerPagerAdapter extends FragmentStatePagerAdapter implements TrackingActionEchoContract.ViewPagerHolder {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Donated Meals", "Delivered Meals" };
    private Context context;

    private ArrayList<Meal> meals;
    private ArrayList<Delivery> deliveries;

    private UserMealsTrackingFragment userMealsTrackingFragment;
    private UserDeliveriesTrackingFragment userDeliveriesTrackingFragment;

    public SimpleTrackerPagerAdapter(FragmentManager fm, ArrayList<Meal> meals, ArrayList<Delivery> deliveries) {
        super(fm);

        this.meals = meals;
        this.deliveries = deliveries;

        userMealsTrackingFragment = new UserMealsTrackingFragment();
        userDeliveriesTrackingFragment = new UserDeliveriesTrackingFragment();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                userMealsTrackingFragment.setMeals(meals);
                return userMealsTrackingFragment;

            case 1:
                userDeliveriesTrackingFragment.setDeliveries(deliveries);
                return userDeliveriesTrackingFragment;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public void requestFragmentsToSyncAfterMealCancel(Delivery delivery) {
        userDeliveriesTrackingFragment.syncFragment(delivery);
    }
}
