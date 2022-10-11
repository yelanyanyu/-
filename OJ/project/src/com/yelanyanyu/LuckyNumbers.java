package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class LuckyNumbers {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nums = scanner.nextLine();
        int num = Integer.parseInt(nums);
        int superLuckyMan = Integer.MIN_VALUE;
        for (int i = num; i < Integer.MAX_VALUE; i++) {
            if (isSuperLucky(String.valueOf(i))) {
                superLuckyMan = i;
                break;
            }
        }
        System.out.println(superLuckyMan);
    }

    public static boolean isLuckyNum(String nums, HashMap<Character, Integer> chCount) {

        int count = 0;
        boolean flag = true;
        for (int i = 0; i < nums.length(); i++) {
            char ch = nums.charAt(i);
            if (ch == '4' || ch == '7') {
                if (chCount.containsKey(ch)) {
                    chCount.put(ch, chCount.get(ch) + 1);
                } else {
                    chCount.put(ch, 1);
                }
            } else {
                flag = false;
            }
        }
        return flag;
    }

    //只统计4和7的个数
    public static void countCH(String nums, HashMap<Character, Integer> chCount) {
        int count = 0;
        for (int i = 0; i < nums.length(); i++) {
            char ch = nums.charAt(i);
            if (ch == '4' || ch == '7') {
                if (chCount.containsKey(ch)) {
                    chCount.put(ch, chCount.get(ch) + 1);
                } else {
                    chCount.put(ch, 1);
                }
            }
        }
    }

    public static boolean isSuperLucky(String nums) {
        HashMap<Character, Integer> chCount = new HashMap<>();
        if (!isLuckyNum(nums, chCount)) {
            return false;
        }
        if (!chCount.containsKey('4') && chCount.containsKey('7')) {
            return false;
        } else {
            return chCount.get('4') == chCount.get('7');
        }
    }

    @Test
    public void Test01() {
        String nums = "4747";
        System.out.println(isSuperLucky(nums));
//        System.out.println(chCount);
    }

}
