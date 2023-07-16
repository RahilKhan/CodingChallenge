package com.example.demo.challenges.leetcode.combination;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/combination-sum/
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * The test cases are generated such that the number of unique combinations that sum up to target is less
 * than 150 combinations for the given input.
 * <p>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 * <p>
 * Constraints:
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 */

@Slf4j
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        candidates = IntStream.of(candidates).distinct().filter(cd -> cd > 0).toArray();// filter out duplicate and negative numbers
        Arrays.sort(candidates); //
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int start) {
        log.info("candidates : {} -- target : {} -- start : {} -- temp : {}", candidates, target, start, temp);
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(temp)); // add a valid combination to the result
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]) {
                break; // no further numbers need to be checked if the target is less than the current number
            }
            temp.add(candidates[i]); // add the current number in the temp list
            backtrack(result, temp, candidates, target - candidates[i], i); // call the backtrack function recursively with reduced target and starting position as i
            temp.remove(temp.size() - 1); // remove the last element, backtrack
            log.info("<---candidates : {} -- target : {} -- start : {} -- temp : {}", candidates, target, start, temp);
        }


    }

    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();
        int[] candidates = {2, 2, 3, 6, 7};
        int target = 7;
        System.out.println("{2, 3, 6, 7} : " + obj.combinationSum(candidates, target)); // Expected output: [[2, 2, 3], [7]]
        candidates = new int[]{6, 7, 2, 3};
        System.out.println("{6, 7, 2, 3} : " + obj.combinationSum(candidates, target));
        candidates = new int[]{6, 2, 7, 8, 6, 2, 3, 9, 8, 6, 3};
        System.out.println("{6, 2, 7, 8, 6, 2, 3, 9, 8, 6, 3} : " + obj.combinationSum(candidates, target));
        candidates = new int[]{8, 3, 2, 9, 2, 6, 2, 8, 3, 2, 9};
        System.out.println("{ 2, 2, 3, 3, 6, 6, 6, 7, 8, 8, 9} : " + obj.combinationSum(candidates, target));
        candidates = new int[]{8, 3, 2, 9, 2, 6, 2, 8, 3, 2, 9};
        System.out.println("{8, 3, 2, 9, 2, 6, 2, 8, 3, 2, 9} : " + obj.combinationSum(candidates, target));
        candidates = new int[]{-2, 6, 2, -8, 9, -3, 13, 1, 2, 6};
        System.out.println("{-2, 6, 2, -8, 9, -3, 13, 1, 2, 6} : " + obj.combinationSum(candidates, target));
        candidates = new int[]{-2, -3, 0, 1, 2, 6, 5, -8, 9, 13, 1, 2, 6};
        target = 9;
        System.out.println("{-2, -3, 0, 1, 2, 6, 5, -8, 9, 13, 1, 2, 6} : " + obj.combinationSum(candidates, target));
    }

}
