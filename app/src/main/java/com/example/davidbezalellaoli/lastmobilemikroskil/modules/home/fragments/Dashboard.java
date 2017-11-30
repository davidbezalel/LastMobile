package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.SplashScreen;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters.User_RV_Adapter;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters.User_RV_Grid_Adapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class Dashboard extends Fragment {
    private View view;
    private RecyclerView rv;
    private User_RV_Grid_Adapter userRvGridAdapter;

    public Dashboard() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initView();
        initObject();
        return view;
    }
    private void initView() {
        rv = (RecyclerView) view.findViewById(R.id.dashboard_rv);

        /*LayoutManager: linear*/
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));

        /*LayoutManager: Grid*/
//        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv.setHasFixedSize(true);
    }

    private void initObject() {
        userRvGridAdapter = new User_RV_Grid_Adapter();

        /*setting user rv adapter: inject data to adapter*/
        userRvGridAdapter.setUsers(SplashScreen.users);

        rv.setAdapter(userRvGridAdapter);
    }
}
