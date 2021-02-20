package com.study.sort;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int[] arr = new int[30];
        for (int i = 0; i < 30; i++) {
            arr[i] = (int) (Math.random() *100);
        }
        System.out.println("原始数组:" + Arrays.toString(arr));
        long start = System.currentTimeMillis();
//        SelectSort.selectSort(arr); // 选择排序
        QuickSort.quickSort(arr, 0, arr.length - 1); // 快速排序
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }

}
