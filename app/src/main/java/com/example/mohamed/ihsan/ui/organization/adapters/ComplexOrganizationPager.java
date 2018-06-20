package com.example.mohamed.ihsan.ui.organization.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mohamed.ihsan.repositories.kitchen.Kitchen;
import com.example.mohamed.ihsan.repositories.user.User;
import com.example.mohamed.ihsan.ui.organization.fragments.OrganizationCurrentKitchensFragment;
import com.example.mohamed.ihsan.ui.organization.fragments.OrganizationCurrentVolunteersFragment;
import com.example.mohamed.ihsan.ui.organization.fragments.OrganizationHistoryKitchensFragment;

import java.util.ArrayList;

/**
 * Created by Mohamed on 12/05/2018.
 */

public class ComplexOrganizationPager extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Current Kitchens", "History", "Volunteers" };
    private Context context;

    private OrganizationCurrentKitchensFragment organizationCurrentKitchensFragment;
    private OrganizationCurrentVolunteersFragment organizationCurrentVolunteersFragment;
    private OrganizationHistoryKitchensFragment organizationHistoryKitchensFragment;

    private ArrayList<Kitchen> currentKitchens;
    private ArrayList<Kitchen> historyKitchens;
    private ArrayList<User> users;

    public ComplexOrganizationPager(FragmentManager fm, ArrayList<Kitchen> currentKitchens, ArrayList<Kitchen> historyKitchens, ArrayList<User> users) {
        super(fm);
        this.currentKitchens = currentKitchens;
        this.historyKitchens = historyKitchens;
        this.users = users;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                organizationCurrentKitchensFragment = new OrganizationCurrentKitchensFragment();
                organizationCurrentKitchensFragment.setKitchens(currentKitchens);
                return organizationCurrentKitchensFragment;

            case 1:
                organizationHistoryKitchensFragment = new OrganizationHistoryKitchensFragment();
                organizationHistoryKitchensFragment.setKitchens(historyKitchens);
                return  organizationHistoryKitchensFragment;

            case 2:
                organizationCurrentVolunteersFragment = new OrganizationCurrentVolunteersFragment();
                organizationCurrentVolunteersFragment.setUsers(users);
                return  organizationCurrentVolunteersFragment;
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
}
