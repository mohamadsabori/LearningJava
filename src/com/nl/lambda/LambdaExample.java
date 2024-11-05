package com.nl.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T1", 500.00),
                new Transaction("T2", 150.00),
                new Transaction("T3", 100.00)
        );

        transactions.sort(Comparator.comparingDouble(Transaction::amount));
        System.out.println("Sorted Transactions using Lambda expression");
        transactions.forEach(System.out::println);
    }
}
