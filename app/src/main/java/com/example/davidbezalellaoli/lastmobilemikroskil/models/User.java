package com.example.davidbezalellaoli.lastmobilemikroskil.models;

import android.util.Base64;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.AesEncrypt;

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

    public String getSecretKey() {
        return secretKey;
    }

    private String secretKey;

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
}
