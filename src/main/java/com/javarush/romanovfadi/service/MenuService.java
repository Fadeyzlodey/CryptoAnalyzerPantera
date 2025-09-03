package com.javarush.romanovfadi.service;

import java.util.Scanner;

public class MenuService {
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Caesar cipher (RU/EN).\n" +
                " 1) Encrypt (Шифрует текст)\n" +
                " 2) Decrypt (Расшифровывает текст, если знаешь ключ)\n" +
                " 3) Bruteforce (Перебор ключей)\n" +
                " 4) Crack (Стат. анализ, найдет ключ)\n" +
                " 5) Exit");

        while (true) {
            System.out.print("Выбери режим: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                case "Encrypt":
                    EncryptService.handle(sc);
                    break;
                case "2":
                case "Decrypt":
                    DecryptService.handle(sc);
                    break;
                case "3":
                case "Bruteforce":
                    BruteforceService.handle(sc);
                    break;
                case "4":
                case "Crack":
                    CrackService.handle(sc);
                    break;
                case "5":
                case "Exit":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неизвестная команда. Введите значение от 1 до 5");
            }
        }
    }
}
