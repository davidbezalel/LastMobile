package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidbezalellaoli.lastmobilemikroskil.Main;
import com.example.davidbezalellaoli.lastmobilemikroskil.R;

public class Home extends Main {

    private TextView mTextMessage;
    private TextView title;
    private ImageView logout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Bundle bundle = new Bundle();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    bundle.putInt("mode",com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home.HOME_CODE);
                    com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home home = new com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home();
                    home.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, home).commit();
                    return true;
                case R.id.navigation_dashboard:
                    bundle.putInt("mode",com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home.DASHBOARD_CODE);
                    com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home dashboard = new com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home();
                    dashboard.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, dashboard).commit();
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = new Bundle();
        bundle.putInt("mode",com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home.HOME_CODE);
        com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home home = new com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home();
        home.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.content, home).commit();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
