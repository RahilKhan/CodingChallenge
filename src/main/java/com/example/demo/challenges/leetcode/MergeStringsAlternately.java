package com.example.demo.challenges.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1768. Merge Strings Alternately
 * You are given two strings word1 and word2.
 * Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 * Return the merged string.
 * <p>
 * Example 1:
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 * <p>
 * Example 2:
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 * <p>
 * Example 3:
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 * <p>
 * Constraints:
 * 1 <= word1.length, word2.length <= 100
 * word1 and word2 consist of lowercase English letters.
 */
@Slf4j
public class MergeStringsAlternately {
    public static void main(String... args) {

        List<String> word1List = new ArrayList<>();
        word1List.add("abc");
        word1List.add("a");
        word1List.add("abc");
        word1List.add("abcd");
        word1List.add("ef");
        word1List.add("");
        word1List.add("wert");
        word1List.add("");

        List<String> word2List = new ArrayList<>();
        word2List.add("d");
        word2List.add("def");
        word2List.add("def");
        word2List.add("ef");
        word2List.add("abcd");
        word2List.add("abcd");
        word2List.add("");
        word2List.add("");

        for (int i = 0; i < word1List.size(); i++) {
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternately(word1List.get(i), word2List.get(i)));
        }

        for (int i = 0; i < word1List.size(); i++) {
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
            log.info("{} + {} => {}", word1List.get(i), word2List.get(i), mergeAlternatelyOptimzed(word1List.get(i), word2List.get(i)));
        }

    }

    public static String mergeAlternatelyOptimzed(String word1, String word2) {
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length())
                sb.append(word1.charAt(i++));
            if (j < word2.length())
                sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }

    public static String mergeAlternately(String word1, String word2) {

        if (word1.isBlank() && !word2.isBlank())
            return word2;
        else if (!word1.isBlank() && word2.isBlank())
            return word1;
        else if (word1.isBlank() && word2.isBlank())
            return "";

        char[] ltArray = word1.toCharArray();
        char[] rtArray = word2.toCharArray();
        char[] temp = new char[word1.length() + word2.length()];

        int k = ltArray.length;
        if (ltArray.length < rtArray.length) {
            k = ltArray.length;
        } else if (rtArray.length < ltArray.length) {
            k = rtArray.length;
        }

        for (int i = 0, j = 0; i < k; i++, j += 2) {
            temp[j] = ltArray[i];
            temp[j + 1] = rtArray[i];
        }

        int index = k * 2;
        if (ltArray.length > rtArray.length) {
            while (index > 0 && index < temp.length) {
                temp[index] = ltArray[k];
                index++;
                k++;
            }
        }

        if (rtArray.length > ltArray.length) {
            while (index > 0 && index < temp.length) {
                temp[index] = rtArray[k];
                index++;
                k++;
            }
        }

        return new String(temp);
    }

}
