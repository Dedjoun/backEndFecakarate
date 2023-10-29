package com.fecakarate.backendfecakarate.Utils;

import java.util.Random;

public class RandomUtil {

    public static String generateRandommatricule(int len) {

        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";

        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();

    }
}
