package com.javarush.romanovfadi.service;

import com.javarush.romanovfadi.analysis.BruteForce;

import java.util.Scanner;

public class BruteforceService {
    public static void handle(Scanner sc) {
        try {
            String text = EncryptService.readText(sc, "Введите шифртекст:");
            if (text == null) return;

            String result = BruteForce.bruteForceReport(text);
            EncryptService.saveOrPrint(sc, result);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
