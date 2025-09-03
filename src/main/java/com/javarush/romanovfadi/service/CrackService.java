package com.javarush.romanovfadi.service;

import com.javarush.romanovfadi.analysis.FrequencyAnalyzer;
import com.javarush.romanovfadi.analysis.LanguageDetector;
import com.javarush.romanovfadi.cipher.CaesarCipher;
import com.javarush.romanovfadi.cipher.Language;

import java.util.Scanner;

public class CrackService {
    public static void handle(Scanner sc) {
        try {
            String text = EncryptService.readText(sc, "Введите шифртекст:");
            if (text == null) return;

            Language lang = LanguageDetector.detect(text);
            int bestK = FrequencyAnalyzer.bestShift(text, lang);
            String plain = CaesarCipher.transform(text, bestK, false);

            System.out.println("Detected lang=" + lang + ", k=" + bestK);
            EncryptService.saveOrPrint(sc, plain);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
