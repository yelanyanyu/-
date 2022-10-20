package com.yelayanyu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class MinCoinsOnePaper {


    private static int dp3(int[] arr, int aim) {
        if (aim < 0) {
            return Integer.MAX_VALUE;
        }
        if ((arr == null || arr.length == 0) && aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = info.coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            /**
             * mod表示组数，
             */
            for (int mod = 0; mod < Math.min(aim + 1, info.coins[index]); mod++) {
                int coin = coins[index];
                int zhang = zhangs[index];
                LinkedList<Integer> list = new LinkedList<>();
                list.addLast(mod);
                dp[index][mod] = dp[index + 1][mod];
                for (int r = mod + info.coins[index]; r <= aim; r += info.coins[index]) {
                    //更新最值
                    while (!list.isEmpty()
                            && (dp[index + 1][list.peekLast()] == Integer.MAX_VALUE
                            || dp[index + 1][r]
                            <= dp[index + 1][list.peekLast()] + compensate(r, list.peekLast(), coin))) {
                        list.pollLast();
                    }
                    list.addLast(r);
                    //筛除无效元素
                    if (list.peekFirst() == (r - (zhang + 1) * coin)) {
                        list.pollFirst();
                    }
                    dp[index][r] = dp[index + 1][list.peekFirst()] + compensate(r, list.peekFirst(), coin);
                }
            }
        }
        return dp[0][aim];
    }

    /**
     * @param curIndex
     * @param preIndex
     * @param coin
     * @return
     */
    private static int compensate(int curIndex, int preIndex, int coin) {
        return (curIndex - preIndex) / coin;
    }

    private static int process2(Info info, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == info.coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int curCoin = info.coins[index];
        int curZhang = info.zhangs[index];
        int min = Integer.MAX_VALUE;
        for (int zhangs = 0; zhangs <= curZhang; zhangs++) {
            int p1 = process2(info, index + 1, rest - zhangs * info.coins[index]);
            if (p1 != Integer.MAX_VALUE) {
                p1 += zhangs;
            }
            min = Math.min(p1, min);
        }
        return min;
    }

    /**
     * 方法二未优化的动态规划,有枚举行为
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int dp2(int[] arr, int aim) {
        if (aim < 0) {
            return Integer.MAX_VALUE;
        }
        if ((arr == null || arr.length == 0) && aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int N = info.coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int min = Integer.MAX_VALUE;
                for (int zhangs = 0; zhangs <= info.zhangs[index]; zhangs++) {
                    int p1 = Integer.MAX_VALUE;
                    if ((rest - zhangs * info.coins[index] >= 0)) {
                        p1 = dp[index + 1][rest - zhangs * info.coins[index]];
                    }
                    if (p1 != Integer.MAX_VALUE) {
                        p1 += zhangs;
                    }
                    min = Math.min(p1, min);
                }
                dp[index][rest] = min;
            }
        }
        return dp[0][aim];
    }

    /**
     * 将arr转化为Info
     *
     * @param arr
     * @return
     */
    private static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    private static int dp1(int[] arr, int aim) {
        int len = arr.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[len][i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < len; i++) {
            dp[i][0] = 0;
        }
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = Integer.MAX_VALUE;
                if (rest - arr[index] >= 0) {
                    p2 = dp[index + 1][rest - arr[index]];
                }
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim];
    }

    private static int minCoins1(int[] arr, int aim) {
        if (aim <= 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    private static int minCoins2(int[] arr, int aim) {
        if (aim <= 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process2(info, 0, aim);
    }

    //
    private static int process1(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        int len = arr.length;
        if (index == len) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int p1 = process1(arr, index + 1, rest);
        int p2 = process1(arr, index + 1, rest - arr[index]);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    // 为了测试
    private static int[] randomArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    private static void printArray(int[] arr) {
        System.out.print("arr= ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 10;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins2(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            int ans4 = dp3(arr, aim);
            if (ans1 != ans2 || ans1 != ans3 || ans4 != ans1) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("aim= " + aim);
                System.out.println("minCoins2= " + ans1);
                System.out.println("dp1= " + ans2);
                System.out.println("dp2= " + ans3);
                System.out.println("dp3= " + ans4);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

    private static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }


}
