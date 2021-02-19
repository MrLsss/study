package com.study;

import java.util.Stack;

/**
 * 题目：整数反转
 *
 * 需求：给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 示例 1: 输入: 123 输出: 321
 *
 * 示例 2: 输入: -123 输出: -321
 *
 * 示例 3: 输入: 120 输出: 21
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class No7_ReverseInteger {

    public static void main(String[] args) {
        int res = reverse(-123);
        System.out.println(res);
    }

    public static int reverse(int x) {
        Stack<Character> s = new Stack<>();
        String num = x + "";
        Character[] chars = new Character[num.length()];
        int length = num.length();
        for (int i = 0; i < length; i++) {
            s.push(num.charAt(i));
        }
        for (int i = 0; i < length; i++) {
            chars[i] = s.pop();
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if ('-' == (chars[i])) {
                res.insert(0, chars[i]);
                continue;
            }
            res.append(chars[i]);
        }
        try {
            return Integer.parseInt(res.toString());
        } catch (Exception e) {
            return 0;
        }
    }

}
