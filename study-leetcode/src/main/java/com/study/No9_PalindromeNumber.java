package com.study;

/**
 * 题目：回文数
 *
 * 需求：判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01。因此它不是一个回文数。
 *
 * TODO：进阶：你能不将整数转为字符串来解决这个问题吗？
 */
public class No9_PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(123321));
    }

    /**
     * 转为字符串的方式
     */
    public static boolean isPalindrome(int x) {
        boolean flag = true;
        String str = x + "";
        int length = str.length();
        Character[] c = new Character[length];
        for (int i = 0; i < length; i++) {
            c[i] = str.charAt(i);
        }
        for (int i = 0; i < c.length; i++) {
            if (!c[i].equals(c[c.length - 1 - i])) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
