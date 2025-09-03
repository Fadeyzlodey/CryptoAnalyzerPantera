package com.javarush.romanovfadi.analysis;

import com.javarush.romanovfadi.cipher.CaesarCipher;
import com.javarush.romanovfadi.cipher.Language;

public class LanguageDetector {
    public static Language detect(String text) {
        int ru = 0, en = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (CaesarCipher.getRuUp().indexOf(c) >= 0 || CaesarCipher.getRuLo().indexOf(c) >= 0) ru++;
            else if (CaesarCipher.getEnUp().indexOf(c) >= 0 || CaesarCipher.getEnLo().indexOf(c) >= 0) en++;
        }
        return (ru >= en) ? Language.RU : Language.EN;
    }
}
