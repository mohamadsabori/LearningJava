package com.nl.multithreading;

import java.util.concurrent.*;

class CallableDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer> f;
        Future<Double> f2;
        Future<Integer> f3;
        System.out.println("Starting");
        f = es.submit(new Sum(10));
        f2 = es.submit(new Hypot(3, 4));
        f3 = es.submit(new Factorial(5));
        try {
            System.out.println(f.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException | ExecutionException ex) {

        }
        es.shutdown();
        System.out.println("Done");
    }
}

class Sum implements Callable<Integer> {
    Integer stop;

    Sum(Integer num) {
        this.stop = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (var i = 0; i < stop; i++)
            sum += i;
        return sum;
    }
}

class Hypot implements Callable<Double> {
    double side1, side2;

    Hypot(double s1, double s2) {
        this.side1 = s1;
        this.side2 = s2;
    }

    @Override
    public Double call() throws Exception {
        return Math.sqrt((side1 * side2) + (side1 * side2));
    }
}

class Factorial implements Callable<Integer> {
    int stop;

    Factorial(int stop) {
        this.stop = stop;
    }

    @Override
    public Integer call() throws Exception {
        int fact = 1;
        for (var i = 2; i <= stop; i++) {
            fact *= i;
        }
        return fact;
    }
}