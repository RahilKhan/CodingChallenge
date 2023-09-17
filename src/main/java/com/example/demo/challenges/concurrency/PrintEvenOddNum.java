package com.example.demo.challenges.concurrency;

/**
 * Write a code piece using threading where the output should be 1,2,3,4,5,6,7,8,9,10.
 * 2 threads:
 * one thread print all odd numbers
 * another print all even numbers
 */
public class PrintEvenOddNum {
    private int number = 0;
    private final int maxNumbers = 31;

    public static void main(String... args) {
        PrintEvenOddNum pnum = new PrintEvenOddNum();
        Runnable rEven = pnum::printEvenNum;
        Runnable rOdd = pnum::printOddNum;

        Thread t1 = new Thread(rEven);
        Thread t2 = new Thread(rOdd);

        t1.start();
        t2.start();
    }


    public synchronized void printEvenNum() {
        while (number < maxNumbers) {
            while (number % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            System.out.println("ThreadEven : " + number++);
            notifyAll();
        }
    }

    public synchronized void printOddNum() {
        while (number < maxNumbers) {
            while (number % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            System.out.println("ThreadOdd  :   " + number++);
            notifyAll();
        }
    }

}

