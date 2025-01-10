package com.nl.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AnonymousClassExample {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T1", 500.00),
                new Transaction("T2", 150.00),
                new Transaction("T3", 100.00)
        );
        transactions.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return Double.compare(o1.amount(), o2.amount());
            }
        });

        System.out.println("Sorted Transactions using Anonymous class");
        transactions.forEach(System.out::println);
    }
}
