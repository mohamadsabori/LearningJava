package com.nl.stream;

import java.util.Arrays;
import java.util.List;

public class FilterExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 6, 10, 21, 20000);
        List<Integer> evenNumber = numbers.stream().filter(e -> e % 2 == 0).toList();
        evenNumber.forEach(System.out::println);
    }
}
