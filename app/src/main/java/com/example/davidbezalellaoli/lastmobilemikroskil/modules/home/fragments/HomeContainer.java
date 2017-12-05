package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters.HomeVpAdapter;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.interfaces.ChangeViewPagerClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeContainer extends Fragment {

    private static ViewPager viewPager;
    private static HomeVpAdapter homeVpAdapter;
    private View view;
    private ChangeViewPagerClasses changeViewPagerClasses;


    public HomeContainer() {
    }

    public HomeContainer(ChangeViewPagerClasses changeViewPagerClasses) {
        this.changeViewPagerClasses = changeViewPagerClasses;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_container, container, false);
        initView();
        initObject();
        return view;
    }

    private void initObject() {
        homeVpAdapter = new HomeVpAdapter(getChildFragmentManager());
        homeVpAdapter.addFragment(new Home());
        homeVpAdapter.addFragment(new Home2());
        viewPager.setAdapter(homeVpAdapter);
    }

    public void changeFragment() {
        if (this.changeViewPagerClasses != null) {
            changeViewPagerClasses.changeViewPagerFragment(homeVpAdapter, viewPager);
        }
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.homecontainer_vp);
    }

}
