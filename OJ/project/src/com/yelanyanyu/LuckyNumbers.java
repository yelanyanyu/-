package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author yelanyanyu@zjxu.edu.cn * @version 1.0 */@SuppressWarnings({"all"})
public class LuckyNumbers {
    //对数器
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nums = scanner.nextLine();
        try {
            System.out.println(ways2(nums));
        } catch (NumberFormatException e) {
        }
    }
    @Test
    public void Test03(){
        int testTimes = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String randomNum = getRandomNum(1, 10000);
            long i1 = 0;
            long i2 = 0;
            try {
                i1 = ways1(randomNum);
                i2 = ways2(randomNum);
            } catch (Exception e) {
                System.out.println("nums= " + randomNum);
            }
            if (i1 != i2) {
                System.out.println("Opps");
                System.out.println("nums= " + randomNum);
                System.out.println("ways1= " + i1);
                System.out.println("ways2= " + i2);
            }
        }
        System.out.println("测试结束");
    }

    public static long ways1(String nums) {
        long num = Long.parseLong(nums);
        long superLuckyMan = Long.MIN_VALUE;
        for (long i = num; i < Long.MAX_VALUE; i++) {
            if (isSuperLucky(String.valueOf(i))) {
                superLuckyMan = i;
                break;
            }
        }
        return superLuckyMan;
    }

    //采用修改字符串的方式
    //1.从高位开始，依次修改数的值，若某一位小于等于4，则修改为4；大于4小于等于7，则修改为7；
    public static long ways2(String nums) {
        long num = Long.parseLong(nums);
        long superLuckyMan = Long.MIN_VALUE;
        int len = nums.length();
        //初始化map
        HashMap<Integer, String> maxMap = new HashMap<>();
        HashMap<Integer, String> minMap = new HashMap<>();
        setMap(maxMap, minMap);


        if (len % 2 == 1) {//奇数位
            superLuckyMan = Long.parseLong(minMap.get(len + 1));
        } else {//偶数位
            long max = Long.parseLong(maxMap.get(len));
            long min = Long.parseLong(minMap.get(len));
            if (num <= min) {//小于最小值
                superLuckyMan = Long.parseLong(minMap.get(len));
            } else if (num >= max) {//大于最大值
                superLuckyMan = Long.parseLong(minMap.get(len + 2));
            } else {//介于最大值和最小值之间,遍历
                superLuckyMan = findSubSuper(nums, maxMap);
            }
        }
        return superLuckyMan;
    }

    private static long findSubSuper(String nums, HashMap<Integer, String> maxMap) {
        long length = nums.length();
        long max = Long.parseLong(maxMap.get(length));
        long num = Long.parseLong(nums);
        long superLuckyMan = Long.MIN_VALUE;
        for (long i = num; i <= max; i++) {
            if (isSuperLucky(String.valueOf(i))) {
                superLuckyMan = i;
                break;
            }
        }
        return superLuckyMan;
    }

    public static void setMap(HashMap<Integer, String> maxMap, HashMap<Integer, String> minMap) {
        maxMap.put(0, "");
        maxMap.put(2, "74");
        maxMap.put(4, "7744");
        maxMap.put(6, "777444");
        maxMap.put(8, "77774444");
        maxMap.put(10, "7777744444");
        maxMap.put(12, "777777444444");
        maxMap.put(14, "77777774444444");
        minMap.put(0, "");
        minMap.put(2, "47");
        minMap.put(4, "4477");
        minMap.put(6, "444777");
        minMap.put(8, "44447777");
        minMap.put(10, "4444477777");
        minMap.put(12, "444444777777");
        minMap.put(14, "44444447777777");
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

    //for test
    public static String getRandomNum(int minValue, int maxValue) {
        return String.valueOf((int) (Math.random() * (maxValue - minValue + 1) + minValue));
    }


    @Test
    public void Test01() {
        String nums = "7744";
        System.out.println(isSuperLucky(nums));
//        System.out.println(chCount);
    }

    @Test
    public void Test02() {

        String nums = "4444477777";
        System.out.println(ways2(nums));
    }

}