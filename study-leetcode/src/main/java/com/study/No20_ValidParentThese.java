package com.study;

import java.util.Stack;

/**
 * 题目：有效的括号
 *
 * 需求：
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 *
 * 示例1: 输入: "()" 输出:true
 *
 * 示例2: 输入: "()[]{}" 输出: true
 *
 * 示例3: 输入: "(]" 输出: false
 *
 * 示例4: 输入: "([)]" 输出: false
 *
 * 示例5: 输入: "{[]}" 输出: true
 *
 */
public class No20_ValidParentThese {

    public static void main(String[] args) {
        System.out.println(isValid("{{]}}"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c || '[' == c || '{' == c) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character top = stack.pop();
                    if (')' == c && top != '(') {
                        return false;
                    }
                    if (']' == c && top != '[') {
                        return false;
                    }
                    if ('}' == c && top != '{') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

}
