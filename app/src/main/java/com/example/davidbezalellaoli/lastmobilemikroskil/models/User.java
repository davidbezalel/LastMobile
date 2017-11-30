package com.example.davidbezalellaoli.lastmobilemikroskil.models;

import android.util.Base64;
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
    public String name, nim, profile_image;
    private String password;
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public User (String nim, String name, String password) throws Exception {
        this.id = userIdIncrement;
        this.nim = nim;
        this.name = name;
        this.secretKey = AesEncrypt.getInstance().getSecretKey();
        this.password = AesEncrypt.getInstance().encrypt(password, encodedStringSecretKeyToString(this.secretKey));
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
    public void setProfile_image(String imaga_path){
        this.profile_image = imaga_path;
    }
    public String getProfile_image(){
        return this.profile_image;
    }
    public String getJurusan() {
        String prodi = this.nim.substring(2, 5);
        if(prodi.equals("021")){
            return "(D3) Manajemen Informatika";
        }else if(prodi.equals("111")){
            return "(S1) Teknik Informatika";
        }else if(prodi.equals("211")){
            return "(S1) Sistem Informasi";
        }else if( prodi.equals("711")){
            return "(S1) Manajemen";
        }else if(prodi.equals("811")){
            return "(S1) Akuntansi";
        }else if(prodi.equals("421")){
            return "(S2) Magister Teknologi Informasi";
        }
        return "Jurusan tidak ditemukan";
    }
}
