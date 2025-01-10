package com.nl.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreConProdExample {
    public static void main(String[] args) {
        Queue q = new Queue();
        new Thread(new QueueConsumer(q)).start();
        new Thread(new Producre(q)).start();
    }
}

class Queue {
    int n;
    Semaphore conSem = new Semaphore(0);
    Semaphore prodSem = new Semaphore(1);

    void get() {
        try {
            conSem.acquire();
        } catch (InterruptedException ex) {
            System.out.println("Got:" + ex.getMessage());
        }
        System.out.println("Got:" + n);
        prodSem.release();
    }

    void put(int n) {
        try {
            prodSem.acquire();
        } catch (InterruptedException ex) {
            System.out.println("Put:" + ex.getMessage());
        }
        System.out.println("Put:" + n);
        this.n = n;
        conSem.release();
    }
}

class Producre implements Runnable {
    Queue q;

    Producre(Queue q) {
        this.q = q;
    }

    @Override
    public void run() {
        for (var i = 0; i < 5; i++) q.put(i);
    }
}

class QueueConsumer implements Runnable {
    Queue q;

    QueueConsumer(Queue q) {
        this.q = q;
    }

    @Override
    public void run() {
        for (var i = 0; i < 5; i++) q.get();
    }
}