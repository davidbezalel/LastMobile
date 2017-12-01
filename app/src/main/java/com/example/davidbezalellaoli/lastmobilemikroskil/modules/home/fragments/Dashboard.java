package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.SplashScreen;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities.AuthParent;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters.User_RV_Adapter;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters.User_RV_Adapter_Grid;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    private View view;
    private RecyclerView rv;
    private User_RV_Adapter_Grid userRvAdapterGrid;

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

        /*LayoutManager: Grid*/
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv.setHasFixedSize(true);
    }

    private void initObject() {
        userRvAdapterGrid = new User_RV_Adapter_Grid();

        /*setting user rv adapter: inject data to adapter*/
        userRvAdapterGrid.setUsers(SplashScreen.users);

        rv.setAdapter(userRvAdapterGrid);
    }

}

