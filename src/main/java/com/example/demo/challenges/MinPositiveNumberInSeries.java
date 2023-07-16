package com.example.demo.challenges;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Find the smallest positive integer that does not occur in a given sequence.
 * 0 is to be excluded.
 *
 * eg.
 * {1, 3, 5} -> 2
 * {-1, -3, -2, 0} -> 1
 * {-1, -3, -2, 0, 2} -> 1
 * {1, 2, 3, 4, 6,9} -> 5
 */

@Slf4j
public class MinPositiveNumberInSeries {

    public static void main(String... args) {
        int[] A = {1, 2, 3, 4, 6, 9};
        findMinNo(A);
        findMinNo(new int[]{1, 3, 5});
        findMinNo(new int[]{-1, -3, -2, 0});
        findMinNo(new int[]{-1, -3, -2, 0, 2});
        findMinNo(new int[]{-1, -3, -2});
        findMinNo(new int[]{-1, -3, 2});
        findMinNo(new int[]{-1, -3, 1});
        findMinNo(new int[]{-1, -3, 5});

    }

    public static void findMinNo(int[] A) {
        Set<Integer> intSet = Arrays.stream(A).boxed().collect(Collectors.toSet());
        List<Integer> numList = intSet.stream().sorted().toList();

        int minNum = 0;
        if (numList.get(numList.size() - 1) == 1) {
            minNum = 2;
            log.info("Array -> {} :: minNum: {}", A, minNum);
            return;
        }

        for (int i = 0; i < numList.size(); i++) {
            if (numList.get(i) < 0)
                minNum = 1;
            else if (numList.get(i) > 0) {
                if (!numList.contains(1))
                    minNum = 1;
                else {
                    int loopNum = numList.get(i);
                    if (loopNum > 1 && !numList.contains(loopNum - 1)) {
                        if (minNum == 0)
                            minNum = loopNum - 1;
                        else if (minNum > loopNum - 1)
                            minNum = loopNum - 1;
                    }
                }
            }
        }
        log.info("Array -> {} :: minNum: {}", A, minNum);

    }

}
