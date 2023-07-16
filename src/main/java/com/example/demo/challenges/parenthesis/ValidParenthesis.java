package com.example.demo.challenges.parenthesis;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;


/**
 * https://leetcode.com/problems/valid-parentheses/
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Example 1:
 * Input: s = "()"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * <p>
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */
@Slf4j
public class ValidParenthesis {

    public static void main(String... args) {
        String validStr = "({()})[]{}({})()";
        log.info("Parenthesis string '{}' is valid : {}", validStr, isValid(validStr));

        String invalidStr = "({()})[{}({})()";
        log.info("Parenthesis string '{}' is valid : {}", invalidStr, isValid(invalidStr));

        String parenthesisStr = "(>}";
        log.info("Parenthesis string '{}' is valid : {}", parenthesisStr, isValid(parenthesisStr));

        parenthesisStr = "()[]{}";
        log.info("Parenthesis string '{}' is valid : {}", parenthesisStr, isValid(parenthesisStr));

        parenthesisStr = "(]";
        log.info("Parenthesis string '{}' is valid : {}", parenthesisStr, isValid(parenthesisStr));

        parenthesisStr = ")(<>{}][";
        log.info("Parenthesis string '{}' is valid : {}", parenthesisStr, isValid(parenthesisStr));

        parenthesisStr = "()[]^{}";
        log.info("Parenthesis string '{}' is valid : {}", parenthesisStr, isValid(parenthesisStr));

    }

    public static boolean isValid(String s) {
        boolean isValid = false;

        Stack stack = new Stack();
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (isOpenBracket(charArray[i])) {
                stack.push(charArray[i]);
            } else if (isClosingBracket(charArray[i])) {
                /* Condition to check if closing bracket arrives and stack is empty -> invalid str */
                if (stack.isEmpty())
                    return false;
                char stackedChar = (char) stack.pop();
                if (!isValidPair(stackedChar, charArray[i])) {
                    break;
                } else {
                    isValid = true;
                }
            } else {
                /* illegal character */
                return false;
            }
            if (!stack.isEmpty())
                isValid = false;
        }

        return isValid;
    }

    private static boolean isValidPair(char stackedChar, char strChar) {
//        log.info("stackedChar- {} :: strChar- {}", stackedChar, strChar);
        boolean isValid;
        switch (strChar) {
            case '}':
                isValid = stackedChar == '{' ? true : false;
                break;
            case ')':
                isValid = stackedChar == '(' ? true : false;
                break;
            case ']':
                isValid = stackedChar == '[' ? true : false;
                break;
            case '>':
                isValid = stackedChar == '<' ? true : false;
                break;
            default:
                log.info("Unrecognized character");
                return false;
        }

        return isValid;
    }

    public static boolean isOpenBracket(char chr) {
        if (chr == '{' || chr == '(' || chr == '[' || chr == '<')
            return true;
        return false;
    }

    public static boolean isClosingBracket(char chr) {
        if (chr == '}' || chr == ')' || chr == ']' || chr == '>')
            return true;
        return false;
    }


}
