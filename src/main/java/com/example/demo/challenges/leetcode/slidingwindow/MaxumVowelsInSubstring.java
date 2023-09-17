package com.example.demo.challenges.leetcode.slidingwindow;

/**
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * <p>
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 */

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class MaxumVowelsInSubstring {

    public static void main(String... args) {
        String str = "abciiidef";
        int k = 3;
        log.info("maxVowels(\"{}\") & k = {} -> {}", str, k, maxVowels(str, k));

        str = "aeiou";
        k = 2;
        log.info("maxVowels(\"{}\") & k = {} -> {}", str, k, maxVowels(str, k));

        str = "weallloveyou";
        k = 7;
        log.info("maxVowels(\"{}\") & k = {} -> {}", str, k, maxVowels(str, k));

        str = "novowels";
        k = 1;
        log.info("maxVowels(\"{}\") & k = {} -> {}", str, k, maxVowels(str, k));

    }

    /**
     * Use of ArrayList for vowels & Sytem.gc() reduses memory consumption by 0.5MB(42MB - 41MB)
     * Inc Runtime by 33ms(46m -13ms)
     * Beats 100% in memory consumption with only 0.5MB of  but saving.
     * Not worth it.
     */
    public static int maxVowels(String s, int k) {
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u','A', 'E', 'I', 'O', 'U');
        int sum = 0;
        char[] chr = s.toCharArray();
        for(int i = 0 ;  i < k; i++){
            sum = vowels.contains(chr[i]) ? ++sum : sum;
        }
        int max = sum;

        for(int i = 1; i <= chr.length - k; i++){
            sum = vowels.contains(chr[i + k - 1]) ? ++sum : sum;
            sum = vowels.contains(chr[i - 1]) ? --sum : sum;
            max = Math.max(sum, max);
        }
        System.gc();
        return max;
    }

    /**
     * Optimal approach.
     * Use of System.gc() reduces memory consumption and uses tertiary operator which is clean.
     * Runtime : 11ms
     * Memory : 42MB
     */
    public static int maxVowels2(String s, int k) {
        int sum = 0;
        char[] chr = s.toCharArray();
        for(int i = 0 ;  i < k; i++){
            sum = isVowel(chr[i]) ? ++sum : sum;
        }
        int max = sum;

        for(int i = 1; i <= chr.length - k; i++){
            sum = isVowel(chr[i + k - 1]) ? ++sum : sum;
            sum = isVowel(chr[i - 1]) ? --sum : sum;
            max = Math.max(sum, max);
        }
        System.gc();
        return max;
    }
    public static boolean isVowel(char chr){
        return chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u' ||
                chr == 'A' || chr == 'E' || chr == 'I' || chr == 'O' || chr == 'U';
    }
}
