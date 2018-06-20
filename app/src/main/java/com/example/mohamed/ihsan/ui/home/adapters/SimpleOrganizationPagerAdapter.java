package com.example.mohamed.ihsan.ui.home.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mohamed.ihsan.repositories.organization.Organization;
import com.example.mohamed.ihsan.ui.home.fragments.AlreadyJoinedOrganizationsFragment;
import com.example.mohamed.ihsan.ui.home.fragments.AvailableToJoinOrganizationsFragment;

import java.util.ArrayList;

/**
 * Created by Mohamed on 11/05/2018.
 */

public class SimpleOrganizationPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Already Joined", "Available To Join" };
    private Context context;

    // organizations
    private ArrayList<Organization> alreadyJoinedOrganizationsList;
    private ArrayList<Organization> availableToJoinOrganizationList;

    private AlreadyJoinedOrganizationsFragment alreadyJoinedOrganizationsFragment;
    private AvailableToJoinOrganizationsFragment availableToJoinOrganizationsFragment;

    private Fragment fragment;

    public SimpleOrganizationPagerAdapter(FragmentManager fm, Context context, ArrayList<Organization> alreadyJoinedOrganizations, ArrayList<Organization> availableToJoinOrganization) {
        super(fm);
        this.context = context;
        this.alreadyJoinedOrganizationsList = alreadyJoinedOrganizations;
        this.availableToJoinOrganizationList = availableToJoinOrganization;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
            default:
                alreadyJoinedOrganizationsFragment = new AlreadyJoinedOrganizationsFragment();
                alreadyJoinedOrganizationsFragment.setAlreadyJoinedOrganizationsList(alreadyJoinedOrganizationsList);
                return alreadyJoinedOrganizationsFragment;

            case 1:
                availableToJoinOrganizationsFragment = new AvailableToJoinOrganizationsFragment();
                availableToJoinOrganizationsFragment.setAvailableToJoinOrganizationsFragment(availableToJoinOrganizationList);
                return availableToJoinOrganizationsFragment;
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
