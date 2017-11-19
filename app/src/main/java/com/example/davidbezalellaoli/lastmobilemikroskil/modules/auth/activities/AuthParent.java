package com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.fragments.Login;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.AesEncrypt;

import java.util.ArrayList;
import java.util.List;

public class AuthParent extends AppCompatActivity {

    public static List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_parent);

        users = new ArrayList<>();
        try {
            users.add(new User("151110001", "STMIK Mikroskil", "password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.authparent, new Login()).commit();
    }
}
