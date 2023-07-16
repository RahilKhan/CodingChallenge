package com.example.demo.challenges.codility;

/**
 *

 This is a demo task.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

 For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

 Given A = [1, 2, 3], the function should return 4.

 Given A = [−1, −3], the function should return 1.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [−1,000,000..1,000,000].

 Copyright 2009–2023 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
public class Solution {
// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");


    public static int solution(int[] A) {
        // Implement your solution here
        int smallestNo = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - 1; j++) {
                if (A[i] > A[i + 1]) {
                    smallestNo = A[i + 1];
                    A[i + 1] = A[i];
                    A[i] = smallestNo;
                } else {
                    break;
                }
            }
        }

        smallestNo = 0;
        for (int num : A) {
            if (num <= 0) {
                continue;
            } else {
                smallestNo = num;
                return smallestNo;
            }

        }

        return smallestNo;
    }

    public static void main(String... args) {
        int[] A = {1, 3, 0, 2, -1, -2};
        System.out.println(solution(A));
    }

}
