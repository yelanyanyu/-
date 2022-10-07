package com.yelayanyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class AllLessNumSubArray {
    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length <= 0 || sum < 0) {
            return 0;
        }
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();
        int N = arr.length;
        int count = 0;
        int R = 0;
        for (int L = 0; L < arr.length; L++) {//先固定L，再移动R

            while (R < N) {
                //更新两个双端队列
                while (!maxList.isEmpty() && arr[R] >= arr[maxList.peekLast()]) {
                    maxList.pollLast();
                }
                maxList.addLast(R);

                while (!minList.isEmpty() && arr[R] <= arr[minList.peekLast()]) {
                    minList.pollLast();
                }
                minList.addLast(R);


                Integer max = maxList.peekFirst();
                Integer min = minList.peekFirst();
                if ((arr[max] - arr[min]) > sum) {//R到达预计不满足要求的区域
                    break;
                } else {
                    R++;
                }
            }
            //剔除队列中过期的元素
            if (maxList.peekFirst() == L) {
                maxList.pollFirst();
            }
            if (minList.peekFirst() == L) {
                minList.pollFirst();
            }
            count += R - L;
        }
        return count;
    }

    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }

    /**
     * 给定一个整型数组 arr， 和一个整数 num。某个 arr 中的子数组 sub，如果想达标，必须满足
     * $sub中最大值-sub中最小值<=num$，返回 arr 中达标子数组的数量。
     */
    @Test
    public void Test02() {
        int[] arr = generateRandomArray(10, 100);
        int sum = (int) (Math.random() * (100 + 1));
        System.out.println(right(arr, sum));
        System.out.println(num(arr, sum));
    }

    @Test
    public void Test01() {
        System.out.println(Arrays.toString(generateRandomArray(8, 100)));
    }

}
