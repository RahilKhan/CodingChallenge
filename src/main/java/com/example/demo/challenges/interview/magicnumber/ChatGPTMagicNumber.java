package com.example.demo.challenges.interview.magicnumber;

import java.util.Arrays;

public class ChatGPTMagicNumber {

    public static void main(String[] args) {
        for (int i = 1000; i <= 9999; i++) {
            int steps = countSteps(i);
            if (steps >= 0) {
                System.out.printf("Number %d took %d steps to reach 6174\n", i, steps);
            } else {
                System.out.printf("Number %d cannot reach 6174\n", i);
            }
        }
    }

    public static int countSteps(int num) {
        int steps = 0;
        while (num != 6174) {
            int[] digits = new int[4];
            digits[0] = num / 1000;
            digits[1] = (num % 1000) / 100;
            digits[2] = (num % 100) / 10;
            digits[3] = num % 10;
            Arrays.sort(digits); // sort in ascending order
            int ascending = digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3]; // sort in ascending order
            Arrays.sort(digits); // sort in descending order
            int descending = digits[3] * 1000 + digits[2] * 100 + digits[1] * 10 + digits[0]; // sort in descending order
            num = descending - ascending;
            if (num == 0) {
                return -1; // cannot reach the magic number
            }
            steps++;
        }
        return steps;
    }
}