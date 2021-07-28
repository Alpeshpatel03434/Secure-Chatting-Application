package com.my.chatting;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class myAES {

    private static final byte[] KeyValue =
            new byte[]{ 'a', 'P', 'd', 'S', 'g', 'V', 'k',
                    'Y', 'p', '3', 's', '6', 'v', '9', 'y', '$', 'B',
                    '?', 'E', '(', 'H', '+', 'M', 'b', 'Q', 'e', 'T',
                    'h', 'W', 'm', 'Z', 'q' };


    public static String Encrypt(String data) {

        try {

            // Create default byte array
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0};

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Key key = generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
            byte[] encVal = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encVal);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public static String Decrypt(String encryptedData) {

        try {

            // Create default byte array
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0};

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Key key = generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivspec);

            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    // Generate AES Key
    private static Key generateKey() {
        return new SecretKeySpec(KeyValue, "AES");
    }


//    // Main function
//    public static void main(String[] args) {
//
//        // Input
//        Scanner sc = new Scanner(System.in);
//
//        // String Input
//        String Plaintext = sc.nextLine();
//
//        // Encrypt Plaintext
//        String encryptedString;
//        encryptedString = Encrypt(Plaintext);
//
//        System.out.println("Encrypt text :\t"+encryptedString);
//
//        // Decrypt Encrypted text
//        String decryptedString;
//        decryptedString = Decrypt(encryptedString);
//
//        System.out.println("Decrypt text :\t"+decryptedString);
//
//    }

}