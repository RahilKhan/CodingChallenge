package com.example.demo.challenges.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintOddEvenNumExecutor {

    private static int num = 0;
    private final static int maxNum = 31;

    public static void main(String... args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable oddRunner = () -> {
            while (num < maxNum) {
                while (num % 2 == 0) {
//                    Thread.yield();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("ThreadOdd  :   " + num++);
            }
        };

        Runnable evenRunner = () -> {
            while (num < maxNum) {
                while (num % 2 != 0) {
//                    Thread.yield();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("ThreadEven : " + num++);
            }
        };

        executorService.submit(evenRunner);
        executorService.submit(oddRunner);

        executorService.shutdown();
    }
}
