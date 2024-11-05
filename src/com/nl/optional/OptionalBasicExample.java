package com.nl.optional;

import java.util.Optional;

public class OptionalBasicExample {
    public static void main(String[] args) {
        String nullableString = "null";

        Optional<String> optionalValue = Optional.ofNullable(nullableString);

        String value = optionalValue.orElse("Default value");
        System.out.println(value);

        optionalValue.ifPresent(s -> System.out.println("Value is present " + s));
        Optional<Integer> length = optionalValue.map(String::length);
        System.out.println("length: " + length.orElse(9));
    }
}
