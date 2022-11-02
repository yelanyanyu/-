package com.yelanyanyu;

import org.junit.jupiter.api.Test;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class KMP {
    private static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() == 0 || s1.length() == 0) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNextArray(str2);
        int x = 0;
        int y = 0;
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    private static int[] getNextArray(char[] str2) {
        if (str2.length == 0 || str2 == null) {
            return null;
        }
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int pn = 0;//记录前缀的后一个位置的next值
        int i = 2;
        while (i < str2.length) {
            if (str2[i - 1] == str2[pn]) {
                /*
                若相应字符相同则i位置的next值就为pn位置的next值+1；
                同时更新i和pn的值：判断下一个字符
                 */
                next[i++] = ++pn;
            } else if (pn > 0) {
                /*
                不断更新pn的值，也就是不断寻找能与str[i-1]匹配的pn值，
                直到越界
                 */
                pn = next[pn];
            } else {
                /*
                假如找不到相应的pn，使得pn对应的字符和i-1位置的字符相同，
                i位置的next值就为0，同时去判断下一个字符
                 */
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int possibilities = 3;
        int strSize = 10;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = StringTestUtils.getRandomString(possibilities, strSize);
            String match = StringTestUtils.getRandomString(possibilities, matchSize);
            int ans1 = getIndexOf(str, match);
            int ans2 = str.indexOf(match);
            if (ans1 != ans2) {
                System.out.println("=============================");
                System.out.println("Oops!");
                System.out.print("str= " + str);
                System.out.println("\tmatch= " + match);
                System.out.println("ans1= " + ans1);
                System.out.println("ans2= " + ans2);
                System.out.println();
                break;
            }
        }
        System.out.println("test finish");
    }

    @Test
    public void Test01() {
        String str = "abaabaaa";
        String match = "abaaa";
        System.out.println(getIndexOf(str, match));
        System.out.println(str.indexOf(match));
    }

}
