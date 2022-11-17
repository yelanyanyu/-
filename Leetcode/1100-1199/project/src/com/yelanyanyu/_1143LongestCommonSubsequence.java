package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _1143LongestCommonSubsequence {
    /**
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return str1[...i]和str2[...j]的最长公共子序列长度
     */
    private static int process(char[] str1, char[] str2, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        //ij都不是最长子序列元素
        int p1 = process(str1, str2, i - 1, j - 1);
        //i是
        int p2 = process(str1, str2, i, j - 1);
        //j是
        int p3 = process(str1, str2, i - 1, j);
        //ij都是
        int p4 = str1[i] == str2[j] ? 1 + p1 : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    private static int dp(char[] str1, char[] str2) {
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int i = 1; i < M; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], str1[0] == str2[i] ? 1 : 0);
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                //ij都不是最长子序列元素
                int p1 = dp[i - 1][j - 1];
                //i是
                int p2 = dp[i][j - 1];
                //j是
                int p3 = dp[i - 1][j];
                //ij都是
                int p4 = str1[i] == str2[j] ? 1 + p1 : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[N - 1][M - 1];
    }

    //https://leetcode.cn/problems/longest-common-subsequence/
    public int longestCommonSubsequence(String text1, String text2) {
        if ("".equals(text1) || "".equals(text2) || text1 == null || text2 == null) {
            return 0;
        }
        return dp(text1.toCharArray(), text2.toCharArray());
    }
}
