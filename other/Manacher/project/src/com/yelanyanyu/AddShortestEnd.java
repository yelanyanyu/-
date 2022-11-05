package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class AddShortestEnd {
    /*
	给定一个字符串str，只能在其后面添加字符，使得str变为回文串，至少要添几个字
	符，就能实现？
	实质：求必须包含最后一个字符的情况下，最长回文子串是多长。
	 */
	/*
	找到第一个能包括最后一个字符的回文串中心，然后把前面的逆序接到后面就可以了
	 */

    private static int maxLength(String s1) {
        if (s1 == null || s1.length() == 0) {
            return 0;
        }
        char[] str = manacherArray(s1);
        int[] pArr = new int[str.length];
        int R = -1;
        int c = -1;
        int res = 1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * c - i]) : 1;
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
            if (R >= str.length - 1) {
                res = pArr[i];
                break;
            }
        }
        return s1.length() - res + 1;
    }

    private static char[] manacherArray(String str) {
        char[] res = new char[str.length() * 2 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = ((i & 1) == 0) ? '#' : str.charAt(i / 2);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(maxLength(str1));
    }

}
