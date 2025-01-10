package com.nl.filesnio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class OpenOrCreate {
    public static void main(String[] args) throws IOException {
        var path = Path.of(".");
        var path2 = Path.of("goltar");
        System.out.println(path.resolve(path2));
        if (Files.exists(path)) {
            System.out.println("Normalized absolute path " + path.toAbsolutePath().normalize());
            System.out.println("Directory path " + path.toRealPath());
            System.out.println("Is directory " + Files.isDirectory(path));
            System.out.println("Parent is " + path.getParent());
            System.out.println(Files.getLastModifiedTime(path));
            if (Files.isRegularFile(path)) {
                System.out.println(Files.size(path));

            } else {
                try (Stream<Path> file = Files.list(path)) {
                    file.map(Path::getFileName).forEach(System.out::println);
                }
            }
        }
    }
}
