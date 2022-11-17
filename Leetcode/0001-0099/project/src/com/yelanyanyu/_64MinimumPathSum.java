package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _64MinimumPathSum {

    private static int dp(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] dp = new int[N][M];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                dp[i][j] = Math.min(p2, p1) + grid[i][j];
            }
        }
        return dp[N - 1][M - 1];
    }

    public static int dp2(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dp = new int[cols];
        dp[0] = matrix[0][0];
        for (int i = 1; i < cols; i++) {
            dp[i] = dp[i - 1] + matrix[0][i];
        }
        for (int i = 1; i < rows; i++) {
            dp[0] += matrix[i][0];
            for (int j = 1; j < cols; j++) {
                int p1 = dp[j - 1] + matrix[i][j];
                int p2 = dp[j] + matrix[i][j];
                dp[j] = Math.min(p2, p1);
            }
        }
        return dp[cols - 1];
    }

    //https://leetcode.cn/problems/minimum-path-sum/
    public int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        return dp2(grid);
    }
}
