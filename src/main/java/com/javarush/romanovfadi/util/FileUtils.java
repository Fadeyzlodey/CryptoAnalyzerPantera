package com.javarush.romanovfadi.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String readFile(String path) throws IOException {
        Path p = Paths.get(path);
        return Files.readString(p);
    }

    public static void writeFile(String path, String content) throws IOException {
        try (FileWriter fw = new FileWriter(path, StandardCharsets.UTF_8)) {
            fw.write(content);
        }
    }
}
