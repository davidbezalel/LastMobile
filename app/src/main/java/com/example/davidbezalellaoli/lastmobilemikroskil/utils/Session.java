package com.example.davidbezalellaoli.lastmobilemikroskil.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.google.gson.Gson;

/**
 * Created by davidbezalellaoli on 11/19/17.
 */

public class Session {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static Session session;
    private Context context;

    private final int PRIVATE_MODE = 0;

    private final String PREFERENCE_NAME = "session";
    private final String KEY_IS_USER_LOGGED_IN = "is.user.logged.in";
    private final String KEY_USER_DATA = "user.data";


    private Session (Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public static Session getInstance(Context context) {
        if (session == null) {
            session = new Session(context);
        }
        return session;
    }

    public void createSession (User user) {
        editor.putBoolean(KEY_IS_USER_LOGGED_IN, true);
        editor.putString(KEY_USER_DATA, new Gson().toJson(user));
        editor.commit();
    }

    public User getUserLoggedIn() {
        return (new Gson().fromJson(sharedPreferences.getString(KEY_USER_DATA, ""), User.class));
    }

    public void clearSession () {
        editor.clear();
        editor.commit();
    }

    public boolean isUserLoggedIn () {
        return sharedPreferences.getBoolean(KEY_IS_USER_LOGGED_IN, false);
    }
}
