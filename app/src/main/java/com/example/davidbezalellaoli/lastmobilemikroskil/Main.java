package com.example.davidbezalellaoli.lastmobilemikroskil;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities.AuthParent;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.Session;


public class Main extends AppCompatActivity {
    private TextView title;
    private ImageView logout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        event();
    }

    private void initview() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        title = (TextView) findViewById(R.id.actionbar_title);
        logout = (ImageView) findViewById(R.id.actionbar_logout);
        title.setText("LastMobile");

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        progressBar.setVisibility(View.GONE);


        TextView userText = (TextView) findViewById(R.id.main_user);
        User user = Session.getInstance(getApplicationContext()).getUserLoggedIn();

        userText.setText(String.valueOf(user.getId()) + " " + user.name + " " + user.nim + " " + user.getPassword() + " " + user.getSecretKey());

    }

    private void event() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler _hadler = new Handler();
                _hadler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Session.getInstance(getApplicationContext()).clearSession();
                        Intent _intent = new Intent(getApplicationContext(), AuthParent.class);
                        startActivity(_intent);
                        finish();
                    }
                }, 1500);
            }
        });
    }

}
