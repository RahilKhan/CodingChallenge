package com.example.demo.challenges.leetcode.slidingwindow;

import lombok.extern.slf4j.Slf4j;


@Slf4j
class MaximumArraySubarray {

    public static void main(String... args) {

        //        int[] n1 = new int[5];
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;

        log.info("max : {}", findMaxAverage(nums, k));

        nums = new int[]{0, 4, 0, 3, 2};
        k = 1;
        log.info("max : {}", findMaxAverage(nums, k));

    }

    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        log.info("\n\nmax - initial : {}", max);

        for (int i = 1; i <= nums.length - k; i++) {
            sum += nums[i + k - 1] - nums[i - 1];
            max = Math.max(sum, max);
        }

        /**
         * Reduces memory consumption but increases Runtime
         *   Memory : 51.54ms
         *   Runtime: 5ms
         * without System.gc()
         *   Memory : 54ms
         *   Runtime: 2ms
         * */
        System.gc();
        return (double) max / k;
    }
}