package com.example.davidbezalellaoli.lastmobilemikroskil.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.AesEncrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by davidbezalellaoli on 11/18/17.
 */

public class User {
    private static int userIdIncrement = 1;
    private int id;
    public String name, nim;
    private String password;
    private String secretKey;
    public int imagePofil;

    public String getSecretKey() {
        return secretKey;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }



    public User (String nim, String name, String password,int imageProfil) throws Exception {
        this.id = userIdIncrement;
        this.nim = nim;
        this.name = name;
        this.secretKey = AesEncrypt.getInstance().getSecretKey();
        this.password = AesEncrypt.getInstance().encrypt(password, encodedStringSecretKeyToString(this.secretKey));
        this.imagePofil = imageProfil;
        userIdIncrement++;
    }

    public boolean passwordCheck (String password) throws Exception {
        return AesEncrypt.getInstance().decrypt(this.password, encodedStringSecretKeyToString(this.secretKey)).equals(password) ? true : false;
    }

    private SecretKey encodedStringSecretKeyToString (String encodedKey) {
        byte[] decodedKey = Base64.decode(encodedKey, Base64.DEFAULT);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static boolean passwordValidation (String password) {
        /*
        * 1. (?=.*[a-z]) => Minimal ada 1 huruf kecil
        * 2. (?=.*[A-Z]) => Minimal ada 1 huruf besar
        * 3. (?=.*[0-9]) => Minimal ada 1 digit angka
        * 4. ([A-Za-z0-9]{8,12}) => Hanya boleh huruf kecil, huruf besar, dan angka,...
        *                           serta min. digit adalah 8 dan maks. digit adalah 12
        * */
        Pattern r = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])([A-Za-z0-9]{8,12})");
        Matcher m = r.matcher(password);

        return m.find();
    }

    public static boolean nimValidation (String nim) {
        // ([0-9]{9}) => Hanya boleh angka, serta min. dan maks. digit adalah 9
        Pattern r = Pattern.compile("([0-9]{9})");
        Matcher m = r.matcher(nim);

        return m.find();
    }

    public static boolean programStudiValidation (String nim) {
        /*
        * Daftar Program Studi
        * - 021 => (D-3) Manajemen Informatika
        * - 111 => (S-1) Teknik Informatika
        * - 211 => (S-1) Sistem Informasi
        * - 711 => (S-1) Manajemen
..        * - 811 => (S-1) Akuntansi
        * - 421 => (S-2) Magister Teknologi Informasi
        * */
        String prodi = nim.substring(2, 5);
        return prodi.equals("021") || prodi.equals("111") || prodi.equals("211") || prodi.equals("711") || prodi.equals("811") || prodi.equals("421");
    }

    public String getProgramStudi (String nim) {
        String prodi = nim.substring(2, 5);
        switch(prodi) {
            case "021":
                return "Manajemen Informatika";
            case "111":
                return "Teknik Informatika";
            case "211":
                return "Sistem Informasi";
            case "711":
                return "Manajemen";
            case "811":
                return "Akuntansi";
            case "421":
                return "Magister Teknologi Informasi";
            default :
                return "Bukan mahasiswa sini hehe";
        }
    }
}
