package com.javarush.romanovfadi.service;

import com.javarush.romanovfadi.cipher.CaesarCipher;
import com.javarush.romanovfadi.util.FileUtils;

import java.util.Scanner;

public class EncryptService {
    public static void handle(Scanner sc) {
        try {
            String text = readText(sc, "Введите текст для шифрования:");
            if (text == null) return;

            System.out.print("Ключ: ");
            int key = Integer.parseInt(sc.nextLine());

            String encrypted = CaesarCipher.transform(text, key, true);
            saveOrPrint(sc, encrypted);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }


    static String readText(Scanner sc, String prompt) {
        System.out.println("1) Ввести текст вручную\n2) Указать файл");
        System.out.print("Выбери режим:");
        String choice = sc.nextLine().trim();
        try {
            if (choice.equals("1")) {
                System.out.print(prompt + " ");
                return sc.nextLine();
            } else if (choice.equals("2")) {
                System.out.print("Укажи путь к файлу: ");
                String path = sc.nextLine();
                return FileUtils.readFile(path);
            } else {
                System.out.println("Неверный выбор. Возврат в меню.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
            return null;
        }
    }

    static void saveOrPrint(Scanner sc, String result) {
        while (true) {
            System.out.println("Сохранить результат в файл? (y/n)");
            String answer = sc.nextLine().trim();

            if (answer.equalsIgnoreCase("y")) {
                try {
                    System.out.print("Введите путь для сохранения: ");
                    String outPath = sc.nextLine();
                    FileUtils.writeFile(outPath, result);
                    System.out.println("Результат сохранён в " + outPath);
                } catch (Exception e) {
                    System.out.println("Ошибка записи: " + e.getMessage());
                }
                break; // корректный ввод, выходим
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("Результат:\n" + result);
                break; // корректный ввод, выходим
            } else {
                System.out.println("Некорректный ввод! Пожалуйста, введите 'y' или 'n'.");
            }
        }
    }
}
