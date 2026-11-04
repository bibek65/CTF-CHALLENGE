package com.example.app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {

    private FileUtils() {}

    public static byte[] readBytes(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

    public static String readString(Path path) throws IOException {
        return Files.readString(path);
    }

    public static void writeBytes(Path path, byte[] data) throws IOException {
        Files.createDirectories(path.getParent());
        Files.write(path, data);
    }

    public static String getExtension(String filename) {
        int dot = filename.lastIndexOf('.');
        return dot >= 0 ? filename.substring(dot + 1).toLowerCase() : "";
    }
}
