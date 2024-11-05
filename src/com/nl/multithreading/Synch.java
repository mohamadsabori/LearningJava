package com.nl.multithreading;

class CallMe {
    public synchronized void call(String msg){
        System.out.print("[" + msg);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("]");
    }
}
class Caller implements Runnable{
    Thread t;
    String message;
    CallMe target;

    Caller(CallMe targ, String msg){
        this.message = msg;
        this.target = targ;
        t = new Thread(this);
    }

    public void run(){
        target.call(message);
    }
}
public class Synch {
    public static void main(String[] args){
        CallMe callMe = new CallMe();
        Caller obj1 = new Caller(callMe, "Hello");
        Caller obj2 = new Caller(callMe, "Synchronization");
        Caller obj3 = new Caller(callMe, "World");
        obj1.t.start();
        obj2.t.start();
        obj3.t.start();
        try{
            obj1.t.join();
            obj2.t.join();
            obj3.t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
