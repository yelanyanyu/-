package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class StringTestUtils {
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }
}
