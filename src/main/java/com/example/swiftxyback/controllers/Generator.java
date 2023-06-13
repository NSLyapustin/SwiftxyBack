package com.example.swiftxyback.controllers;

import java.io.IOException;

public class Generator {
    public static String generate(String template) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/main/java/com/example/swiftxyback/controllers/script.py", template);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        String result = new String(process.getInputStream().readAllBytes());
        return result.startsWith("Traceback") ? "" : result;
    }
}
