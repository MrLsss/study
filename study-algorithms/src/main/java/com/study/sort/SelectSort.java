package com.study.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 原理：
 * <p>
 *     首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 *     再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *     重复第二步，直到所有元素均排序完毕。
 * </p>
 * 时间复杂度：O(n²)
 * 应用场景：数据规模越小越好。唯一的好处可能就是不占用额外的内存空间。
 */
public class SelectSort {

    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        int length = arr.length;
        // 总共需要进行n-1轮比较（因为第一次就是从第一个开始的，所以第一个不需要算在里面）
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            // 每轮需要比较的次数为 n-i
            for (int j = i + 1; j < length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
