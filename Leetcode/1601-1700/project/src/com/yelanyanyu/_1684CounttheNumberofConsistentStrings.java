package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _1684CounttheNumberofConsistentStrings {
    //https://leetcode.cn/problems/count-the-number-of-consistent-strings/
    public int countConsistentStrings(String allowed, String[] words) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < allowed.length(); i++) {
            set.add(allowed.charAt(i));
        }
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < words.length; i++) {
            flag = true;
            for (int j = 0; j < words[i].length(); j++) {
                if (!set.contains(words[i].charAt(j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void Test01() {
        String allowed = "abc";
        String[] words = {"a","b","c","ab","ac","bc","abc"};
        System.out.println(countConsistentStrings(allowed, words));
    }

}
