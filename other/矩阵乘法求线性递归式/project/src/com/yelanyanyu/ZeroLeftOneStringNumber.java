package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class ZeroLeftOneStringNumber {
    /*
    对数器
     */
    public static void main(String[] args) {
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = getRandomNum(1, 30);
            int ans1 = getNum1(n);
            int ans2 = getNum2(n);
            if (ans1 != ans2) {
                System.out.println("Opps");
                System.out.println("n= " + n);
                System.out.println("ans1= " + ans1);
                System.out.println("ans2= " + ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + 1 * res[1][0];

    }

    private static int[][] matrixPower(int[][] base, int p) {
        if (base.length != base[0].length) {
            throw new RuntimeException("不是方阵,无法求幂!");
        }
        int[][] m = base;
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, m);
            }
            m = multiMatrix(m, m);
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

    private static int getRandomNum(int minValue, int maxValue) {
        return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
    }

    private static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    private static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }
}
