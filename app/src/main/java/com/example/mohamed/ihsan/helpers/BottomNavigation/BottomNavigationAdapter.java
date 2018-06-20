package com.example.mohamed.ihsan.helpers.BottomNavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed on 28/04/2018.
 */

public class BottomNavigationAdapter extends SmartFragmentStatePagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();

    public BottomNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    // Our custom method that populates this Adapter with Fragments
    public void addFragments(Fragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
