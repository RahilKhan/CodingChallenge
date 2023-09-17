package com.example.demo.challenges.concurrency;

public class SyncDemoRunnable {

    public static final int lc = 100;
    public static void main(String... args) throws InterruptedException {
        SyncCounter counter = new SyncCounter(0);
        SyncDemoRunnable sdemo = new SyncDemoRunnable();
//        Runnable r1 = sdemo.incr1(counter);
        Runnable r1 = () -> {
            for (int i = 0; i < lc; i++) {
                counter.incCounter();
                System.out.println("incr1: " + counter.getCounter());
            }
        };
        Runnable r2 = sdemo.incr2(counter);;

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Counter: " + counter.getCounter());
    }

    public Runnable incr1(SyncCounter counter) {
        for (int i = 0; i < lc; i++) {
            counter.incCounter();
            System.out.println("incr1: " + counter.getCounter());
        }
        return null;
    }

    public Runnable incr2(SyncCounter counter) {
        for (int i = 0; i < lc; i++) {
            counter.incCounter();
            System.out.println("incr2: " + counter.getCounter());
        }
        return null;
    }
}
