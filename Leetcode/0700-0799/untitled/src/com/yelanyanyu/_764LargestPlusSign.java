package com.yelanyanyu;

import org.junit.jupiter.api.Test;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _764LargestPlusSign {
    /**
     * (i,j)向下和向右走能够得到的最大结构
     *
     * @return
     */
    private static int process(int[][] matrix, int i, int j) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i >= rows || j >= cols || i < 0 || j < 0) {
            return 0;
        }
        int k = 0;
        for (k = 1; k < rows; k++) {
            if (outOfRange(i, j, k, rows, cols)
                    || matrix[i + k][j] == 0
                    || matrix[i - k][j] == 0
                    || matrix[i][j + k] == 0
                    || matrix[i][j - k] == 0) {
                break;
            }
        }
        int p1 = process(matrix, i + 1, j);
        int p2 = process(matrix, i, j + 1);
        return Math.max(p1, Math.max(p2, k));
    }

    private static boolean outOfRange(int i, int j, int k, int rows, int cols) {
        if ((i + k >= rows) || (i - k) < 0 || (j + k) >= cols || (j - k) < 0) {
            return true;
        }
        return false;
    }

    public int orderOfLargestPlusSign1(int n, int[][] mines) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 1;
            }
        }
        for (int i = 0; i < mines.length; i++) {
            matrix[mines[i][0]][mines[i][1]] = 0;
        }
        return process(matrix, 0, 0);
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 1;
            }
        }
        for (int i = 0; i < mines.length; i++) {
            matrix[mines[i][0]][mines[i][1]] = 0;
        }
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = matrix[n - 1][n - 1] == 0 ? 0 : 1;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1], matrix[i][n - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[n - 1][i] = Math.max(dp[n - 1][i + 1] , matrix[n - 1][i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int k = 0;
                for (k = 0; k < n; k++) {
                    if (outOfRange(i, j, k, n, n)
                            || matrix[i + k][j] == 0
                            || matrix[i - k][j] == 0
                            || matrix[i][j + k] == 0
                            || matrix[i][j - k] == 0) {
                        break;
                    }
                }
                int p1 = 0;
                if (i + 1 < n && j < n) {
                    p1 = dp[i + 1][j];
                }
                int p2 = 0;
                if (j + 1 < n && i < n) {
                    p2 = dp[i][j + 1];
                }
                dp[i][j] = Math.max(p1, Math.max(p2, k));
            }
        }
        return dp[0][0];
    }

    @Test
    public void Test01() {
        int[][] mines = {{0, 0}, {0, 1}, {1, 0}};
        int n = 2;
        System.out.println(orderOfLargestPlusSign1(n, mines));
        System.out.println(orderOfLargestPlusSign(n, mines));
    }

    @Test
    public void Test02() {
        int[][] mines = {{0, 0}, {1, 1}};
        int n = 2;
        System.out.println(orderOfLargestPlusSign1(n, mines));
        System.out.println(orderOfLargestPlusSign(n, mines));
    }
}
