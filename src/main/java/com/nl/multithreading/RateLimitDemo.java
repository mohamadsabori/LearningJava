package com.nl.multithreading;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RateLimitDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        List<Future<String>> futureList = new ArrayList<>();
        final int TASKS = 250;
        for (var i = 0; i < TASKS; i++)
            futureList.add(executorService.submit(() -> get("https://horstmann.com/random/word")));
        for (Future<String> stringFuture : futureList) {
            System.out.println(stringFuture.get());
        }
        executorService.close();
    }

    private static HttpClient client = HttpClient.newHttpClient();
    private static final Semaphore SEMAPHORE = new Semaphore(20);

    private static String get(String url) {
        try {
            var request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            SEMAPHORE.acquire();
            try {
                Thread.sleep(100);
                return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            } finally {
                SEMAPHORE.release();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            var rex = new RuntimeException();
            rex.initCause(ex);
            throw rex;
        }
    }
}
