package com.example.demo.challenges.leetcode.combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * Example 2:
 * Input: digits = ""
 * Output: []
 * <p>
 * Example 3:
 * Input: digits = "2"
 */
public class LetterCombinations {

    private Map<String, String> digitToAlphaMap;

    public static void main(String... args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        String digits = "23";
        List<String> combinations = null;
        combinations = letterCombinations(digits);
        System.out.println("combinations : " + combinations);

        digits = "";
        combinations = letterCombinations(digits);
        System.out.println("combinations : " + combinations);

        digits = "2";
        combinations = letterCombinations(digits);
        System.out.println("combinations : " + combinations);

        digits = "234";
        combinations = letterCombinations(digits);
        System.out.println("combinations : " + combinations);

    }

    public static List<String> letterCombinations(String digits) {
        System.out.println("digits : " + digits);

        List<String> combinationStr = new ArrayList<>();
        Map<Character, String> digitToAlphaMap = new HashMap<>();
        digitToAlphaMap.put('2', "abc");
        digitToAlphaMap.put('3', "def");
        digitToAlphaMap.put('4', "ghi");
        digitToAlphaMap.put('5', "jkl");
        digitToAlphaMap.put('6', "mno");
        digitToAlphaMap.put('7', "pqrs");
        digitToAlphaMap.put('8', "tuv");
        digitToAlphaMap.put('9', "wxyz");
        System.out.println("digitToAlphaMap : " + digitToAlphaMap);

        if (digits.isBlank()) {
            return combinationStr;
        }
        if (digits.length() == 1) {
            char[] cArr = digitToAlphaMap.get(digits.charAt(0)).toCharArray();

            for (int i = 0; i < cArr.length; i++) {
                combinationStr.add(cArr[i] + "");
            }
            return combinationStr;
        }

        char[] cArr = digits.toCharArray();
        char[] cArr1 = digitToAlphaMap.get(cArr[0]).toCharArray();
        char[] cArr2 = digitToAlphaMap.get(cArr[1]).toCharArray();
        for (int i = 0; i < cArr1.length; i++) {
            for (int j = 0; j < cArr2.length; j++) {
                String combStr = cArr1[i] + "" + cArr2[j]; //Arrays.toString(cArr1[i]). + cArr2[j]);
                combinationStr.add(combStr);
            }
        }
        return combinationStr;
    }

}
