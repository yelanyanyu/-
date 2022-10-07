package com.yelayanyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class SlidingWindowMaxArray {
    /**
     * 假设一个固定大小为 W 的窗口，依次划过 arr，返回每一次滑出状况的最大值。
     * 例如，arr=[4, 3, 5, 4, 3, 3, 6, 7]，W=3
     * 返回：[5, 5, 5, 4, 6, 7]
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr.length <= 0 || arr == null || w <= 0) {
            return null;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int[] res = new int[arr.length];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            //插入元素
            while (!list.isEmpty() && arr[R] >= arr[list.peekLast()]) {
                list.pollLast();
            }
            list.addLast(R);
            //弹出队列中无效参数
            if (list.peekFirst() <= R - w) {
                list.pollFirst();
            }
            //判断窗口是否已满
            if (R >= w - 1) {
                res[index++] = arr[list.peekFirst()];
            }
        }
        return Arrays.copyOf(res, index);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 10;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
                System.out.println("w= " + w + ", origin = " + Arrays.toString(arr));
                System.out.println("ans1= " + Arrays.toString(ans1));
                System.out.println("ans2= " + Arrays.toString(ans2));
            }
//            System.out.println(Arrays.toString(ans1));
//            System.out.println(Arrays.toString(ans2));
        }
        System.out.println("test finish");
    }

    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    @Test
    public void Test01() {
        int[] arr = {21, 5, 57, 89, 32, 62, 49, 91, 56};
        int w = 9;
        int[] maxWindow = getMaxWindow(arr, w);
        System.out.println(Arrays.toString(maxWindow));
    }
}
