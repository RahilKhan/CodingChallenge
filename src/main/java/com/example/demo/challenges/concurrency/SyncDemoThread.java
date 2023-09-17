package com.example.demo.challenges.concurrency;

public class SyncDemoThread {
    public static final int lc = 100;

    public static void main(String... args) {
        SyncCounter counter = new SyncCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < lc; i++) {
                counter.incCounter();
                System.out.println(Thread.currentThread().getName() + " : " + counter.getCounter());
            }
        });
        t1.setName("T1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < lc; i++) {
                counter.incCounter();
                System.out.println(Thread.currentThread().getName() + "   : " + counter.getCounter());
            }
        });
        t2.setName("T2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println("counter : " + counter.getCounter());
    }
}
