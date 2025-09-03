package com.javarush.romanovfadi.service;

import com.javarush.romanovfadi.cipher.CaesarCipher;

import java.util.Scanner;

public class DecryptService {
    public static void handle(Scanner sc) {
        try {
            String text = EncryptService.readText(sc, "Введите текст для расшифровки:");
            if (text == null) return;

            System.out.print("Ключ: ");
            int key = Integer.parseInt(sc.nextLine());

            String decrypted = CaesarCipher.transform(text, key, false);
            EncryptService.saveOrPrint(sc, decrypted);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
