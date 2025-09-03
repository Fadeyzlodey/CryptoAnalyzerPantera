package com.javarush.romanovfadi.cipher;

import java.util.Locale;

public class CaesarCipher {

    private static final String EN_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String EN_LO = EN_UP.toLowerCase(Locale.ROOT);
    private static final String RU_UP = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RU_LO = RU_UP.toLowerCase(new Locale("ru"));

    public static String transform(String text, int key, boolean encrypt) {
        StringBuilder sb = new StringBuilder(text.length());
        int k = encrypt ? key : -key;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (isInAlphabet(ch, EN_UP)) sb.append(shift(ch, k, EN_UP));
            else if (isInAlphabet(ch, EN_LO)) sb.append(shift(ch, k, EN_LO));
            else if (isInAlphabet(ch, RU_UP)) sb.append(shift(ch, k, RU_UP));
            else if (isInAlphabet(ch, RU_LO)) sb.append(shift(ch, k, RU_LO));
            else sb.append(ch);
        }
        return sb.toString();
    }

    private static boolean isInAlphabet(char c, String alphabet) {
        return alphabet.indexOf(c) >= 0;
    }

    private static char shift(char c, int key, String alphabet) {
        int len = alphabet.length();
        int idx = alphabet.indexOf(c);
        int n = Math.floorMod(idx + key, len);
        return alphabet.charAt(n);
    }

    public static String getEnUp() {
        return EN_UP;
    }

    public static String getEnLo() {
        return EN_LO;
    }

    public static String getRuUp() {
        return RU_UP;
    }

    public static String getRuLo() {
        return RU_LO;
    }
}
