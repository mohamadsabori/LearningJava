package com.nl.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CDLDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5000);
        System.out.println("Starting:");
        new Thread(new CDLThread(cdl)).start();
        try {
            System.out.println("Main thread waited.");
            while (!cdl.await(1, TimeUnit.MILLISECONDS)) {
                System.out.println("Could not reach to resource");
            }
            System.out.println("Resource is available.");
        } catch (InterruptedException ex) {
            System.out.println("Main thread" + ex.getMessage());
        }
    }
}

class CDLThread implements Runnable {
    CountDownLatch cdl;

    CDLThread(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (var i = 0; i < 5000; i++) {
            System.out.println(i);
            cdl.countDown();
        }
    }
}
