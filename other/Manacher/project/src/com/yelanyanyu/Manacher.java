package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class Manacher {
    private static int max(String s1) {
        int max = 0;
        if ("".equals(s1) || s1 == null) {
            return max;
        }
        char[] str = manacherArray(s1);
        int[] pArr = new int[str.length];
        int R = -1;
        int c = -1;
        for (int i = 0; i < str.length; i++) {
            /*
            当s在pStr内时，i半径为i`的半径
            当s超过pStr时，i半径为R-i
             */
            pArr[i] = R > i ? Math.min(pArr[2 * c - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                c = i;
            }
            max = Math.max(max, pArr[i]);
        }
        //为什么要减1？
        return max - 1;
    }

    private static char[] manacherArray(String str) {
        char[] res = new char[str.length() * 2 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (i % 2 == 0) ? '#' : str.charAt(i / 2);
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherArray(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            int ans1 = max(str);
            int ans2 = right(str);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println("str= " + str);
                System.out.println("ans1= " + ans1);
                System.out.println("ans2= " + ans2);
                break;
            }
        }
        System.out.println("test finish");
    }

    @Test
    public void Test01() {
        String str = "122146546";
        System.out.println(String.valueOf(manacherArray(str)));
    }

    @Test
    public void Test02() {
        String str = "cecbcadbd";
        max(str);
    }
}
