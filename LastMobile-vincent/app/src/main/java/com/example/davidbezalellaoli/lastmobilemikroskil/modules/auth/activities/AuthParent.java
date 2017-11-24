package com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.fragments.Login;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.AesEncrypt;

import java.util.ArrayList;
import java.util.List;

public class AuthParent extends AppCompatActivity {

    public List<User> users;
    private AlertDialog alertDialog;

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

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    public int isUserExisting(String nim){
        for(int i=0;i<users.size();i++){
            if(users.get(i).nim.equals(nim)){
                return i;
            }
        }
        return -1;
    }

    public void showAlertDialog (String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(AuthParent.this);
        builder.setMessage(message)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
    }


}
