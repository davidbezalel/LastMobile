package com.example.davidbezalellaoli.lastmobilemikroskil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities.AuthParent;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.activities.Home;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.Session;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {


    public static List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        users = new ArrayList<>();
        try {
            users.add(new User("150210001", "I am Grootttt", "password",R.drawable.groot));
            users.add(new User("151110002", "Peter Parker", "password",R.drawable.peterr));
            users.add(new User("152110003", "Nicolas Cage", "password",R.drawable.udontsay));
            users.add(new User("159990004", "Kevin Hart", "password",R.drawable.kevinhart));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                    if (Session.getInstance(getApplicationContext()).isUserLoggedIn()) {
                        Intent intent = new Intent(getApplicationContext(), Home.class);
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
