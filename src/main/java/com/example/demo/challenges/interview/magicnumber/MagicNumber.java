package com.example.demo.challenges.interview.magicnumber;

/**
 * Indian mathematician D.R. Kaprekar observed a very interesting property of number 6174.
 * * If you take any four digit number (let's say 3281)
 * and arrange its digits in descending order (8321)
 * and ascending order (1238)
 * and then subtract the smaller number from the larger number
 * -- and if you repeat that process, you will reach number 6174.
 * <p>
 * For example:
 * * 3281:
 * 8321 - 1238 = 7083
 * 8730 - 0378 = 8352
 * 8532 - 2358 = 6174
 * <p>
 * Number 6174 - when you do this process, will result into 6174 itself.
 * Exception to this is a four digit number with all same digits (such as 1111, 2222, 3333 etc.), as that will result into 0 in the very first step.
 * <p>
 * Your task is to write a function that finds out that for all four digit numbers (from 1000 to 9999),
 * how many steps does it take to reach the magic number (6174). The function should also find out if it can't reach the number 6174 for whatever reason.
 */

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MagicNumber {

    public static int number;
    public static boolean isValidForMajicNum;

    public static void main(String... args) {
//        int num = 1111;
//        isValidMagicNumber(num);
//
//        num = 3333;
//        isValidMagicNumber(num);
//
//        num = 8321;
//        isValidMagicNumber(num);
//
//        num = 7617;
//        isValidMagicNumber(num);
//
//        num = 6174;
//        isValidMagicNumber(num);
//
//        num = 8352;
//        isValidMagicNumber(num);

        for (int i = 1000; i <= 9999; i++) {
            isValidMagicNumber(i);
        }
    }

    public static void isValidMagicNumber(int number) {
        if (number == 0) {
            log.info("Not valid for majic number : {}", number);
        }
        isValidForMajicNum = true;
        for (int i = 1; i < 10; i++) {
            if (number / i == 1111) {
                log.info("Not valid for majic number : {}", number);
                isValidForMajicNum = false;
                break;
            }
        }
        if (!isValidForMajicNum) return;

        log.info("{} is valid Majic number : {}", number, backtrack(number));
    }

    public static boolean backtrack(int number) {
        log.info("\t number : {}", number);
        int numAsc = 0;
        int numDsc = 0;
        int tempNum = 0;
        isValidForMajicNum = false;

        if (number == 6174) return isValidForMajicNum = true;
        if (number < 6174) return isValidForMajicNum = false;

        List<Integer> numLs = new ArrayList<>();
        while (number > 0) {
            numLs.add(number % 10);
            number /= 10;
        }
        numLs = numLs.stream().sorted().toList();

        for (int i = 0; i < numLs.size(); i++) {
            numAsc = numAsc * 10 + numLs.get(i);
            numDsc = numDsc * 10 + numLs.get(numLs.size() - i - 1);
        }

        tempNum = numDsc - numAsc;
        backtrack(tempNum);
//        log.info("\t number : {}, numDsc : {} :: numAsc : {}, tempNum : {} ", number, numDsc, numAsc, tempNum);
        return isValidForMajicNum;
    }


}
