package com.yelayanyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class GasStationf {
    /**
     * 有两个数组 gas[]和 cost[]，分别记载着一个加油站的油量和到下一个加油站的耗油量（距离 ）。
     * 现有一辆卡车，求该卡车从哪个地方出发才能**逆时针**走完一圈，回到起点。
     * 设该卡车油箱无限大，且初始没有油。
     * 返回一个结果数组，里面记载着出发点是否可走完一圈的结果。
     */
    /**
     * 返回进行了前缀和的数组
     *
     * @param g
     * @param c
     * @return
     */
    public static int[] sumArray(int[] g, int[] c) {
        int[] sub = new int[g.length];
        for (int i = 0; i < sub.length; i++) {
            sub[i] = g[i] - c[i];
        }
        //计算前缀和
        int[] arr = new int[g.length * 2];
        arr[0] = sub[0];
        for (int i = 1; i < arr.length / 2; i++) {
            arr[i] = arr[i - 1] + sub[i];
        }
        int index = 0;
        for (int i = arr.length / 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + sub[index++];
        }
        // TODO: 2022/10/19 前缀和

        return null;
    }

    public static boolean[] goodArray(int[] g, int[] c) {
        return null;
    }

    @Test
    public void Test01() {
        int[] g = {1, 1, 3, 2, 4};
        int[] c = {2, 1, 1, 3, 3};
        sumArray(g, c);
    }

}
