package com.yelanyanyu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _0816AmbiguousCoordinates {


    private static void addStringList(ArrayList<String> list, String str1, String str2) {

    }


    //https://leetcode.cn/problems/ambiguous-coordinates/description/
    public List<String> ambiguousCoordinates(String s) {
        //处理字符串
        String str = s.substring(1, s.length() - 1);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < str.length(); i++) {
            for (int lineLeft = 1; lineLeft < i; lineLeft++) {
                if (!(str.charAt(i - 1) == '0' && str.charAt(i) == '0')) {
                    // TODO: 2022/11/7 以下是错误代码，忽略了首位为0的情况是不能随意加.的
//                    addStringList(list, str.substring(0, i), str.substring(i));
                } else {
                    //若第一个字符后最后一个字符都不为0，则可以加入list

                }
            }
            for (int lineRight = i + 1; lineRight < str.length(); lineRight++) {

            }
        }
        return list;
    }

    @Test
    public void TestSubString() {
        String str = "0123456";
        System.out.println(str.substring(0, str.length()));
    }

}
