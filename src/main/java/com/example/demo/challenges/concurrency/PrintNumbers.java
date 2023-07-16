package com.example.demo.challenges.concurrency;

public class PrintNumbers {

    private int number = 0;
    private int maxNumbers = 30;

    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        Runnable r1 = () -> printNumbers.printNumber(0);
        Runnable r2 = () -> printNumbers.printNumber(1);
        Runnable r3 = () -> printNumbers.printNumber(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t1.start();
        t2.start();
        t3.start();
    }

    public synchronized void printNumber(int threadId) {
        while (number < maxNumbers) {
            while (number % 3 != threadId) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread-" + threadId + ": " + number++);
            notifyAll();
        }
    }
}