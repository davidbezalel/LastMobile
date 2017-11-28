package com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidbezalellaoli.lastmobilemikroskil.Main;
import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities.AuthParent;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.activities.Home;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.Session;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    private View view;
    private EditText nim, password;
    private Button login;
    private TextView registerText;
    private LinearLayout register;
    private ProgressBar progressBar;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private User userLoggedIn;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        initview();
        initobject();
        event();

        return view;
    }

    private void initview() {
        /* view declaration */
        progressBar = (ProgressBar) view.findViewById(R.id.login_progress);
        registerText = (TextView) view.findViewById(R.id.login_registertext);
        register = (LinearLayout) view.findViewById(R.id.login_register);
        nim = (EditText) view.findViewById(R.id.login_nim);
        password = (EditText) view.findViewById(R.id.login_password);
        login = (Button) view.findViewById(R.id.login_action);

        /* view setting */
        progressBar.setVisibility(View.GONE);
        registerText.setPaintFlags(registerText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void initobject() {
        dialogBuilder = new AlertDialog.Builder(getContext());
    }


    private void event() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.authparent, new Register()).commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                final Handler _handler = new Handler();

                boolean ok = _handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(nim.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                            dialogBuilder.setMessage("Harap isi semua field").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            dialog = dialogBuilder.create();
                            dialog.show();
                        } else {
                            try {
                                boolean _isRegistered = false;
                                boolean _isPasswordMatch = false;
                                for (User user: AuthParent.users) {
                                    if (user.nim.equals(nim.getText().toString())) {
                                        _isRegistered = true;
                                        if (user.passwordCheck(password.getText().toString())) {
                                            _isPasswordMatch = true;
                                            userLoggedIn = user;
                                        }
                                        break;
                                    }
                                }

                                if (!_isRegistered) {
                                    dialogBuilder.setMessage("Maaf, NIM anda tidak terdaftar").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    dialog = dialogBuilder.create();
                                    dialog.show();
                                } else if (!_isPasswordMatch) {
                                    dialogBuilder.setMessage("Maaf, Password yang anda masukkan salah").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    dialog = dialogBuilder.create();
                                    dialog.show();
                                } else {
                                    Session.getInstance(getContext()).createSession(userLoggedIn);
                                    Intent _intent = new Intent(getContext(), Home.class);
                                    getContext().startActivity(_intent);
                                    getActivity().finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1500);
            }
        });
    }


}
