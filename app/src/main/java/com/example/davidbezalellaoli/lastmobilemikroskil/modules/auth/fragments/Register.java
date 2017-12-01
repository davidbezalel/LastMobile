package com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.fragments;


import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.auth.activities.AuthParent;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    private View view;
    private ProgressBar progressBar;
    private LinearLayout login;
    private TextView loginText, prodiText;
    private Button register;
    private EditText registerName, registerNim, registerPassword, registerRePassword;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        initview();
        event();
        return view;
    }

    private void initview() {
        progressBar = (ProgressBar) view.findViewById(R.id.register_progress);
        loginText = (TextView) view.findViewById(R.id.register_loginText);
        prodiText = (TextView) view.findViewById(R.id.register_prodi);
        login = (LinearLayout) view.findViewById(R.id.register_login);

        loginText.setPaintFlags(loginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        progressBar.setVisibility(View.GONE);

        registerName       = (EditText) view.findViewById(R.id.register_name);
        registerNim        = (EditText) view.findViewById(R.id.register_nim);
        registerPassword   = (EditText) view.findViewById(R.id.register_password);
        registerRePassword = (EditText) view.findViewById(R.id.register_repassword);

        register = (Button) view.findViewById(R.id.register_action);
        dialogBuilder = new AlertDialog.Builder(getContext());
    }

    private void event() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.authparent, new Login()).commit();
            }
        });
        
        prodiText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupDialog("Daftar Kode Program Studi\n021 => (D-3) Manajemen Informatika\n111 => (S-1) Teknik Informatika\n211 => (S-1) Sistem Informasi\n711 => (S-1) Manajemen\n811 => (S-1) Akuntansi\n421 => (S-2) Magister Teknologi Informasi");
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Handler _hadler = new Handler();
                progressBar.setVisibility(View.VISIBLE);
                _hadler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (userValidation()) {
                            try {
                                AuthParent.users.add(new User(registerNim.getText().toString(), registerName.getText().toString(), registerPassword.getText().toString(),"http://api.learn2crack.com/android/images/donut.png"));
                                popupDialog("Akun berhasil terdaftar.");
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

    private void popupDialog (String message) {
        dialogBuilder.setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog = dialogBuilder.create();
        dialog.show();
    }

    private boolean userValidation () {
        // Validasi 1: Semua field tidak boleh kosong
        if (TextUtils.isEmpty(registerName.getText().toString()) || TextUtils.isEmpty(registerNim.getText().toString()) || TextUtils.isEmpty(registerPassword.getText().toString()) || TextUtils.isEmpty(registerRePassword.getText().toString())) {
            popupDialog("Harap isi semua field!");
            return false;
        }

        // Validasi 2: NIM harus 9 digit angka
        if (!User.nimValidation(registerNim.getText().toString())) {
            popupDialog("NIM hanya boleh terdiri dari 9 digit angka");
            return false;
        }

        // Validasi 3: Harus sesuai dengan nomor Program Studi
        if (!User.programStudiValidation(registerNim.getText().toString())) {
            String tahun = registerNim.getText().toString().substring(0, 2);
            String prodi = registerNim.getText().toString().substring(2, 5);
            String nomor = registerNim.getText().toString().substring(5, 9);
            popupDialog(tahun + "-" + prodi + "-" + nomor + ", kode program studi " + prodi + " tidak sesuai. Harap melihat standard kode program studi pada bagian link 'Lihat Kode Program Studi'");
            return false;
        }

        // Validasi 4: Tidak boleh ada NIM yang sama
        for (User usr : AuthParent.users) {
            Log.e("List User", usr.nim);
            if (usr.nim.equals(registerNim.getText().toString())) {
                popupDialog("NIM " + registerNim.getText().toString() + " sudah terdaftar!");
                return false;
            }
        }

        // Validasi 5: Password dan RePassword harus sama
        if (!registerPassword.getText().toString().equals(registerRePassword.getText().toString())) {
            popupDialog("Password kedua tidak cocok!");
            return false;
        }

        // Validasi 6: Password harus sesuai dengan method validationPassword pada Class User
        if (!User.passwordValidation(registerPassword.getText().toString())) {
            popupDialog("Password harus terdiri dari 1 huruf kecil, 1 huruf besar, dan 1 digit angka. Panjang password min. 8 digit dan maks. 12 digit!");
            return false;
        }
        return true;
    }
}
