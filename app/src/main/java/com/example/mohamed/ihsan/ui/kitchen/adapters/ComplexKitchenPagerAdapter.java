package com.example.mohamed.ihsan.ui.kitchen.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.mohamed.ihsan.repositories.meal.Meal;
import com.example.mohamed.ihsan.ui.kitchen.contracts.KitchenActionsEchoContract;
import com.example.mohamed.ihsan.ui.kitchen.fragments.KitchenCurrentlyDeliveredMealsFragment;
import com.example.mohamed.ihsan.ui.kitchen.fragments.KitchenDeliveredMealsFragment;
import com.example.mohamed.ihsan.ui.kitchen.fragments.KitchenToBeDeliveredMealsFragment;

import java.util.ArrayList;

/**
 * Created by Mohamed on 18/05/2018.
 */

public class ComplexKitchenPagerAdapter extends FragmentStatePagerAdapter implements KitchenActionsEchoContract.ViewPagerHolder {

    private ArrayList<Meal> deliveredMeals;
    private ArrayList<Meal> currentlyDeliveredMeals;
    private ArrayList<Meal> toBeDeliveredMeals;
    private boolean canManage;

    private KitchenDeliveredMealsFragment kitchenDeliveredMealsFragment;
    private KitchenCurrentlyDeliveredMealsFragment kitchenCurrentlyDeliveredMealsFragment;
    private KitchenToBeDeliveredMealsFragment kitchenToBeDeliveredMealsFragment;

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Delivered Meals", "Meals Currently Being Delivered", "Meals To Be Delivered" };

    public ComplexKitchenPagerAdapter(FragmentManager fm, boolean canManage, ArrayList<Meal> deliveredMeals, ArrayList<Meal> currentlyDeliveredMeals, ArrayList<Meal> toBeDeliveredMeals) {
        super(fm);
        this.deliveredMeals = deliveredMeals;
        this.currentlyDeliveredMeals = currentlyDeliveredMeals;
        this.toBeDeliveredMeals = toBeDeliveredMeals;
        this.canManage = canManage;

        kitchenDeliveredMealsFragment = new KitchenDeliveredMealsFragment();
        kitchenCurrentlyDeliveredMealsFragment = new KitchenCurrentlyDeliveredMealsFragment();
        kitchenToBeDeliveredMealsFragment = new KitchenToBeDeliveredMealsFragment();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                kitchenDeliveredMealsFragment.setCanManage(canManage);
                kitchenDeliveredMealsFragment.setDeliveredMeals(deliveredMeals);
                return kitchenDeliveredMealsFragment;

            case 1:
                kitchenCurrentlyDeliveredMealsFragment.setCanManage(canManage);
                kitchenCurrentlyDeliveredMealsFragment.setCurrentlyDeliveredMeals(currentlyDeliveredMeals);
                return kitchenCurrentlyDeliveredMealsFragment;

            case 2:
                kitchenToBeDeliveredMealsFragment.setCanManage(canManage);
                kitchenToBeDeliveredMealsFragment.setToBeDeliveredMeals(toBeDeliveredMeals);
                return kitchenToBeDeliveredMealsFragment;
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
    public void requestFragmentsToSyncAfterMealTransaction(int mode, Meal meal) {

        switch (mode) {

            // case 1: a meal from 'to be delivered' will be moved to 'currently being delivered'
            case 1:
                kitchenToBeDeliveredMealsFragment.requestAdapterToSync(mode, meal);
                kitchenCurrentlyDeliveredMealsFragment.requestAdapterToSync(mode, meal);
                break;

            // case 2: a meal was set for delivery then the delivery was cancelled
            case 2:
                kitchenToBeDeliveredMealsFragment.requestAdapterToSync(mode, meal);
                kitchenCurrentlyDeliveredMealsFragment.requestAdapterToSync(mode, meal);
                break;

            // case 3: a currently delivered meal was confirmed to be received
            case 3:
                kitchenDeliveredMealsFragment.requestAdapterToSync(mode, meal);
                kitchenCurrentlyDeliveredMealsFragment.requestAdapterToSync(mode, meal);
                break;
        }
    }
}
