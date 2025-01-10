package com.nl.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreTesting {
    public static void main(String[] args) {
        Semaphore sm = new Semaphore(1);
        new Thread(new IncThread(sm, "A")).start();
        new Thread(new DecThread(sm, "B")).start();
    }
}

class Shared {
    static int count = 0;
}

class IncThread implements Runnable {
    String name;
    Semaphore sm;

    IncThread(Semaphore semaphore, String n) {
        this.sm = semaphore;
        name = n;
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " waiting for permit.");
                sm.acquire();
                System.out.println(name + " permission acquired");
                Shared.count++;
                System.out.println(name + ":" + Shared.count);
                sm.release();
                Thread.sleep(10);
            }
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

class DecThread implements Runnable {
    String name;
    Semaphore sm;

    DecThread(Semaphore semaphore, String n) {
        this.sm = semaphore;
        name = n;
    }

    @Override
    public void run() {
        System.out.println("Starting thread " + name);
        try {
            for (var i = 0; i < 5; i++) {
                System.out.println(name + " Waiting for permit.");
                sm.acquire();
                System.out.println(name + " permission acquired");
                Shared.count--;
                System.out.println(name + ":" + Shared.count);
                sm.release();
                Thread.sleep(10);
            }
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}