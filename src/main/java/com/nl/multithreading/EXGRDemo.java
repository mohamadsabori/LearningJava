package com.nl.multithreading;

import java.util.concurrent.Exchanger;

public class EXGRDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new UseString(exchanger)).start();
        new Thread(new MakeString(exchanger)).start();
    }
}

class MakeString implements Runnable {
    Exchanger<String> exgr;
    String str = "";

    MakeString(Exchanger<String> exchanger) {
        this.exgr = exchanger;
    }

    @Override
    public void run() {
        char chr = 'A';
        for (var i = 0; i < 5; i++) {
            for (var j = 0; j < 3; j++) str += chr++;
            System.out.println(str + " was made.");
            try {
                str = exgr.exchange(str);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

class UseString implements Runnable {
    Exchanger<String> exgr;
    String str;

    UseString(Exchanger<String> exchanger) {
        this.exgr = exchanger;
    }

    @Override
    public void run() {
        try {
            for (var i = 0; i < 5; i++) {
                str = exgr.exchange("");
                System.out.println("GOT: " + str);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
