package com.dp;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class Horse {
    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(horse(x, y, step));
        System.out.println(DP(x, y, step));
        System.out.println(waysdp(x, y, step));

//        System.out.println(jump(x, y, step));
    }

    /**
     * 主函数
     *
     * @param x 目标位置横坐标
     * @param y 目标位置纵坐标
     * @param k 总步数
     * @return 方法数
     */
    public static int horse(int x, int y, int k) {
        return process(0, 0, k, x, y);
    }

    /**
     * 递归函数
     *
     * @param a    马当前位置的横坐标
     * @param b    马当前位置的纵坐标
     * @param rest 剩余步数
     * @param x    目标位置横坐标
     * @param y    目标位置纵坐标
     * @return 从(a, b)开始到(x, y)，在剩余步数为rest的情况下的方法数
     */
    public static int process(int a, int b, int rest, int x, int y) {
        //边界条件1
        if (a < 0 || b < 0 || a > 9 || b > 8) {
            return 0;
        }
        //边界条件2
        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }
        int ways = 0;
        ways += process(a + 1, b + 2, rest - 1, x, y);
        ways += process(a + 1, b - 2, rest - 1, x, y);
        ways += process(a - 1, b + 2, rest - 1, x, y);
        ways += process(a - 1, b - 2, rest - 1, x, y);
        ways += process(a + 2, b + 1, rest - 1, x, y);
        ways += process(a + 2, b - 1, rest - 1, x, y);
        ways += process(a - 2, b + 1, rest - 1, x, y);
        ways += process(a - 2, b - 1, rest - 1, x, y);
        return ways;
    }

    /**
     * 动态规划版
     *
     * @param x 0~9
     * @param y 0~8
     * @param k 0~k
     * @return
     */
    public static int DP(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[x][y][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 9; b++) {
                    int ways = 0;
                    ways += pick(dp, a + 1, b + 2, rest - 1);
                    ways += pick(dp, a + 1, b - 2, rest - 1);
                    ways += pick(dp, a - 1, b + 2, rest - 1);
                    ways += pick(dp, a - 1, b - 2, rest - 1);
                    ways += pick(dp, a + 2, b + 1, rest - 1);
                    ways += pick(dp, a + 2, b - 1, rest - 1);
                    ways += pick(dp, a - 2, b + 1, rest - 1);
                    ways += pick(dp, a - 2, b - 1, rest - 1);
                    //填表
                    dp[a][b][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int a, int b, int rest) {
        if (a < 0 || b < 0 || a > 9 || b > 8) {
            return 0;
        }
        return dp[a][b][rest];
    }

    public static int waysdp(int a, int b, int s) {
        int[][][] dp = new int[10][9][s + 1];
        dp[a][b][0] = 1;
        for (int step = 1; step <= s; step++) { // 按层来
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
                            + getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
                            + getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
                            + getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
                }
            }
        }
        return dp[0][0][s];
    }

    public static int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }
}
