package com.findwise.kerberos;

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;

/**
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 */
public class JceTest {

    public static void main(String[] args) {

        System.out.println("Testing for unlimited crypto:");
        try {
            int length = Cipher.getMaxAllowedKeyLength("AES");
            boolean unlimited = (length == Integer.MAX_VALUE);
            System.out.println("Supportet: "+unlimited);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getStackTrace());
        }
    }
}
