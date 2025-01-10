package com.nl.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new BarAction());
        System.out.println("Starting");
        new Thread(new CBMyThread("A", cyclicBarrier)).start();
        new Thread(new CBMyThread("B", cyclicBarrier)).start();
        new Thread(new CBMyThread("C", cyclicBarrier)).start();
        new Thread(new CBMyThread("D", cyclicBarrier)).start();
        new Thread(new CBMyThread("E", cyclicBarrier)).start();
        new Thread(new CBMyThread("F", cyclicBarrier)).start();
    }
}

class CBMyThread implements Runnable {
    String name;
    CyclicBarrier cbr;

    CBMyThread(String n, CyclicBarrier cbr) {
        this.name = n;
        this.cbr = cbr;
    }

    @Override
    public void run() {
        System.out.println(name + " is starting");
        try {
            cbr.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(name + " has crossed the barrier");
    }
}

class BarAction implements Runnable {
    @Override
    public void run() {
        System.out.println("Barrier reached.");
    }
}
