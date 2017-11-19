package com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.fragments;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    private View view;
    private ProgressBar progressBar;
    private LinearLayout login;
    private TextView loginText;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        initview();
        event();
        return view;
    }

    private void initview() {
        progressBar = (ProgressBar) view.findViewById(R.id.register_progress);
        loginText = (TextView) view.findViewById(R.id.register_loginText);
        login = (LinearLayout) view.findViewById(R.id.register_login);

        loginText.setPaintFlags(loginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        progressBar.setVisibility(View.GONE);
    }

    private void event() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.authparent, new Login()).commit();
            }
        });
    }

}
