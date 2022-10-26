package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class FibonacciProblem {
    /*
    对数器
     */
    public static void main(String[] args) {
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = getRandomNum(1, 30);
            int ans1 = f1(n);
            int ans2 = f2(n);
            if (ans1 != ans2) {
                System.out.println("Opps!");
                System.out.println("n= " + n);
                System.out.println("ans1= " + ans1);
                System.out.println("ans2= " + ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 非暴力方法,O(logN)
     *
     * @param n
     * @return
     */
    private static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return 1 * res[0][0] + 1 * res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int p) {
        int row = base.length;
        int col = base[0].length;
        if (row != col) {
            throw new RuntimeException("不是方阵,无法求幂!");
        }
        int[][] res = new int[row][col];
        /*
        置res的初始值为单位矩阵
         */
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] M = base;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) { //最后一位为1的时候，加入条件
                res = multiMatrix(res, M);
            }
            //M^1,M^2,M^4,M^8,...
            M = multiMatrix(M, M);
        }
        return res;
    }

    private static int[][] multiMatrix(int[][] m1, int[][] m2) {
        if (m2.length != m1[0].length) {
            throw new RuntimeException("矩阵乘法错误!");
        }
        int row = m1.length;
        int col = m2[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 暴力方法,O(N)
     *
     * @param n
     * @return
     */
    private static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /*
    for test
     */
    private static int getRandomNum(int minValue, int maxValue) {
        return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
    }

    @Test
    public void Test01() {
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] base1 = {
                {1, 1},
                {1, 0}
        };
        int[][] res = multiMatrix(base1, base);
        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }
}
