package com.nl.multithreading;

public class CurrentThreadExample {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current thread: " + t);

        t.setName("My Thread");

        System.out.println("After name change: " + t);

        try{
            for(var n = 5; n > 0; n--){
                System.out.println(n);
                Thread.sleep(100);
            }

        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted " + e.getMessage());
        }
    }
}
