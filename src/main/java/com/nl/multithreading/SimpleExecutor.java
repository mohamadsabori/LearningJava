package com.nl.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExecutor {
    public static void main(String[] args) {
        CountDownLatch ctl = new CountDownLatch(5);
        CountDownLatch ctl2 = new CountDownLatch(5);
        CountDownLatch ctl3 = new CountDownLatch(5);
        CountDownLatch ctl4 = new CountDownLatch(5);
        ExecutorService es = Executors.newFixedThreadPool(3);
        System.out.println("Starting");
        es.execute(new MyExecutorThread("A", ctl));
        es.execute(new MyExecutorThread("B", ctl2));
        es.execute(new MyExecutorThread("C", ctl3));
        es.execute(new MyExecutorThread("D", ctl4));
        try {
            ctl.await();
            ctl2.await();
            ctl3.await();
            ctl4.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        es.shutdown();
        System.out.println("Done!");
    }
}

class MyExecutorThread implements Runnable {
    CountDownLatch countDownLatch;
    String name;

    MyExecutorThread(String name, CountDownLatch ctl) {
        this.countDownLatch = ctl;
        this.name = name;
    }

    @Override
    public void run() {
        for (var i = 0; i < 5; i++) {
            System.out.println(name + " " + i);
            countDownLatch.countDown();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {

            }
        }
    }
}