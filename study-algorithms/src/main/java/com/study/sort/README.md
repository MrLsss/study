# 排序算法相关

## 快速排序
### 原理
从数列中挑出一个元素，称为 "基准"（pivot）<br>
重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。<br>
在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；<br>
递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；

### 时间复杂度
最坏运行情况是 O(n²)，平摊期望时间是 O(nlogn)

### java实现
```java
/**
     * 快速排序
     *
     * @param arr 数组
     * @param l   左边起点
     * @param r   右边起点
     */
    public static void quickSort(int[] arr, int l, int r) {
        if (l > r) {
            return;
        }

        int left = l;
        int right = r;
        // 取数组最左侧的值为基准
        int pivot = arr[left];

        while (left < right) {
            /**
             * 先从右边开始移动
             */

            // right从从右向左移动，当遇到比pivot小的值的时候停止
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            // 此时，right指向的值比pivot小，将该值放置到pivot左边，即left位置，而现在left指向的是最左侧的值，已经赋值给pivot，不会丢失数据
            if (left < right) {
                arr[left] = arr[right];
            }

            // right停止后，left开始从左向右移动，当遇到比pivot大的值的时候停止
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 此时left指向的值比pivot大，将该值放置到pivot右边，即right位置，而现在right位置的值已经赋值给left位置了，不会丢失数据
            if (left < right) {
                arr[right] = arr[left];
            }

            // 此时left和right重合，所以将此时的pivot放置在此处，此时pivot左边的都比pivot小，右边的都比pivot大
            if (left >= right) {
                arr[left] = pivot;// arr[right] = pivot 等价
            }
        }
        // 对arr pivot左边的重复
        quickSort(arr, l, left - 1);
        // 对arr pivot右边的重复
        quickSort(arr, left + 1, r);
        System.out.println(Arrays.toString(arr));
    }
```
> 参考视频：https://www.bilibili.com/video/BV1at411T75o?from=search&seid=1095845796031808176


## 选择排序
### 原理
首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。<br>
再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。<br>
重复第二步，直到所有元素均排序完毕。

### 时间复杂度
O(n²)

### 应用场景
数据规模越小越好。唯一的好处可能就是不占用额外的内存空间。

### java实现
```java
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
```