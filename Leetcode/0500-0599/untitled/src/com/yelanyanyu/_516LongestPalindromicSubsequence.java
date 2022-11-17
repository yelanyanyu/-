package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _516LongestPalindromicSubsequence {
    //https://leetcode.cn/problems/longest-palindromic-subsequence/
    private static int process(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L > R) {
            return 0;
        }
        //LR都不为回文串中的字符
        int p1 = process(str, L + 1, R - 1);
        //L是，R不是
        int p2 = process(str, L, R - 1);
        //R是，L不是
        int p3 = process(str, L + 1, R);
        //LR都是
        int p4 = str[L] == str[R] ? 2 + p1 : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    private static int dp(char[] str) {
        int[][] dp = new int[str.length][str.length];
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str.length; j++) {
                dp[i][j] = 0;
            }
        }
        int i = 0;
        int j = 0;
        while (i < str.length && j < str.length) {
            dp[i++][j++] = 1;
        }
        for (i = str.length - 2; i >= 0; i--) {
            for (j = i + 1; j < str.length; j++) {
                //LR都不为回文串中的字符
                int p1 = dp[i + 1][j - 1];
                //L是，R不是
                int p2 = dp[i][j - 1];
                //R是，L不是
                int p3 = dp[i + 1][j];
                //LR都是
                int p4 = str[i] == str[j] ? 2 + p1 : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][str.length - 1];
    }

    public int longestPalindromeSubseq(String s) {
        if ("".equals(s) || s == null) {
            return 0;
        }
//        return process(s.toCharArray(), 0, s.length() - 1);
        return dp(s.toCharArray());
    }
}
