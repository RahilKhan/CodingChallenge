package com.example.demo.challenges.leetcode.combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {

    private static String[] arr = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        String digits = "23";
        List<String> combinations = letterCombinations(digits);
        System.out.println("Combinations: " + combinations);


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
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder temp, String digits, int index) { //Map<Character, String> map,
        System.out.println("1. index : " + index + " -- digits : " + digits + " -- temp : " + temp + " -- result : " + result);
        if (index == digits.length()) {
            result.add(temp.toString());
            return;
        }
        int digit = Integer.parseInt(String.valueOf(digits.charAt(index)));
        String letters = arr[digit];
        for (char letter : letters.toCharArray()) {
            temp.append(letter);
            backtrack(result, temp, digits, index + 1);
            System.out.println("\t 2. index : " + index + " -- digits : " + digits + " -- temp : " + temp + " -- result : " + result);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public List<String> letterCombinations1(String digits) {
        String[] arr = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        rec(0, digits, ans, arr, sb);
        return ans;
    }

    public void rec(int i, String digits, List<String> ans, String[] arr, StringBuilder sb) {
        if (i == digits.length()) {
            if (sb.length() > 0)
                ans.add(sb.toString());
            return;
        }

        /**
         * https://stackoverflow.com/questions/12438336/why-we-use-0-beside-charati
         * string.charAt(i) - '0'converts it to an integer.
         * Terrible code. It's slightly arcane, only works with ASCII and is much harder to parse mentally.
         * Better to Integer.parseInt()
         */
        int digit = digits.charAt(i) - '0';
        int len = arr[digit].length();
        for (int j = 0; j < len; j++) {
            sb.append(arr[digit].charAt(j));
            rec(i + 1, digits, ans, arr, sb);
            sb.setLength(sb.length() - 1);
        }
    }

}