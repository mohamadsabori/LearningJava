package com.nl.multithreading;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrafficLight {

    private static final int QUITE = 0;
    static Scanner sc = new Scanner(System.in);
    static int roads;
    static int interval;
    static AtomicBoolean systemState = new AtomicBoolean(false);
    static final LocalTime startTime = LocalTime.now();
    static List<String> queue = Collections.synchronizedList(new ArrayList<>());

    static Thread t = new Thread(() -> {
        while (true) {
            while (systemState.get()) {
                cleanOutput();
                printSystemState();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
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
        int userCmd = -1;
        while (userCmd != QUITE) {
            cleanOutput();
            displayMenu();
            try {
                userCmd = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                userCmd = -1; //Invalid value
            }
            switch (userCmd) {
                case 1 -> addRoad();
                case 2 -> deleteRoad();
                case 3 -> openSystemState();
                case QUITE -> quit();
                default -> System.out.println("Incorrect option");
            }
            sc.nextLine();
        }
        quit();
    }

    private static void deleteRoad() {
        if (queue.isEmpty()) {
            System.out.println("queue is empty");
        } else {
            System.out.println(queue.remove(0) + " deleted!");
        }
    }

    private static void addRoad() {
        System.out.print("Input road name:");
        String roadName = sc.nextLine();
        if (queue.size() == roads) {
            System.out.println("Queue is full");
        } else {
            queue.add(roadName);
            System.out.println(roadName + " Added!");
        }
    }

    private static void displayMenu() {
        System.out.println("""
                Menu:
                1. Add
                2. Delete
                3. System
                0. Quit
                """);
    }

    private static void openSystemState() {
        systemState.set(true);
        // Wait for the user to press Enter to exit the system state
        sc.nextLine(); // Wait for Enter
        systemState.set(false);
        showMenu();
    }

    private static void printSystemState() {
        long livedSeconds = Duration.between(startTime, LocalTime.now()).getSeconds();
        System.out.printf("! %ds. have passed since system startup !\n"
                , livedSeconds);
        System.out.printf("! Number of roads: %d !\n", roads);
        System.out.printf("! Interval: %d !\n", interval);
        displayRoadStatuses();
        System.out.println("! Press \"Enter\" to open menu !");
    }

    private static void displayRoadStatuses() {
        long livedSeconds = Duration.between(startTime, LocalTime.now()).getSeconds();
        long openRoadIndex = (livedSeconds / interval) % queue.size();
        for (var i = 0; i < queue.size(); i++) {
            if (openRoadIndex == i) {
                System.out.printf(queue.get(i) + " will be \u001B[32m open for %ds.\u001B[0m\n"
                        , Math.abs((livedSeconds % interval) - interval));
            } else {
                System.out.printf(queue.get(i) + " will be \u001B[31m closed for %ds.\u001B[0m\n"
                        , calculateClosedTime(i, livedSeconds, openRoadIndex, queue.size()));
            }
        }
    }

    private static long calculateClosedTime(int roadIndex, long elapsedTime, long openRoadIndex, int roadsSize) {
        if (roadIndex > openRoadIndex) {
            return (roadIndex - openRoadIndex) * interval - (int) (elapsedTime % interval);
        } else {
            return (roadsSize - openRoadIndex + roadIndex) * interval - (int) (elapsedTime % interval);
        }
    }

    private static void quit() {
        sc.close();
//        t.stop();
        System.out.println("Bye!");
        System.exit(0);
    }
}
