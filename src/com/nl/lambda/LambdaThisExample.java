package com.nl.lambda;

public class LambdaThisExample {
    public void doSomething(){
        new Thread(() -> System.out.println("Lambda expression this " + this))
                .start();
    }

    public static void main(String[] args) {
        new LambdaThisExample().doSomething();
    }
}
