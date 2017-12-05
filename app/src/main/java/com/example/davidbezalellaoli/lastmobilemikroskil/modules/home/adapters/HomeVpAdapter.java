package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidbezalellaoli on 12/4/17.
 */

public class HomeVpAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;

    public HomeVpAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void addFragment (Fragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(final int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
