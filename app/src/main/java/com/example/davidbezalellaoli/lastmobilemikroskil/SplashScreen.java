package com.example.davidbezalellaoli.lastmobilemikroskil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities.AuthParent;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.Session;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                    if (Session.getInstance(getApplicationContext()).isUserLoggedIn()) {
                        Intent intent = new Intent(getApplicationContext(), Main.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), AuthParent.class);
                        startActivity(intent);
                    }
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}
