package com.example.davidbezalellaoli.lastmobilemikroskil.utils;

import android.util.Base64;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by davidbezalellaoli on 11/18/17.
 */

public class AesEncrypt {

    private static AesEncrypt aesEncrypt;
    private Cipher cipher;

    private AesEncrypt() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance("AES");
    }

    public static AesEncrypt getInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
        if (aesEncrypt == null)
            aesEncrypt = new AesEncrypt();
        return aesEncrypt;
    }

    public String encrypt(String text, SecretKey secretKey) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] _encryptValue = cipher.doFinal(text.getBytes());
        return (Base64.encodeToString(_encryptValue, 128));
    }

    public String decrypt(String text, SecretKey secretKey) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] _decryptValue = cipher.doFinal(Base64.decode(text, 128));
        return (new String(_decryptValue));

    }

    public String getSecretKey() throws Exception {
        KeyGenerator _keyGenerator = KeyGenerator.getInstance("AES");
        _keyGenerator.init(128);
        SecretKey _secretKey = _keyGenerator.generateKey();
        return Base64.encodeToString(_secretKey.getEncoded(), Base64.DEFAULT);
    }
}
