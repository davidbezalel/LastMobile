package com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.fragments;


import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities.AuthParent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {

    AuthParent authParent;

    private View view;
    private ProgressBar progressBar;
    private LinearLayout login;
    private TextView loginText;
    private TextView registerBtn;
    private TextView nameText;
    private TextView nimText;
    private TextView passwordText;
    private TextView rePasswordText;

    private TextView[] textViews;
    private String[] messages;



    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);

        initView();
        initObject();
        event();
        return view;
    }

    private void initView() {
        progressBar = (ProgressBar) view.findViewById(R.id.register_progress);
        loginText = (TextView) view.findViewById(R.id.register_loginText);
        login = (LinearLayout) view.findViewById(R.id.register_login);
        registerBtn = (TextView)view.findViewById(R.id.register_action);

        nameText = (TextView)view.findViewById(R.id.register_name);
        nimText = (TextView)view.findViewById(R.id.register_nim);
        passwordText = (TextView)view.findViewById(R.id.register_password);
        rePasswordText = (TextView)view.findViewById(R.id.register_repassword);



        loginText.setPaintFlags(loginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        progressBar.setVisibility(View.GONE);
    }


    private void initObject(){
        authParent =(AuthParent)getActivity();
        textViews = new TextView[]{nameText,nimText,passwordText};
        messages = new String[]{getString(R.string.error_empty_name)
                ,getString(R.string.error_empty_nim)
                ,getString(R.string.error_empty_password)};
    }

    private void event() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.authparent, new Login()).commit();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        String validation = validation();
        if(validation==null){
            progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(authParent.isUserExisting(nimText.getText().toString())!=-1){
                            progressBar.setVisibility(View.GONE);
                            authParent.showAlertDialog(getString(R.string.existing_nim));
                        }else {
                            authParent.users.add(new User(
                                    nimText.getText().toString()
                                    , nameText.getText().toString()
                                    , passwordText.getText().toString()
                            ));
                            progressBar.setVisibility(View.GONE);
                            authParent.showAlertDialog(getString(R.string.register_successful));
                            authParent.onBackPressed();

                        }
                    }catch (Exception e){e.printStackTrace();}
                }
            },1000);

        }else{
            authParent.showAlertDialog(validation);
        }
    }

    private String validation(){
        for(int i=0;i<textViews.length;i++){
            if(TextUtils.isEmpty(textViews[i].getText().toString())){
                return messages[i];
            }
        }
        if(nimText.getText().toString().length()!=9){
            return getString(R.string.invalid_nim);
        }
        if(!isPasswordValid(passwordText.getText().toString())){
            return getString(R.string.invalid_password);
        }
        if(!rePasswordText.getText().toString().equals(passwordText.getText().toString())){
            return getString(R.string.error_re_password);
        }
        return null;
    }

    public boolean isPasswordValid(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,15}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
