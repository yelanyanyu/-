package com.yelanyanyu;

import java.util.Arrays;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class AllTimesMinToMax {

    //暴力方法
    private static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    private static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            int ans1 = max1(arr);
            int ans2 = max2(arr);
            if (ans1 != ans2) {
                System.out.println("FUCK!");
                System.out.println("arr: " + Arrays.toString(arr));
                System.out.println("ans1= " + ans1);
                System.out.println("ans2= " + ans2);
                break;
            }
        }
        System.out.println("test finish");
    }

    private static int max2(int[] arr) {
        // TODO: 2022/10/20
        return 0;
    }
}
