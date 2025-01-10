package com.nl.filesnio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class BigFileReading {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("./BigText.txt");
        System.out.println(path.toAbsolutePath().normalize());
        try (var buffer = new BufferedReader(new FileReader(path.toFile()));) {
            buffer.lines().parallel().forEach(e -> System.out.println(e + " Thread name: " + Thread.currentThread().getName()));
        }
    }
}
