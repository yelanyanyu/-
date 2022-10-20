package com.yelayanyu;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

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

        return arr;
    }

    public static boolean[] goodArray1(int[] g, int[] c) {
        int[] arr = sumArray(g, c);
        boolean[] res = new boolean[g.length];
        LinkedList<Integer> list = new LinkedList<>();
        int w = g.length + 1;
        int L = 0;
        for (int r = 0; r < arr.length; r++) {
            while (!list.isEmpty() && arr[list.peekLast()] >= arr[r]) {
                list.pollLast();
            }
            list.addLast(r);
            if (list.peekFirst() <= r - w) {
                list.pollFirst();
            }
            /**
             * 判断是否有效
             */
            //窗口形成了之后再比
            if (r >= w - 1) {
                int pre = L == 0 ? 0 : arr[L - 1];
                res[L++] = (arr[list.peekFirst()] - pre) < 0 ? false : true;
            }
        }
        return res;
    }

    public static boolean[] goodArray2(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            arr[i + N] = g[i] - c[i];
        }
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }
        boolean[] ans = new boolean[N];
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[w.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
        }
        return ans;
    }

    private static int getRandInteger(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private static int[] getRandomArrays(int min, int max, int len) {
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = getRandInteger(min, max);
        }
        return res;
    }

    @Test
    public void Test01() {
        int testTimes = 100000;
        System.out.println("测试开始========");
        for (int i = 0; i < testTimes; i++) {
            int[] g = getRandomArrays(1, 100, 5);
            int[] c = getRandomArrays(1, 9, 5);
            boolean[] ans1 = goodArray1(g, c);
            boolean[] ans2 = goodArray2(g, c);
            if (!Arrays.equals(ans1, ans2)) {
                System.out.println("Opps");
                System.out.println("g=" + Arrays.toString(g));
                System.out.println("c=" + Arrays.toString(c));
                System.out.println("preSum=" + Arrays.toString(sumArray(g, c)));
                System.out.println("ans1= " + Arrays.toString(ans1));
                System.out.println("ans2= " + Arrays.toString(ans2));
                break;
            }
        }
        System.out.println("测试结束========");
    }

    @Test
    public void Test02() {
        int[] g = {3, 7, 7, 7, 5};
        int[] c = {6, 5, 2, 4, 2};
        System.out.println(Arrays.toString(goodArray1(g, c)));
    }


}
