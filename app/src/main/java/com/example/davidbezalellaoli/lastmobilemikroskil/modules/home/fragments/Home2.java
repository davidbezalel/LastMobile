package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.SplashScreen;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters.Home2UserVpAdapter;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters.HomeVpAdapter;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.interfaces.ChangeViewPagerClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home2 extends Fragment implements ChangeViewPagerClasses{

    private ViewPager viewPager;
    private Button change;
    private Home2UserVpAdapter home2UserVpAdapter;
    private View view;

    public Home2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home2, container, false);
        initView();
        initObject();
        eventListener();
        return view;
    }

    private void eventListener() {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new HomeContainer(Home2.this)).changeFragment();
            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.home2_vp);
        change = (Button) view.findViewById(R.id.hom2_change);
        viewPager.setPageMargin(16);
    }

    private void initObject() {
        home2UserVpAdapter = new Home2UserVpAdapter();

        home2UserVpAdapter.setUsers(SplashScreen.users);
        viewPager.setAdapter(home2UserVpAdapter);
    }

    @Override
    public void changeViewPagerFragment(HomeVpAdapter homeVpAdapter, ViewPager viewPager) {
        List<Fragment> _fragments = new ArrayList<>();
        _fragments.add(new Notification());
        _fragments.add(new Dashboard());
        homeVpAdapter.setFragments(_fragments);
        viewPager.setAdapter(homeVpAdapter);
    }
}
