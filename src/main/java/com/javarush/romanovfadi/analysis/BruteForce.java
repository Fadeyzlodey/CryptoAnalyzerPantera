package com.javarush.romanovfadi.analysis;

import com.javarush.romanovfadi.cipher.CaesarCipher;

import java.util.Locale;

public class BruteForce {

    public static String bruteForceReport(String cipher) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Brute force EN (0..25) ===\n");
        for (int i = 0; i < CaesarCipher.getEnUp().length(); i++) {
            String cand = CaesarCipher.transform(cipher, i, false);
            sb.append(String.format(Locale.ROOT, "k=%2d: %s\n", i, sample(cand)));
        }
        sb.append("\n=== Brute force RU (0..32) ===\n");
        for (int i = 0; i < CaesarCipher.getRuUp().length(); i++) {
            String cand = CaesarCipher.transform(cipher, i, false);
            sb.append(String.format(Locale.ROOT, "k=%2d: %s\n", i, sample(cand)));
        }
        return sb.toString();
    }

    private static String sample(String s) {
        int max = Math.min(80, s.length());
        return s.substring(0, max).replace('\n', ' ');
    }
}
