package com.yelanyanyu;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _0816AmbiguousCoordinates {


    private static void addStringList(ArrayList<String> list, String str, int i, int lineLeft, int lineRight) {
        list.add("(" + str.substring(0, lineLeft) + "." + str.substring(lineLeft, i) + ","
                + str.substring(i, lineRight) + "." + str.substring(lineRight));
    }


    //https://leetcode.cn/problems/ambiguous-coordinates/description/
    public List<String> ambiguousCoordinates(String s) {
        //处理字符串
        String str = s.substring(1, s.length() - 1);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (int i = 1; i < str.length(); i++) {//第一层循环，用来遍历逗号
            //找出左边所有的可行点
            for (int lineLeft = 1; lineLeft < i; lineLeft++) {

            }

            //找出右边所有的可行点
            for (int lineRight = i + 1; lineRight < str.length() - 1; lineRight++) {

            }
            //遍历组合所有可行点
            for (Integer Left : left) {
                for (Integer Right : right) {

                }
            }
        }
        return list;
    }

    @Test
    public void TestSubString() {
        String str = "0123456";
        System.out.println(str.substring(0, str.length()));
    }

    @Test
    public void Test01() {
        String s = "(123)";
        System.out.println(ambiguousCoordinates(s));
    }

}
