package com.nl.multithreading;

import java.util.Scanner;

public class SystemStatus {
    static int systemTime = 0;
    static boolean systemStatus = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while(true){
                systemTime++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "QueueThread").start();

        while(true){
            new Thread(() -> {
                while (systemStatus) {
                    System.out.printf("%d s system is working%n", systemTime);
                    System.out.println("Please 'enter' to show menu");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            sc.nextLine();
            systemStatus = false;
            System.out.println("Returned to menu");
        }


    }
}
