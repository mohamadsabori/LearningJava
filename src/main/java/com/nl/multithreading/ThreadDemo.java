package com.nl.multithreading;

class NewThread implements Runnable{
    Thread t;
    NewThread(){
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
    }
    @Override
    public void run() {
        try{
            for(var i = 5; i > 0; i--){
                System.out.println("Child thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child Thread interrupted.");
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        NewThread t = new NewThread();
        t.t.start();

        try{
            for(var j = 5; j > 0; j--){
                System.out.println("Main Thread:" + j);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Main Thread interrupted.");
        }
    }
}
