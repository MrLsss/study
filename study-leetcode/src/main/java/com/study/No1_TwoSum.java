package com.study;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 题目：两数之和
 *
 * 需求：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class No1_TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> sum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int target1 = target - nums[i];
            if (sum.containsKey(target1) && sum.get(target1) != i) {
                return new int[]{i, sum.get(target1)};
            }
        }
        throw new IllegalArgumentException("参数传错了");
    }

}
