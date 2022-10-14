package com.yelanyanyu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class TwoBagsofPotatoes_CF239A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int[] ways = ways(y, k, n);
        printArray(ways);
    }

    public static int[] ways(int y, int k, int n) {
        int maxX = n - y;
        int[] res = new int[maxX];
        int index = 0;
        boolean flag = false;
        for (int x = 1; x < maxX; x++) {
            if ((x + y) % k == 0) {
                res[index++] = x;
                flag = true;
            }
        }
        if (!flag) {
            return new int[]{-1};
        }
        return res;
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length && arr[i] != 0; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
