package com.yelanyanyu;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class IsRotation {
    /*
	判断str2是否是str1的旋转串
	 */
	/*
	拼接两个str1，若str2是str1的子串，那么就是旋转串
	 */
    public static boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() * str1.length() <= 0) {
            return false;
        }
        String str = str1 + str1;
        return pos(str, str2) == -1 ? false : true;
    }

    private static int pos(String str, String str2) {
        char[] strArr = str.toCharArray();
        char[] str2Arr = str2.toCharArray();
        int[] next = getNextArray(str2Arr);
        int x = 0;
        int y = 0;
        while (x < strArr.length && y < str2Arr.length) {
            if (strArr[x] == str2Arr[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2Arr.length ? x - y : -1;
    }

    private static int[] getNextArray(char[] str2Arr) {
        int[] next = new int[str2Arr.length];
        next[0] = -1;
        next[1] = 0;
        int pn = 0;//前缀串的后一个字符对应的next值
        int i = 2;
        while (i < str2Arr.length) {
            if (str2Arr[i - 1] == str2Arr[pn]) {
                next[i++] = ++pn;
            } else if (pn > 0) {
                pn = next[pn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "yelanyanyu";
        String str2 = "yanyuyelan";
        System.out.println(isRotation(str1, str2));
    }
}

