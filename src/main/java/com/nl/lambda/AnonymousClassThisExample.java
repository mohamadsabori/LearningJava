package com.nl.lambda;

public class AnonymousClassThisExample {
    public void doSomething(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class this " + this);
            }
        }).start();
    }
    public static void main(String[] args) {
        new AnonymousClassThisExample().doSomething();
    }
}
