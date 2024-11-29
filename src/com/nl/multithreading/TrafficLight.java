package com.nl.multithreading;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrafficLight {
    static Scanner sc = new Scanner(System.in);
    static int roads;
    static int interval;
    static AtomicBoolean systemState = new AtomicBoolean(false);
    static final LocalTime startTime = LocalTime.now();
    static Queue<String> queue = new ConcurrentLinkedQueue<>();

    static Thread t = new Thread(() -> {
        while (true) {
            while (systemState.get()) {
                cleanOutput();
                printSystemState();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
        }
    }, "QueueThread");


    public static void main(String[] args) {
        System.out.println("Welcome to the traffic management system!");
        t.setDaemon(true);
        t.start();
        askRoadInformation();
    }

    private static void askRoadInformation() {
        askIntegerValue();
        showMenu();
    }

    private synchronized static void cleanOutput() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void askIntegerValue() {
        System.out.print("Input the number of roads: >");
        roads = getValueFromConsole();
        System.out.print("Input the interval: >");
        interval = getValueFromConsole();
    }

    private static int getValueFromConsole() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value <= 0) {
                    System.out.print("Error! Incorrect Input. Try again");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.print("Error! Incorrect Input. Try again");
            }
        }
    }

    private static void showMenu() {
        while (true) {
            cleanOutput();
            System.out.println("Menu:");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. System");
            System.out.println("0. Quit");

            int userCmd;
            try {
                userCmd = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                userCmd = -1; //Invalid value
            }
            switch (userCmd) {
                case 1 -> {
                    System.out.print("Input road name:");
                    String roadName = sc.nextLine();
                    if (queue.size() == roads) {
                        System.out.println("Queue is full");
                    } else {
                        queue.add(roadName);
                        System.out.println(roadName + " Added!");
                    }
                }
                case 2 -> {
                    if (queue.isEmpty()) {
                        System.out.println("queue is empty");
                    } else {
                        System.out.println(queue.poll() + " deleted!");
                    }
                }
                case 3 -> openSystemState();
                case 0 -> quit();
                default -> {
                    System.out.println("Incorrect option");
                }
            }
            sc.nextLine();
        }
    }

    private static void openSystemState() {
        systemState.set(true);
        // Wait for the user to press Enter to exit the system state
        sc.nextLine(); // Wait for Enter
        systemState.set(false);
        showMenu();
    }

    private static void printSystemState() {
        System.out.printf("! %ds. have passed since system startup !\n"
                , Duration.between(startTime, LocalTime.now()).getSeconds());
        System.out.printf("! Number of roads: %d !\n", roads);
        System.out.printf("! Interval: %d !\n", interval);
        queue.forEach(System.out::println);
        System.out.println("! Press \"Enter\" to open menu !");
    }

    private static void quit() {
        sc.close();
        System.out.println("Bye!");
//        t.stop();
        System.exit(0);
    }
}
