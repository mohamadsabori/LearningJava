package com.nl.multithreading;

class NewRunnableThread implements Runnable {
    String name;
    Thread t;

    NewRunnableThread(String threadName) {
        name = threadName;
        t = new Thread(this, name);
        System.out.println("new Thread: " + t);
    }

    public void run() {
        try {
            for (var i = 5; i > 0; i--) {
                System.out.println(name + " " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + name + " interrupted");
        }
        System.out.println(name + " exiting");
    }
}

public class DemoJoin {
    public static void main(String[] args) {
        NewRunnableThread t1 = new NewRunnableThread("thread-one");
        NewRunnableThread t2 = new NewRunnableThread("thread-two");
        NewRunnableThread t3 = new NewRunnableThread("thread-three");
        t1.t.start();
        t2.t.start();
        t3.t.start();
        System.out.println("thread One is Alive :" + t1.t.isAlive());
        System.out.println("thread Two is Alive :" + t2.t.isAlive());
        System.out.println("thread Three is Alive :" + t3.t.isAlive());
        try{
            System.out.println("Waiting for thread to finish!");
            t1.t.join();
            t2.t.join();
            t3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        System.out.println("thread One is Alive :" + t1.t.isAlive());
        System.out.println("thread Two is Alive :" + t2.t.isAlive());
        System.out.println("thread Three is Alive :" + t3.t.isAlive());

        System.out.println("Main Thread exciting.");
    }
}
